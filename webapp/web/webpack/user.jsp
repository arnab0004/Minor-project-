<%-- 
    Document   : user
    Created on : 17 Nov, 2023, 5:01:37 PM
    Author     : POUSHALI
--%>

<%@page import="java.sql.DriverManager"%>
<%@page import="oracle.jdbc.OracleResultSetMetaData"%>
<%@page import="oracle.jdbc.OracleResultSet"%>
<%@page import="oracle.jdbc.OraclePreparedStatement"%>
<%@page import="oracle.jdbc.OracleConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Record Displayer</title>
        <!--step 2: ADDING A INTERVAL STYLE FOR TABLE----->
        <style>
            table tr,td{
                padding: 10px;
                border: 5px solid blue;
                border-collapse: collapse
            }
            th
            {
                padding: 10px;
                border: 5px solid blue;
                border-collapse:collapse;
                color:chartreus     
            }
        </style>
    </head>
    <%!
//        STEP 3: DECLARING OBJECTS AND VARIABLES

        OracleConnection oconn;
        OraclePreparedStatement ops;
        OracleResultSet ors;
        OracleResultSetMetaData orsmd;
        int counter,recounter,colcounter;
        %>
        <%
//        <!--STEP 4: REGISTRATION OF ORACLE DRIVER-->
        DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
        oconn=(OracleConnection)DriverManager.getConnection("jdbc:oracle:thin:@LAPTOP-NI9HCUJ9:1521:orcl1","POUSHALI","GHOSAL");
        
//        step 6: INSTANTIATING THE STATEMENT OBJECT
ops=(OraclePreparedStatement)oconn.prepareCall("select * from USER_DETAILS");

//step 7: FILLING UP THE DATABASE RECORDS IN A TEMPORARY CONTAINER

ors=(OracleResultSet)ops.executeQuery();

//step 8: GETTING THE COLUMNS INFORMATION (METADATA)

orsmd=(OracleResultSetMetaData)ors.getMetaData();
%>

<body style="background-color: white">
    <!--STEP 1: BASIC STRUCTURE OF A TABLE---->
    <table>
        <thead>
            <tr>
                <%
//                    step 9: BRINGING THE TABLE HEADINGS

for(counter=1;counter<=orsmd.getColumnCount();counter++)
{
    %>
    <th>
        <%=orsmd.getColumnName(counter)%>
    </th>
    <%
}
%>
<!--<th>ACTION</th>-->
            </tr>
        </thead>
        <tbody>
            <%
//                step 10: BRINGING ALL THE RECORDS AND DISPLAYING AS TABLE ROWS

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
<!--<td>
    <button>Edit</button>
    <button>Delete</button>
</td>-->
    </tr>
    <%
}
%>
        </tbody>
    </table>
<%
//    step 11: CLOSING THE CONNECTIONS
ors.close();
ops.close();
oconn.close();
%>
    </body>
</html>
