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
function testGame(){
        setCookie("username", "player1",20);
  
        var playerList=["player2","player3","player4","player5","player6"];

        post({command:"creategame", username: 'player1', gamename:'testGame'});
        
        var gameId=getSessionId();
        
        for (var i = 0; i < 18; i++){
            if (i<5){post({command: "joingame", username:"player2", gameid:gameId });

                }

            if(i===16){post({command:"startgame", playername1: '', playername2:""});
                }
            
            if(i===17){
                post({command:"sethouse" ,player:'player1',house:"Stark"});
                post({command:"sethouse" ,player:'player2',house:"Greyjoy"});

            }
        };
        location.reload();
}