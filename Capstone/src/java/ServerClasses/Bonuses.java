/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ServerClasses;

public class Bonuses {
    
    private final String[] ATTACK_BONUS_NORTH = new String[]{"The Lands of Always Winter",
            "The Haunted Forest","Castle Black"};
    private final String[] ATTACK_BONUS_MID = new String[]{"The Flatlands","The Golden Fields",
            "The Disputed Lands"};
    private final String[] ATTACK_BONUS_RIGHT = new String[]{"Vaes Dorthrak", "Eastern Dathraki Sea",
            "Palt of Illen"};    
    private final int BONUS_LENGTH = 3;
    
    protected boolean checkNorthBonus(String territory){
        for(int i = 0; i < BONUS_LENGTH; i++){
            if(ATTACK_BONUS_NORTH[i].equals(territory)){
                return true;
            }
        }
        return false;
    }
    protected boolean checkMidBonus(String territory){
        for(int i = 0; i < BONUS_LENGTH; i++){
            if(ATTACK_BONUS_MID[i].equals(territory)){
                return true;
            }
        }
        return false;
    }
    
    protected boolean checkRightBonus(String territory){
        for(int i = 0; i < BONUS_LENGTH; i++){
            if(ATTACK_BONUS_RIGHT[i].equals(territory)){
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
