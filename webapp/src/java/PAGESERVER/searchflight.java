/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAGESERVER;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;

/**
 *
 * @author POUSHALI
 */
public class searchflight extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    String vFROMLOC,vTOLOC;
    OracleConnection oconn;
    OraclePreparedStatement ost;
    OracleResultSet ors;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet searchflight</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet searchflight at " + request.getContextPath() + "</h1>");
            vFROMLOC=request.getParameter("dep");
            vTOLOC=request.getParameter("des");
             out.println("<h2>fromloc:"+vFROMLOC+"</h2>");
              out.println("<h2>toloc:"+vTOLOC+"</h2>");
              
            try {
                DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                 oconn=(OracleConnection)DriverManager.getConnection("jdbc:oracle:thin:@LAPTOP-NI9HCUJ9:1521:orcl1","POUSHALI","GHOSAL");
                 ost=(OraclePreparedStatement)oconn.prepareStatement("SELECT * FROM FLIGHT_DETAILS1 where fromloc=? and toloc=?");
                 ost.setString(1, vFROMLOC);
                 ost.setString(2, vTOLOC);
                 ors=(OracleResultSet)
                         ost.executeQuery();
                 if(ors.next())
                 {
                     response.sendRedirect("http://localhost:8080/webapp/webpack/searchflight.jsp");
                 }
                  else
              {
                  response.sendRedirect("http://localhost:8080/webapp/STATPAGES/WrongPass1.html");
              }
                         
            } catch (SQLException ex) {
                Logger.getLogger(searchflight.class.getName()).log(Level.SEVERE, null, ex);
            }
             
              
              
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
