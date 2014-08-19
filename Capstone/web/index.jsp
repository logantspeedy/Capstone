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
            <div class="banner">
            <img src="images/GoT/gameBanner.png" alt="Game Of Thrones"  style="width: 100%" >
            </div>
        </div>
        
        <div class="navigation"> <a href="http://localhost:8082/Capstone/GoT.jsp"><img src="http://fontmeme.com/newcreate.php?text=start%20new%20game&name=Game of Thrones.ttf&size=11&style_color=2B2C38" alt="Start New Game"></a></div>
        
        <div class ="mainContainer">
            <div style="opacity: 0.0 "class="commentWindow">
            </div>
            
            <div class="gameWindow" style="overflow:hidden">
            <iframe href="http://fontmeme.com/game-of-thrones-font/" width="100%" height="100%" src="//www.youtube.com/embed/0cfRjmZX7_U?rel=0&autoplay=1&controls=0&showinfo=0" frameborder="0" allowfullscreen></iframe>
            </div>
        </div> 
        <div class="footer" >
            <p>Copyright © team4</p>
        </div>
    
        
    </body>
</html>
