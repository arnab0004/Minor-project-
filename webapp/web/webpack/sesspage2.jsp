<%-- 
    Document   : sesspage2
    Created on : 12 Nov, 2023, 9:24:26 AM
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
                HttpSession sess=request.getSession(false);
                try
                {
                    if(sess!=null)vname=sess.getAttribute("fname").toString();
                }
                catch(Exception ex)
                {
                    %>
                    <script>
                        alert("Session was not created!!!");
                        alert("Redirecting for logging===>>");
                        location.href="";
                    </script>
                    <%
                }
%>
<span style="align-content:flex-end">
    Welcome<%=vname%>.
    <a href="">Sign Out</a>
</span>
    <h1>This is page 2 after logging in properly!</h1>
    <a href="">Home</a>
    <a href="">Page 2</a>
    <a href="">Page 3</a>
    <a href="">Page 4</a>
    </body>
</html>
