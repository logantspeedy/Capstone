/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var first = null;
var second = null;
var id = null; // for svg reference
function setSVGClickEvents(i) {
    id = i.id;
    var input = i.id.replace(/_/g, " "); // for server stuff
    if (player === currentPlayer) {
        switch (currentPhase) {
            case "claim":
                {
                    if (nodes !== null) {
                        for (var i = 0; i < nodes.length; i++) {
                            if (nodes[i].territoy === input) {
                                if (nodes[i].controllingPlayer === " ") {
                                    playSound('claim');
                                    console.log(input);
                                    $("#" + id).attr('class', 'claim'); // change class of clicked svg
                                    setTimeout(function(){
                                        $("#" + id).attr('class', 'default'); // reset class of clicked svg 
                                    },1000);
                                    post({command: "claimterritory", playername: player, territory: input});
                                    
//                                    $("#" + id).attr('class', 'default'); // reset class of clicked svg              
                                    break;
                                }
                                else {
                                    console.log("Territory Taken");
                                    break;
                                }
                            }
                        }

                    }
                    break;
                }

            case "reinforce":
                {
                    if (nodes !== null) {
                        for (var i = 0; i < nodes.length; i++) {
                            if (nodes[i].territoy === input) {
                                if (nodes[i].controllingPlayer === player) {
                                    playSound('reinforce');
                                    post({command: "reinforce", territory: input, troops: 1});
                                    break;
                                }
                                else {
                                    console.log("Not Players Territory");
                                    break;
                                }
                            }
                        }
                    }
                    break;
                }
            case "attack":
                {
                    if (first === null) {
                        if (nodes !== null) {
                            for (var i = 0; i < nodes.length; i++) {
                                if (nodes[i].territoy === input) {
                                    if (nodes[i].controllingPlayer === player) {
                                        if (nodes[i].canAttack === true) {
                                            first = input;
                                            $("#" + id).attr('class', 'attack'); // change class of clicked svg
                                            setOppositionClass(input, "defenderClick"); // change class of adjacent areas, owned by other players
                                            console.log("first attack" + first + second);
                                            break;
                                        }
                                        else {
                                            console.log("can't attack with this ter");
                                            displayPopUp(3);
                                            break;
                                        }

                                    }
                                    else {
                                        console.log("Not players ter to attack with");
                                        break;
                                    }
                                }
                            }
                        }
                        break;
                    }
                    else {
                        if (nodes !== null) {
                            // unselect selected area
                            if (first === input) {
                                $(".attack").attr('class', "default"); // reset class of first clicked svg 
                                $(".defenderClick").attr('class', "default"); // reset class of adjacent areas, owned by other players
                                first = null;
                            }
                            else {
                                for (var i = 0; i < nodes.length; i++) {
                                    if (nodes[i].territoy === input) {
                                        if (nodes[i].controllingPlayer !== player) {
                                            playSound('attack');
                                            second = input;
                                            console.log(first + second);
                                            $("#" + id).attr('class', 'defend');// change class of clicked svg
                                            $(".defenderClick").attr('class', "default"); // reset class of adjacent areas, owned by other players
                                            setTimeout(function(){
                                                $("#" + id).attr('class', 'default'); // reset class of clicked svg 
                                                $(".attack").attr('class', "default"); // reset class of first clicked svg 
                                            },1000);
                                            post({command: "attack", attackingterritory: first, defendingterritory: second});
                                            first = null;
                                            second = null;
//                                            $("#" + id).attr('class', 'default'); // reset class of clicked svg 
//                                            $(".attack").attr('class', "default"); // reset class of first clicked svg 
                                            break;
                                        }
                                        else {
                                            console.log("can't attack yourself");
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    break;
                }
            case "fortify":
                {
                    if (nodes !== null) {
                        if (first === null) {
                            for (var i = 0; i < nodes.length; i++) {
                                if (nodes[i].territoy === input) {
                                    if (nodes[i].controllingPlayer === player) {

                                        first = input;
                                        $("#" + id).attr('class', 'attack'); // change class of clicked svg
                                        setOwnedClass(input, "fortClick"); // change class of adjacent areas, owned by player
                                        console.log("fortify from: " + first + ", Choose target");
                                        break;

                                    }
                                    else {
                                        console.log("not your territory");
                                        break;
                                    }
                                }
                            }
                            break;
                        }

                        else {
                            for (var i = 0; i < nodes.length; i++) {
                                if (nodes[i].territoy === input) {
                                    if (nodes[i].controllingPlayer === player) {

                                        playSound('fortify');
                                        second = input;
                                        $("#" + id).attr('class', 'fort'); // change class of clicked svg
                                        $(".fortClick").attr('class', "default"); // reset class of adjacent areas, owned by player
                                        $(".fortifypopup").show();


                                        break;


                                    }
                                    else {
                                        console.log("Not players ter");
                                        break;
                                    }
                                }
                            }

                        }
                    }
                    break;
                }

        }

    }
    else {
        displayPopUp(2);
    }


}
// =====================================================================================
// new
// =====================================================================================
function follow() {
    console.log("in follow");
    $("#follower").hide();
//    $("path").mouseover(mouseoverHandler);
//    $("path").mouseout(mouseoutHandler);
}
function mouseoverHandler(i) {
    var id = i.id;
    var input = id.replace(/_/g, " ");
//    $("#" + id).attr('class', 'hover');
    $("#follower").css({"position": "absolute",
        "width": "100px",
        "height": "40px",
        "line-height": "10px",
        "padding": "10px",
        "font-size": "14px",
        "text-align": "center",
        "background": "#d2caa0",
        "border": "2px solid #424242",
        "font-family": "Cordia New",
        "color": "black",
        "border-radius": "5px",
        "text-shadow": "rgba(0, 0, 0, 0.0980392) 1px 1px 1px",
        "box-shadow": "rgba(0, 0, 0, 0.0980392) 1px 1px 2px 0px"});
    $("#hoverTerritory").empty().append("<strong>" + input + "</strong></br>");
//    $("#hoverUnits").empty().append("Units : " + getTerritotyTroopCount(input));
    $("#follower").stop(true, true);
    $("#follower").fadeIn("fast");
    var gto = getTerrOwner(input);
    var gca = getCanAttack(input);
    var gttc = getTerritotyTroopCount(input);
    if (player === currentPlayer) {
        if (first === null) {
            switch (currentPhase) {
                case "claim":
                    {
                        if (gto === " ") {
                            $("#" + id).attr('class', 'hover');
                        }
                        break;
                    }

                case "reinforce":
                    {
//                        $("#" + id).attr('class', 'hover');
                        break;
                    }
                case "attack":
                    {
                        if (gto === player) {
                            if (gca === true) {
                                if (gttc > 1) {
                                    $("#" + id).attr('class', 'attackerHover');
                                    setOppositionClass(input, "defenderHover");
                                }
                            }
                        }
                        break;
                    }
                case "fortify":
                    {
                        if (gto === player) {
                            $("#" + id).attr('class', 'attackerHover');
                            setOwnedClass(input, "fortHover");
                        }
                        break;
                    }
            }
        }
    }

}





function mouseoutHandler(i) {
    var id = i.id;
//    $("#" + id).attr('class', 'default');
    $("#follower").fadeOut("fast");
    if (first === null) {
        $(".hover").attr('class', "default");
        $(".attackerHover").attr('class', "default");
        $(".defenderHover").attr('class', "default");
        $(".fortHover").attr('class', "default");
        $(".reinforceHover").attr('class', "default");
//    console.log("testing remove none existing class");
    }
}

var adjacentNodesDictionary = {};

function setAdjacentNodes() {
    var i;
    if (nodes !== null) {
        for (i = 0; i < nodes.length; i++) {
            var terr = nodes[i].territoy;
            var adj = nodes[i].adjacentNodes;
            adjacentNodesDictionary[terr] = adj;
        }
    }
}

function resetEndPhase(){
    $(".attack").attr('class', "default");  
    $(".defenderClick").attr('class', "default");
    $(".defend").attr('class', 'default');
    $(".fort").attr('class', 'defend');
    $(".fortClick").attr('class', "default");
    first = null;
}