
package Servlets;

import Logica.Controladora;
import Logica.Empleado;
import Logica.Usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SvModificarEmpleadoOk", urlPatterns = {"/SvModificarEmpleadoOk"})
public class SvModificarEmpleadoOk extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
            Controladora control = new Controladora();

            int dni = Integer.parseInt(request.getParameter("dni"));
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String direccion = request.getParameter("direccion");
            String cargo = request.getParameter("cargo");
            String usuario = request.getParameter("user");
            String contraseña = request.getParameter("password");
            long id = Long.parseLong(request.getParameter("idUsuario"));
            
            Usuario us = control.traerUsuario(id);
            us.setUsuario(usuario);
            us.setPassword(contraseña);
            
            Empleado empl = us.getUsuarioEmpleado();
            empl.setNombre(nombre);
            empl.setApellido(apellido);
            empl.setDni(dni);
            empl.setCargo(cargo);
            empl.setDireccion(direccion);
            us.setUsuarioEmpleado(empl);
            
            control.modificarUsuario(us, empl);
            
            
            HttpSession misesion = request.getSession();
            misesion.setAttribute("listaEmpleados", control.traerUsuarios());
            response.sendRedirect("consultarempleados.jsp");
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
