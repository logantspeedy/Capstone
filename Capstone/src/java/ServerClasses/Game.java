package serverClasses;

/** 
 * Class to control game logic.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Game {
	
	private Board board;	
	private String currentPlayer;		
	private ArrayList<String> playerList;	
	private int turnStage;
	
	public Game(String[] players){
		
		currentPlayer = null;		
		playerList = new ArrayList();	
		for(int i = 0;i < players.length; i++){
			playerList.add(players[i]);
		}		
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}
	
	public String getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(String currentPlayer) {
		this.currentPlayer = currentPlayer;
	}	
	/** 	 
	 * @return Next player in ArrayList, returns 1st player if at end of the ArrayList.
	 */
	public String nextPlayer()
	{
		int playerPos = 0;		
		if(currentPlayer==null)
		{
			currentPlayer = playerList.get(playerPos);
			return currentPlayer;						
		}
		
		playerPos=playerList.indexOf(currentPlayer);
		if(playerPos<(playerList.size()-1))
		{
			playerPos++;
		}
		else
		{
			playerPos=0;
		}
		currentPlayer = playerList.get(playerPos);
		return currentPlayer;		
	}	
	
	private void NewTurn()
	{
		//sets current player to new player
		//set turn stage to 1
		//updates each country with auto reinforcements (random reinforcements)
		//decides quantity for reinforce stage of turn
		
		int x =0;
	}
	
	public void NextStage()
	{
		// takes the game stage and increments it by 1, if stage 3 runs newturn()
		// possibly sends a return packet telling the client the new game stage
		if(turnStage==3)
		{
			NewTurn();	
		}
		else
		{
			turnStage++;	
		}
	}
	
	//functions that control the core moves of the game
	//***********************************************************************
	
	/**
	 * Adds troops to territory.
	 * @param territory
	 * @param troops
	 * @return updated board.
	 */
	public Board reinforce(String territory, int troops){
		board.changeTroops(territory, troops);
		return board;
	}
	
	/**	  
	 * Moves troops from startTerritory to targetTerritoy
	 * @param startTerritory/
	 * @param targetTerritory
	 * @param troops
	 * @return updated board.
	 */
	public Board fortify(String startTerritory, String targetTerritory, int troops){
		board.fortify(startTerritory, targetTerritory, troops);
		return board;
	}	
	/**
	 * NOT FINISHED
	 * @param attackingTerritory
	 * @param defendingTerritory
	 */
	public Board attack(String attackingTerritory, String defendingTerritory){		
		//Create variables required to calculate an attack.		
		int aArmy = board.getTroops(attackingTerritory);
		int dArmy = board.getTroops(defendingTerritory);
		int[] aRolls;
		int[] dRolls;
		
		//Attack logic.
		if(board.isAdj(attackingTerritory, defendingTerritory)){
			//Territories are adjacent.
			if(board.getControllingPlayer(attackingTerritory) == currentPlayer
					&& board.getControllingPlayer(defendingTerritory) != currentPlayer){
				//Territories are controlled by different players.
				//Work out how large each army is.
				aArmy = (aArmy > 3)?3:(aArmy - 1);
				aArmy = (aArmy == 0)?1:aArmy;
				dArmy = (dArmy > 3)?3:(dArmy - 1);
				dArmy = (dArmy == 0)?1:dArmy;
				//Change troops in each territory.
				board.changeTroops(attackingTerritory, -aArmy);
				board.changeTroops(defendingTerritory, -dArmy);
				//Generate Dice rolls.
				aRolls = rollDice(aArmy);
				dRolls = rollDice(dArmy);
				//Compare results.
				for(int i = 2; i > -1; i--){
					if(aRolls[i] > dRolls[i]){
						dRolls[i] = -2;
					}
					else if(aRolls[i] < dRolls[i]){
						aRolls[i] = -2;
					}
					else if(aRolls[i] == dRolls[i]){						
					}
				}
			}
			//Territories controlled by same player.
			else{}			
		}
		//Territories not adjacent, can't attack.
		else{}	
		return board;
	}
	/**Dice roll for calculating attacks. 
	 * @param armySize
	 * @return a sorted (low to high) array.
	 */
	public int[] rollDice(int armySize){		
		int[] rolls = new int[]{-1, -1, -1};
		//Creates dice roll data.
		for(int i  = 0; i < armySize; i++){
			rolls[i] = (int)(6.0 * Math.random()) + 1;					
		}
		//Sorts low to high.
		Arrays.sort(rolls);			
		return rolls;
	}
}
