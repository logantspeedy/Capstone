/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function testGameSwitchPlayer(){
    var p = getCookie();
    if (p==="player1"){setCookie("username", "player2",20);}
    if (p==="player2"){setCookie("username", "player1",20);}
        var p = getCookie();
    location.reload();
}
function testGame(pha){
    var gameId=getSessionId();
    if (pha === 1){
        setCookie("username", "player1",20);
        post({command:"creategame", username: 'player1', gamename:'testGame'});
    }

    if(pha === 2){post({command: "joingame", username:"player2", gameid:gameId });}
    if(pha === 3){post({command: "joingame", username:"player3", gameid:gameId });}
    if(pha === 4){post({command: "joingame", username:"player4", gameid:gameId });}
    if(pha === 5){post({command: "joingame", username:"player5", gameid:gameId });}
    if(pha === 6){post({command: "joingame", username:"player6", gameid:gameId });}
  
    if(pha===7){post({command:"startgame", playername1: '', playername2:""});}
    if(pha===8){
        post({command:"sethouse" ,player:'player1',house:"Stark"});
        post({command:"sethouse" ,player:'player2',house:"Greyjoy"});
        post({command:"sethouse" ,player:'player3',house:"Lannister"});
        post({command:"sethouse" ,player:'player4',house:"Dothraki"});
        post({command:"sethouse" ,player:'player5',house:"Baratheon"});
        post({command:"sethouse" ,player:'player6',house:"Targaryen"});

    }
    if(pha===9){
    window.location="GoT.jsp";
    }
}