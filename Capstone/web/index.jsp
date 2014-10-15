<%-- 
    Document   : index
    Created on : Jul 23, 2014, 3:15:53 PM
    Author     : sszeb
--%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html> 
    <head>
        <script src='js/jquery-1.9.1.js' type='text/javascript'></script>
        <!--load scripts-->
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/layouts.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/pagesizing.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/images.js"></script>
        
        
        
        
        <!--style sheet and meta data-->
        <link rel="stylesheet" type="text/css" href="css/htmlBody.css">
        <link rel="stylesheet" type="text/css" href="css/fontsandColours.css">
        <link rel="stylesheet" type="text/css" href="css/index.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        
    </head>
    
    <body onload="resize()" onresize="resize()">
        <div id="view">
        <div id="btl"><img src="images/border/btl.png" alt=""></div>
        <div id="btr"><img src="images/border/btr.png" alt=""></div>
        <div id="filler"></div>
        <div id="bbl"><img src="images/border/bbl.png" alt=""></div>
        <div id="bbr"><img src="images/border/bbr.png" alt=""></div>
        
        <!--<div class ="loader" id="loader"><br/><br/><br/><br/><br/><br/><img src="images/ajax-loader.gif" alt=""></div>-->
        
        <div class="mainContainer">
            <span id='test'></span>
            
            <div class="header"></div>

            <div class="login">
                <a href="login.jsp">
                    <h1>Login</h1>
                </a>
            </div>

            <hr>

            <div class="video" >
                <iframe href="http://fontmeme.com/game-of-thrones-font/" width="100%" height="100%" src="//www.youtube.com/embed/0cfRjmZX7_U?rel=0&autoplay=1&controls=0&showinfo=0" frameborder="0" allowfullscreen></iframe>
            </div>

            <h3>“When you play the game of thrones, you win or you die.”― Cersei Lanister</h3> 

            <div class="footer" ></div>
            
        </div>
        
        </div>
        
</body>

<script>
        
    $(document).ready(function ()
        {
          insertHeader();
          insertFooter();
//        gameLogic();
        });

</script>

</html>
