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
        <link rel="stylesheet" type="text/css" href="css/gamecss.css">
        <link rel="stylesheet" type="text/css" href="css/fontsandColours.css">
        <link rel="stylesheet" type="text/css" href="css/chooseHouse.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Game of Thrones</title>

        <script src='js/jquery-1.9.1.js' type='text/javascript'></script>
        
        
        <!--Scripts-->
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/chooseHouse.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/displayers.js"></script>
        <!--<script type="text/javascript" src="${pageContext.request.contextPath}/js/gameLogic.js"></script>-->
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/getters.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/images.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/javaScript.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/layouts.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/setters.js"></script>
        <!--<script type="text/javascript" src="${pageContext.request.contextPath}/js/sounds.js"></script>-->


    </head>
    <body>
        <div class="header" style="z-index: 12"></div>

        <div class="navigation" id="nav" ></div>
        <!--<div class ="loader" id="loader"><img src="images/ajax-loader.gif" alt=""></div>-->
        <div class ="mainContainer">
            

            <div id="backGround" style='z-index: 1; height:100% '>
                <!--<img id="GoTimg" style="width: 100%; height:100%" src="images/gameboard/GoT.jpg" >-->
            </div> 
                           
 
            <div class="lobby" id="chooseHouse"style="z-index: 11;width: 60%; height: 90%;  margin: auto;
                position: absolute;  top: 0; left: 0; bottom: 0; right: 0; ">
                <h3 >Please Choose a House</h3>
                               <table>
  
                    <tr>
                        <td id='starkBanner' style='width: 33.3%; background: rgba(20, 20, 20, 0.7);'><img style='width: 45%;   ' src='images/banners/starkBanner.png' ></td>
                        <td id='greyjoyBanner' style='width: 33.3%; background: rgba(20, 20, 20, 0.7);'><img style='width: 45%;  ' src='images/banners/greyjoyBanner.png' ></td>
                        <td id='lannisterBanner'style='width: 33.3%; background: rgba(20, 20, 20, 0.7);'><img style='width: 45%;  ' src='images/banners/lannisterBanner.png' ></td>
                    </tr>
                    <tr>
                        <td id='baratheonBanner' style='width: 33.3%; background: rgba(20, 20, 20, 0.7);'><img style='width: 45%;  ' src='images/banners/baratheonBanner.png' ></td>
                        <td id='dothrakiBanner' style='width: 33.3%; background: rgba(20, 20, 20, 0.7);'><img style='width: 45%;  ' src='images/banners/dothrakiBanner.png' ></td>
                        <td id='targaryenBanner' style='width: 33.3%; background: rgba(20, 20, 20, 0.7);'><img style='width: 45%;  ' src='images/banners/targaryenBanner.png' ></td>
                    </tr>
                </table>;
            </div>


        <div class="footer" ></div>
        <script>

            $(document).ready(function()
            {
                insertHeader();
                insertFooter();
                insertNav();
                $("td").attr("isselected", "false");
                displayPickHouse();
                window.setInterval(checkHouseStatus, 1000); 
                
            });

        </script>
    </body>



</html>

