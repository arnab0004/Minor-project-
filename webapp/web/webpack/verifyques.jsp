<%-- 
    Document   : verifyques
    Created on : 8 Nov, 2023, 12:00:53 PM
    Author     : POUSHALI
--%>

<%@page import="oracle.jdbc.OracleResultSet"%>
<%@page import="oracle.jdbc.OraclePreparedStatement"%>
<%@page import="oracle.jdbc.OracleConnection"%>
<%@page import="java.sql.DriverManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script>
            function funClose()
            {
                if(window.parent)
                    if(confirm("Closing window....")===true)window.parent.window.close();
                else if(confirm("Closing window...")===true)
                    window.close();
            }
        </script>
    </head>
    <body style="background-color:white">
        <%!
            String vemail,vques,vans;
            OracleConnection oconn;
            OraclePreparedStatement ost;
            OracleResultSet ors=null;
            %>
            <%
                vemail=request.getParameter("pemail");
                DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                oconn=(OracleConnection)DriverManager.getConnection("jdbc:oracle:thin:@LAPTOP-NI9HCUJ9:1521:orcl1","POUSHALI","GHOSAL");
                ost=(OraclePreparedStatement)oconn.prepareStatement("SELECT * FROM USER_DETAILS where email=?");
                ost.setString(1, vemail);
                ors=(OracleResultSet)
                        ost.executeQuery();
                if(ors.next())
                {
                    vques=ors.getString("SQUES");
                    vans=ors.getString("SANS");
                }
                else
                {
                    %>
                    <script>
                        alert("Do not try any malaligned URL.\n youcan only use the link received in mail");
                        window.close();
                    </script>
                    <%
                }
                    ost.close();
                    oconn.close();
                    if(request.getParameter("bVerify")!=null)
                {   
                    if(request.getParameter("tbAns").equals(vans))
                    {
                    %>
        <script>
                 alert("Security Answer verified succesfully!!!");
                 location.href="http://localhost:8080/webapp/webpack/NewPassword1.jsp?pemail=<%=vemail%>";
        </script>
        <%
            }
        else
            {
            %>
                <h3 style="color:red">
                 Wrong Answer.Please try again!!!
                </h3>
        <%
        }
}
else
{
%>
<!--<h3 style="color:blueviolet">
    Please verify your security credentials.
</h3>-->
        <%
            }
    %>
       <h1 style="margin-left: 600px;">verify the security questions</h1><br>
    <form name="frmSecurity" method="POST" action="http://localhost:8080/webapp/webpack/verifyques.jsp?pemail=<%=vemail%>"
        style="border: 1px solid #ccc; padding: 20px; background-color: rgb(10, 109, 209);max-width: 400px;margin-left: 600px;">
        <div>
            <label for="tbQues">QUESTION</label>
            <input type="text" size="30" name="tbQues" value="<%=vques%>" readonly /><br><BR>
    
            <label for="tbAns">ANSWER</label>
            <input type="text" size="30" name="tbAns" required/>
    
            <div style="margin-top: 10px;">
                <button type="submit" name="bVerify">Verify</button><br>
            </div>
            <div>
                <button type="reset" name="bReset">Reset</button>
                <button type="button" name="bClose" onclick="funClose();">Close</button>
            </div>
        </div>
    </form>

    </body>
</html>
