/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAGESERVER;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author POUSHALI
 */
public class sendmail extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    String vto,vfrom,vcc,vbcc,vsubject,vbody;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet sendmail</title>");            
            out.println("</head>");
            out.println("<body>");
            //getting user data from html input 
            vto=request.getParameter("tbTo");
            //out.println(vto);
            vfrom=request.getParameter("tbFrom");
            vcc=request.getParameter("tbCC");
            vbcc=request.getParameter("tbBCC");
            vsubject=request.getParameter("tbSubject");
            vbody=request.getParameter("taBody");
            
                    final String username="projectminor27@gmail.com";
                    final String password="shmyasdrbuxlqacw";
                    
                    Properties props=new Properties();
                    props.put("mail.smtp.auth","true");
                    props.put("mail.smtp.starttls.enable","true");
                    props.put("mail.smtp.host","smtp.gmail.com");
                    props.put("mail.smtp.port","587");
                    
                    Session session=Session.getInstance(props,new javax.mail.Authenticator()
                    {
                        protected PasswordAuthentication getPasswordAuthentication()
                        {
                            return new PasswordAuthentication(username,password);
                        }
                    });
                    
                   
            try {
                 Message message=new MimeMessage(session);
                message.setFrom(new InternetAddress(username));
                 message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(vto));
                    message.setRecipients(Message.RecipientType.CC,InternetAddress.parse(vcc));
                    message.setRecipients(Message.RecipientType.CC,InternetAddress.parse(vbcc));
                    message.setSubject(vsubject);
                    
                    Random random=new Random();
                    int x=0;
                    while(x<1000)
                    {
                        x=random.nextInt(9999);
                        
                    }
                    out.println("<h2 style='color:darkblue'>New OTP generated in Website:"+x+"</h2>");
                    vbody+="\n your new OTP is"+x;
                    //no need to give coding lines 78-85 if you do not want an OTP
                    
                    message.setText(vbody);
                    Transport.send(message);
                    out.println("<h2 style='color:green'>Mail with new OTP sent successfully</h2>");
                    
            } catch (MessagingException ex) {
                out.println("<h2 style='color:red'>"+ex.getMessage()+"</h2>");
            
            
                Logger.getLogger(sendmail.class.getName()).log(Level.SEVERE, null, ex);
            }
                finally
            {
                   out.println("</body>");
            out.println("</html>");
            }
         
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
