<%-- 
    Document   : GoT
    Created on : 31/07/2014, 11:46:19 AM
    Author     : Zeb
--%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
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
                <area territory="Flints Finger" href="#" shape="poly" coords="114,230,180,231,179,263,117,263,109,231,180,231,179,263,117,263,180,231,179,263,180,231" />
                <area territory="The Twins" href="#" shape="poly" coords="190,242,251,242,250,275,190,275,189,240,251,242,250,275,190,275,251,242,250,275,251,242" />
                <area territory="The Three Sisters" href="#" shape="poly" coords="265,255,326,254,326,286,266,287,263,251,326,254,326,286,266,287,326,254,326,286,326,254" />
                <area territory="Pyke" href="#" shape="poly" coords="100,281,159,283,158,314,100,315,97,278,159,283,158,314,100,315,159,283,158,314,159,283" />
                <area territory="Riverlands" href="#" shape="poly" coords="218,302,277,302,278,332,218,333,215,301,277,302,278,332,218,333,277,302,278,332,277,302" />
                <area territory="The Eyrie" href="#" shape="poly" coords="293,314,353,314,353,344,293,344,292,312,353,314,353,344,293,344,353,314,353,344,353,314" />
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
        
        var gameJSON = <%=gameJSON%>;
        var currentPlayer = "<%=currentPlayer%>";
        var currentPhase = "<%=currentPhase%>";
        var currentStage = "<%=currentStage%>";
        
