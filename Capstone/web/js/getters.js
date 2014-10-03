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
    playerList = gameJSON.playerList;
    currentPhase = gameJSON.currentPhase.toString();
    currentPlayer = gameJSON.currentPlayer.name.toString();
    currentPlayerHouse = gameJSON.currentPlayer.house.toString();
    currentPlayerTroops = gameJSON.currentPlayer.army.toString();
    nodes = gameJSON.board.nodes;
    house = post({command:"getplayershouse", player:player}).toLowerCase();
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
            for (i = 0; i < nodes.length; i++){
                if (nodes[i].territoy === id){
                    unitCount=nodes[i].troops;
                    return unitCount;
        }
        }}
        
    
}

function getPlayersHouse(play){
    var response = post({command:"getplayershouse", player:play});
    return response;
}

function getCookie(cname) {
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