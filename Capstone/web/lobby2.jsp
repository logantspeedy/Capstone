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
        <link type="text/css" rel="stylesheet" href="js/hover/jquery.qtip.css" />
        <link rel="stylesheet" type="text/css" href="css/htmlBody.css">
        <link rel="stylesheet" type="text/css" href="css/index.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script> 
         
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
        <script src="//cdn.pubnub.com/pubnub.min.js"></script>  
        
        <script type="text/javascript" src="js/hover/jquery.qtip.js"></script>
        
        
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

            <div class="video" style="background:rgba(210, 202, 160, 0.7);-moz-border-radius: 15px;border-radius: 15px;padding:20px;display:table;text-align:center;">
            <%
            if ((session.getAttribute("username") == null)){
                out.write("Please login first!");
                out.write("<script>setTimeout(function () { window.location.href = 'login.jsp';}, 1000);</script>");                
                
            }
            else{
                
                %>
                

    <div class="alert alert-warning">
        <a href="#" class="close" data-dismiss="alert">&times;</a>
        <strong>You have been invited to a game!</strong> <br/><br/> <button type="button" id="invite-alert" class="btn btn-primary join">Join</button>
    </div>

                
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
                                    <div class="btn-group" data-toggle="buttons">
                                        <label class="btn btn-warning active">
                                            <input type="checkbox" id ="private-game" name="private-game" checked> Private
                                        </label>
                                    </div>
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
            
            <div id="ingame">false</div><div id="ingame-private">false</div>
            <div class="footer" ><hr/><p>a Team4 Production 2014 Massey capstone@massey.ac.nz ©</div>

        </div>
        
        </div>
        
</body>
        
<script>
            
    
    
    
    $(".alert-warning").hide();
            $("#ingame").hide();
            $("#ingame-private").hide();
            $(".invite").hide();
            $('.private-game').attr('checked', false);
            updateGameList();updatePlayerList();checkStartGame();applyToolTip();checkInGame();
            setInterval(function(){updateGameList();updatePlayerList();checkStartGame();checkInvites();checkInGame();}, 5000);
            function applyToolTip(){
            $('.list-group-item').qtip({
                style: { classes: 'qtip-bootstrap' },
                content: {
                    title: {
                        text: 'Game Information',
                        button: true
                        },

                    attr: 'titlee' // Tell qTip2 to look inside this attr for its content
                    },
                position: {

                    at: 'left bottom', // at the bottom right of...


                }    
                
            });
            }
            
            
            function inviteUser(sessionid){
                $.ajax({
                  type: "POST",
                  url: "MainServlet",
                  dataType : 'json',
                  data: {command: "invite", sessionid: sessionid}
                  }).done(function( data ) {
                      console.log(data);
                      

                  });                   
            }            

            function checkInvites(){
                $.ajax({
                  type: "POST",
                  url: "MainServlet",
                  dataType : 'json',
                  data: {command: "checkinvite"}
                  }).done(function( data ) {
                      if (data != "false"){
                          $("#invite-alert").attr("onclick", "joinGame('" + data + "')");
                          $(".alert-warning").show();
                        console.log(data);
                      }
                  });                   
            }    
    
            function checkStartGame(){
                $.ajax({
                  type: "POST",
                  url: "MainServlet",
                  dataType : 'json',
                  data: {command: "gamestart"}
                  }).done(function( data ) {
                      if (data === true){
                            window.location.href = 'chooseHouse.jsp';
                        }

                  });                   
            }    
            
            function checkInGame(){
                $.ajax({
                  type: "POST",
                  url: "MainServlet",
                  dataType : 'json',
                  data: {command: "checkingame"}
                  }).done(function( data ) {
                      if (data !== true){
                        
                        $(".join").show();
                        $(".invite").hide();
                        $("#ingame").html("false");
                        $("#ingame-private").html("false");
                        $("#create-game").show();

                      }
                      else{
                        if ($("#ingame-private").html() === "true"){
                            $(".invite").show();
                        }
                      }

                  });                   
            }

    
    
    
          function getPlayerList(sessionId){
            
            var playerListReturn=null;
            $.ajax({
                  type: "POST",
                  url: "MainServlet",
                  dataType : 'json',
                  data: {command: "getgames"},async: false
                  }).done(function( data ) {
                        var x;
                        for(x in data){ 	 
                            if (data[x][0] === sessionId){                              
                                var playerList = data[x][3].split(",");
                                playerListReturn = playerList;
                            }
                        }
                  });
                  
                  return playerListReturn;
              }            
            
            
          function startGame(sessionId){
            var playerList = getPlayerList(sessionId);
            if (playerList.length < 2){
                console.log("error, not enough players");
                return;
            }
            $.ajax({
                  type: "POST",
                  url: "MainServlet",
                  dataType : 'json',
                  data: {command: "startgame", players: playerList},
                  success: checkStartGame()
                  })
             
          }            
            
            
            
            function logout(){
                $.ajax({
                  type: "POST",
                  url: "MainServlet",
                  dataType : 'json',
                  data: {command: "logout"}
                  })
                setTimeout(function () { window.location.href = 'login.jsp';}, 2000);
              }
              
            function printUser(playerIndex,data){
                var sessionId = data[playerIndex][1];
                $.ajax({
                  type: "POST",
                  url: "MainServlet",
                  dataType : 'json',
                  data: {command: "checkuseringame", sessionid: sessionId}
                  }).done(function( ingame ) {
                          if (ingame === true){
                              $("#online-users").append('<li id="' + data[playerIndex][0] +'" class="list-group-item">' + data[playerIndex][0] + '&nbsp;</li>');
                          }
                          else{
                              $("#online-users").append('<li id="' + data[playerIndex][0] +'" class="list-group-item">' + data[playerIndex][0] + '&nbsp;<button type="submit" class="btn btn-warning invite" onclick="inviteUser(\'' + data[playerIndex][1] + '\')">Invite</button></li>');
                          }
                          checkInGame();


                  });                   
            }              
              
              
              
            function updatePlayerList(){
                $.ajax({
                  type: "POST",
                  url: "MainServlet",
                  dataType : 'json',
                  data: {command: "getusers"}
                  }).done(function( data ) { 
                      $("#online-users").html("");
                      for (var playerIndex in data){
                          printUser(playerIndex,data);

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
              
            function printGameData(x,data,sessionId){
                          var gameSessionId = data[x][0]; var gameName = data[x][1]; var createdBy = data[x][2]; var players = data[x][3]; var private = data[x][4];
                          
                          //if game is in progress
                          if(data[x][5]){
                              return;
                          }
                          var gameType;
                          if (private === "true"){
                              gameType = "Private";
                          }
                          else{
                              gameType = "Public";
                          }
                          
                          $.ajax({
                            type: "POST",
                            url: "MainServlet",
                            dataType : 'json',
                            data: {command: "usercheck",gamesessionid: gameSessionId},
                            async:false,
                            success: function(inGame) {
                                //console.log(data[x]);
                            //console.log("for game " + gameName + " the answer is " + inGame);
                            
                            if (inGame === true){
                            if (sessionId === gameSessionId){
                                $( "#avaliable-games" ).append('<li id="' + gameSessionId + '" class="list-group-item" style="padding:30px;" title="Created by: '+ createdBy + ' \n Players: ' + players + ' \n Game Type: ' + gameType + '">' + gameName + '<div style="display:inline;float:right;"><button type="submit" class="btn btn-primary start" onclick="startGame(\'' + gameSessionId + '\')">Start</button>&nbsp;<button type="submit" class="btn btn-danger leave" onclick="leaveGame()">Leave</button></div></li>');
                                if(private === "true"){
                                    $("#ingame-private").html("true");
                                }
                            }
                            else{
                                    $( "#avaliable-games" ).append('<li id="' + gameSessionId + '" class="list-group-item" style="padding:30px;" title="Created by: '+ createdBy + ' \n Players: ' + players + ' \n Game Type: ' + gameType + '">' + gameName + '<div style="display:inline;float:right;"><button type="submit" class="btn btn-danger leave" onclick="leaveGame()">Leave</button></div></li>');
                            }
                            $(".join").hide();
                            $("#create-game").hide();
                            $("#ingame").html("true");

                            }
                            else{  
                                if (private === "false"){
                                    $( "#avaliable-games" ).append('<li id="' + gameSessionId + '" class="list-group-item" style="padding:30px;" title="Created by: '+ createdBy + ' \n Players: ' + players + ' \n Game Type: ' + gameType + '">' + gameName + '<div style="display:inline;float:right;"><button type="submit" class="btn btn-primary join" onclick="joinGame(\'' + gameSessionId + '\')">Join</button></div></li>');
                                } 
  
                                
                            if ($("#ingame").html() === "true"){
                                  $(".join").hide();

                              }
                             
                              
                            }
                            applyToolTip();
                          
                            }
                      });                
            }
              
            function doUpdateGameList(sessionId){
                $.ajax({
                  type: "POST",
                  url: "MainServlet",
                  dataType : 'json',
                  async:false,
                  data: {command: "getgames"},
                  }).done(function( data ) { 
                      //console.log(data);
                      $("#avaliable-games").html("");
                      for(var x in data){
                          printGameData(x,data,sessionId);
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
                  data: {command: "creategame", username: username, gamename: gamename, private: $('#private-game').is(':checked')},
                  success: updateGameList()
                  });
          }
          
        function leaveGame(){
            console.log('leave game');
            $.ajax({
                  type: "POST",
                  url: "MainServlet",
                  dataType : 'json',
                  data: {command: "leavegame"},
                  success: leaveGameSuccess()
                  });
               
            }
        function leaveGameSuccess(){
            $(".join").show();
            $(".invite").hide();
            $("#ingame").html("false");
            $("#ingame-private").html("false");
            $("#create-game").show();
            updateGameList();
        }    
            
        function joinGame(sessionId){
            $(".alert-warning").hide();
            console.log("joining game");
            var username = $('#username').text();
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

