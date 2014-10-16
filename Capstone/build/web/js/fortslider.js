/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function onfortclick(){
    $('.fortifypopup').hide();
    var t = $('#sliderTroops').slider("option", "value");
    console.log(first + " " + second + " " + t);
    post({command: "fortify", startterritory: first, targetterritory: second, troops: t});
    first = null;
    second = null;
    $("#" + id).attr('class', 'default'); // reset class of clicked svg 
    $(".attack").attr('class', "default"); // reset class of first clicked svg 
    
    
}
