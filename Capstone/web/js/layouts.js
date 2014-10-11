/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function insertHeader(){
    $('.header').empty().append("<img class='gameBanner' src='images/banners/gameBanner.png' alt='Game Of Thrones'>");
}
  
    
function insertFooter(){
    $('.footer').empty().append('<hr>');
    $('.footer').append('<p>a Team4 Production 2014 Massey capstone@massey.ac.nz©</p>');
}

function insertNav(){
    $('#nav').append(   
        '<table >'+
           ' <tr>'+
              '<th style="padding-top: 6%" ><a href="home">Home</a></th>'+
              '<th></th><th></th>'+
              '<th style="padding-top: 6%"  ><a href="about">About</a></th>'+
              '<th></th><th></th>'+
              '<th style="padding-top: 6%" ><a href="contact">Contact</a></th>'+
              '<th></th><th></th>'+
              '<th style="padding-top: 6%" >Lobby</th>'+
              '<th></th><th></th>'+
              '<th style="padding-top: 6%" ><a href="">Leave Game</a></th>'+
              '<th></th><th></th>'+
              '<th style="padding-top: 6%" ><a href="test">Signout</a></th>'+
            '</tr>'+
          '</table>');
}

function openWindow() {
    newWindow = window.open("", null, "height=200,width=400,status=yes,toolbar=no,menubar=no,location=no");  

    var form = '<form><input type="text" id="formValueId"/><input type="button" id="button-id"/></form>';
    newWindow.document.write(form);

        $(document).ready(function() {
        $('#button-id').click(function() {
          foo($('#formValueId').val());
        });
        });
}