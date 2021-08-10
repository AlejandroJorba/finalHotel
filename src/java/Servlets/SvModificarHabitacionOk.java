package Servlets;

import Logica.Controladora;
import Logica.Habitacion;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SvModificarHabitacionOk", urlPatterns = {"/SvModificarHabitacionOk"})
public class SvModificarHabitacionOk extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //Inicializo controladora
        Controladora control = new Controladora();
        
        //Recibo los datos ingresados en el form
        int pisoHabitacion = Integer.parseInt(request.getParameter("pisoHabitacion"));
        int numeroHabitacion = Integer.parseInt(request.getParameter("numeroHabitacion"));
        String nombreHabitacion = request.getParameter("nombreHabitacion");
        String tipoHabitacion = request.getParameter("tipoHabitacion");
        double precioHabitacion = Double.parseDouble(request.getParameter("precioHabitacion"));
        long id = Long.parseLong(request.getParameter("idHabitacion"));
        
        //Traigo la habitación por la id y le seteo los datos recibidos del form modificar hab
        Habitacion hab = control.traerHabitacion(id);
        hab.setNombre_habitacion(nombreHabitacion);
        hab.setNum_habitacion(numeroHabitacion);
        hab.setPiso_habitacion(pisoHabitacion);
        hab.setPrecio_habitacion(precioHabitacion);
        hab.setTipo_habitacion(tipoHabitacion);
        
        //Modifico llamando a la controladora
        control.modificarHabitacion(hab);
        
        HttpSession misesion = request.getSession();
        misesion.setAttribute("listaHabitaciones", control.traerHabitaciones());
        response.sendRedirect("consultahabitaciones.jsp");
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
