/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var gameSoundsOn = true;

function toggleSound(){
    if (gameSoundsOn === true){gameSoundsOn = false;}
    else{gameSoundsOn = true;}
}

function startBackgroundMusic(){
    if (gameSoundsOn === true){
        document.getElementById('sgameMusic').play();}
    else {console.log("Sound is Muted");}
}

function playSound(sound){
    if (gameSoundsOn === true){
        if (sound === 'attack'){
            document.getElementById('sattack').play();
        }

            if (sound === 'fortify'){
            document.getElementById('sfort').play();
        }

            if (sound === 'yourturn'){
            document.getElementById('syourturn').play();
        }

            if (sound === 'claim'){
            document.getElementById('sclaim').play();
        }

            if (sound === 'notyourturn'){
            document.getElementById('snotTurn').play();
        }

            if (sound === 'reinforce'){
            document.getElementById('sreinforce').play();
        }
    }
    else {console.log("Sound is Muted");}
    
    
}
