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
        
<!--            <input onClick="test()" type="button" value="switchPlayer">
            <input onClick="zoom(200)" type="button" value="zoom in">
            <input onClick="zoom(-200)" type="button" value="zoom out">-->
        </div>
        
        <div class="navigation"> <a href="http://localhost:8082/Capstone/GoT.jsp"><img src="http://fontmeme.com/newcreate.php?text=start%20new%20game&name=Game of Thrones.ttf&size=11&style_color=2B2C38" alt="Start New Game"></a></div>
        
        <div class="mainContainer">
        
            <div class="commentWindow">
                </div>
        <div class="gameWindow">
            <img id="gameMap"src="images/GoT/GoTblankSMALL.jpg" alt="" usemap="#Map" />


            <map name="Map" id="Map">
                <area id ="test" territory="Flints Finger" href="#" shape="poly" coords="114,230,180,231,179,263,117,263,109,231,180,231,179,263,117,263,180,231,179,263,180,231"/>
                <area territory="The Twins" href="#" shape="poly" coords="190,242,251,242,250,275,190,275,189,240,251,242,250,275,190,275,251,242,250,275,251,242" />
                <area territory="The Three Sisters" href="#" shape="poly" coords="265,255,326,254,326,286,266,287,263,251,326,254,326,286,266,287,326,254,326,286,326,254" />
                <area territory="Pyke" href="#" shape="poly" coords="100,281,159,283,158,314,100,315,97,278,159,283,158,314,100,315,159,283,158,314,159,283" />
                <area territory="Riverlands" href="#" shape="poly" coords="218,302,277,302,278,332,218,333,215,301,277,302,278,332,218,333,277,302,278,332,277,302" />
                <area territory="The Eyrie" href="#" shape="poly" coords="293,314,353,314,353,344,293,344,292,312,353,314,353,344,293,344,353,314,353,344,353,314" />
            </map>
        </div>
        

        <!--</div>-->
        </div>
        <div class="footer" >
            <p>Copyright © team4</p>
        </div>


        
    <script>
        function showCurrentPlayersHouse(){
            var currentPlayersHouse = null;    
            if (currentPlayer ==="Player 1"){
                currentPlayersHouse ='images/GoT/stark.jpg';
                
            }
            else if (currentPlayer === "Player 2"){
                currentPlayersHouse ='images/GoT/lannister.jpg';}
            else {return null;}
            
            return $('.commentWindow').append('<img style="width:100%" src ='+ currentPlayersHouse+'></img>');
            
        }
        
        
    <%
        String gameJSON = (String) request.getSession().getAttribute("game");
        String currentPlayer = (String) request.getSession().getAttribute("currentplayer");
        String currentPhase = (String) request.getSession().getAttribute("currentphase");
        String currentStage = (String) request.getSession().getAttribute("currentstage");
        String armyCount = (String) request.getSession().getAttribute("army");
    %>
        
        var gameJSON = <%=gameJSON%>;
        var currentPlayer = "<%=currentPlayer%>";
        var currentPhase = "<%=currentPhase%>";
        var currentStage = "<%=currentStage%>";
        var armyCount = "<%=armyCount%>";

        
