
package Servlets;

import Logica.Controladora;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "SvHabitaciones", urlPatterns = {"/SvHabitaciones"})
public class SvHabitaciones extends HttpServlet {

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
        //Traigo los datos del JSP de registrar habitaciones
        int pisoHabitacion = Integer.parseInt(request.getParameter("pisoHabitacion"));
        int numeroHabitacion = Integer.parseInt(request.getParameter("numeroHabitacion"));
        String nombreHabitacion = request.getParameter("nombreHabitacion");
        String tipoHabitacion = request.getParameter("tipoHabitacion");
        double precioHabitacion = Double.parseDouble(request.getParameter("precioHabitacion"));
        
        //La sesión son los datos o acciones hechas por un usuario en la web
        //Traer sesión y asignar atributos para usar en cualquier JSP
        request.getSession().setAttribute("pisoHabitacion", pisoHabitacion);
        request.getSession().setAttribute("numeroHabitacion", numeroHabitacion);
        request.getSession().setAttribute("nombreHabitacion", nombreHabitacion);
        request.getSession().setAttribute("tipoHabitacion", tipoHabitacion);
        request.getSession().setAttribute("precioHabitacion", precioHabitacion);
        
        //conecto la lógica con el servlet
        Controladora control = new Controladora();
        control.crearHabitacion(numeroHabitacion, pisoHabitacion, pisoHabitacion, precioHabitacion, nombreHabitacion, tipoHabitacion);
        
        //respuesta y redirección a otra página
        response.sendRedirect("habitacionok.jsp");

    }   

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
