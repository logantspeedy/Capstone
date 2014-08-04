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
        <h1><a href="http://fontmeme.com/game-of-thrones-font/"><img src="http://fontmeme.com/newcreate.php?text=Winter%20is%20Coming&name=Game of Thrones.ttf&size=40&style_color=2B2C38" alt="Game of Thrones Font"></a><p style="padding-left: 15px; font-size: 10px;"></p></h1>
        
        <div class="controls">
            <input onClick="setTerritoryOwner()" type="button" value="reset">
            <input onClick="zoom(200)" type="button" value="zoom in">
            <input onClick="zoom(-200)" type="button" value="zoom out">
        </div>
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
        
        
        <script>
        //game image size var
        var z = $(window).width();
        
        //on page load set game board
        $(document).ready(function ()
            {
            setTerritoryOwner();
            });


        //update map to current board
        function gameLogic(){
            if (board.currentPhase.phase ==="setup"){
                setupPhase();
            }
            else if (board.currentPhase.phase ==="reinforce"){
                reinforcePhase();
            }
        }
        
        function setupPhase(){
            $('.commentWindow').append('<p> Current Player: '+board.currentPlayer.player+'</p>');
            //display current player
     
        }
        
        function reinforcePhase(){
            $('.commentWindow').append('<p> Current Player: '+board.currentPlayer.player+': select territory to Reinforce </p>');
            
            //display current player
     
        }
        
        function callClaimTerritory(){
            $.ajax({
              type: "POST",
              url: "/MainServlet",
              data: { methodToInvoke: "pickTerr" , data: "terrioty, player" }
            }).done(function( msg ) {
              alert( "Data Saved: " + msg );
            });
        }
        
        function callReinforce(){
            $.ajax({
              type: "POST",
              url: "/MainServlet",
              data: { methodToInvoke: "reinforce" , data: "terrioty, units" }
            }).done(function( msg ) {
              alert( "Data Saved: " + msg );
            });
        }
        
        function callAttack(){   
            $.ajax({
              type: "POST",
              url: "/MainServlet",
              data: { methodToInvoke: "reinforce" , data: "ur terrioty, their ter, units" }
            }).done(function( msg ) {
              alert( "Data Saved: " + msg );
            });
        }
        
        function callFortify(){
            $.ajax({
              type: "POST",
              url: "/MainServlet",
              data: { methodToInvoke: "reinforce" , data: "terrioty, units" }
            }).done(function( msg ) {
              alert( "Data Saved: " + msg );
            });
        }
        
        function clickHandler(data) {
               
               if(board.currentPhase.phase ==="setup"){
               // do post to  Claim territory
               }
               
               else if (board.currentPhase.phase ==="reinforce"){
                   window.alert("Chose to reinforce "+ data.key);
                   //do post request to reinforce
                  
               }
               else if (board.currentPhase.phase ==="attack"){
                   //do post to attack
               }
               
               else if (board.currentPhase.phase ==="fortify"){
                   //do post to attack
               }
        }
        function setTerritoryOwner(){
               setBackground();
               gameLogic();
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
    
    var currentPlayer = null;
    var board = {
        "currentPlayer" : {player:1},
        "currentPhase" : {phase:"reinforce" }
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
