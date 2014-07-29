<%-- 
    Document   : index
    Created on : Jul 23, 2014, 3:15:53 PM
    Author     : ltspeedy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form action="http://localhost:8080/Capstone/MainServlet" method="POST">
         Username: <input type="text" name="command">
        <input type="submit" value="Submit">
        </form>   
        
        
        <%

           out.write("<br/>hey");
            
            
         %>
    </body>
</html>
