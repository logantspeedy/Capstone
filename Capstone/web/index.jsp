<%-- 
    Document   : index
    Created on : Jul 23, 2014, 3:15:53 PM
    Author     : ltspeedy
--%>



<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    </head>
    <body>
        <h1 id="title">Welcome to the risk game!</h1>
        <%
        List<String> territories = Arrays.asList("Flints Finger", "The Twins", "The Three Sisters", "Pyke", "Riverlands", "The Eyrie");
        List<String> playerList = Arrays.asList("Player 1", "Player 2", "Player 3", "Player 4");
        %>

        
        <a href="javascript:void(0)" id="start-game">Start game!</a><br/>
        <div id="body">
            Current Player: <div id="player-name"></div><br/>
        <div id="setup">
            <h1>SETUP STAGE</h1>
            <div id="setup-claimterritory"> 
                <h3>Claim Territory</h3>
                <strong>Select Territory:</strong><br/>
                <% 
                    for (String territory : territories){
                        out.write(territory + ": <input type='radio' name='territory' class='setup-claimterritory-territory' value='" + territory + "'/><br/>");
                    }
                %>
                <br/><a href="javascript:void(0)" id="setup-claimterritory-link">Claim territory</a>              
            </div>
            
            <div id="setup-reinforce">
                <h3>Reinforce Territory</h3>
                 <strong>Select Territory:</strong><br/>
                <% 
                    for (String territory : territories){
                        out.write(territory + ": <input type='radio' name='territory' class='setup-reinforce-territory' value='" + territory + "'/><br/>");
                    }
                %>
                <br/><strong>Troops:</strong> <input type="number" name="troops" class="setup-reinforce-troops"/><br/>
                <br/><a href="javascript:void(0)" id="setup-reinforce-link">Reinforce</a>               
            </div>
            
        </div>

        <div id="game">
            <h1>GAME STAGE</h1>
            <div id="game-reinforce">
                <h3>Reinforce Territory</h3>
                 <strong>Select Territory:</strong><br/>
                <% 
                    for (String territory : territories){
                        out.write(territory + ": <input type='radio' name='territory' class='game-reinforce-territory' value='" + territory + "'/><br/>");
                    }
                %>
                <br/><strong>Troops:</strong> <input type="number" name="troops" class="game-reinforce-troops"/><br/>
                <br/><a href="javascript:void(0)" id="game-reinforce-link">Reinforce</a>   
             
            </div>

            <div id="game-attack">
                <h3>Attack Territory</h3>
                <strong>Select Attacking Territory:</strong><br/>
                <% 
                    for (String territory : territories){
                        out.write(territory + ": <input type='radio' name='attackingterritory' class='game-attack-attackingterritory' value='" + territory + "'/><br/>");
                    }
                %>                 

                <br/><strong>Select Defending Territory:</strong><br/>
                <% 
                    for (String territory : territories){
                        out.write(territory + ": <input type='radio' name='defendingterritory' class='game-attack-defendingterritory' value='" + territory + "'/><br/>");
                    }
                %>                

                <br/><a href="javascript:void(0)" id="game-attack-link">Attack!</a>               
            </div>            
            
            <div id="game-fortify">
                <h3>Fortify Territory</h3>
                <strong>Select Start Territory:</strong><br/>
                <% 
                    for (String territory : territories){
                        out.write(territory + ": <input type='radio' name='startterritory' class='game-fortify-startterritory' value='" + territory + "'/><br/>");
                    }
                %>                 

                <br/><strong>Select Target Territory:</strong><br/>
                <% 
                    for (String territory : territories){
                        out.write(territory + ": <input type='radio' name='targetterritory' class='game-fortify-targetterritory' value='" + territory + "'/><br/>");
                    }
                %>                
                <br/><strong>Troops:</strong> <input type="number" name="troops" class="game-fortify-troops"/><br/>    
                <br/><a href="javascript:void(0)" id="game-fortify-link">Fortify</a>               
            </div>            
            
            
        </div>        
        
        </div>
        
        
 
        
        
        <%

           //out.write("<br/>Enter commands above ^<br/><br/>Currently supported:<ul><li>newboard (command) : returns json of new board</li><li>reinforce (command, terriotory, troops): returns json of new board </li><li>attack (command, attacking territory, defending territory, aarmy, darmy) : returns json of new board </li><li>fortify (start territory, target territory, troops) : returns json of new board </li><li>end : returns nothing (changes the game object state to next turns)</li></ul>");
           request.getSession().removeAttribute("game");
           
           String test = (String) request.getSession().getAttribute("game");
           out.write(test);

         %>

                
         <div id="post-json-msg"></div>  
         <script>
