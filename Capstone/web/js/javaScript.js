/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function test(i){alert(i);}
// =============
function follow(){
    console.log("in follow");
    $("#follower").hide();
    $("path").mouseover(mouseoverHandler);
    $("path").mouseout(mouseoutHandler);
}
function mouseoverHandler() {
    var pathMouseover = null;
    if (!$(this).attr('class')) {
        pathMouseover = this.id;
        console.log(this.id);
    }
    else {
        pathMouseover = this.className.baseVal;
        console.log(this.className.baseVal);
    }
    console.log(pathMouseover);
    $("#follower").css({"position":"absolute",
                      "margin-left":"-100px",
		      "width":"100px",
		      "height":"40px",
		      "line-height":"10px",
		      "padding":"10px",
		      "font-size":"14px",
		      "text-align":"center",
		      "background":"#d2caa0",
		      "border":"2px solid #424242",
                      "font-family":"Cordia New",
                      "color": "#black",
		      "border-radius":"5px",
		      "text-shadow":"rgba(0, 0, 0, 0.0980392) 1px 1px 1px",
		      "box-shadow":"rgba(0, 0, 0, 0.0980392) 1px 1px 2px 0px"});
    $("#hoverTerritory").empty().append("<strong>"+pathMouseover+"</strong></br>");

    $("#hoverUnits").empty().append("Units : "+ getTerritotyTroopCount(pathMouseover));
//     + nodes[pathMouseover].troops
    $("#follower").stop(true, true);
    console.log("over");
    $("#follower").fadeIn("fast");
}
function getTerritotyTroopCount(id){
        var unitCount = null;
        for (i = 0; i < nodes.length; i++){
        if (nodes[i].territoy === id){
            unitCount=nodes[i].troops;
            return unitCount;
        }
        
    }
}
function mouseoutHandler(){
    $("#follower").fadeOut("fast");
}

    // Variables
    var gameJSON = null;
    var currentPhase = null;
    var currentPlayer = null;
    var currentPlayerHouse = null;
    var currentPlayerTroops = null;
    var nodes = null;
    var player = getCookie();
    var house = post({command:"getplayershouse", player:player}).toLowerCase();    
    
    function getGameJSON(){
        gameJSON = post({command:"getgamedata"});
        currentPhase = gameJSON.currentPhase.toString();
        currentPlayer = gameJSON.currentPlayer.name.toString();
        currentPlayerHouse = gameJSON.currentPlayer.house.toString();
        currentPlayerTroops = gameJSON.currentPlayer.army.toString();
        nodes = gameJSON.board.nodes;
//        console.log(nodes);
//      currentPlayerTroops = gameJSON.currentPlayer.troops.toString();
    }
    function insertFlag(idName) {
        var bbox = $("#" + idName)[0].getBBox();
        var xAxis = bbox.x;
        var yAxis = bbox.y;
        var width = bbox.width;
        var height = bbox.height;
        var fWidth = 65;
        var fHeight = 70;
        var divWidth = $("#GoTimg").width();
        var divHeight = $("#GoTimg").height();
        var viewBoxHeight =   1334;
        var viewBoxWidth = 2048;
        var ratioWidth = divWidth/viewBoxWidth;
        var ratioHeight = divHeight/viewBoxHeight;
        var setX = (xAxis + (width / 2) - (fWidth / 2))*ratioWidth;
        var setY = (yAxis + (height / 2) - (fHeight / 2))*ratioHeight;


        //var foreignObjectTag = document.createElement('foreignObject');
//                var foreignObjectTag = $('<foreignObject/>');
        // var divTag = document.createElement('div');
//                var divTag = $('<div/>');
        //var imgTag = document.createElement('img');
        var imgTag = $('<img/>');
        imgTag.css("left", setX)
            .css("top", setY)
            .css("position", "relative")
            .css("z-index", 2)
            .attr("src", "images/houseFlags/Stark.png")
            .attr("height", fHeight*ratioHeight)
            .attr("width", fWidth*ratioWidth)
            .attr("id", "img" + idName);
        imgTag.prependTo("#gameWindow");}

    function insertHeader(){
        $('.header').append('<img src="images/banners/gameBanner.png" alt="Game Of Thrones"  style="height: 112%; width:80%; z-index: 2" >');
    }    
    
    function insertFooter(){
        $('.footer').append('<p><hr style="color: #d2caa0">a Team4 Production 2014 Massey capstone@massey.zc.nz ©</p>');
    }

    function insertNav(){
        $('#nav').append(   
            '<table >'+
               ' <tr>'+
                  '<th style="padding-top: 6%" ><a href="home">home</a></th>'+
                  '<th></th><th></th>'+
                  '<th style="padding-top: 6%"  ><a href="about">about</a></th>'+
                  '<th></th><th></th>'+
                  '<th style="padding-top: 6%" ><a href="contact">contact</a></th>'+
                  '<th></th><th></th>'+
                  '<th style="padding-top: 6%" >House Moto Here</th>'+
                  '<th></th><th></th>'+
                  '<th style="padding-top: 6%" ><a href="">leave game</a></th>'+
                  '<th></th><th></th>'+
                  '<th style="padding-top: 6%" ><a href="test">signout</a></th>'+
                '</tr>'+
              '</table>');
    }
    
    function insertInfoTable(){
        
        $('#playersBanner').attr("src","images/banners/"+house+"Banner.png");
        $('#phase').empty().append("<h3>PHASE</h3>"+"<hr>"+currentPhase);
        $('#currentPlayer').empty().append("<h3>CURRENT PLAYER</h3>"+"<hr>"+currentPlayer);
        $('#bonuses').empty().append("<h3>Bonuses</h3>"+"<hr>"+currentPlayerHouse+"</br>"+" Free Troops:" +currentPlayerTroops);
    }

    function setFlags(){
        for (i = 0; i < nodes.length; i++) {
            var ter = nodes[i].territoy.toString();
            var controller = nodes[i].controllingPlayer.toString();
            if (controller.replace(/ /g,'') === ""){
            }
            else{
            $('#img'+ter.replace(/ /g,'')).attr("src","images/houseFlags/"+(getPlayersHouse(controller)).replace(/ /g,'')+".png");}
    }
}

    function getSessionId(){
        var response = post({command:"getsessionid"});
        return response;
    }
    
    function getPlayersHouse(play){
        var response = post({command:"getplayershouse", player:play});
        return response;
    }
    
    function setCookie(cname, cvalue, exdays) {
        var d = new Date();
        d.setTime(d.getTime() + (exdays*24*60*60*1000));
        var expires = "expires="+d.toUTCString();
        document.cookie = cname + "=" + cvalue + "; " + expires;
} 
    
    function getCookie(cname) {
        var ca = document.cookie.split(';');

        var username = ca[0].split("=");

        return username[1];
    }
    
    function displayPickHouse(){
        //code for when mosue over make images bigger
        //        /* Teaser image swap function */
        //    $('img.swap').hover(function () {
        //        this.src = '/images/signup_big_hover.png';
        //    }, function () {
        //        this.src = '/images/signup_big.png';
        //    });
        var html="<table>"+
                    "<tr>"+
                        "<td style='width: 33.3% ;'></td>"+
                        "<td style='width: 33.3%; color: #573d1c;'><h3 >Please Choose a House</h3></td>"+
                        "<td style='width: 33.3%'></td>"+
                    "</tr>"+
                    "<tr>"+
                        "<td id='starkBanner' style='width: 33.3%; background: rgba(20, 20, 20, 0.7);'><img style='width: 45%;   ' src='images/banners/starkBanner.png' ></td>"+
                        "<td id='greyjoyBanner' style='width: 33.3%; background: rgba(20, 20, 20, 0.7);'><img style='width: 45%;  ' src='images/banners/greyjoyBanner.png' ></td>"+
                        "<td id='lannisterBanner'style='width: 33.3%; background: rgba(20, 20, 20, 0.7);'><img style='width: 45%;  ' src='images/banners/lannisterBanner.png' ></td>"+
                    "</tr>"+
                    "<tr>"+
                        "<td id='baratheonBanner' style='width: 33.3%; background: rgba(20, 20, 20, 0.7);'><img style='width: 45%;  ' src='images/banners/baratheonBanner.png' ></td>"+
                        "<td id='dothrakiBanner' style='width: 33.3%; background: rgba(20, 20, 20, 0.7);'><img style='width: 45%;  ' src='images/banners/dothrakiBanner.png' ></td>"+
                        "<td id='targaryenBanner' style='width: 33.3%; background: rgba(20, 20, 20, 0.7);'><img style='width: 45%;  ' src='images/banners/targaryenBanner.png' ></td>"+
                    "</tr>"+
                "</table>";
        document.getElementById('chooseHouse').innerHTML += html;
//        element1 = document.getElementById("starkBanner");
//        element1.addEventListener("click", post({command:"sethouse" ,house:"Stark"}), false);
        
        $("#starkBanner").click(function() {
        post({command:"sethouse" , player:getCookie(),house:"Stark"});
        window.location="GoT.jsp";});
        $("#greyjoyBanner").click(function() {
        post({command:"sethouse" ,player:getCookie(),house:"Greyjoy"});
        window.location="GoT.jsp";});
    
        $("#lannisterBanner").click(function() {        
        post({command:"sethouse" ,player:getCookie(),house:"Lannister"});
        
        window.location="GoT.jsp";});
        $("#baratheonBanner").click(function() {
        post({command:"sethouse" ,player:getCookie(),house:"Baratheon"});
        window.location="GoT.jsp";});
        $("#dothrakiBanner").click(function() {
        post({command:"sethouse" ,player:getCookie(),house:"Dothraki"});
        window.location="GoT.jsp";});
        $("#targaryenBanner").click(function() {
        post({command:"sethouse" ,player:getCookie(),house:"Targaryen"});
        window.location="GoT.jsp";});
    }
   
    function displayPlayersMoto(){
        //make so get playersHouse moto
        $('#moto').append('<img style="width 10px" src="http://fontmeme.com/newcreate.php?text=Players%20Housemoto&name=Game of Thrones.ttf&size=11&style_color=221a16"  alt="Start New Game">');
        
    }
    
    function updateDisplay(){
        getGameJSON();
        setFlags();
        insertInfoTable();
    }
    var first = null;
    var second = null;
    function setSVGClickEvents(i){
        var input = i.id;
        switch(currentPhase){            
            case "claim":{
                    post({command:"claimterritory", playername:player, territory:input});                
                    break;
            }
                
            case "reinforce":{
//                    openWindow();
                    var t = parseInt(prompt("How many troops", "0"));                       
                    post({command:"reinforce", territory:input, troops:t});
                    break;
            }      
            case "attack":{
                    
                    if (first === null){first = input;
                    console.log(first + second);}
                    else{ 
                        second = input;
                        console.log(first + second);
                        post({command: "attack" , attackingterritory:first, defendingterritory:second});
                        first = null;
                        second = null;
                    }
        }
        }
        updateDisplay();
    }
    function openWindow() {
        newWindow = window.open("", null, "height=200,width=400,status=yes,toolbar=no,menubar=no,location=no");  
       
        var form = '<form><input type="text" id="formValueId"/><input type="button" id="button-id"/></form>';
        newWindow.document.write(form);
        
            $(document).ready(function() {
            $('#button-id').click(function() {
              foo($('#formValueId').val());
            });
            });
    }

    function setValue(value) {
        document.getElementById('value').value = value;
    }
    
    function gameLogic(){
       var gameJSON = post({command:"getgamedata"});
        
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
        var returnData=null;
//        alert(postData);
        $.ajax({
          type: "POST",
          url: "MainServlet",
          dataType : 'json',
          data: postData,
          async: false 
        ,
          success : function(data){
//            window.location.href='GoT.jsp';
            returnData= data;
           }});
        return returnData;
    }
    


