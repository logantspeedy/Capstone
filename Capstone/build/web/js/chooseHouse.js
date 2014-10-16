/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
  function displayPickHouse(){
        getGameJSON();
        if (gameJSON === null){window.location.href='chooseHouse.jsp';}
        //code for when mosue over make images bigger
        //        /* Teaser image swap function */
        //    $('img.swap').hover(function () {
        //        this.src = '/images/signup_big_hover.png';
        //    }, function () {
        //        this.src = '/images/signup_big.png';
        //    });
        else{
            $("#starkBanner").click(function() {
            post({command:"sethouse" , player:getCookie(),house:"Stark"});
            window.location="GoT.jsp";
            });
            $("#greyjoyBanner").click(function() {
            post({command:"sethouse" ,player:getCookie(),house:"Greyjoy"});
            window.location="GoT.jsp";});

            $("#lannisterBanner").click(function() {        
            post({command:"sethouse" ,player:getCookie(),house:"Lannister"});
            window.location="GoT.jsp";});

            $("#baratheonBanner").click(function() {
            post({command:"sethouse" ,player:getCookie(),house:"Baratheon"});
            window.location="GoT.jsp";});

            $("#dothrakiBanner").click(function() {
            post({command:"sethouse" ,player:getCookie(),house:"Dothraki"});
            window.location="GoT.jsp";});

            $("#targaryenBanner").click(function() {
            post({command:"sethouse" ,player:getCookie(),house:"Targaryen"});
            window.location="GoT.jsp";});
        }
    }
 
 function checkHouseStatus(){
    getGameJSON();
    playerList = gameJSON.playerList;
    if (playerList === null){window.location.href='chooseHouse.jsp';}
    for (var i = 0; i < playerList.length; i++) {
        var hosue = playerList[i].house.toLowerCase();
        if(hosue==="stark"){
            $("#starkBanner").attr("isselected", "true");
            $("#starkBanner").attr('onclick','').unbind('click');
            $("#starkBanner").click(function() {alert("House is Taken");});
        }
        if(hosue==="lannister"){
            $("#lannisterBanner").attr("isselected", "true");
            $("#lannisterBanner").attr('onclick','').unbind('click');
            $("#lannisterBanner").click(function() {alert("House is Taken");});}
        
        if(hosue==="baratheon"){
            $("#baratheonBanner").attr("isselected", "true");
            $("#baratheonBanner").attr('onclick','').unbind('click');
            $("#baratheonBanner").click(function() {alert("House is Taken");});}
        
        if(hosue==="dothraki"){
            $("#dothrakiBanner").attr("isselected", "true");
            $("#dothrakiBanner").attr('onclick','').unbind('click');
            $("#dothrakiBanner").click(function() {alert("House is Taken");});}
        
        if(hosue==="targaryen"){
            $("#targaryenBanner").attr("isselected", "true");
            $("#targaryenBanner").attr('onclick','').unbind('click');
            $("#targaryenBanner").click(function() {alert("House is Taken");});}
        
        if(hosue==="greyjoy"){
            $("#greyjoyBanner").attr("isselected", "true");
            $("#greyjoyBanner").attr('onclick','').unbind('click');
            $("#greyjoyBanner").click(function() {alert("House is Taken");});}
    }
//    reloadStylesheets();
 }



function reloadStylesheets() {
    var queryString = '?reload=' + new Date().getTime();
    $('link[rel="stylesheet"]').each(function () {
        this.href = this.href.replace(/\?.*|$/, queryString);
    });
}