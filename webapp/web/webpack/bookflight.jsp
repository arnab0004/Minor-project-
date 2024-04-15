<%-- 
    Document   : bookflight
    Created on : 20 Nov, 2023, 11:56:17 AM
    Author     : POUSHALI
--%>

<%@page import="oracle.jdbc.OracleResultSetMetaData"%>
<%@page import="oracle.jdbc.OracleResultSet"%>
<%@page import="oracle.jdbc.OraclePreparedStatement"%>
<%@page import="oracle.jdbc.OracleConnection"%>
<%@page import="java.sql.DriverManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>bookflight Page</title>
        <style>
            table,tr,td
            {
                padding: 10px;
                border: 5px solid blue;
                border-collapse: collapse
            }
            th{
                padding: 10px;
                border: 5px solid blue;
                border-collapse: collapse;
                color: black
            }
        </style>
    </head>
    <%!
        OracleConnection oconn;
        OraclePreparedStatement ops;
        OracleResultSet ors;
        OracleResultSetMetaData orsmd;
        int counter,recounter,colcounter;
        %>
        <%
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            oconn=(OracleConnection)DriverManager.getConnection("jdbc:oracle:thin:@LAPTOP-NI9HCUJ9:1521:orcl1","POUSHALI","GHOSAL");
            ops=(OraclePreparedStatement)oconn.prepareCall("select * from bookflight");
            ors=(OracleResultSet)ops.executeQuery();
            orsmd=(OracleResultSetMetaData)ors.getMetaData();
            %>
            <body style="background-color: white">
                <table>
                    <thead>
                        <tr>
                            <%
                                for(counter=1;counter<=orsmd.getColumnCount();
                                        counter++)
                                {
                                    %>
                                    <th>
                                        <%=orsmd.getColumnName(counter)%>
                                    </th>
                                    <%
                                }
%>

                        </tr>
                    </thead>
                    <tbody>
                        <%
                            while(ors.next()==true)
                            {
                                %>
                                <tr>
                                    <%
                                        for(counter=1;counter<=orsmd.getColumnCount();counter++)
                                        {
                                            %>
                                            <th>
                                                <%=ors.getString(counter)%>
                                            </th>
                                            <%
                                        }
%>
                                </tr>
                                <%
                            }
%>
                    </tbody>
                </table>
<%
    ors.close();
    ops.close();
    oconn.close();
    %>
            </body>
    
</html>
