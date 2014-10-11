<%-- 
    Document   : waiting
    Created on : 5/10/2014, 9:00:59 PM
    Author     : Zeb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Waiting</title>
        
        <script src='js/jquery-1.9.1.js' type='text/javascript'></script>
        <!--Scripts-->
        
        <!--<script type="text/javascript" src="${pageContext.request.contextPath}/js/chooseHouse.js"></script>-->
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/displayers.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/gameLogic.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/getters.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/images.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/javaScript.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/layouts.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/setters.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/sounds.js"></script>
        
    </head>
    <body>
        <h1>Waiting for other players to Pick House</h1>
        
        <script>
            function checkForGame(){
                getGameJSON();
                if(gameJSON === null){window.location="GameEnded.jsp";}
                
                else{if(checkIfAllPlayersHouse() === 0){window.location="GoT.jsp";}}
                
            }
            $(document).ready(function()
            {
                    
                window.setInterval(checkForGame, 2500);

            });
            
        </script>
    </body>
</html>
