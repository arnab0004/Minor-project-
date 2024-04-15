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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OraclePreparedStatement;

/**
 *
 * @author POUSHALI
 */
public class Flightpage extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    String vFLIGHTNO,vFLIGHTNAME,vFROM,vTO, vDEPARTURE,vCABIN,vTICKETFARE,vADULTS,vCHILDREN,vINFANTS;
    OracleConnection oconn;
    OraclePreparedStatement ops;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Flightpage</title>");            
            out.println("</head>");
            out.println("<body>");
           vFLIGHTNO=request.getParameter("Flight_no");
           vFLIGHTNAME=request.getParameter("Flight_name");
           vFROM=request.getParameter("from");
           vTO=request.getParameter("to");
           vDEPARTURE=request.getParameter("departure");
           vCABIN=request.getParameter("cabin");
           vTICKETFARE=request.getParameter("ticket_price");
//           vADULTS=request.getParameter("adults");
//           vCHILDREN=request.getParameter("children");
//           vINFANTS=request.getParameter("infants");
//            out.println("<h1>printing the html form values in this servlet...</h1>");
//             out.println("<h2>FLIGHT NO:"+vFLIGHTNO+"</h2>");
//            out.println("<h2>FLIGHT NAME:"+vFLIGHTNAME+"</h2>");
//            out.println("<h2>FROM:"+vFROM+"</h2>");
//            out.println("<h2>TO:"+vTO+"</h2>");
//            out.println("<h2>DEPARTURE:"+vDEPARTURE+"</h2>");
//             out.println("<h2>CABIN:"+vCABIN+"</h2>");
//            out.println("<h2>TICKETFARE:"+vTICKETFARE+"</h2>");
//             out.println("<h2>ADULTS:"+vADULTS+"</h2>");
//              out.println("<h2>CHILDREN:"+vCHILDREN+"</h2>");
//               out.println("<h2>:"+vINFANTS+"</h2>");
            
           
            try {
                DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                 oconn=(OracleConnection)DriverManager.getConnection("jdbc:oracle:thin:@LAPTOP-NI9HCUJ9:1521:orcl1","POUSHALI","GHOSAL");
                 ops=(OraclePreparedStatement)oconn.prepareCall("INSERT INTO FLIGHT_DETAILS1(FLIGHTNO,FLIGHTNAME,FROMLOC,TOLOC,DEPARTURE,CABIN,TICKETFARE)VALUES(?,?,?,?,?,?,?)");
                 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                 Date dt=sdf.parse(vDEPARTURE);
                 SimpleDateFormat sdf1=new SimpleDateFormat("dd-MM-yyyy");
                 vDEPARTURE=sdf1.format(dt);
                 
                    ops.setString(1,vFLIGHTNO);
            ops.setString(2,vFLIGHTNAME);
            ops.setString(3,vFROM);
            ops.setString(4,vTO);
            ops.setString(5,vDEPARTURE);
            ops.setString(6,vCABIN);
            ops.setString(7,vTICKETFARE);
//            ops.setString(8, vADULTS);
//            ops.setString(9, vCHILDREN);
//            ops.setString(10, vINFANTS);
            
            int x=ops.executeUpdate();
            if(x>0)
            {
                response.sendRedirect("http://localhost:8080/webapp/STATPAGES/Admintable.html");
            }
            else
            {
                out.println("<h2 style='color:brown>record could not be added...");
            }
            ops.close();
            oconn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Flightpage.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(Flightpage.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(Flightpage.class.getName()).log(Level.SEVERE, null, ex);
        }
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
