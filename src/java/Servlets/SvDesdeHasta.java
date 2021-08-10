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

@WebServlet(name = "SvDesdeHasta", urlPatterns = {"/SvDesdeHasta"})
public class SvDesdeHasta extends HttpServlet {
    
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
            SimpleDateFormat formateo = new SimpleDateFormat("yyyy-MM-dd");
            
            //Pedir y asignar dni
            String dniConsulta = request.getParameter("dniConsulta");
            request.getSession().setAttribute("dniConsulta", dniConsulta);
            
            //Pedir fecha desde
            String desde = request.getParameter("desde");
            Date desdeFormat = formateo.parse(desde);
            request.getSession().setAttribute("desdeFormat", desdeFormat);
            request.getSession().setAttribute("desde", desde);
            
            //Pedir fecha hasta
            String hasta = request.getParameter("hasta");
            Date hastaFormat = formateo.parse(hasta);
            request.getSession().setAttribute("hastaFormat", hastaFormat);
            
            //Traer la lista de reservas
            List<Reserva> listaDesdeHasta = control.traerReservas();
            misesion.setAttribute("listaDesdeHasta", listaDesdeHasta); 
            //Redirect
            response.sendRedirect("consultahuesped2.jsp");
        } catch (ParseException ex) {
            Logger.getLogger(SvDesdeHasta.class.getName()).log(Level.SEVERE, null, ex);
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
