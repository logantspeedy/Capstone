/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ServerClasses;

/**
 *
 * @author Taylor
 */
public class Attack {
    /** should be a function in another class i think
    * Inputs countryA , counrtyB
    * step 1 check countryA and countryB are adjacent and that countryA is current player and countryB isn't'
    * Step 2 figure out how many armies are fighting from each country
    * if countryA troopsize>=4 then 3 else troopsize-1 as long as troop size != 1
    * if countryB troopsize>=3 then 3 else troopsize
    * step 3 take the troops out of the country
    * board.changetroops(countryA,-numberOfAtttackers)
    * board.changetroops(countryB,-numberOfDeffenders)
    * step 4 fight the troops
    * create attackersDice list and deffendersDice list both of size 3 and with -1 in each space
    * now generate a random number between 1-6 for each attacker (numberOfAttackers) and put them into the attackersDice list
    * repeat for the deffenders
    * now sort both lists from highest to lowest
    * step 5 run attacks
    * now loop x from 1-3 and compare attackersDice[x] and deffendersDice[x] 
    * the larger one is left as its dice roll and the smaller is chaged to -2
    * now loop through each list and for each possitive number add 1 to the country troop count
    * step 6 check defendeing country has more than 0 armies in it
    * if it doesnt attacking player gains control of it and move all but 1 of attacking countries armies into it
 
    */
    }
