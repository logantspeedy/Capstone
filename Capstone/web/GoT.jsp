<%-- 
    Document   : GoT
    Created on : 31/07/2014, 11:46:19 AM
    Author     : Zeb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="gamecss.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>GoT Page</title>
        
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
        <script src="jquery.imagemapster.js"></script>
        
    </head>
    <body class="body">
       
        
        <div class="header">
             <h1><a href="http://fontmeme.com/game-of-thrones-font/"><img src="http://fontmeme.com/newcreate.php?text=Winter%20is%20Coming&name=Game of Thrones.ttf&size=40&style_color=2B2C38" alt="Game of Thrones Font"></a><p style="padding-left: 15px; font-size: 10px;"></p></h1>
        
            <input onClick="switchPlayer()" type="button" value="switchPlayer">
            <input onClick="zoom(200)" type="button" value="zoom in">
            <input onClick="zoom(-200)" type="button" value="zoom out">
        </div>
        
        <div class="mainContainer">
        <div class="commentWindow">
        </div>
        
        <div class="gameWindow">
            <img id="gameMap"src="images/GoT/GoTblankSMALL.jpg" alt="" usemap="#Map" />


            <map name="Map" id="Map">
                <area territory="FlintsFinger" href="#" shape="poly" coords="114,230,180,231,179,263,117,263,109,231,180,231,179,263,117,263,180,231,179,263,180,231" />
                <area territory="TheTwins" href="#" shape="poly" coords="190,242,251,242,250,275,190,275,189,240,251,242,250,275,190,275,251,242,250,275,251,242" />
                <area territory="TheThreeSisters" href="#" shape="poly" coords="265,255,326,254,326,286,266,287,263,251,326,254,326,286,266,287,326,254,326,286,326,254" />
                <area territory="Pyke" href="#" shape="poly" coords="100,281,159,283,158,314,100,315,97,278,159,283,158,314,100,315,159,283,158,314,159,283" />
                <area territory="Riverlands" href="#" shape="poly" coords="218,302,277,302,278,332,218,333,215,301,277,302,278,332,218,333,277,302,278,332,277,302" />
                <area territory="TheEyrie" href="#" shape="poly" coords="293,314,353,314,353,344,293,344,292,312,353,314,353,344,293,344,353,314,353,344,353,314" />
            </map>
        </div>
        
        <div class="chatBox">
            Player 1: Hey its your turn hurry up!!! <br>
            Player 2: Sorry was AFK :0
        </div>
        </div>
        
        <div id="footer" >
Copyright © team4</div>

