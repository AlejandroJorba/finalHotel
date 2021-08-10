
package Servlets;

import Logica.Controladora;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvReserva", urlPatterns = {"/SvReserva"})
public class SvReserva extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
        //Traigo los datos del JSP de registrar reservas
        int cant_personas = Integer.parseInt(request.getParameter("cantidadHuesped"));
        String check_in = request.getParameter("check_in");
        String check_out = request.getParameter("check_out");
        int num_habitacion = Integer.parseInt(request.getParameter("numeroHabitacion"));
        int dni = Integer.parseInt(request.getParameter("dni"));
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String nacimiento = request.getParameter("nacimiento");
        String direccion = request.getParameter("direccion");
        String profesion = request.getParameter("profesion");
        String usuarioRes = request.getParameter("usuario");
        //Traer sesión y asignar atributos para usar en cualquier JSP
        request.getSession().setAttribute("cant_personas", cant_personas);
        request.getSession().setAttribute("check_in", check_in);
        request.getSession().setAttribute("check_out", check_out);
        request.getSession().setAttribute("num_habitacion", num_habitacion);
        request.getSession().setAttribute("dni", dni);
        request.getSession().setAttribute("nombre", nombre);
        request.getSession().setAttribute("apellido", apellido);
        request.getSession().setAttribute("nacimiento", nacimiento);
        request.getSession().setAttribute("direccion", direccion);
        request.getSession().setAttribute("profesion", profesion);
        request.getSession().setAttribute("usuarioRes", usuarioRes);

        //Controladora logica y persistencia
        Controladora control = new Controladora();
        try {
            //creo todo
            control.crearReserva(num_habitacion, cant_personas, usuarioRes, check_in, check_out, profesion, dni, nombre, apellido, nacimiento, direccion);
        } catch (ParseException ex) {
            Logger.getLogger(SvReserva.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect("reservaok.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
