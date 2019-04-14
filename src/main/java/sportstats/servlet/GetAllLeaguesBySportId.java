/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package sportstats.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sportstats.domain.League;
import sportstats.service.GetAllLeaguesBySportIdService;
import sportstats.service.ServiceRunner;

/**
 *
 * @author Niklas
 */
@WebServlet( name = "GetAllLeaguesBySportId", urlPatterns = {"/GetAllLeaguesBySportId"})
public class GetAllLeaguesBySportId extends HttpServlet {
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        ObjectMapper objectMapper = new ObjectMapper();
        
        int sportId = Integer.parseInt(request.getParameter("id"));
        
        ServiceRunner<List> serviceRunner = new ServiceRunner(new GetAllLeaguesBySportIdService(sportId));
        List<League> leagues = serviceRunner.execute();
    
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GetAllLeaguesBySportId</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GetAllLeaguesBySportId at " + request.getContextPath() + "</h1>");
            
            for(int i = 0; i < leagues.size(); i++) 
                out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(leagues.get(i).getLeagueName()) + "</br>");
          
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    
    // Handles the HTTP <code>POST</code> method.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    // Returns a short description of the servlet
    @Override
    public String getServletInfo() {
        return "Short description";
    }
 
}
