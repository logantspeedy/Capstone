/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function setFlags(){
    for (i = 0; i < nodes.length; i++) {
        var ter = nodes[i].territoy.toString();
        var controller = nodes[i].controllingPlayer.toString();
        if (controller.replace(/ /g,'') === ""){
            $('#img'+ter.replace(/ /g,'')).attr("src","images/houseFlags/notClaimed.png");
        }
        else if (controller==="Nomad"){
            $('#img'+ter.replace(/ /g,'')).attr("src","images/houseFlags/freeFolk.png");
        }

        else{
        $('#img'+ter.replace(/ /g,'')).attr("src","images/houseFlags/"+(getPlayersHouse(controller)).replace(/ /g,'')+".png");}
    }
}

function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    var expires = "expires="+d.toUTCString();
    document.cookie = cname + "=" + cvalue + "; " + expires;
} 

function setValue(value) {
    document.getElementById('value').value = value;
}

function endPhase(){
    if (currentPlayer === player){
    post({command:"nextphase"});
    }
    else {displayPopUp(2);}
}

function setTerTroop(){
    for (i = 0; i < nodes.length; i++) {
        var ter = nodes[i].territoy.toString();
        var controller = nodes[i].controllingPlayer.toString();
        var number = getTerritotyTroopCount(ter);
        if (controller.replace(/ /g,'') === ""){
        }
        else{
            if(number !== 0){
                $('#num'+ter.replace(/ /g,'')).empty().append("<strong>"+getTerritotyTroopCount(ter)+"</strong>");
                    }
                }
}
}