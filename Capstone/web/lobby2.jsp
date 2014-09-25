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

                    Lobby

            </div>

            <hr/><br/>

            <div class="video" style="background:rgba(210, 202, 160, 0.7);-moz-border-radius: 15px;border-radius: 15px;padding:20px;display:table;text-align:center;height:100%;">
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
                out.write("<div class='alert alert-info'>Welcome <strong id='username'>" + (String) session.getAttribute("username") + "</strong>! <button type='submit' class='btn btn-danger' onclick='logout()'>Logout</button></div>");
            
            
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
                            <li id="test" class="list-group-item" style="padding:30px;">test game<div style="display:inline;float:right;"><button type="submit" class="btn btn-primary start">Start</button>&nbsp;<button type="submit" class="btn btn-danger leave">Leave</button></div></li>
                            <li id="test" class="list-group-item" style="padding:30px;">test game2<div style="display:inline;float:right;"><button type="submit" class="btn btn-primary join">Join</button></div></li>
                        </div>
                    </div>
                            
                            
                            

                    
                   
                    <div class="panel panel-default" id="create-game">
                        <div class="panel-heading">Create Game</div>
                        <ul class="list-group" id="create-output"></ul>
                        <div class="panel-body">
                            <form id="create">
                            <div class="input-group">
                                <input type="text" class="form-control" id="create-input" />
                                <span class="input-group-btn">
                                    <button type="button" class="btn btn-success create" onclick="createGame()">Create</button>
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
            

            <div class="footer" ><hr/><p>a Team4 Production 2014 Massey capstone@massey.ac.nz ©</div>
            
        </div>
        
        </div>
        
</body>
        
<script>

            updatePlayerList();updateGameList();
            setInterval(function(){updatePlayerList();updateGameList();}, 5000);
            var joinedGame = false;
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
                      var html = "";
                      for (var playerIndex in data){
                          html = html.concat('<li id="' + data[playerIndex] +'" class="list-group-item">' + data[playerIndex] + '</li>');
                      }
                      if (html !== $("#online-users").html()){
                          $( "#online-users" ).html(html);
                      }
                  }); 
              }  
        function updateGameList(){
                $.ajax({
                  type: "POST",
                  url: "MainServlet",
                  dataType : 'json',
                  data: {command: "getsessionidlobby"}
                  }).done(function( data ) { 
                      doUpdateGameList(data);
                  });
            }              
              
              
              
            function doUpdateGameList(sessionId){
                $.ajax({
                  type: "POST",
                  url: "MainServlet",
                  dataType : 'json',
                  data: {command: "getgames"}
                  }).done(function( data ) { 
                      var html = ""
                      var inGame = true;
                      var gameSessionId; var gameName; var createdBy; var players;
                      for(var x in data){
                          gameSessionId = data[x][0]; gameName = data[x][1]; createdBy = data[x][2]; players = data[x][3];
                          

                          
                          if (sessionId === gameSessionId){
                              html = html.concat('<li id="' + gameSessionId + '" class="list-group-item" style="padding:30px;">' + gameName + '<div style="display:inline;float:right;"><button type="submit" class="btn btn-primary start" onclick="startGame()">Start</button>&nbsp;<button type="submit" class="btn btn-danger leave" onclick="leaveGame()">Leave</button></div></li>');
                              //inGame = true;
                              //concat game creator list item
                              //set inGame = true
                          }
                          else{
                              
                              
                              html = html.concat('<li id="' + gameSessionId + '" class="list-group-item" style="padding:30px;">' + gameName + '<div style="display:inline;float:right;"><button type="submit" class="btn btn-primary join" onclick="joinGame(\'' + gameSessionId + '\')">Join</button></div></li>');
                              //do a normal join list item
                          }
                          //html = html.concat('<li id="' + data[playerIndex] +'" class="list-group-item">' + data[playerIndex] + '</li>');
                      }
                      //check if ingame
                      
                                                $.ajax({
                            type: "POST",
                            url: "MainServlet",
                            dataType : 'json',
                            data: {command: "usercheck",gamesessionid: gameSessionId},
                            success: function(data) {
                                if (data === true){
                                    //console.log("true");
                                    $(".join").hide();
                          $("#create-game").hide();
                                    inGame = true;
                            }
                                else{
                                    //console.log("false");
                                    $(".join").show();
                          $("#create-game").show();
                                    inGame = false;
                            } 
                            //console.log(inGame);  
                                

                      if (inGame === true){
                          $(".join").hide();
                          $("#create-game").hide();
                      }
                      else{
                          $(".join").show();
                          $("#create-game").show();
                      }
                            }
                            
                            });                      
                      //check if html has changed. if not then it's not worth updating
                      if (html != $("#avaliable-games").html()){
                          $( "#avaliable-games" ).html(html);
                      }
                  }); 
              }
              
              
          function createGame(){              
                var username = $('#username').text();
                var gamename = $('#create-input').val();
                if (gamename === "" || username === ""){
                    console.log("Error creating game");
                    return;
                }
                $.ajax({
                  type: "POST",
                  url: "MainServlet",
                  dataType : 'json',
                  data: {command: "creategame", username: username, gamename: gamename},
                  success: updateGameList()
                  }).done(function( data ) {
                      console.log(data);
                    //updateGameList();
                    

                  }); 
          }
          
        function leaveGame(){
            console.log('leave game');
            $.ajax({
                  type: "POST",
                  url: "MainServlet",
                  dataType : 'json',
                  data: {command: "leavegame"},
                  success: updateGameList()
                  })
            }          
        function joinGame(sessionId){
            console.log("joining game");
            var username = $('#username').text();
            console.log(sessionId);
            console.log(username);
            $.ajax({
                  type: "POST",
                  url: "MainServlet",
                  dataType : 'json',
                  data: {command: "joingame", username: username, gameid: sessionId},
                  success: updateGameList()
                  });
          }           
             
</script>





</html>

