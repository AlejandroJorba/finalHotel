package Servlets;

import Logica.Controladora;
import Logica.Habitacion;
import Logica.Huesped;
import Logica.Reserva;
import Logica.Usuario;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SvModificarOk", urlPatterns = {"/SvModificarOk"})
public class SvModificarOk extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int cant_personas = Integer.parseInt(request.getParameter("cantidadHuesped"));
            String check_in = request.getParameter("check_in");
            String check_out = request.getParameter("check_out");
            int num_habitacion = Integer.parseInt(request.getParameter("numeroHabitacion"));
            int dni = Integer.parseInt(request.getParameter("dni"));
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String direccion = request.getParameter("direccion");
            String profesion = request.getParameter("profesion");
            String usuarioRes = request.getParameter("usuario");
            long id = Long.parseLong(request.getParameter("id"));
            
            SimpleDateFormat formateo = new SimpleDateFormat("yyyy-MM-dd");
            Date check_inParse = formateo.parse(check_in);
            Date check_outParse = formateo.parse(check_out);
            
            
            Controladora control = new Controladora();
            Reserva res = control.traerReserva(id);
            res.setCant_personas(cant_personas);
            res.setCheck_in(check_inParse);
            res.setCheck_out(check_outParse);
            
            Habitacion h = control.traerHabitacion(num_habitacion);
            res.setHabitacion(h);
            
            Huesped hue = res.getHuesped();
            hue.setApellido(apellido);
            hue.setDireccion(direccion);
            hue.setDni(dni);
            hue.setNombre(nombre);
            hue.setProfesion(profesion);
            Usuario us = res.getUsuario();
            us.setUsuario(usuarioRes);
            
            
            long checkIn = check_inParse.getTime();
            long checkOut = check_outParse.getTime();
            long diferencia = checkOut - checkIn;
            double tiempoHospedado = Math.floor(diferencia / (1000 * 60 * 60 * 24));
            res.setCant_noches(tiempoHospedado);
            
            double precioHab = h.getPrecio_habitacion();
            double precioFinal = tiempoHospedado * precioHab;
            
            res.setPrecio_final(precioFinal);
            
            control.modificarReserva(res, hue);

            HttpSession misesion = request.getSession();
            misesion.setAttribute("listaReserva", control.traerReservas());
            response.sendRedirect("consultareservas.jsp");
            
        } catch (ParseException ex) {
            Logger.getLogger(SvModificarOk.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
