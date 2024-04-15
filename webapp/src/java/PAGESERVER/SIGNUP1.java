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
public class SIGNUP1 extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    String vFIRSTNAME,vLASTNAME,vGENDER,vEMAIL,vPHONE_NUMBER,vDATE_OF_BIRTH,vADDRESS,vPASSWORD,vCONFIRM,vSQUES,vSANS;
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
            out.println("<title>Servlet SIGNUP1</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SIGNUP1 at " + request.getContextPath() + "</h1>");
            vFIRSTNAME=request.getParameter("fname");
            vLASTNAME=request.getParameter("lname");
            vGENDER=request.getParameter("gender");
            vEMAIL=request.getParameter("email");
            vPHONE_NUMBER=request.getParameter("pfone");
            vDATE_OF_BIRTH=request.getParameter("date");
          vADDRESS=request.getParameter("address");
            vPASSWORD=request.getParameter("npassword");
            vCONFIRM=request.getParameter("cpassword");
            vSQUES=request.getParameter("sques");
            vSANS=request.getParameter("sanswer");
            out.println("<h1>printing the html form values in this servlet...</h1>");
            out.println("<h2>FirstName:"+vFIRSTNAME+"</h2>");
            out.println("<h2>Lastname:"+vLASTNAME+"</h2>");
            out.println("<h2>Gender:"+vGENDER+"</h2>");
            out.println("<h2>Email:"+vEMAIL+"</h2>");
            out.println("<h2>PhoneNumber:"+vPHONE_NUMBER+"</h2>");
            out.println("<h2>DateOfBirth:"+vDATE_OF_BIRTH+"</h2>");
            out.println("<h2>Address:"+vADDRESS+"</h2>");
            out.println("<h2>Password:"+vPASSWORD+"</h2>");
            out.println("<h2>ConfirmPassword:"+vCONFIRM+"</h2>");
               out.println("<h2>Security Question:"+vSQUES+"</h2>");
            out.println("<h2>ANSWER:"+vSANS+"</h2>");
            
            
            try {
                DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                 oconn=(OracleConnection)DriverManager.getConnection("jdbc:oracle:thin:@LAPTOP-NI9HCUJ9:1521:orcl1","POUSHALI","GHOSAL");
            ops=(OraclePreparedStatement)oconn.prepareCall
        ("INSERT INTO USER_DETAILS(FIRSTNAME,LASTNAME,GENDER,EMAIL,PHONENUMBER,DATEOFBIRTH,ADDRESS,PASSWORD,CONFIRM,SQUES,SANS)values(?,?,?,?,?,?,?,?,?)");
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            Date dt=sdf.parse(vDATE_OF_BIRTH);
            SimpleDateFormat sdf1=new SimpleDateFormat("dd-MMM-yyyy");
            vDATE_OF_BIRTH=sdf1.format(dt);
            
            ops.setString(1,vFIRSTNAME);
            ops.setString(2,vLASTNAME);
            ops.setString(3,vGENDER);
            ops.setString(4,vEMAIL);
            ops.setString(5,vPHONE_NUMBER);
            ops.setString(6,vDATE_OF_BIRTH);
            ops.setString(7,vADDRESS);
            ops.setString(8,vPASSWORD);
            ops.setString(9,vCONFIRM);
            ops.setString(10,vSQUES);
            ops.setString(11,vSANS);
            
            int x=ops.executeUpdate();
            if(x > 0)
            {
                out.println("<h2 style='color:green>record inserted successfully...");
            }
            else
            {
                out.println("<h2 style='color:brown>record could not be added...");
            }
            ops.close();
            oconn.close();
            } catch (SQLException ex) {
                Logger.getLogger(SIGNUP1.class.getName()).log(Level.SEVERE, null, ex);
                
                out.println("<h2 style='color:red>Error is:"+ex.toString()+"</h2>");
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
            Logger.getLogger(SIGNUP1.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(SIGNUP1.class.getName()).log(Level.SEVERE, null, ex);
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