</div>
        
    <script>
    <%
        String gameJSON = (String) request.getSession().getAttribute("game");
        String currentPlayer = (String) request.getSession().getAttribute("currentplayer");
        String currentPhase = (String) request.getSession().getAttribute("currentphase");
        String currentStage = (String) request.getSession().getAttribute("currentstage");
    %>
    var board; 
    $.getJSON('http://localhost:8084/MainServlet', function(jsonAr){
             board = jsonAr;});
     
    //game image size var
    var z = $(window).width();

    //on page load set game board
    $(document).ready(function ()
        {
        gameLogic();
        });


    //update map to current board
    function gameLogic(){
        setTerritoryOwner();
        if (currentStage ==="setup"){
            setupPhase();
        }
        else if (currentStage === "game"){
            game();

    }
    var claim = null;
    function setupPhase(){
        var chosenClaim = false;
        if (currentPhase === claim){
            $('.commentWindow').append('<p>Player '+board.currentPlayer.player+': pick avaliable territory</p>');

            $("#Map").click(function(){
                if (!chosenClaim){
                    $('.commentWindow').append('<p> Player '+board.currentPlayer.player+' chose '+ claim +' </p>');
                    chosenClaim = true;
                    callClaimTerritory(board.currentPlayer.player, claim);}
            });
        };
        
        else (reinforcePhase();)
    
    }
    
    function game(){
        if (currentPhase === 'reinforcePhase'){
            reinforcePhase();
        };
        
        else if (currentPhase === 'attack'){
            attackPhase();
        };
        
        else if (currentPhase === 'fortify'){
            
        };
    }
    
    var reinforceArea = null;
    function reinforcePhase(){
        var reinforceChosen = false;
        $('.commentWindow').append('<p> Player '+board.currentPlayer.player+' :select territory to Reinforce </p>');
        
        $("#Map").click(function(){
            if (!reinforceChosen){
                $('.commentWindow').append('<p> Player '+board.currentPlayer.player+' chose '+ reinforceArea +' </p>');
                reinforceChosen = true;
                callReinforce(reinforceArea, 1);}
        });

        

    }
    var attacker = null;
    var defender = null;
    function attackPhase(){
        attacker = null;
        defender = null;
        
        $('.commentWindow').append('<p> Current Player: '+board.currentPlayer.player+': select territory to atttack with </p>');
        
        var attackerClicked = false;
        var defenderClicked = false;
        
        $("#Map").click(function(){
            if (!attackerClicked){
                $('.commentWindow').append('<p> Current Player: '+board.currentPlayer.player+': select territory to atttack </p>');
                attackerClicked = true;}
            
            $("#Map").click(function(){
                if (!defenderClicked){
                    $('.commentWindow').append('<p>'+attacker+' is attacking '+ defender + '</p>');
                    defenderClicked = true;
                    callAttack(attacker,defender);}
                
            });

        });

    }
    
    var fortifyFrom = null;
    var fortifyTo = null;
    function fortifyPhase(){
        
        fortifyFrom = null;
        fortifyTo = null;
        
        $('.commentWindow').append('<p> Current Player: '+board.currentPlayer.player+': select territory to move units from  </p>');
        
        var fromSelected = false;
        var toSelected = false;
        
        $("#Map").click(function(){
            if (!fromSelected){
                $('.commentWindow').append('<p> Current Player: '+board.currentPlayer.player+': select territory to move units to </p>');
                fromSelected = true;}
            
            $("#Map").click(function(){
                if (!toSelected){
                    $('.commentWindow').append('<p>'+attacker+' is attacking '+ defender + '</p>');
                    toSelected = true;
                    callFortify(fortifyFrom,fortifyTo,1);}
                
            });

        });
        
        
    }

    function callClaimTerritory(pla, ter){
        $.ajax({
          type: "POST",
          url: "/MainServlet",
          data: { command: "claim" , playername:pla, territory:ter }
        }).done(function( msg ) {
          alert( "Data Saved: " + msg );
        });
    }

    function callReinforce(ter,tro){
        $.ajax({
          type: "POST",
          url: "/MainServlet",
          data: { command: "reinforce" , territory:ter, troops:tro }
        }).done(function( msg ) {
          alert( "Data Saved: " + msg );
        });
    }

    function callAttack(ter, def){   
        $.ajax({
          type: "POST",
          url: "/MainServlet",
          data: { command: "attack" , attackingterritory:ter, defendingterritory:def}
        }).done(function( msg ) {
          alert( "Data Saved: " + msg );
        });
    }

    function callFortify(st, tt, tro){
        $.ajax({
          type: "POST",
          url: "/MainServlet",
          data: { methodToInvoke: "reinforce" , startterritory:st, targetterritoy:tt, troops:tro }
        }).done(function( msg ) {
          alert( "Data Saved: " + msg );
        });
    }

    function clickHandler(data) {

           if(currentPhase ==="claim"){
            // add check that clicked area isn't owned
//            if (board[data.key] player ===null)
            claim = data.key;
           
           }

           else if (currentPhase ==="reinforce"){
               reinforceArea = data.key;
               //do post request to reinforce

           }
           else if (currentPhase ==="attack"){
               if (attacker === null){attacker = data.key;}
               else {defender = data.key;}
           }

           else if (currentPhase ==="fortify"){
               fortifyArea = data.key;
           }
    }
    
    function setTerritoryOwner(){
            setBackground();
           $('#gameMap').mapster({
             onClick: clickHandler,
             showToolTip: true,
             mapKey: 'territory',
             singleSelect : false,
             selected:true,
             altImage : 'images/GoT/GoTblankSMALL.jpg',
             areas:  [
                 {   key: "FlintsFinger",
                     selected: true,
                     toolTip: "Flints Finger, " + "Troops: " +
                             zones.FlintsFinger.troops + ", Adjacent Territories: " +
                             zones.FlintsFinger.adjacentNodes,
//         
                     altImage : picAltImg(zones.FlintsFinger)} ,


                 {   key: "TheTwins",
                     selected: true,
                     toolTip: "The Twins, " + "Troops: " +
                             zones.TheTwins.troops + ", Adjacent Territories: " +
                             zones.TheTwins.adjacentNodes,
                     altImage : picAltImg(zones.TheTwins)},

                 {   key: "TheThreeSisters",
                     selected: true,
                     toolTip: "The Three Sisters, " + "Troops: " +
                             zones.TheThreeSisters.troops + ", Adjacent Territories: " +
                             zones.TheThreeSisters.adjacentNodes,
                     altImage : picAltImg(zones.TheThreeSisters)},

                 {   key: "Pyke",
                     selected: true,
                     toolTip: "Pyke, " + "Troops: " +
                             zones.Pyke.troops + ", Adjacent Territories: " +
                             zones.Pyke.adjacentNodes,
                     altImage : picAltImg(zones.Pyke)},

                 {   key: "Riverlands",
                     selected: true,
                     toolTip: "Riverlands, " + "Troops: " +
                             zones.Riverlands.troops + ", Adjacent Territories: " +
                             zones.Riverlands.adjacentNodes,
                     altImage : picAltImg(zones.Riverlands)},

                 {   key: "TheEyrie",
                     selected: true,
                     toolTip: "The Eyrie, " + "Troops: " +
                             zones.TheEyrie.troops + ", Adjacent Territories: " +
                             zones.TheEyrie.adjacentNodes,
                     altImage : picAltImg(zones.TheEyrie)} ]
             });
             $('#gameMap').mapster('resize', z, 0, 1000);
     };
     
    function zoom(inOut){
        z = z+inOut;
        setTerritoryOwner();
    }
    
    function picAltImg(ter){
        if (ter.player === 1){
        var altImg = 'images/GoT/GoTstarkSMALL.jpg';}
        else {var altImg = 'images/GoT/GoTLanesterSMALL.jpg';}
        return altImg;
    }
    
    function setBackground(){
        setCurrentPlayer();
        var bImg = null;
        if (currentPlayer ===1){bImg = "images/forest.jpg";}
        else {bImg = "images/KingsLanding.jpg";}
        $('.body').css({'background-image': 'url'+'('+bImg+')'});
        
    }
           
    function getCurrentPlayer(){
        return board.currentPlayer.player;
    }
    
    function setCurrentPlayer(){
        currentPlayer = board.currentPlayer.player;
    }
    function switchPlayer(){
        if (getCurrentPlayer()===1){currentPlayer =2;}
        else {currentPlayer =1;}
        gameLogic();
    }
    
    
         
    var currentPlayer = null;
    var board = {
        "currentPlayer" : {player:1},
        "currentPhase" : {phase:"setup" }
    };
    
    var zones ={
    "FlintsFinger" : {territory:"FlintsFinger",player:1,continent:"The North",troops:10,
        adjacentNodes:["The Twins","Pyke"]},
    "TheTwins" : {territory:"TheTwins",player:1,continent:"The North",troops:10,
        adjacentNodes:["Flints Finger","The Three Sisters","Riverlands"]},
    "TheThreeSisters" : {territory:"TheThreeSisters",player:1,continent:"The North",troops:10,
        adjacentNodes:["The Twins","Riverlands","TheEyrie"]},
    "Pyke" : {territory:"Pyke",player:2,continent:"The North",troops:10,
        adjacentNodes:["FlintsFinger"]},
    "Riverlands" : {territory:"Riverlands",player:2,continent:"The North",troops:10,
        adjacentNodes:["The Twins","The Three Sisters","TheEyrie"]},
    "TheEyrie" : {territory:"TheEyrie",player:2,continent:"The North",troops:10,
        adjacentNodes:["Riverlands","The Three Sisters"]}
    };
        
    </script>
</body>
    

</html>
