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
public class bookflight extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    String vFLIGHTNO,vFROMLOC,vTOLOC,vPREFERREDAIRLINE,vPREFERREDSEATING,vDEPARTUREDATE,vADULTS,vCHILDREN,vINFANTS,vSELECTFARE,vRETURNDATE,vFULLNAME,vPHNO,vEMAIL;
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
            out.println("<title>Servlet bookflight</title>");            
            out.println("</head>");
            out.println("<body>");
//            out.println("<h1>Servlet bookflight at " + request.getContextPath() + "</h1>");
            vFLIGHTNO=request.getParameter("flno");
            vFROMLOC=request.getParameter("from");
            vTOLOC=request.getParameter("to");
            vPREFERREDAIRLINE=request.getParameter("pair");
            vPREFERREDSEATING=request.getParameter("pseat");
            vDEPARTUREDATE=request.getParameter("Text");
            vADULTS=request.getParameter("adult");
            vCHILDREN=request.getParameter("child");
            vINFANTS=request.getParameter("inf");
            vSELECTFARE=request.getParameter("selector1");
            vRETURNDATE=request.getParameter("Text");
            vFULLNAME=request.getParameter("Name");
            vPHNO=request.getParameter("Phoneno");
            vEMAIL=request.getParameter("Email");
            
//            out.println("<h2>FLIGHTNO:"+vFLIGHTNO+"</h2>");
//            out.println("<h2>FROM LOC:"+vFROMLOC+"</h2>");
//            out.println("<h2>TO LOC:"+vTOLOC+"</h2>");
//            out.println("<h2>PREFERRED AIRLINE:"+vPREFERREDAIRLINE+"</h2>");
//            out.println("<h2>PREFERRED SEATING:"+vPREFERREDSEATING+"</h2>");
//            out.println("<h2>DEPARTURE DATE:"+vDEPARTUREDATE+"</h2>");
//            out.println("<h2>ADULTS:"+vADULTS+"</h2>");
//            out.println("<h2>CHILDREN:"+vCHILDREN+"</h2>");
//            out.println("<h2>INFANTS:"+vINFANTS+"</h2>");
//            out.println("<h2>SELECT FARE:"+vSELECTFARE+"</h2>");
//            out.println("<h2>RETURN DATE:"+vRETURNDATE+"</h2>");
//            out.println("<h2>FULL NAME:"+vFULLNAME+"</h2>");
//            out.println("<h2>PHNO:"+vPHNO+"</h2>");
//            out.println("<h2>EMAIL:"+vEMAIL+"</h2>");
            
            try {
                DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                 oconn=(OracleConnection)DriverManager.getConnection("jdbc:oracle:thin:@LAPTOP-NI9HCUJ9:1521:orcl1","POUSHALI","GHOSAL");
                 ops=(OraclePreparedStatement)oconn.prepareCall("INSERT INTO BOOKFLIGHT(FLIGHTNO,FROMLOC,TOLOC,PREFERREDAIRLINE,PREFERREDSEATING,DEPARTUREDATE,ADULTS,CHILDREN,INFANTS,SELECTFARE,RETURNDATE,FULLNAME,PHNO,EMAIL)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
           
                 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                 Date dt=sdf.parse(vDEPARTUREDATE);
                 SimpleDateFormat sdf1=new SimpleDateFormat("dd-MMM-yyyy");
                 vDEPARTUREDATE=sdf1.format(dt);
                 
                 SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd");
                 Date dt1=sdf.parse(vRETURNDATE);
                 SimpleDateFormat sdf3=new SimpleDateFormat("dd-MMM=yyyy");
                 vRETURNDATE=sdf3.format(dt1);
                 
                 ops.setString(1, vFLIGHTNO);
                 ops.setString(2, vFROMLOC);
                 ops.setString(3, vTOLOC);
                 ops.setString(4, vPREFERREDAIRLINE);
                 ops.setString(5, vPREFERREDSEATING);
                 ops.setString(6,vDEPARTUREDATE);
                 ops.setString(7,vADULTS);
                 ops.setString(8, vCHILDREN);
                 ops.setString(9, vINFANTS);
                 ops.setString(10, vSELECTFARE);
                 ops.setString(11, vRETURNDATE);
                 ops.setString(12, vFULLNAME);
                 ops.setString(13, vPHNO);
                 ops.setString(14, vEMAIL);
                 
                 int x=ops.executeUpdate();
                 if(x>0)
                 {
                       response.sendRedirect("http://localhost:8080/webapp/STATPAGES/payment2.html");
                 }
                 else
                 {
                       out.println("<h2 style='color:brown'> Record could not be added...");
                 }
                 
                 ops.close();
                 oconn.close();
            } catch (SQLException ex) {
                Logger.getLogger(bookflight.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(bookflight.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(bookflight.class.getName()).log(Level.SEVERE, null, ex);
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
