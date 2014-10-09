/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
    // Variables
var gameJSON = null;
var currentPhase = null;
var currentPlayer = null;
var currentPlayerHouse = null;
var currentPlayerTroops = null;
var nodes = null;
var player = getCookie();
var house = null;   
var playerList = null;

function getGameJSON(){
    gameJSON = post({command:"getgamedata"});
    if (gameJSON !== null){
        playerList = gameJSON.playerList;
        currentPhase = gameJSON.currentPhase.toString();
        currentPlayer = gameJSON.currentPlayer.name.toString();
        currentPlayerHouse = gameJSON.currentPlayer.house.toString();
        currentPlayerTroops = gameJSON.currentPlayer.army.toString();
        nodes = gameJSON.board.nodes;
        house = post({command:"getplayershouse", player:player}).toLowerCase();
    }
    else{console.log("getting gameJSON of null in getGameJSON");
        window.location="GameEnded.jsp";}
//        console.log(nodes);
//      currentPlayerTroops = gameJSON.currentPlayer.troops.toString();
}

function post(postData){
    var returnData=null;
//        alert(postData);
    $.ajax({
      type: "POST",
      url: "MainServlet",
//          dataType : 'json',
      data: postData,
      async: false 
    ,
      success : function(data){
//            window.location.href='GoT.jsp';
        returnData= data;
       }});
    return returnData;
}  
    
function getSessionId(){

        var sessionid=null;
        $.ajax({
            type: "POST",
            url: "MainServlet",
            dataType : 'text',
            data: {command: "getsessionid"},async: false
            }).done(function( data ) {
                sessionid = data.trim();

            }).fail(function() {
                alert("Error in Get Session Id Post");
            });

              return sessionid;
          }
          
function getTerritotyTroopCount(id){
        var unitCount = null;
        if (nodes !== null){
            for ( var i = 0; i < nodes.length; i++){
                if (nodes[i].territoy === id){
                    unitCount=nodes[i].troops;
                    return unitCount;
        }
        }}
        
    
}

function getPlayersHouse(play){
    if (playerList !== null){
        for (var i = 0; i < playerList.length; i++){
            if (playerList[i].name === play){
                return playerList[i].house;
            }
        }
    }
    else{
        consol.log("player list is null in getPlayerHouse");
        return null;}
        
//    var response = post({command:"getplayershouse", player:play});
   
}

function getCookie() {
        var ca = document.cookie.split(';');

        var username = ca[0].split("=");

        return username[1];
    }
    
function getAllPlayerTroopCount(p){
    var unitCount = 0;
    var u = null;
    if (nodes !== null){
        for (i = 0; i < nodes.length; i++){
            if (nodes[i].controllingPlayer === p ){
                u=nodes[i].troops;
                unitCount = unitCount + u ;
    }
        }
            }
    return unitCount;
}
// ==============================================================================
// new 
// ==============================================================================
function getAdjacentNodes(id) {
    var i;
    var adjNodes = [];
    if (nodes !== null) {
        for (i = 0; i < nodes.length; i++) {
            if (nodes[i].territoy === id) {
                adjNodes = nodes[i].adjacentNodes;
                return adjNodes;
            }
        }
    }
}
function getTerrOwner(id) {
    var i;
    var terrOwner = "";
    if (nodes !== null) {
        for (i = 0; i < nodes.length; i++) {
            if (nodes[i].territoy === id) {
                terrOwner = nodes[i].controllingPlayer;
                return terrOwner;
            }
        }
    }
}