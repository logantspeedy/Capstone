/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var sgameMusic= new Audio('sounds/ingame.mp3');
var syourturn = new Audio('sounds/Saracen.mp3');
var sclaim = new Audio('sounds/tf4.wav');
var sattack = new Audio('sounds/sword.mp3');
var snotTurn = new Audio('sounds/deni.wav');
var sreinforce = new Audio('sounds/AgeUp.mp3');
function loadSounds(counter){
    if (counter === 1){var sfort = new Audio('sounds/Teuton.mp3');loadSounds(counter+1);}
    
    if (counter === 2){ var syourturn = new Audio('sounds/Saracen.mp3');loadSounds(counter+1);}

    if (counter === 3){var sclaim = new Audio('sounds/tf4.wav');loadSounds(counter+1);}

    //http://www.freesfx.co.uk from
    if (counter === 4){var sattack = new Audio('sounds/sword.mp3');loadSounds(counter+1);}

    //https://www.youtube.com/watch?v=v8aokfpvS7M&list=UUtHhtnum-35K2iUdaYFlk0Q&src_vid=7cUELYuzRGc&feature=iv&annotation_id=annotation_1059356347
    if (counter === 5){var sgameMusic= new Audio('sounds/ingame.mp3');loadSounds(counter+1);}

    //origin
    if (counter === 6){ var snotTurn = new Audio('sounds/deni.wav');loadSounds(counter+1);}
    
    if (counter === 7){var sreinforce = new Audio('sounds/AgeUp.mp3');loadSounds(counter+1);}
    else {initGame(2);}
}