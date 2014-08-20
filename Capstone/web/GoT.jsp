<%-- 
    Document   : GoT
    Created on : 31/07/2014, 11:46:19 AM
    Author     : Zeb
--%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="gamecss.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>GoT Page</title>
        
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
        <script type="text/javascript" 
                  src="${pageContext.request.contextPath}/js/javaScript.js"></script>
        
        
    </head>
    <body class="body">
       
        
        <div class="header">
             <h1><a href="http://fontmeme.com/game-of-thrones-font/"><img src="http://fontmeme.com/newcreate.php?text=Winter%20is%20Coming&name=Game of Thrones.ttf&size=40&style_color=2B2C38" alt="Game of Thrones Font"></a><p style="padding-left: 15px; font-size: 10px;"></p></h1>
        
        </div>
        
        <div class="navigation"> <a href="http://localhost:8082/Capstone/GoT.jsp"><img src="http://fontmeme.com/newcreate.php?text=start%20new%20game&name=Game of Thrones.ttf&size=11&style_color=2B2C38" alt="Start New Game"></a></div>
        
        <div class="mainContainer">
        
        <div class="commentWindow">
        </div>
            
        <div class="gameWindow">
            <button onclick="startGame()">Click me</button> 
        </div>
        

        <!--</div>-->
        </div>
        <div class="footer" >
            <p>Copyright © team4</p>
        </div>


        
    <script>
        
    $(document).ready(function ()
        {
        gameLogic();
        });

    </script>
</body>
    

</html>