//        alert(gameJSON.board.nodes[0].territoy.toLocaleString());
//            alert(gameJSON.board.nodes[0].controllingPlayer.toLocaleString());

    //game image size var
    var z = $(window).width();

    //on page load set game board
    $(document).ready(function ()
        {
        gameLogic();
        });


    //update map to current board
    function gameLogic(){
        
       if (gameJSON === null){
            alert("Click ok to start a new game");
            startGame("Player 1", "Player 2");

         }
        
        else if (currentStage  === "setup"){
            setTerritoryOwner();
            setupPhase();

    }
        }
        
    var claim = "1";
    function setupPhase(){
        var chosenClaim = false;
        if (currentPhase === "claim"){
            
            $('.commentWindow').append('<p>'+currentPlayer+': pick avaliable territory</p>');

            $("#Map").click(function(){
                if (!chosenClaim){
                    $('.commentWindow').append('<p>'+currentPlayer+' chose '+ claim +' </p>');
                    chosenClaim = true;
                    callClaimTerritory(currentPlayer, claim);}
                    
            });
        }
        
        else {reinforcePhase();}
    
    }
    
    function game(){
        if (currentPhase === 'reinforcePhase'){
            reinforcePhase();
        }
        
        else if (currentPhase === 'attack'){
            attackPhase();
        }
        
        else if (currentPhase === 'fortify'){
            
        }
    }
    
    var reinforceArea = null;
    function reinforcePhase(){
        var reinforceChosen = false;
        $('.commentWindow').append('<p> Player '+currentPlayer+' :select territory to Reinforce </p>');
        
        $("#Map").click(function(){
            if (!reinforceChosen){
                $('.commentWindow').append('<p> Player '+currentPlayer+' chose '+ reinforceArea +' </p>');
                reinforceChosen = true;
                callReinforce(reinforceArea, 10);}
        });

        

    }
    var attacker = null;
    var defender = null;
    function attackPhase(){
        attacker = null;
        defender = null;
        
        $('.commentWindow').append('<p> Current Player: '+currentPlayer+': select territory to atttack with </p>');
        
        var attackerClicked = false;
        var defenderClicked = false;
        
        $("#Map").click(function(){
            if (!attackerClicked){
                $('.commentWindow').append('<p> Current Player: '+currentPlayer+': select territory to atttack </p>');
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
        
        $('.commentWindow').append('<p> Current Player: '+currentPlayer+': select territory to move units from  </p>');
        
        var fromSelected = false;
        var toSelected = false;
        
        $("#Map").click(function(){
            if (!fromSelected){
                $('.commentWindow').append('<p> Current Player: '+currentPlayer+': select territory to move units to </p>');
                fromSelected = true;}
            
            $("#Map").click(function(){
                if (!toSelected){
                    $('.commentWindow').append('<p>'+attacker+' is attacking '+ defender + '</p>');
                    toSelected = true;
                    callFortify(fortifyFrom,fortifyTo,1);}
                
            });

        });
        
        
    }

    function startGame(p1, p2){
        alert("Entered startGame");
        $.ajax({
          type: "POST",
          url: "MainServlet",
          data: { command: "startgame" , playername1:p1, playername2:p2 },
          success : function(data){
            window.location.href='GoT.jsp';
           }});}
   
    function callClaimTerritory(pla, ter){

        $.ajax({
          type: "POST",
          url: "MainServlet",
          dataType : 'json',
          data: { command: "claimterritory" , playername:pla, territory:ter },
          success : function(data){
            window.location.href='GoT.jsp';
           }});}

    function callReinforce(ter,tro){
        $.ajax({
          type: "POST",
          url: "MainServlet",
          dataType : 'json',
          data: { command: "reinforce" , territory:ter, troops:tro }
        ,
          success : function(data){
            window.location.href='GoT.jsp';
           }});}

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
           if(currentPhase === "claim"){
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
                 {   key: "Flints Finger",
                     selected: true,
                     toolTip: "Flints Finger, " + "Troops: " +
                             gameJSON.board.nodes[0].troops + ", Adjacent Territories: " +
                             gameJSON.board.nodes[0].adjacentNodes,
//         
                     altImage : picAltImg(gameJSON.board.nodes[0].controllingPlayer)},


                 {   key: "The Twins",
                     selected: true,
                     toolTip: "Flints Finger, " + "Troops: " +
                             gameJSON.board.nodes[1].troops + ", Adjacent Territories: " +
                             gameJSON.board.nodes[1].adjacentNodes,
//         
                     altImage : picAltImg(gameJSON.board.nodes[1].controllingPlayer)},

                 {   key: "The Three Sisters",
                     selected: true,
                    toolTip: "Flints Finger, " + "Troops: " +
                             gameJSON.board.nodes[2].troops + ", Adjacent Territories: " +
                             gameJSON.board.nodes[2].adjacentNodes,
//         
                     altImage : picAltImg(gameJSON.board.nodes[2].controllingPlayer)},

                 {   key: "Pyke",
                     selected: true,
                     toolTip: "Flints Finger, " + "Troops: " +
                             gameJSON.board.nodes[3].troops + ", Adjacent Territories: " +
                             gameJSON.board.nodes[3].adjacentNodes,
//         
                     altImage : picAltImg(gameJSON.board.nodes[3].controllingPlayer)},

                 {   key: "Riverlands",
                     selected: true,
                     toolTip: "Flints Finger, " + "Troops: " +
                             gameJSON.board.nodes[4].troops + ", Adjacent Territories: " +
                             gameJSON.board.nodes[4].adjacentNodes,
//         
                     altImage : picAltImg(gameJSON.board.nodes[4].controllingPlayer)},

                 {   key: "The Eyrie",
                     selected: true,
                     toolTip: "Flints Finger, " + "Troops: " +
                             gameJSON.board.nodes[5].troops + ", Adjacent Territories: " +
                             gameJSON.board.nodes[5].adjacentNodes,
//         
                     altImage : picAltImg(gameJSON.board.nodes[5].controllingPlayer)},
             ]
             });
//             $('#gameMap').mapster('resize', z, 0, 1000);
     }
     
    function zoom(inOut){
        z = z+inOut;
        setTerritoryOwner();
    }
    
    function picAltImg(player){
       
        
        if (player === "Player 1"){
            var altImg = 'images/GoT/GoTstarkSMALL.jpg';}
        else if (player === "Player 2"){
            var altImg = 'images/GoT/GoTLanesterSMALL.jpg';}
        else {var altImg = 'images/GoT/GoTblankSMALL.jpg';}
        
        return altImg;
    }
    
    function setBackground(){
        
        var bImg = null;
        if (currentPlayer === "Player 1"){bImg = "images/forest.jpg";}
        else {bImg = "images/KingsLanding.jpg";}
        $('.body').css({'background-image': 'url'+'('+bImg+')'});
        
    }
           


        
    </script>
</body>
    

</html>
