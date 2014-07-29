package serverClasses;

/** 
 * Class to control game logic.
 */

import java.util.ArrayList;
import java.util.Arrays;;

public class Game {
	
	private Board board;	
	private String currentPlayer;		
	private ArrayList<String> playerList;
	private int playerPos;
	private int turnStage;
	
	public Game(String[] players){
		//Where initial board state will be hardcoded?
		currentPlayer = null;		
		playerList = new ArrayList();
		playerPos = 0;
		board = new Board();
		createNewBoard();
		for(int i = 0;i < players.length; i++){
			playerList.add(players[i]);
		}		
	}
	/**
	 * Creates a new board of standard places. With no troops and no players.
	 */
	public void createNewBoard(){
		ArrayList<Node> nodes = new ArrayList();
		Node castleBlack = new Node("Castle Black", null, "The North", 0, new String[]{"Winterfell", "Crasters Keep"});
		Node winterfell = new Node("Winterfell", null, "The North", 0, new String[]{"The Twins", "Crasters Keep"});
		Node theTwins = new Node("The Twins", null, "Riverlands", 0, new String[]{"Winterfell", "The Eyrie", "Iron Islands"});
		Node theEyrie = new Node("The Eyrie", null, "The Vale", 0, new String[]{"The Twins", "Kings Landing"});
		nodes.add(castleBlack);
		nodes.add(winterfell);
		nodes.add(theTwins);
		nodes.add(theEyrie);
		board.setBoard(nodes);
	}
	
	public void setControllingPlayer(String territory, String player){
		board.changeController(territory, player);
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
		playerPos = playerList.indexOf(currentPlayer);
	}	
	/** 	 
	 * @return Next player in ArrayList, returns 1st player if at end of the ArrayList.
	 */
	public String nextPlayer()
	{				
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
	 * Initial stage of the game where players claim territories.
	 * Changes control of the territory to player and adds one troop. 
	 * @param territory
	 * @param player
	 * @return Updated board.
	 */
	public Board claimTerritory(String territory, String player){
		if(board.getNode(territory).getControllingPlayer() == null){
			board.changeController(territory, player);
			board.changeTroops(territory, 1);
		}
		return board;
	}
	
	/**
	 * Adds troops to territory.
	 * @param territory
	 * @param troops
	 * @return updated board.
	 */
	public Board reinforce(String territory, int troops){
		if(board.getControllingPlayer(territory) == currentPlayer){
			board.changeTroops(territory, troops);
		}
		return board;
	}
	
	/**	  
	 * Moves troops from startTerritory to targetTerritoy
	 * @param startTerritory
	 * @param targetTerritory
	 * @param troops
	 * @return updated board.
	 */
	public Board fortify(String startTerritory, String targetTerritory, int troops){
		//Check to see if player controlls both territories.
		if(board.getControllingPlayer(startTerritory) == currentPlayer &&
				board.getControllingPlayer(targetTerritory) == currentPlayer){
			//Check to see if startTerritory has enough troops to transfer.
			if(board.getTroops(startTerritory) > troops){
				board.fortify(startTerritory, targetTerritory, troops);	
			}
		}				
		return board;
	}	
	/**
	 * NOT FINISHED
	 * @param attackingTerritory
	 * @param defendingTerritory
	 */
	public Board attack(String attackingTerritory, String defendingTerritory, int aArmy, int dArmy){		
		//Create variables required to calculate an attack.		
		int[] aRolls;
		int[] dRolls;		
		//Attack logic.
		//Territories are adjacent check.
		if(board.isAdj(attackingTerritory, defendingTerritory)){		
			//Territories are controlled by different players check.
			if(board.getControllingPlayer(attackingTerritory) == currentPlayer
					&& board.getControllingPlayer(defendingTerritory) != currentPlayer){				
				//Change troops in each territory.
				board.changeTroops(attackingTerritory, -aArmy);
				board.changeTroops(defendingTerritory, -dArmy);
				//Generate Dice rolls.
				aRolls = rollDice(aArmy);
				dRolls = rollDice(dArmy);
				//Compare results.
				//2 - dArmy because only need to compare as many dice as the defenders rolled (1 or 2).				
				for(int i = 2; i > (2 - ((aArmy > dArmy)?dArmy:aArmy)); i--){
					if(aRolls[i] > dRolls[i]){
						dRolls[i] = -2;						
					}
					else if(aRolls[i] < dRolls[i]){
						aRolls[i] = -2;
					}
					else if(aRolls[i] == dRolls[i]){
						aRolls[i] = -2;
					}
				}
				//Reduce army sizes.
				for(int i = 0; i < 3; i++){
					if(aRolls[i] == -2){
						aArmy--;
					}
					else if(dRolls[i] == -2){
						dArmy--;
					}
				}
				//Check to see if player now controls territory.
				if(dArmy == 0 && board.getTroops(defendingTerritory) == 0){
					board.changeController(defendingTerritory, currentPlayer);
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
	private int[] rollDice(int armySize){		
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
