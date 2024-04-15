<%-- 
    Document   : Homepage
    Created on : 11 Nov, 2023, 11:18:32 AM
    Author     : POUSHALI
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%!
            String vname;
            %>
            <%
                HttpSession sess=request.getSession(true);
                try
                {
                    if(sess!=null)vname=sess.getAttribute("fname").toString();
                }
                catch(Exception ex)
                {
                    %>
                    <script>
                        alert("Session was not created!!!");
                        alert("Redirecting for logging==>>");
                        location.href="http://localhost:8080/webapp/STATPAGES/test2login.html";
                    </script>
                    <%
                }
%>
<span style="align-content:flex-end">
    Welcome <%=vname%>. <a href="http://localhost:8080/webapp/sessLOgOut">Sign Out</a>
</span>
<h1>This is home page after logging in properly!</h1>
<a href="http://localhost:8080/webapp/Homepage.jsp">Home</a>
<a href="http://localhost:8080/webapp/sesspage2.jsp">Page 2</a>
<!--<a href="">Page 3</a>
<a href="">Page 4</a>
    </body>-->
</html>
