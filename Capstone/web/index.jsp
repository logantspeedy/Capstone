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
            <table>
                <tr><td>Command:</td> <td><input type="text" name="command" id="command"></td></tr> <%--<a href="javascript:void(0)" id="reinforce">Add reinforce options</a><br/>--%>
                <tr><td>Territory:</td> <td><input type="text" name="territory" id="territory"></td></tr>
                <tr><td>Attacking Territory:</td> <td> <input type="text" name="attackingterritory" id="attackingterritory"></td></tr>
                <tr><td>Defending Territory:</td> <td> <input type="text" name="defendngterritory" id="defendngterritory"></td></tr>
                <tr><td>Army A:</td> <td> <input type="number" name="aarmy" id="aarmy"></td></tr>
                <tr><td>Army B:</td> <td> <input type="number" name="darmy" id="darmy"></td></tr>
                <tr><td>Start Territory:</td> <td> <input type="text" name="startterritory" id="startterritory"></td></tr>
                <tr><td>Target Territory:</td> <td> <input type="text" name="targetterritory" id="targetterritory"></td></tr>         
                <tr><td>Troops:</td> <td> <input type="number" name="troops" id="troops"></td></tr>   
            </table>
            <input type="submit" value="Submit" id="submit">
        </form>   
        
        
        <%

           out.write("<br/>Enter commands above ^<br/><br/>Currently supported:<ul><li>newboard (command) : returns json of new board</li><li>reinforce (command, terriotory, troops): returns json of new board </li><li>attack (command, attacking territory, defending territory, aarmy, darmy) : returns json of new board </li><li>fortify (start territory, target territory, troops) : returns json of new board </li><li>end : returns nothing (changes the game object state to next turns)</li></ul>");
           String test = (String) request.getSession().getAttribute("game");
           
           
           out.write(test);
            
         %>
         
         
         <script>
         $("#reinforce").click(function() {
           $( "<input type='text' name='territory' id='reinforceinput'>" ).insertBefore( "#submit" );  
            });
         </script>
    </body>
</html>
