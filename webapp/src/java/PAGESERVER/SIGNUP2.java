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
public class SIGNUP2 extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    String vFIRSTNAME,vLASTNAME,vGEN,vEMAIL,vPHNO,vDOB,vADD,vPASS,vCPASS,vSQUES,vSANS;
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
            out.println("<title>Servlet SIGNUP2</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SIGNUP2 at " + request.getContextPath() + "</h1>");
            vFIRSTNAME=request.getParameter("fname");
            vLASTNAME=request.getParameter("lname");
            vGEN=request.getParameter("gender1");
            vEMAIL=request.getParameter("email");
            vPHNO=request.getParameter("pfone");
            vDOB=request.getParameter("date");
            vADD=request.getParameter("address");
            vPASS=request.getParameter("npassword");
            vCPASS=request.getParameter("cpassword");
            vSQUES=request.getParameter("sques");
            vSANS=request.getParameter("sanswer");
            
//            out.println("<h2>FIRSTNAME:"+vFIRSTNAME+"</h2>");
//            out.println("<h2>LASTNAME:"+vLASTNAME+"</h2>");
//            out.println("<h2>GENDER:"+vGEN+"</h2>");
//            out.println("<h2>EMAIL:"+vEMAIL+"</h2>");
//            out.println("<h2>PHONE NO:"+vPHNO+"</h2>");
//            out.println("<h2>DATE OF BIRTH:"+vDOB+"</h2>");
//            out.println("<h2>ADDRESS:"+vADD+"</h2>");
//            out.println("<h2>PASSWORD:"+vPASS+"</h2>");
//            out.println("<h2>CONFIRM PASSWORD:"+vCPASS+"</h2>");
//            out.println("<h2>SECURITY QUESTION:"+vSQUES+"</h2>");
//            out.println("<h2>ANSWER:"+vSANS+"</h2>");
//            
            try {
                DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                
                 oconn=(OracleConnection)DriverManager.getConnection("jdbc:oracle:thin:@LAPTOP-NI9HCUJ9:1521:orcl1","POUSHALI","GHOSAL");
                 ops=(OraclePreparedStatement)oconn.prepareCall("INSERT INTO USER_DETAILS(FIRSTNAME,LASTNAME,GENDER,EMAIL,PHONENUMBER,DATEOFBIRTH,ADDRESS,PASSWORD,CONFIRM,SQUES,SANS)values(?,?,?,?,?,?,?,?,?,?,?)");
                 
                 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                 Date dt=sdf.parse(vDOB);
                 SimpleDateFormat sdf1=new SimpleDateFormat("dd-MMM-yyyy");
                 vDOB=sdf1.format(dt);
                 
                 ops.setString(1, vFIRSTNAME);
                 ops.setString(2, vLASTNAME);
                 ops.setString(3, vGEN);
                 ops.setString(4, vEMAIL);
                 ops.setString(5,vPHNO);
                 ops.setString(6, vDOB);
                 ops.setString(7, vADD);
                 ops.setString(8, vPASS);
                 ops.setString(9, vCPASS);
                 ops.setString(10, vSQUES);
                 ops.setString(11, vSANS);
                 
                 int x=ops.executeUpdate();
                 if(x>0)
                 {
                          response.sendRedirect("testlanding2.html");
                 }
                 else
                 {
                              out.println("<h2 style='color:brown'> Record could not be added...");

                         }
                 ops.close();
                 oconn.close();
                 
            } catch (SQLException ex) {
                Logger.getLogger(SIGNUP2.class.getName()).log(Level.SEVERE, null, ex);
            }
            
           
            
            out.println("<h2>");
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
            Logger.getLogger(SIGNUP2.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(SIGNUP2.class.getName()).log(Level.SEVERE, null, ex);
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
