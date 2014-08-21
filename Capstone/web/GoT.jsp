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
        <body>
        <div class="header">
           <img src="images/banners/gameBanner.png" alt="Game Of Thrones"  style="height: 112%; width:80%; z-index: 2" >
        </div>
        
        <div class="navigation"> 
            <div style="width:100%; position: absolute;">
                <img src="images/banners/spear.png" alt="Game Of Thrones"  style="width:100%; height:100%">
            </div>
            
            <div style="width:100%; height: 85%; position: absolute; z-index: 1;  ">
                <table style="margin-left: auto; margin-right: auto">
                <tr>
                  <th style="padding-bottom: 10px" ><a href="home">home</a></th>
                  <th></th><th></th>
                  <th style="padding-bottom: 10px"  ><a href="about">about</a></th>
                  <th></th><th></th>
                  <th style="padding-bottom: 10px" ><a href="contact">contact</a></th>
                  <th></th><th></th><th></th><th></th>
                  <th style="padding-top:5px"><a href="http://localhost:8082/Capstone/GoT.jsp"> <img src="http://fontmeme.com/newcreate.php?text=Players%20Housemoto&name=Game of Thrones.ttf&size=11&style_color=221a16"  alt="Start New Game"></a></th>
                  <th></th><th></th><th></th>
                  <th style="padding-bottom: 10px" ><a href="">leave game</a></th>
                  <th></th><th></th>
                  <th style="padding-bottom: 10px" ><a href="test">signout</a></th>
                </tr>
              </table> 
            </div>
            
        </div>
        
        <div class ="mainContainer">
            <div style="opacity: 0.5 "class="commentWindow">
                <img style="width: 100%; height:99%" src="images/banners/starkLeft.png" >
            </div>
            
            <div class="gameWindow" style="overflow:auto">
                <img style="width: 100%; height:99%" src="images/gameboard/GOT.jpg" >
           
            </div>
            
            <div style="opacity: 0.5 "class="commentWindow">
                <img style="width: 100%; height:99%" src="images/banners/starkRight.png" >
            </div>
        </div> 
        
        <div class="footer" >
            
            <p><hr style="color: #d2caa0">a Team4 Production 2014 Massey capstone@massey.zc.nz ©</p>
        </div>
    
        
    


        
    <script>
        
    $(document).ready(function ()
        {
        
//        gameLogic();
        });

    </script>
</body>
    

</html>
