/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function updateDisplay(call){
    if (call===1){getGameJSON(); updateDisplay(call+1);}
    if (call===2){setFlags(); updateDisplay(call+1);}
    if (call===3){displayPopUp(1);updateDisplay(call+1);}
    if (call===4){insertInfoTable(); updateDisplay(call+1);}
    if (call===5){setTerTroop(); updateDisplay(call+1);}
    if (call===6){playerBanners(); updateDisplay(call+1);}
//        if (call===6){console.log("diplay updated");}

}

function displayPopUp(poId){
    if (poId === 1){
        if ((currentPlayer===player) && ($("#pop1").attr("shown")=== "false")){
            syourturn.play();
            $("#pop1").css({"visibility":"visible"});
            $("#pop1").attr("shown", "true");
        }
        if (currentPlayer!==player){$("#pop1").attr("shown", "false");
        }
    }
    if (poId === 2){
        snotTurn.play();
        $("#pop2").css({"visibility":"visible"});
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
            $(bannerId).empty().append("<p>"+playerList[i].name+"<br>Troops: "+getAllPlayerTroopCount(playerList[i].name)+"<br>Free Units:" +playerList[i].army+"<br>Attack Bonus:"+playerList[i].attackBonus +"</p>");}
    }
}

function follow(){
    console.log("in follow");
    $("#follower").hide();
//    $("path").mouseover(mouseoverHandler);
//    $("path").mouseout(mouseoutHandler);
}

function mouseoverHandler(i) {
    var input = i.id;
//    if (!$(e).attr('class')) {
//        pathMouseover = e.id;
//        console.log(e.id);
//    }
//    else {
//        pathMouseover = e.className.baseVal;
//        console.log(e.className.baseVal);
//    }
    $("#follower").css({"position":"absolute",
                      
		      "width":"100px",
		      "height":"40px",
		      "line-height":"10px",
		      "padding":"10px",
		      "font-size":"14px",
		      "text-align":"center",
		      "background":"#d2caa0",
		      "border":"2px solid #424242",
                      "font-family":"Cordia New",
                      "color": "black",
		      "border-radius":"5px",
		      "text-shadow":"rgba(0, 0, 0, 0.0980392) 1px 1px 1px",
		      "box-shadow":"rgba(0, 0, 0, 0.0980392) 1px 1px 2px 0px"});
    $("#hoverTerritory").empty().append("<strong>"+input+"</strong></br>");

    $("#hoverUnits").empty().append("Units : "+ getTerritotyTroopCount(input));
//     + nodes[pathMouseover].troops
    $("#follower").stop(true, true);

    $("#follower").fadeIn("fast");
}

function mouseoutHandler(){
    $("#follower").fadeOut("fast");
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


