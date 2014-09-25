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
        <link rel="stylesheet" type="text/css" href="testGamecss.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Game of Thrones</title>

        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>


        <style>
        body a:link {color: #573d1c;}
        
        </style>
        
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script> 
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" rel="stylesheet">
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
        <script src="//cdn.pubnub.com/pubnub.min.js"></script>        
        
        
    </head>
    <body>
        <!--<button onclick="getCurrentPhase()">getGameData</button>-->
        <div class="header" style="z-index: 15"></div>
        
        <div class="navigation" id="nav" ></div>
        
        <div class ="mainContainer">
            
                <div id="backGround" style='z-index: 1; height:100% '>
                    <img id="GoTimg" style="width: 100%; height:100%" src="images/gameboard/GoT.jpg" >
                </div>
            
            <div class="lobby" style="z-index: 2;width: 95%; height: 90%;  margin: auto;
            position: absolute;  top: 0; left: 0; bottom: 0; right: 0; background: rgba(255, 255, 255, 0.7);">
            
            <h1>Login</h1>
            
                
            <%
            if ((session.getAttribute("username") != null)){
                out.write("<br/><div class='row'>You are already logged in as <strong>" + (String) session.getAttribute("username") + "</strong>!</div>");
                out.write("<script>setTimeout(function () { window.location.href = 'lobby2.jsp';}, 1000);</script>");
            }
            else{
            %> 
            <div class="login-container">
            <div class="row"><a href="javascript:void(0)"><img width="234" height="50" src="images/btnLoginFacebook.png"/></a></div><br/>
            <div class="row">OR</div><br/>
            
            
            <div class="row"><a href="javascript:void(0)"><img width="234" height="44" src="images/sign-in-with-google.png"/></a></div><br/>
            <div class="row">OR enter username:</div><br/>
            <div class="row">
                <div class="col-md-2 col-md-offset-5">

                <div class="input-group">
                    <input type="text" class="form-control" id='username' name='username'/>
                    <span class="input-group-btn">
                        <button type="submit" class="btn btn-default" onclick="login()">Login</button>
                    </span>
                </div>
            
   
                </div>
            </div>
            </div> 
                        <%            
                }
            
            %>
               
            </div>
        </div>

           

        

        <div class="footer" ></div>
        
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


                  }); 
                setTimeout(function () { window.location.href = 'lobby2.jsp';}, 500);
              }
        </script>

    </body>



</html>

