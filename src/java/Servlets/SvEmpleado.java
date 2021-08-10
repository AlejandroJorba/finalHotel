
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


@WebServlet(name = "SvEmpleado", urlPatterns = {"/SvEmpleado"})
public class SvEmpleado extends HttpServlet {

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
            processRequest(request, response);
            
            //Traigo los datos del formulario para registrar empleados
            int dni = Integer.parseInt(request.getParameter("dni"));
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String nacimiento = request.getParameter("nacimiento");
            String direccion = request.getParameter("direccion");
            String cargo = request.getParameter("cargo");
            String usu = request.getParameter("user");
            String contra = request.getParameter("password");
            
            //Creo la sesión y asigno atributos
            request.getSession().setAttribute("dni", dni);
            request.getSession().setAttribute("nombre", nombre);
            request.getSession().setAttribute("apellido", apellido);
            request.getSession().setAttribute("nacimiento", nacimiento);
            request.getSession().setAttribute("direccion", direccion);
            request.getSession().setAttribute("cargo", cargo);
            request.getSession().setAttribute("usu", usu);
            request.getSession().setAttribute("contra", contra);
            
            
            //conecto la lógica con el servlet
            Controladora control = new Controladora();
            
        try {
            control.crearEmpleado(usu, contra, cargo, dni, nombre, apellido, nacimiento, direccion);
        } catch (ParseException ex) {
            Logger.getLogger(SvEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            //respuesta y redirección a otra página
            response.sendRedirect("empleadook.jsp");
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
