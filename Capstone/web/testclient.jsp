<%-- 
    Document   : testclient
    Created on : 13/08/2014, 10:38:58 PM
    Author     : Taylor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
    </head>
    <body>
        <h1>Lobby</h1>
        <strong>Username<a href='javascript:showUsernameInput()' id='change-user'> (Change username)</a>:</strong> <input type="text" id="username" name="username"/> 
        <span id="current-data">
            
            
            
        </span><br/>
        Current games:<br/>  
        <table border="1" width="100%">
            <thead>
                <th>Select</th>
                <th>Game Name</th>
                <th>Created by</th>
                <th>Currently players joined</th>
            </thead>
            <tbody id="gamelist"></tbody>
        </table>
        <button onclick="startGame()">Start Game</button>
        <a href='javascript:joinGame()' id='join'>Join game</a><br/>
        <a href='javascript:generateCreateForm()' id='gen-create'>Create game</a><br/>
        <a href='javascript:leaveGame()' id='leave'>Leave Game</a><br/>
        
        <div id="create-form">
            Game name: <input type="text" id="gamename" name="gamename"/><br/>
            <a href='javascript:createGame()' id='create'>Create</a><br/>
        </div>
        
        <script>
        $("#leave").hide();
        $("#create-form").hide();
        $("#change-user").hide();
        <%
        if (session.getAttribute("username") != null) {
            String username = (String) session.getAttribute("username");
            out.write("$('#username').hide(); $('#change-user').show();");
          
            if (session.getAttribute("gamename") != null) {
                String gamename = (String) session.getAttribute("gamename");
                out.write("$('#join').hide(); $('#gen-create').hide(); $('#leave').show(); $('#current-data').html('" + username + "<br/>Game name: " + gamename + "');");
            }
            else if (session.getAttribute("joinedgame") != null){
                String gameId = (String) session.getAttribute("joinedgame");
                out.write("$('#join').hide(); $('#gen-create').hide(); $('#leave').show(); $('#current-data').html('" + username + "<br/>Game name:' + $('#gamename-" + gameId + "').text());");
            }
            else{
                out.write("$('#current-data').html('" + username + "'); $('#username').val('" + username + "');");
            }
        }
        

        %>   
        
        function updateGameList(){
            console.log('update game list');
            $.ajax({
                  type: "POST",
                  url: "MainServlet",
                  dataType : 'json',
                  data: {command: "getgames"}
                  }).done(function( data ) {
                        $( "#gamelist" ).html("");
                        var x;
                        for(x in data){
                            console.log(data);
                            $( "#gamelist" ).append( "<tr><td><input type ='radio' class='join-select' name = 'join-select' value='" + data[x][0] + "'/></td><td id='gamename-" + data[x][0] + "'>" + data[x][1] + "</td><td> " + data[x][2] + "</td><td>" + data[x][3] + "</td></tr>" );
                            //alert(x);
                        }

                  });   
          }
          function getGames(sessionId){
            
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
//                                var lastItem = playerList.pop();
//                                var removedFullStop = lastItem.split(".");
//                                playerList.push(removedFullStop);
                                
//                                                                alert(playerList)
                                playerListReturn = playerList;
                            }
                        }

                  });
                  
                  return playerListReturn;
              }

//var playerList = getGames("1212312dfsdfds23");
          function startGame(){
            var checkedGameId = $("input[type='radio'][name='join-select']:checked").val();
            
            var players = getGames(checkedGameId.toString());
            
           
            
            
            players.push("filler");
            alert(players);
            
            $.ajax({
                  type: "POST",
                  url: "MainServlet",
                  dataType : 'json',
                  data: {command: "startgame", playername1: players[0], playername2: players[1]}
                  }).done(function( data ) {

                            


                  });  
             
          }
          
        function joinGame(){
            var checkedGameId = $("input[type='radio'][name='join-select']:checked").val();
            console.log('join game');
            console.log(checkedGameId);
            console.log($('#username').val());
            $("#current-data").html($('#username').val() + "<br/>Game name: " + $('#gamename-' + checkedGameId).text());
            $("#username").hide();
            $("#join").hide();
            $("#gen-create").hide();
            $("#leave").show();
            $("#change-user").show();
            $.ajax({
                  type: "POST",
                  url: "MainServlet",
                  dataType : 'json',
                  data: {command: "joingame", username: $('#username').val(), gameid: checkedGameId}
                  }).done(function( data ) {

                            updateGameList();


                  });  
             updateGameList();
          }
          
          function generateCreateForm(){
            if ($("#create-form").css('display') == 'none'){
                $("#create-form").show();
            }
            else{
            $("#create-form").hide();
            }
          }
          
          function showUsernameInput(){
            if ($("#username").css('display') == 'none'){
                $("#username").show();
            }
            else{
            $("#username").hide();
            }
          }          

          function createGame(){
            console.log('create game');
            console.log($('#username').val());
            console.log($('#gamename').val());
            $("#current-data").html($('#username').val() + "<br/>Game name: " + $('#gamename').val());
            $("#username").hide();
            $("#join").hide();
            $("#gen-create").hide();
            $("#create-form").hide();
            $("#leave").show();
            $("#change-user").show();
            $.ajax({
                  type: "POST",
                  url: "MainServlet",
                  dataType : 'json',
                  data: {command: "creategame", username: $('#username').val(), gamename: $('#gamename').val()}
                  }).done(function( data ) {

                            updateGameList();


                  }); 
             updateGameList();
          }    
    
        function leaveGame(){
            console.log('leave game');
            console.log($('#username').val());

            $("#current-data").html($('#username').val());
            $("#username").hide();
            $("#join").show();
            $("#gen-create").show();
            $("#leave").hide();
            $("#change-user").show();
            $.ajax({
                  type: "POST",
                  url: "MainServlet",
                  dataType : 'json',
                  data: {command: "leavegame"}
                  }).done(function( data ) {

                            updateGameList();


                  }); 
             updateGameList();
        }
        updateGameList();
        </script>

        
    </body>
</html>
