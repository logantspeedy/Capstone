/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ServerClasses;

public class Bonuses {
    
    private final String[] ATTACK_BONUS_NORTH = new String[]{"The Lands of Always Winter",
            "The Haunted Forest","Bay of Seals","Castle Black","Wolfswood","Karhold"};
    private final String[] ATTACK_BONUS_MID = new String[]{"Braavos","Braavosian Coastlands",
            "The Flatlands","The Golden Fields","The Disputed Lands","Hills of Norvos"};
    private final int BONUS_LENGTH = 6;
    protected boolean checkNorthBonus(String territory){
        for(int i = 0; i < 6; i++){
            if(ATTACK_BONUS_NORTH[i].equals(territory)){
                return true;
            }
        }
        return false;
    }
    protected boolean checkMidBonus(String territory){
        for(int i = 0; i < 6; i++){
            if(ATTACK_BONUS_MID[i].equals(territory)){
                return true;
            }
        }
        return false;
    }
    
    protected int[] addAttackBonus(int[] rolls, int bonus){
        for(int i = 0; i < rolls.length; i++){
            if(rolls[i] == -1){
                
            }
            else{
                rolls[i] += bonus;
            }
        }
        return rolls;
    }
}
