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
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    </head>
    <body>
        <h1>Welcome to the risk game!</h1>
        <form action="http://localhost:8080/Capstone/MainServlet" method="POST">
            Command: <input type="text" name="command" id="command"><br/> <%--<a href="javascript:void(0)" id="reinforce">Add reinforce options</a><br/>--%>
            Territory: <input type="text" name="territory" id="territory"><br/>
            Attacking Territory: <input type="text" name="attackingterritory" id="attackingterritory"><br/>
            Defending Territory: <input type="text" name="defendngterritory" id="defendngterritory"><br/>
            Territory: <input type="text" name="territory" id="territory"><br/>
            Troops: <input type="number" name="troops" id="troops"><br/>
            
            <input type="submit" value="Submit" id="submit">
        </form>   
        
        
        <%

           out.write("<br/>Enter commands above ^<br/><br/>Currently supported:<ul><li>newboard : returns json of new board</li><li>reinforce : returns text reinforce</li><li>attack : returns text attack</li><li>fortify : returns text fortify</li><li>end : returns text end turn</li></ul>");
            
            
         %>
         
         
         <script>
         $("#reinforce").click(function() {
           $( "<input type='text' name='territory' id='reinforceinput'>" ).insertBefore( "#submit" );  
            });
         </script>
    </body>
</html>
