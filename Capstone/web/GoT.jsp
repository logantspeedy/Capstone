<%-- 
    Document   : GoT
    Created on : 31/07/2014, 11:46:19 AM
    Author     : Zeb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>GoT Page</title>
        
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
        <script src="jquery.imagemapster.js"></script>
        
    </head>
    <body>
        <h1>Hello World!</h1>
        <input onClick="setTerritoryOwner(zones.FlintsFinger)" type="button" value="set Stark">
        
        <img id="gameMap"src="images/GoT/GoTblankSMALL.jpg" alt="" usemap="#Map" />

        <map name="Map" id="Map">
            <area territory="FlintsFinger" href="#" shape="poly" coords="114,230,180,231,179,263,117,263,109,231,180,231,179,263,117,263,180,231,179,263,180,231" />
            <area id="TheTwins" territory="TheTwins" href="#" shape="poly" coords="190,242,251,242,250,275,190,275,189,240,251,242,250,275,190,275,251,242,250,275,251,242" />
            <area id="TheThreeSisters" territory="TheThreeSisters" href="#" shape="poly" coords="265,255,326,254,326,286,266,287,263,251,326,254,326,286,266,287,326,254,326,286,326,254" />
            <area id="Pyke" territory="Pyke" href="#" shape="poly" coords="100,281,159,283,158,314,100,315,97,278,159,283,158,314,100,315,159,283,158,314,159,283" />
            <area id="Riverlands" territory="Riverlands" href="#" shape="poly" coords="218,302,277,302,278,332,218,333,215,301,277,302,278,332,218,333,277,302,278,332,277,302" />
            <area id="TheEyrie" territory="TheEyrie" href="#" shape="poly" coords="293,314,353,314,353,344,293,344,292,312,353,314,353,344,293,344,353,314,353,344,353,314" />
        </map>
        
        
        
        <script>
            $(document).ready(function ()
                {
                $('#gameMap').mapster({
                mapKey: 'territory',
                singleSelect : false,
                selected:true,
                altImage : 'images/GoT/GoTblankSMALL.jpg'
                });
});


                 
        function setTerritoryOwner(ter){

            if (ter.player === 1){
            var altImg = 'images/GoT/GoTstarkSMALL.jpg';}
            else {var altImg = 'images/GoT/GoTLanesterSMALL.jpg';}
            
              $('#gameMap').mapster({
                mapKey: 'territory',
                singleSelect : false,
                selected:true,
                altImage : 'images/GoT/GoTblankSMALL.jpg',
                areas:  [
                    {   key: "FlintsFinger",
                        selected: true,
                        altImage : picAltImg(zones.FlintsFinger)} ,
                    
                    {   key: "TheTwins",
                        selected: true,
                        altImage : picAltImg(zones.TheTwins)},
                    
                    {   key: "TheThreeSisters",
                        selected: true,
                        altImage : picAltImg(zones.TheThreeSisters)},
                    
                    {   key: "Pyke",
                        selected: true,
                        altImage : picAltImg(zones.Pyke)},
                    
                    {   key: "Riverlands",
                        selected: true,
                        altImage : picAltImg(zones.Riverlands)},
                    
                    {   key: "TheEyrie",
                        selected: true,
                        altImage : picAltImg(zones.TheEyrie)} ]
     });
     };
function picAltImg(ter){
            if (ter.player === 1){
            var altImg = 'images/GoT/GoTstarkSMALL.jpg';}
            else {var altImg = 'images/GoT/GoTLanesterSMALL.jpg';}
            return altImg
}

        
        
//        function updateTerOwner(){
////            for (var t in zones){console.log(zones[t]);}
//            for (var t in zones){setTerritoryOwner(zones[t]);}
//        }
            
        
        var zones ={
        "FlintsFinger" : {territory:"FlintsFinger",player:1,continent:"The North",troops:10,
            adjacentNodes:["The Twins","Pyke"]},
        "TheTwins" : {territory:"TheTwins",player:1,continent:"The North",troops:10,
            adjacentNodes:["Flints Finger","The Three Sisters","Riverlands"]},
        "TheThreeSisters" : {territory:"TheThreeSisters",player:1,continent:"The North",troops:10,
            adjacentNodes:["The Twins","Riverlands","TheEyrie"]},
        "Pyke" : {territory:"Pyke",player:2,continent:"The North",troops:10,
            adjacentNodes:["FlintsFinger"]},
        "Riverlands" : {territory:"Riverlands",player:2,continent:"The North",troops:10,
            adjacentNodes:["The Twins","The Three Sisters","TheEyrie"]},
        "TheEyrie" : {territory:"TheEyrie",player:2,continent:"The North",troops:10,
            adjacentNodes:["Riverlands","The Three Sisters"]}
    };
        
        </script>
    </body>
    

</html>
