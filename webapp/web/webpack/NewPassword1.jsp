<%-- 
    Document   : NewPassword1
    Created on : 16 Nov, 2023, 9:14:09 PM
    Author     : POUSHALI
--%>

<%@page import="oracle.jdbc.OraclePreparedStatement"%>
<%@page import="oracle.jdbc.OracleConnection"%>
<%@page import="java.sql.DriverManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Password Reset page</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                background-color: #f5f5f5;
            }
    
            form {
                width: 80%;
                max-width: 400px;
                background-color: rgb(3, 114, 217);
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
    
            label {
                display: block;
                margin: 15px 0 5px;
                font-weight: bold;
            }
    
            input {
                width: 100%;
                padding: 10px;
                margin-bottom: 15px;
                box-sizing: border-box;
            }
    
            button {
                background-color: #4caf50;
                color: white;
                padding: 15px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                width: 100%;
            }
    
            button:hover {
                background-color: #4580a0;
            }
        </style>

        
    </head>
    <body>
        <%!
            String vemail,vpass;
            OracleConnection oconn;
            OraclePreparedStatement ost;
            HttpSession sess;
            %>
            <%
                if(request.getParameter("bConfirm")!=null)
                {
                    if(request.getParameter("tpass").equals(request.getParameter("cpass")))
                    {
                        sess=request.getSession(false);
                        vpass=request.getParameter("tpass");
                        vemail=sess.getAttribute("sessemail").toString();
                        DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                        oconn=(OracleConnection)DriverManager.getConnection("jdbc:oracle:thin:@LAPTOP-NI9HCUJ9:1521:orcl1","POUSHALI","GHOSAL");
                        ost=(OraclePreparedStatement)oconn.prepareStatement("update USER_DETAILS set PASSWORD=?where EMAIL=?");
                        ost.setString(1, vpass);
                        ost.setString(2,vemail);
                        int x=ost.executeUpdate();
                        ost.close();
                        oconn.close();
                        sess.invalidate();
                        %>
                        <script>
                            alert("Password reset successfully!!!You can now login using your new password ");
                            alert("Redirecting for logging==>>");
                            location.href="http://localhost:8080/webapp/STATPAGES/test2login.html";
                            
                        </script>
                        <%             
                    }
                    else
                        {
                                    %>
                    <h3 style="color: red">Password do not match!!!Please try again!!!</h3>
                                    <%
                                    }
                                }
                                else
{
vemail=request.getParameter("pemail");
sess=request.getSession(true);
sess.setAttribute("sessemail",vemail);
%>
<!--<h3 style="color: blueviolet">Please verify your security credentials</h3>-->
<%
}
%>
        <form>
                <h2 style="text-align: center;">Reset Password</h2>
                <label for="newPassword">New Password:</label>
                <input type="password" id="newPassword" name="tpass" required>
        
                <label for="confirmPassword">Confirm Password:</label>
                <input type="password" id="confirmPassword" name="cpass" required>
        
                <button type="submit" name="bConfirm">confirm</button>
            </form>


    </body>
</html>
