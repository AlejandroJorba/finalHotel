
package Servlets;
import Logica.Controladora;
import Logica.Reserva;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SvConsultaUsuario", urlPatterns = {"/SvConsultaUsuario"})
public class SvConsultaUsuario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            Controladora control = new Controladora();

            HttpSession misesion = request.getSession();
            String usuarioReserva = request.getParameter("usuarioConsulta");
            
            List<Reserva> listita = control.traerUsu(usuarioReserva);
            //Forma vieja de buscar el usuario, va de la mano con el otro jsp
            //request.getSession().setAttribute("usuarioReserva", usuarioReserva);
            //List<Reserva> listaUsuarioReservas = control.traerReservas();
            if(listita != null) {
            misesion.setAttribute("listaReserva", listita);}
            else {
                misesion.setAttribute("listaReserva", control.traerReservas());
            }
            response.sendRedirect("consultareservas.jsp");
            
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