//        alert(gameJSON.board.nodes[0].territoy.toLocaleString());
//            alert(gameJSON.board.nodes[0].controllingPlayer.toLocaleString());

    //game image size var
    var windowWidth = $(window).width();
    

    //on page load set game board
    $(document).ready(function ()
        {
        gameLogic();
        });


    //update map to current board
    function gameLogic(){
        
       if (gameJSON === null){
            startGame("Player 1", "Player 2");
         }
        
        else if (currentStage  === "setup"){
            showCurrentPlayersHouse();
            setTerritoryOwner();
            setupPhase();}
            
        else if (currentStage  === "game"){
            showCurrentPlayersHouse();
            setTerritoryOwner();
            gamePhase();}

    
        }
        
    var claim = null;
    function setupPhase(){
        var chosenClaim = false;
        if (currentPhase === "claim"){
            
            $('.commentWindow').append('<p>'+currentPlayer+': Pick avaliable territory.</p>');

            $("#Map").click(function(){
                if (!chosenClaim){
                    $('.commentWindow').append('<p>'+currentPlayer+' chose '+ claim +' </p>');
                    chosenClaim = true;
                    callClaimTerritory(currentPlayer, claim);}
                    
            });
        }
        
        else {reinforcePhase();}
    
    }
    
    function gamePhase(){
        if (currentPhase === 'reinforce'){
            reinforcePhase();
        }
        
        else if (currentPhase === 'attack'){
            attackPhase();
        }
        
        else if (currentPhase === 'fortify'){
            fortifyPhase();
        }
    }
    
    var reinforceArea = null;
    var armysDeployed = null;
    function reinforcePhase(){
        alert("Reinforce Phase");
        var reinforceChosen = false;
        $('.commentWindow').append('<p>'+currentPlayer+': Select Territory to Reinforce. You have '+armyCount+' troops left to place.</p>');
        $('.commentWindow').append('<button onclick="nextPhase()">End Phase</button>');
        
        $("#Map").click(function(){
            if (!reinforceChosen){
                reinforceChosen = true;
                callReinforce(reinforceArea, armysDeployed);}
        });

        

    }
    
    var attacker = null;
    var defender = null;
    function attackPhase(){
        alert("Attacking Phase!");
        attacker = null;
        defender = null;
        
        $('.commentWindow').append('<p> Current Player: '+currentPlayer+': select territory to atttack with </p>');
        $('.commentWindow').append('<button onclick="nextPhase()">End Phase</button>');
        
        var attackerClicked = false;
        var defenderClicked = false;
        
        $("#Map").click(function(){
            if (!attackerClicked){
                $('.commentWindow').append('<p> Current Player: '+currentPlayer+': select territory to atttack </p>');
                $('.commentWindow').append('<button onclick="nextPhase()">End Phase</button>');
                attackerClicked = true;}
            
            $("#Map").click(function(){
                if (!defenderClicked){
                    defenderClicked = true;
                    callAttack(attacker,defender);}
                
            });

        });

    }
    
    var fortifyFrom = null;
    var fortifyTo = null;
    var fortifyAmount = null;
    function fortifyPhase(){
        alert("Forty Phase");
        fortifyFrom = null;
        fortifyTo = null;
        
        $('.commentWindow').append('<p> Current Player: '+currentPlayer+': select territory to move units from  </p>');
        $('.commentWindow').append('<button onclick="nextPhase()">End Phase</button>');
        var fromSelected = false;
        var toSelected = false;
        
        $("#Map").click(function(){
            if (!fromSelected){
                $('.commentWindow').append('<p> Current Player: '+currentPlayer+': select territory to move units to </p>');
                $('.commentWindow').append('<button onclick="nextPhase()">End Phase</button>');
                fromSelected = true;}
            
            $("#Map").click(function(){
                if (!toSelected){
                    toSelected = true;
                    callFortify(fortifyFrom,fortifyTo,fortifyAmount);}
                
            });

        });
        
        
    }
    
    function nextPhase(){
        $.ajax({
          type: "POST",
          url: "MainServlet",
          data: { command: "nextphase"},
          success : function(data){
            window.location.href='GoT.jsp';
           }});}
    

    function startGame(p1, p2){
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
          url: "MainServlet",
          dataType : 'json',
          data: { command: "attack" , attackingterritory:ter, defendingterritory:def}
        ,
          success : function(data){
            window.location.href='GoT.jsp';
           }});}

    function callFortify(st, tt, tro){
        $.ajax({
          type: "POST",
          url: "MainServlet",
          dataType : 'json',
          data: { command: "fortify" , startterritory:st, targetterritory:tt, troops:tro }
        ,
          success : function(data){
            window.location.href='GoT.jsp';
           }});}

    function clickHandler(data) {
           if(currentPhase === "claim"){
            // add check that clicked area isn't owned
//            if (board[data.key] player ===null)
            
            claim = data.key;
           
           }

           else if (currentPhase ==="reinforce"){
               reinforceArea = data.key;
               armysDeployed=prompt('Enter number of troops','1');
               //do post request to reinforce

           }
           else if (currentPhase ==="attack"){
               if (attacker === null){attacker = data.key;}
               else {defender = data.key;}
           }

           else if (currentPhase ==="fortify"){
               if (fortifyFrom === null){fortifyFrom= data.key;}
               else {fortifyTo=data.key;
               fortifyAmount=prompt('Enter number of troops to move','1');}
               
           }
    }
    
    function setTerritoryOwner(){
//            setBackground();
           $('#gameMap').mapster({
             onClick: clickHandler,
             showToolTip: true,
             mapKey: 'territory',
             singleSelect : false,
             selected:true,
             toolTipContainer: '<div style="width:150px; height:100px; color:#BBBBBB"> </div>',
             altImage : 'images/GoT/GoTblankSMALL.jpg',
             areas:  [
                 {   key: "Flints Finger",
                     selected: true,
                     toolTip: "<h3>Flints Finger</h3>" + "Troops: " +
                             gameJSON.board.nodes[0].troops + "<p><b>Adjacent Territories:</b></p>" +
                             gameJSON.board.nodes[0].adjacentNodes,
//         
                     altImage : picAltImg(gameJSON.board.nodes[0].controllingPlayer)},


                 {   key: "The Twins",
                     selected: true,
                     toolTip: "<h3>The Twins</h3>" + "Troops: " +
                             gameJSON.board.nodes[1].troops + "<p><b>Adjacent Territories:</b></p>" +
                             gameJSON.board.nodes[1].adjacentNodes,
//         
                     altImage : picAltImg(gameJSON.board.nodes[1].controllingPlayer)},

                 {   key: "The Three Sisters",
                     selected: true,
                    toolTip: "<h3>The Three Sisters</h3>" + "Troops: " +
                             gameJSON.board.nodes[2].troops + "<p><b>Adjacent Territories:</b><p>" +
                             gameJSON.board.nodes[2].adjacentNodes,
//         
                     altImage : picAltImg(gameJSON.board.nodes[2].controllingPlayer)},

                 {   key: "Pyke",
                     selected: true,
                     toolTip: "<h3>Pyke</h3>" + "Troops: " +
                             gameJSON.board.nodes[3].troops + "<p><b>Adjacent Territories:</b></p>" +
                             gameJSON.board.nodes[3].adjacentNodes,
//         
                     altImage : picAltImg(gameJSON.board.nodes[3].controllingPlayer)},

                 {   key: "Riverlands",
                     selected: true,
                     toolTip: "<h3>Riverlands</h3>" + "Troops: " +
                             gameJSON.board.nodes[4].troops + "<p><b>Adjacent Territories:</b></p>" +
                             gameJSON.board.nodes[4].adjacentNodes,
//         
                     altImage : picAltImg(gameJSON.board.nodes[4].controllingPlayer)},

                 {   key: "The Eyrie",
                     selected: true,
                     toolTip: "<h3>The Eyrie</h3>" + "Troops: " +
                             gameJSON.board.nodes[5].troops + "<p><b>Adjacent Territories:</b></p>" +
                             gameJSON.board.nodes[5].adjacentNodes,
//         
                     altImage : picAltImg(gameJSON.board.nodes[5].controllingPlayer)},
             ]
             });
             $('#gameMap').mapster('resize', (windowWidth*0.7), 0, 0);
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
