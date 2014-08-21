<%-- 
    Document   : index
    Created on : Jul 23, 2014, 3:15:53 PM
    Author     : ltspeedy
--%>



<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="gamecss.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
    </head>
    <body>
        <div class="header">
           <img src="images/banners/gameBanner.png" alt="Game Of Thrones"  style="height: 112%; width:80%;" >
        </div>
        
        <div class="navigation"> 
            <div style="width:100%; position: absolute;">
                <img src="images/banners/spear.png" alt="Game Of Thrones"  style="width:100%; height:100%">
            </div>
            
            <div style="width:100%; height: 100%; position: absolute; z-index: 1; padding-top: 10px">
            <a href="http://localhost:8082/Capstone/GoT.jsp"> <img style="height: 100%;"src="http://fontmeme.com/newcreate.php?text=Enter%20Lobby&name=Game of Thrones.ttf&size=11&style_color=221a16"  alt="Start New Game"></a>
            </div>
        </div>
        
        <div class ="mainContainer">
            <div style="opacity: 0.5 "class="commentWindow">
            </div>
            
            <div class="gameWindow" style="overflow:hidden">
            <iframe href="http://fontmeme.com/game-of-thrones-font/" width="100%" height="100%" src="//www.youtube.com/embed/0cfRjmZX7_U?rel=0&autoplay=1&controls=0&showinfo=0" frameborder="0" allowfullscreen></iframe>
            </div>
            
            <div style="opacity: 0.5 "class="commentWindow">
            </div>
        </div> 
        
        <div class="footer" >
            <p>a Team4 Production 2014 Massey capstone@massey.zc.nz ©</p>
        </div>
    
        
    </body>
</html>
