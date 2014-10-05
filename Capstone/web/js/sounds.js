/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


//var sfort = document.getElementById("sfort")[0];
//var syourturn = document.getElementById("syourturn")[0];
//var sclaim = document.getElementById("sclaim")[0];
////var sattack = document.getElementById("sattack");
//var sgameMusic = document.getElementById("sgameMusic")[0];
//var snotTurn = document.getElementById("snotTurn")[0];
//var sreinforce = document.getElementById("sreinforce")[0];


var sgameMusic= null;
var syourturn = null;
var sclaim = null;
var sattack = null;
var snotTurn = null;
var sreinforce = null;
function startBackgroundMusic(){
        if (sgameMusic !== null){
            sgameMusic.volume=0.3;
            sgameMusic.play();
    }
}

function playTest(){
    document.getElementById('sattack').play();
}
