package Servlets;

import Logica.Controladora;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SvUsuario", urlPatterns = {"/SvUsuario"})
public class SvUsuario extends HttpServlet {

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
        
            String user = request.getParameter("user");
            String password = request.getParameter("password");
            
            Controladora control = new Controladora();
            boolean autorizar = control.verificarUsuario(user, password);
            
            if (autorizar == true) {
                HttpSession sesion = request.getSession();
                sesion.setAttribute("user", user);
                sesion.setAttribute("password", password);
                
                response.sendRedirect("inicio.jsp");
            } 
            else {
                response.sendRedirect("index.jsp");
            }
            
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
