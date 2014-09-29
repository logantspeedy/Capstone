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
        
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
        

        <!--style sheet and meta data-->
        
        <link rel="stylesheet" type="text/css" href="css/htmlBody.css">
        <link rel="stylesheet" type="text/css" href="css/index.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script> 
         
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
        <script src="//cdn.pubnub.com/pubnub.min.js"></script>  
        <title>Game of Thrones - login</title>
        
    </head>
    <body>
    
    <body onload="resize()" onresize="resize()" style="background:none;">
        <div id="view">
        <div id="btl"><img src="images/border/btl.png" alt=""></div>
        <div id="btr"><img src="images/border/btr.png" alt=""></div>
        <div id="filler"></div>
        <div id="bbl"><img src="images/border/bbl.png" alt=""></div>
        <div id="bbr"><img src="images/border/bbr.png" alt=""></div>
        
        <div class="mainContainer">
        
            <div class="header"><img class="gameBanner" src="images/banners/gameBanner.png" alt="Game Of Thrones" style="width:80%;height:180%;"></div>

            <div class="login" style="padding:30px;height:10%;font-size: 2em;color:rgba(210, 202, 160, 1);font-family: CharleMagne;">

                    Login

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
            <div class="row"><a href="javascript:void(0)"><img style="width:234px;height:50px;" src="images/btnLoginFacebook.png"/></a></div><br/>
            <div class="row">OR</div><br/>
            
            
            <div class="row"><a href="javascript:void(0)"><img style="width:234px;height:44px;" src="images/sign-in-with-google.png"/></a></div><br/>
            <div class="row">OR enter username:</div><br/>
            <div class="row">
                <div class="col-md-2 col-md-offset-5">

                <div class="input-group" style="width:100%;margin-left: auto ; margin-right: auto ;">
                    <input type="text" class="form-control" id='username' name='username'/>
                    <span class="input-group-btn">
                        <button type="submit" class="btn btn-success" onclick="login()">Login</button>
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
        
        <script>
            function login(){
                if ($('#username').val() == ""){
                    return;
                }
                $.ajax({
                  type: "POST",
                  url: "MainServlet",
                  dataType : 'json',
                  data: {command: "login", username: $('#username').val()}
                  }).done(function( data ) {
                            alert("done");
                            setCookie("username", $('#username').val(),20);

                  }); 
                setTimeout(function () { window.location.href = 'lobby2.jsp';}, 500);
              }
        </script>





</html>

