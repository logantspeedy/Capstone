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
         
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
        <script src="//cdn.pubnub.com/pubnub.min.js"></script>  
        
                <!--Scripts-->
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/javaScript.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/displayers.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/gameLogic.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/getters.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/layouts.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/sounds.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/setters.js"></script>
        
        
        <script src="https://apis.google.com/js/client:platform.js" async defer></script>
        
        <title>Game of Thrones - login</title>
        
    </head>

    <body onload="resize()" onresize="resize()" style="background:none;">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/fb.js"></script>  
<script type="text/javascript" src="${pageContext.request.contextPath}/js/gplus.js"></script>  

  
        
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

                <h1>Login</h1>

            </div>

            <hr/><br/>

            <div class="video" style="background:rgba(210, 202, 160, 0.7);-moz-border-radius: 15px;border-radius: 15px;padding:20px;display:table;text-align:center;">
<%
            if ((session.getAttribute("username") != null)){
                out.write("<br/><div class='row'>You are already logged in as <strong>" + (String) session.getAttribute("username") + "</strong>!</div>");
                out.write("<script>setTimeout(function () { window.location.href = 'lobby2.jsp';}, 1000);</script>");
            }
            else{
            %> 
            <div class="login-container" style="vertical-align:middle;display:table-cell;">
                <div class="row"><fb:login-button size="xlarge" scope="public_profile,email" onlogin="checkLoginState();">   Sign in    </fb:login-button>  </div><br/>
            <div class="row">OR</div><br/>
            
            
            <div class="row">    <span id="signinButton">
  <span
    class="g-signin"
    data-callback="signinCallback"
    data-clientid="948811159249-l26tlsg96j2ocjmssi981mihlckbu88p.apps.googleusercontent.com"
    data-cookiepolicy="single_host_origin"
    data-scope="https://www.googleapis.com/auth/userinfo.profile">
  </span>
</span>  </div><br/>
            <div class="row">OR enter username:</div><br/>
            <div class="row">
                <div class="col-md-2 col-md-offset-5">

                <div class="input-group" style="width:100%;margin-left: auto ; margin-right: auto ;">
                    <input type="text" class="form-control" id='username' name='username'/>
                    <span class="input-group-btn">
                        <button type="submit" class="btn btn-success" onclick="login(null)">Login</button>
                    </span>
                </div>
            
   
                </div>
            </div>
            </div> 
                        <%            
                }
            
            %>
               
            </div>
            

            <div class="footer" ><hr/><p>a Team4 Production 2014 Massey capstone@massey.ac.nz ©</div>
            
        </div>
        
        </div>
       
</body>
        

<script type="text/javascript" src="${pageContext.request.contextPath}/js/login.js"></script>  




</html>

