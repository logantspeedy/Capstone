<%-- 
    Document   : Lobby
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
            
            <h1>Lobby</h1><br/>
            
            
            <%
            if ((session.getAttribute("username") == null)){
                out.write("Please login first!");
                out.write("<script>setTimeout(function () { window.location.href = 'login.jsp';}, 1000);</script>");                
                
            }
            else{
                
                %>
                <div style="position:absolute;width:95%;left:2%;">
                <div class="row">
                <div class="col-md-6">
                <%
                out.write("<div class='alert alert-info'>Welcome <strong id='whoami'>" + (String) session.getAttribute("username") + "</strong>! <button type='submit' class='btn btn-default' onclick='logout()'>Logout</button></div>");
            
            
            %>  
                    <div class="panel panel-default">
                        <div class="panel-heading">Online Users</div>
                        <div class="list-group" id="online-users"></div>
                    </div>
                </div>
            
                <div class="col-md-6">
                


                    <div class="panel panel-default">
                        <div class="panel-heading">Avaliable Games</div>
                        <div class="list-group" id="avaliable-games" style="text-align:left">
                            <li id="test" class="list-group-item" >test game<span class="input-group-btn" style="text-align:right;display: inline;"><button type="submit" class="btn btn-default">Create</button><button type="submit" class="btn btn-default">Create</button></span></li>
                            <li id="test" class="list-group-item">test game2</li>
                        </div>
                    </div>
                            
                            
                            

                    
                   
                    <div class="panel panel-default">
                        <div class="panel-heading">Create Game</div>
                        <ul class="list-group" id="chat-output"></ul>
                        <div class="panel-body">
                            <form id="chat">
                            <div class="input-group">
                                <input type="text" class="form-control" id="chat-input" />
                                <span class="input-group-btn">
                                    <button type="submit" class="btn btn-default">Create</button>
                                </span>
                            </div>
                            </form>
                        </div>
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
            updatePlayerList();
            setInterval(function(){updatePlayerList()}, 5000);
 
            function logout(){
                $.ajax({
                  type: "POST",
                  url: "MainServlet",
                  dataType : 'json',
                  data: {command: "logout"}
                  }).done(function( data ) {
                            alert("done");


                  }); 
                setTimeout(function () { window.location.href = 'login.jsp';}, 2000);
              }
            function updatePlayerList(){
                $.ajax({
                  type: "POST",
                  url: "MainServlet",
                  dataType : 'json',
                  data: {command: "getusers"}
                  }).done(function( data ) { 
                      var html = ""
                      for (var playerIndex in data){
                          html = html.concat('<li id="' + data[playerIndex] +'" class="list-group-item">' + data[playerIndex] + '</li>');
                      }
                      if (html != $("#online-users").html()){
                          $( "#online-users" ).html(html);
                      }
                  }); 
              }              
            function updateGameList(){
                $.ajax({
                  type: "POST",
                  url: "MainServlet",
                  dataType : 'json',
                  data: {command: "getusers"}
                  }).done(function( data ) { 
                      var html = ""
                      for (var playerIndex in data){
                          html = html.concat('<li id="' + data[playerIndex] +'" class="list-group-item">' + data[playerIndex] + '</li>');
                      }
                      if (html != $("#online-users").html()){
                          $( "#online-users" ).html(html);
                      }
                  }); 
              }              
              
        </script>

    </body>



</html>