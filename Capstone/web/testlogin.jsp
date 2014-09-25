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
        <!--load scripts-->
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/layouts.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/pagesizing.js"></script>
        <!--<script type="text/javascript" src="${pageContext.request.contextPath}/js/javaScript.js"></script>-->
        
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
        
        
        <!--style sheet and meta data-->
        <link rel="stylesheet" type="text/css" href="css/htmlBody.css">
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
        
        <div class="mainContainer">
        
            <div class="header"></div>

            <div class="login">
                <a href="login.jsp">
                    <h1>Start Game</h1>
                </a>
            </div>

            <hr>

            <div class="video" >
                Loging things here
            </div>

            <h3>“When you play a game of thrones you win or you die.”― George R.R. Martin</h3> 

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