<%-- 
    Document   : Login
    Created on : 22/09/2014, 11:46:19 AM
    Author     : Taylor
--%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
    <head>
                <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" rel="stylesheet">
        <!--load scripts-->
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/layouts.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/pagesizing.js"></script>
        <!--<script type="text/javascript" src="${pageContext.request.contextPath}/js/javaScript.js"></script>-->
        
        <script src='js/jquery-1.9.1.js' type='text/javascript'></script>
        

        <!--style sheet and meta data-->
        
        <link rel="stylesheet" type="text/css" href="css/htmlBody.css">
        <link rel="stylesheet" type="text/css" href="css/fontsandColours.css">
        <link rel="stylesheet" type="text/css" href="css/index.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>    
        
                <!--Scripts-->
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/javaScript.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/displayers.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/gameLogic.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/getters.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/layouts.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/sounds.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/setters.js"></script>
        
        
        <script src="https://apis.google.com/js/client:platform.js" async defer></script>
        
        <title>Game ended</title>
        
    </head>

    <body onload="resize()" onresize="resize()" style="background:none;"> 
        
        <div id="view">
        <div id="btl"><img src="images/border/btl.png" alt=""></div>
        <div id="btr"><img src="images/border/btr.png" alt=""></div>
        <div id="filler"></div>
        <div id="bbl"><img src="images/border/bbl.png" alt=""></div>
        <div id="bbr"><img src="images/border/bbr.png" alt=""></div>
        
        <div class ="loader" id="loader"><br/><br/><br/><br/><br/><br/><img src="images/ajax-loader.gif" alt=""></div>
        
        <div class="mainContainer">
        
            <div class="header"><img class="gameBanner" src="images/banners/gameBanner.png" alt="Game Of Thrones" style="width:80%;height:180%;"></div>

            <div class="login" style="padding:30px;height:10%;font-size: 2em;color:rgba(210, 202, 160, 1);font-family: CharleMagne;">              

            </div>

            <hr/><br/>
            
            <h1>Your game has ended.</h1>
        
            <h1><a href="lobby.jsp">Return to Lobby</a><h1>
            
  
</span>  </div><br/>           
            </div>                      
               
            </div>
            

           
            
        </div>
        
        </div>
       
</body>
</html>

