package Servlets;

import Logica.Controladora;
import Logica.Reserva;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SvConsultaDia", urlPatterns = {"/SvConsultaDia"})
public class SvConsultaDia extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            Controladora control = new Controladora();
            HttpSession misesion = request.getSession();
            String diaConsulta = request.getParameter("diaConsulta");
            SimpleDateFormat formateo = new SimpleDateFormat("yyyy-MM-dd");
            Date diaFormat = formateo.parse(diaConsulta);
            
            
            request.getSession().setAttribute("diaConsulta", diaFormat);
            List<Reserva> listaReserva = control.traerReservas();
            misesion.setAttribute("listaReserva", listaReserva);
            response.sendRedirect("consultadia.jsp");
        } catch (ParseException ex) {
            Logger.getLogger(SvConsultaDia.class.getName()).log(Level.SEVERE, null, ex);
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
