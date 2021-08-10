package Servlets;

import Logica.Controladora;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvEliminarEmpleado", urlPatterns = {"/SvEliminarEmpleado"})
public class SvEliminarEmpleado extends HttpServlet {

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
            
            Controladora control = new Controladora();
            long id_empleado = Long.parseLong(request.getParameter("idEmpleado"));
            long id_usuario = Long.parseLong(request.getParameter("idUsuario"));
            control.borrarUsuario(id_usuario);
            control.borrarEmpleado(id_empleado);
            
            request.getSession().setAttribute("listaEmpleados", control.traerUsuarios());
            response.sendRedirect("consultarempleados.jsp");


    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
