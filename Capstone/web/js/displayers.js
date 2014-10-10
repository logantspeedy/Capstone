/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//var everythingLoaded = setInterval(function() {
//  if (/loaded|complete/.test(document.readyState)) {
//    clearInterval(everythingLoaded);
//    removeLoader(); // this is the function that gets called when everything is loaded
//  }
//}, 10);
//
//function removeLoader(){
//        $("#loader").css({"visibility":"hidden"});  
//}

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
    if (call===8){}
    
//        if (call===8){console.log("diplay updated");}

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
            $("#pop1").fadeIn("slow");
            setTimeout(function(){$("#pop1").fadeOut("slow");}, 3000);
        }
        if (currentPlayer!==player){$("#pop1").attr("shown", "false");
        }
    }
    if (poId === 2){
//        snotTurn.play();
        $("#pop2").fadeIn("slow");
            setTimeout(function(){$("#pop2").fadeOut("slow");}, 3000);
    }
    
}

function hidePopUp(popId){
    var id = '#pop'+popId;
   
    $(id).css({"visibility":"hidden"});
    
}

function playerBanners(){
    $("div.banners").attr("turn", "false");
    $("div.banners").attr("isAlive", "false");
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
        $(bannerId).attr("isAlive", "true");
    }
    
        for (var i = 0; i < arrayLength; i++) {
            var ph = playerList[i].house;
            var bannerId = "#"+ph.toLowerCase() +"Controls";
            $(bannerId).empty().append("<h1>"+playerList[i].name+"</h1><hr/><img width='80%' height='auto' src='images/banners/"+ph.toLowerCase()+"Banner.png' /><br><h3>Troops: "+getAllPlayerTroopCount(playerList[i].name)+"<br>Free Units:" +playerList[i].army+"<br>Attack Bonus:"+playerList[i].attackBonus +"</h3>");}
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
    if (currentPhase ==="claim"){$('.phase').empty().append("<img style='width:100%; height:100%' src='images/banners/claim.png'</img>");}
    if (currentPhase ==="reinforce"){$('.phase').empty().append("<img style='width:100%; height:100%' src='images/banners/reinforce.png'</img>");}
    if (currentPhase ==="attack"){$('.phase').empty().append("<img style='width:100%; height:100%' src='images/banners/attack.png'</img>");}
    if (currentPhase ==="fortify"){$('.phase').empty().append("<img style='width:100%; height:100%' src='images/banners/for.png'</img>");}


}

function teston(id){
    var bannerId = "#"+id+"";
    console.log(bannerId);
    $(bannerId).fadeIn("slow");
}
function testout(id){
    var bannerId = "#"+id+"";
    setTimeout(function(){$(bannerId).fadeOut("slow");}, 2000);
}


