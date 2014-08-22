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
        <title>Game of Thrones</title>
        
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
        <script type="text/javascript" 
                  src="${pageContext.request.contextPath}/js/javaScript.js"></script>
        
        
    </head>
        <body>
        <div class="header"></div>
        
        <div class="navigation"> 
            <div style="width:100%; position: absolute;">
                <img src="images/banners/spear.png" alt="Game Of Thrones"  style="width:100%; height:100%">
            </div>
            
            <div id="nav" style="width:100%; height: 85%; position: absolute; z-index: 1;  ">                
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
        
        <div class="footer" ></div>
    
        
    


        
    
</body>

<script>
        
    $(document).ready(function ()
        {
            insertHeader();
            insertNav();
            insertFooter();
//        gameLogic();
        });

</script>

</html>

