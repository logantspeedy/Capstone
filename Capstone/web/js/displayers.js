/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var everythingLoaded = setInterval(function() {
  if (/loaded|complete/.test(document.readyState)) {
    clearInterval(everythingLoaded);
    removeLoader(); // this is the function that gets called when everything is loaded
  }
}, 10);

function removeLoader(){
        $("#loader").css({"visibility":"hidden"});  
}

function updateDisplay(call){
        switch (gameJSON.currentStage.toString()) {
            case "Game Won":
            {
                if (player===currentPlayer){
                   $(".mainContainer").empty().append('<br/><h1>YOU WIN</h1><hr>'+
                        '<div class="video" >'+
                        '<iframe href="http://fontmeme.com/game-of-thrones-font/" width="100%" height="100%" src="//www.youtube.com/embed/jPmb0F00YPE?rel=0&autoplay=1&controls=0&showinfo=0" frameborder="0" allowfullscreen></iframe>'+
                        '</div>)');
                break;
                }
                
                else{
                    $(".mainContainer").empty().append('<br/><h1>...</h1><hr>'+
                        '<div class="video" >'+
                        '<iframe href="http://fontmeme.com/game-of-thrones-font/" width="100%" height="100%" src="//www.youtube.com/embed/eRfOU?rel=0&autoplay=1&controls=0&showinfo=0" frameborder="0" allowfullscreen></iframe>'+
                        '</div>)');
                break;
                }
                                        
            }
        }
    if (call===0){getGameJSON(); updateDisplay(call+1);}
    if (call===1){
        if(checkIfAllPlayersHouse() > 0){window.location="waiting.jsp";}
        else {updateDisplay(call+1);}
//        if(playerList === null){
//            console.log("Player List is Null in updateDisplay");
//        }
//        else{updateDisplay(call+1);
        }
//        };
    if (call===2){setFlags(); updateDisplay(call+1);}
    if (call===3){displayPopUp(1);updateDisplay(call+1);}
    if (call===4){insertInfoTable(); updateDisplay(call+1);}
    if (call===5){setTerTroop(); updateDisplay(call+1);}
    if (call===6){playerBanners(); updateDisplay(call+1);}
    if (call===7){setReinforce(); updateDisplay(call+1);}
    if (call===8){updateGameHistory();}
    
//        if (call===8){console.log("diplay updated");}

}
function updateGameHistory(){
    $(".infoTable2").empty();
    console.log(gameHistory.length);
    for(var i = 0; i < gameHistory.length; i++){
        $(".infoTable2").append("<p>"+gameHistory[i]+"</p>");
    }
    $(".infoTable2").append("<hr/> <h3>Current Player - "+currentPlayer+"</h3><h3>Free Units: "+currentPlayerTroops+". - "+
            "Available Fortifies: "+currentPlayerFreeForts+".</h3>");
    var objDiv = document.getElementById("inf2");
    objDiv.scrollTop = objDiv.scrollHeight;
}
function checkIfAllPlayersHouse(){
    var c =0 ;
    for(var i = 0; i < playerList.length; i++){
        if(playerList[i].house===""){c=c+1;}
    }
    return c;
    
}

function displayPopUp(poId){
    if (poId === 1){
        if ((currentPlayer===player) && ($("#pop1").attr("shown")=== "false")){
            playSound('yourturn');
            $("#pop1").attr("shown", "true");
            $("#pop1").fadeIn("fast");
            setTimeout(function(){$("#pop1").fadeOut("fast");}, 1500);
        }
        else if (currentPlayer!==player){$("#pop1").attr("shown", "false");
        }
    }
    if (poId === 2){
//        snotTurn.play();
        $("#pop2").fadeIn("fast");
            setTimeout(function(){$("#pop2").fadeOut("fast");}, 1500);
    }
    
    if (poId === 3){
//        snotTurn.play();
        $("#pop3").fadeIn("fast");
            setTimeout(function(){$("#pop3").fadeOut("fast");}, 1500);
    }
    
}

function hidePopUp(popId){
    var id = '#pop'+popId;
   
    $(id).fadeOut("fast");
    
}

function playerBanners(){
    $("div.banners").attr("turn", "false");
    
    
    if (currentPlayerHouse === ""){
        currentPlayerHouse =  post({command:"getplayershouse", player:gameJSON.startingPlayer}).toLowerCase();
        playerBanners();
    }
    else{
    var bannerId = "#"+currentPlayerHouse.toLowerCase() +"Banner";
    $(bannerId).attr("turn", "true");
    var arrayLength = playerList.length;
    for (var i = 0; i < arrayLength; i++) {
        var ph = playerList[i].house;
//        var alive = playerList[i].isAlive;
        var bannerId = "#"+ph.toLowerCase() +"Banner";
//        $(bannerId).attr("isAlive", alive);
        
        if (getAllPlayerTroopCount(playerList[i].name) !== 0){
            $(bannerId).empty().append("<img width='80%' height='auto' src='images/banners/"+ph.toLowerCase()+"Banner.png' />");
        }
        else{
            $(bannerId).empty().append("<img width='80%' height='auto' src='images/banners/"+ph.toLowerCase()+"Banner2.png' />");
        }
    }
    
        for (var i = 0; i < arrayLength; i++) {
            var ph = playerList[i].house;
            var controlsId = "#"+ph.toLowerCase() +"Controls";
            $(controlsId).empty().append("<h1>"+playerList[i].name+"</h1><hr/><img width='80%' height='auto' src='images/banners/"+ph.toLowerCase()+"Banner.png' /><br><h3>Troops: "+getAllPlayerTroopCount(playerList[i].name)+"<br>Free Units:" +playerList[i].army+"<br>Attack Bonus:"+playerList[i].attackBonus +"</h3>");}
    }
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
    imgTag.prependTo("#gameWindow");
}

function insertInfoTable(){
    if (currentPlayer === player){
        if (currentPhase ==="claim"){$('.phase').empty().append("<img style='width:100%; height:100%' src='images/banners/claim.png'</img>");}
        if (currentPhase ==="reinforce"){$('.phase').empty().append("<img style='width:100%; height:100%' src='images/banners/reinforce.png'</img>");}
        if (currentPhase ==="attack"){$('.phase').empty().append("<img style='width:100%; height:100%' src='images/banners/attack.png'</img>");}
        if (currentPhase ==="fortify"){$('.phase').empty().append("<img style='width:100%; height:100%' src='images/banners/for.png'</img>");}
    }
    else{ $('.phase').empty().append("<h3>Current Player</h3><img width='40%' height='auto' src='images/banners/"+currentPlayerHouse.toLowerCase()+"Banner.png' />");
        
    }

}

function teston(id){
//    var bannerId = "#"+id+"";
//    console.log(bannerId+"in");
//    $(bannerId).fadeIn("fast");
//    setTimeout(function(){$(bannerId).fadeOut("fast");},2500);
}
function testout(id){
    var bannerId = "#"+id+"";
//    console.log(bannerId+"out");
    $(bannerId).toggle();
    setTimeout(function(){$(bannerId).fadeOut("fast");},1500);
}