var playerList = ["Player 1", "Player 2", "Player 3", "Player 4"];             
             
             
$("#player-name").text(playerList[0]);          


//var postParams ={command: "claimterritory", playername: "Player 1", territory:"Pyke"};
//request(postParams);

         var stage = "setup";
         
         $("#body").hide();   
         
         $("#start-game").click(function(){
             $("#start-game").hide();
            $("#body").show();
            $("#game").hide();
            $("#setup-reinforce").hide();
         });
         

         var playerNumber = 0;
         var selectNumber = 0;
             //SETUP STAGE
            $("#setup-claimterritory-link").click(function(){
                
                if (stage === "setup"){
                    var checkedVal = $("input[type='radio'][name='territory']:checked").val();
                    if (!checkedVal){alert("You did not select a value!"); return;}
                    $("input[type='radio'][name='territory']:checked").hide();
                    selectNumber +=1
                    request({command: "claimterritory", playername: $("#player-name").text(), territory:checkedVal});
                    alert('<% out.write((String) request.getSession().getAttribute("currentplayer")); out.write((String) request.getSession().getAttribute("currentphase")); out.write((String) request.getSession().getAttribute("currentstage")); %>');
                    playerNumber +=1;
                    if (playerNumber == playerList.length){playerNumber = 0;}
                    $("#player-name").html(playerList[playerNumber]);
                    
                    if (selectNumber == 6){
                        selectNumber = 0;
                        $("#setup-claimterritory").hide();
                        $("#setup-reinforce").show();
                    }
                }
            });    

            $("#setup-reinforce-link").click(function(){
                if (stage === "setup"){
                    var checkedVal = $("input[type='radio'][name='territory']:checked").val();
                    var troopCount = $('.setup-reinforce-troops').val();                  
                    if (!checkedVal || !troopCount){alert("You did not enter all the inputs!"); return;}
                    $('.setup-reinforce-troops').val('');
                    $("input[type='radio'][name='territory']:checked").hide();
                    
                    alert('<% out.write((String) request.getSession().getAttribute("currentplayer")); out.write((String) request.getSession().getAttribute("currentphase")); out.write((String) request.getSession().getAttribute("currentstage")); %>');             
                    selectNumber +=1;            
                    request({command: "reinforce", territory: checkedVal, troops: troopCount});                    
                    playerNumber +=1;

                    if (playerNumber == playerList.length){playerNumber = 0;}
                    $("#player-name").html(playerList[playerNumber]);

                
                    if (playerNumber == playerList.length){
                        //BEGIN MAIN STAGE
                        stage = "game"
                        playerNumber = 0;
                        $("#player-name").html(playerList[playerNumber]);
                        $("#setup").hide();
                        $("#game").show();
                        $("#game-attack").hide();
                        $("#game-fortify").hide()
                       }
                }
            });     
            

            $("#game-reinforce-link").click(function(){
                if (stage === "game"){
                    $("#game-reinforce").hide();
                    $("#game-attack").show();
                }
            });             
             
            $("#game-attack-link").click(function(){
                if (stage === "game"){
                    $("#game-attack").hide();
                    $("#game-fortify").show();
                }
            }); 
            
            $("#game-fortify-link").click(function(){
                if (stage === "game"){
                    playerNumber +=1;
                    $("#game-reinforce").show();
                    $("#game-fortify").hide();
                    if (playerNumber == playerList.length){
                        playerNumber = 0;
                    }
                    $("#player-name").html(playerList[playerNumber]);
                    
                    
                }
            }); 

    
    //{command: "claimterritory", playername: "Player 1", territory: "Pyke"}
    //{command: "reinforce", territory: "Pyke", troops: "5"}
    //{command: "attack", attackingterritory: "Pyke", defendingterritory: "Flints Fingers"}
    //{command: "fortify", startterritory: "Pyke", targetterritory: "Flints Fingers", troops: "5"}
    //{command: "newphase"}

    function request(parameters){    
        $.ajax({
              type: "POST",
              url: "MainServlet",
              dataType : 'json',
              data: parameters
              }).done(function( data ) {
                    displayData(data);
   
              });  

    }
    
    function displayData (json){
        $("#post-json-msg").html(console.log(json));
        
    }
 
    
    
         </script>
    </body>
</html>
