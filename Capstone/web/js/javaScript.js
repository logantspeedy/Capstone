/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

    function getPlayerFlag(player){
        var PlayersHouse = player.house(); 
        var returnFlag = null;
        if (PlayersHouse ==="Stark"){
            returnFlag ='images/houseFlags/Stark.png';}

        else if (PlayersHouse === "Lannister"){
            returnFlag ='images/houseFlags/Lannister.png';}

        else if (PlayersHouse === "Baratheon"){
            returnFlag ='images/houseFlags/Baratheon.png';}

        else if (PlayersHouse === "Greyjoy"){
            returnFlag ='images/houseFlags/Greyjoy.png';}

        else if (PlayersHouse === "Dothraki"){
            returnFlag ='images/houseFlags/Dothraki.png';}

        else if (PlayersHouse === "Targaryen"){
            returnFlag ='images/houseFlags/Targaryen.png';}


        else {return null;}       
    }

    function gameLogic(){
        
       if (gameJSON === null){
            startGame("Player 1", "Player 2");
         }
        
        else if (currentStage  === "setup"){
            //add show current player
            //display flags for each territory
            setupPhase();}
            
        else if (currentStage  === "game"){
            //add show current player
            //display flags for each territory
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
        //Display current phase somewhere
        fortifyFrom = null;
        fortifyTo = null;
        //display option to skip phase 
        
        var fromSelected = false;
        var toSelected = false;
        
        $("#Map").click(function(){
            if (!fromSelected){
                fromSelected = true;}
            
            $("#Map").click(function(){
                if (!toSelected){
                    toSelected = true;
                    //post(fortify functions)
                    
        }});
        });
        
    }
        
    function post(postData){
        $.ajax({
          type: "POST",
          url: "MainServlet",
          dataType : 'json',
          data: postData 
        ,
          success : function(data){
            window.location.href='GoT.jsp';
           }});
        
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
     


