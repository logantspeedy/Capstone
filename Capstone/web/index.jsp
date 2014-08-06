<html>
<head>
    
    <!--<script src="http://code.jquery.com/jquery-latest.js"></script>-->
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
    <script src="jquery.imagemapster.js"></script>

</head>
<body>

<img style="width:400px;height:400px;border:0;" id="shape1" src="images/shape1.gif" alt="shape1" usemap="#shape1">
<input id="make-small" type="button" value="Make it small">
<input id="make-big" type="button" value="Make it bigger">
<script>

$(document).ready(function ()
{
$('#shape1').mapster({
singleSelect : true,
render_highlight : { altImage : 'images/shape2.gif' },
     mapKey: 'color',
fill : true, altImage : 'images/shape3.gif',
fillOpacity : 1
});
});

 $('#shape1').mapster({
        mapKey: 'color',
        stroke: true,
        strokeWidth: 2,
        strokeColor: 'ff0000'
         });

    $('#make-small').bind('click',function() {
        $('#shape1').mapster('resize', 200, 0, 1000);
    });
    
    $('#make-big').bind('click',function() {
            $('#shape1').mapster('resize', 720, 0, 1000);
        });
function setValue(i){
    window.alert(i.territory);
    }
    
function displayTer(i){
    $('#tooltip').toggle();
    $('#tooltip').html("Troops: "+i.troops+ " Adjacent countries: "+i.adjacentNodes);
    }
  
    

 var Winterfell = {territory:"Winterfell",player:"Player 1",continent:"The North",troops:20,adjacentNodes:["Pyke","Kings Landing"]};
 var Pyke = {territory:"Pyke",player:"Player 1",continent:"The North",troops:10,adjacentNodes:["Winterfell","Kings Landing"]};
 var KingsLanding = {territory:"Kings Landing",player:"Player 1",continent:"The North",troops:10,adjacentNodes:["Winterfell","Pyke","Pentos","Qarth"]};
 var Pentos = {territory:"Pentos",player:"Player 2",continent:"The North",troops:5,adjacentNodes:["Qarth","KingsLanding"]};
 var Qarth = {territory:"Qarth",player:"Player 2",continent:"The North",troops:30,adjacentNodes:["Kings Landing","Pentos"]};

 
</script>
<div id="tooltip" hidden="true"></div>


<map name="shape1">
<area href="" onClick="setValue(Winterfell)" onmouseover="displayTer(Winterfell)" onmouseout="displayTer()" color="blue" shape="poly" coords="210,40,196,28,171,23,145,24,112,29,106,43,105,54,98,56,58,52,44,54,33,83,29,107,45,144,46,148,165,192,175,205,197,140,206,84,207,36" alt="">
<area href="" onClick="setValue(Pyke)" onmouseover="displayTer(Pyke)" onmouseout="displayTer()" color="purple" shape="poly" coords="30,162,50,163,153,201,173,219,138,352,129,373,102,377,37,375,28,357,21,307,33,290,56,286,63,284,61,277,30,254,22,231,24,185,31,161" alt="">
<area href="" onClick="setValue(KingsLanding)" onmouseover="displayTer(KingsLanding)" onmouseout="displayTer()" color="red" shape="poly" coords="216,97,203,161,188,204,185,233,165,292,159,327,144,372,156,379,191,386,228,385,241,376,232,322,229,278,227,223,223,171,223,132,217,96" alt="">
<area href="" onClick="setValue(Qarth)" onmouseover="displayTer(Qarth)" onmouseout="displayTer()"color="orange" shape="poly" coords="238,213,260,208,282,195,296,188,323,185,360,194,368,203,376,227,376,272,375,286,370,289,358,289,357,294,365,305,368,329,365,357,361,370,351,379,331,383,310,382,251,364,249,344,241,307,239,210" alt="">
<area href="" onClick="setValue(Pentos)" onmouseover="displayTer(Pentos)" onmouseout="displayTer()"color="green" shape="poly" coords="288,176,256,195,239,201,236,135,221,53,221,46,242,32,292,32,297,44,300,59,302,64,342,64,361,73,370,90,370,124,370,124,363,146,351,163,329,172,289,176" alt="">
</map>
</body>
</html>