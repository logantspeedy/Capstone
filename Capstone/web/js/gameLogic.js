/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var first = null;
var second = null;
function setSVGClickEvents(i){
    var input = i.id;
    if (player === currentPlayer){
    switch(currentPhase){            
        case "claim":{
                sclaim.play();
                post({command:"claimterritory", playername:player, territory:input});                
                break;
        }

        case "reinforce":{   
                sreinforce.play();
                post({command:"reinforce", territory:input, troops:1});
                break;

        }      
        case "attack":{
                sattack.play();
                if (first === null){
                    first = input;
                console.log("first attack" + first + second);
                break;
            }
                else{ 
                    sattack.play();
                    second = input;
                    console.log(first + second);
                    post({command: "attack" , attackingterritory:first, defendingterritory:second});
                    first = null;
                    second = null;
                    break;
                }                    
        }
        case "fortify":{
            if(first === null){
                first = input;
                console.log("fortify from: " + first + ", Choose target");
            }
            else{
                sfort.play();
                second = input;                    
                var t = parseInt(prompt("How many troops to fortify", "0"));
                console.log(first + " " + second + " " + t);
                post({command: "fortify", startterritory:first, targetterritory:second, troops:t});
                first = null;
                second = null;
                break;
            }
        }
        }
    }
    else{
        displayPopUp(2);}


}