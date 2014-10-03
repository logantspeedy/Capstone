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
// ================================================================================
// new stuff
// ================================================================================
var reinforceFirst = null;
function setReinforce() {
    if (currentPlayer === player) {
        if (currentPhase === "reinforce") {
            if (reinforceFirst === null) {
                setTerrReinforce();
                reinforceFirst = 1;
            }
        } 
    }
    if (currentPhase === "attack") {
            if (reinforceFirst !== null) {
                $('.fortClick').attr("class", 'default');
                reinforceFirst = null;
            }
        }
}
function setTerrReinforce() {
    var i;
    if (nodes !== null) {
        for (i = 0; i < nodes.length; i++) {
            if (nodes[i].controllingPlayer === player) {
                terr = nodes[i].territoy;
                terr = terr.replace(/ /g, "_");
                $('#' + terr).attr("class", 'fortClick');
            }
        }
    }
}
function setOppositionClass(id, clazz) {
    var i;
    var adjNodes = getAdjacentNodes(id);
    console.log(adjNodes, adjNodes);// get list of adjacents
    for (i = 0; i < adjNodes.length; i++) {       // of each in list
//        console.log(adjNodes[i]);
        if (getTerrOwner(adjNodes[i]) !== player) {
            if (getTerrOwner(adjNodes[i]) !== " ") {
                var cN = adjNodes[i].replace(/ /g, "_");
                console.log(cN);
                $('#' + cN).attr("class", clazz);
            }
        }
    }
}
function setOwnedClass(id, clazz) {
    var i;
    var cN = "";
    var adjNodes = getAdjacentNodes(id); // get list of adjacents
    for (i = 0; i < adjNodes.length; i++) {       // of each in list
        cN = adjNodes[i];
        if (getTerrOwner(cN) === player) {
            cN = cN.replace(/ /g, "_");
            $('#' + cN).attr("class", clazz);
        }
    }
}

