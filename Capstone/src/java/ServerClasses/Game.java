package serverClasses;

/** 
 * Class to control game logic.
 */

import java.util.ArrayList;
import java.util.Arrays;;

public class Game {
	
	private Board board;	
	private Player currentPlayer;		
	private ArrayList<Player> playerList;
	private int playerPos;
	private int phaseStage;
	private int noOfTerritories;
	private int claimCounter;
	final int[] startingTroops = new int[]{40, 35, 30, 25, 20};	
	private String currentStage;
	private String currentPhase;
	final String[] possiblePhase = new String[]{"claim", "reinforce", "attack", "fortify"};
	
	public Game(String[] players){
		
		currentPhase = possiblePhase[0];
		currentStage = "setup";
		currentPlayer = null;		
		claimCounter = 0;
		playerList = new ArrayList();
		playerPos = 0;
		board = new Board();
		int troops = startingTroops[players.length - 2];
		createNewBoard();
		for(int i = 0;i < players.length; i++){
			Player player = new Player(players[i], troops);
			playerList.add(player);
		}	
		nextPlayer();
		noOfTerritories = board.getBoard().size();
	}
	/**
	 * Creates a new board of standard places. With no troops and no players.
	 */
	public void createNewBoard(){
		ArrayList<Node> nodes = new ArrayList();
		Node castleBlack = new Node("Castle Black", "", "The North", 0, new String[]{"Winterfell", "Crasters Keep"});
		Node winterfell = new Node("Winterfell", "", "The North", 0, new String[]{"The Twins", "Crasters Keep"});
		Node theTwins = new Node("The Twins", "", "Riverlands", 0, new String[]{"Winterfell", "The Eyrie", "Iron Islands"});
		Node theEyrie = new Node("The Eyrie", "", "The Vale", 0, new String[]{"The Twins", "Kings Landing"});
		nodes.add(castleBlack);
		nodes.add(winterfell);
		nodes.add(theTwins);
		nodes.add(theEyrie);
		board.setBoard(nodes);
	}
	
	//*****************************************************************
	//********************Getters and setters**************************
	//*****************************************************************
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
		return currentPlayer.getName();
	}
	
	public ArrayList<Player> getPlayerList(){
		return playerList;
	}

	public void setCurrentPlayer(String player) {				
		playerPos = playerList.indexOf(getPlayer(player));
		currentPlayer = playerList.get(playerPos);
	}	
	
	public Player getPlayer(String player){
		for(Player p : playerList){
			if(p.getName().equals(player)){
				return p;
			}
		}
		return null;
	}
	
	public String getPhase(){
		return currentPhase;
	}
	
	public String getStage(){
		return currentStage;
	}
	
	public void setPhase(String phase){
		this.currentPhase = phase;
	}
	
	public void setStage(String stage){
		this.currentStage = stage;
	}
	
	//*********************************************************************************
	//**************Functions for getting next player/phase/turn***********************
	//*********************************************************************************
	
	/** 	 
	 * @return Next player in ArrayList, returns 1st player if at end of the ArrayList.
	 */
	public String nextPlayer()
	{				
		if(currentPlayer == null)
		{
			currentPlayer = playerList.get(playerPos);
			return currentPlayer.getName();						
		}
		
		playerPos = playerList.indexOf(currentPlayer);
		if(playerPos < (playerList.size()-1))
		{
			playerPos++;
		}
		else
		{
			playerPos=0;
		}
		currentPlayer = playerList.get(playerPos);
		return currentPlayer.getName();		
	}	
	
	private void newTurn(){
		
		//sets current player to new player
		//set turn stage to 1
		//updates each country with auto reinforcements (random reinforcements)..?
		//decides quantity for reinforce stage of turn
		nextPlayer();
		//In setup stage of game so need to check if progressing to next stage,
		//or next phase.
		if(currentStage.equals("setup")){
			//Check for next phase.
			if(currentPhase.equals("claim") && claimCounter != noOfTerritories){
				phaseStage = 0;				
			}
			//Else ready to move to next phase: reinforce.
			else if(claimCounter == noOfTerritories && currentPhase.equals("claim")){
				phaseStage = 1;
				currentPhase = "reinforce";
			}
			//Everyone out of armies and ready to move to gameStage.
			else if(currentPhase.equals("reinforce") && outOfArmies()){
				currentStage = "game";
			}	
		}	
		//Moved on to the actual game play stage.
		if(currentStage.equals("game")){
			//Assign troops for new turn.
			currentPlayer.setArmy(calcNewArmy(currentPlayer));
			
		}
	}
	
	public void nextPhase()
	{
		// takes the game stage and increments it by 1, if stage 3 runs newturn()
		// possibly sends a return packet telling the client the new game stage
		// A few possibilities, have 2 different stages, setup has 2 phases, game has 3.
		
		//In setup stage therefore changes player after every phase.
		//Only changes phase once which is calculated in nextTurn.
		if(currentStage.equals("setup")){
			newTurn();	
		}
		else{
			if(phaseStage == 3){				
				newTurn();
				phaseStage = 1;
				currentPhase = possiblePhase[phaseStage];
				
			}
			else{
				phaseStage++;	
				currentPhase = possiblePhase[phaseStage];
			}
		}
	}
	//***********************************************************************
	//********functions that control the core moves of the game**************
	//***********************************************************************
	/**
	 * Initial stage of the game where players claim territories.
	 * Changes control of the territory to player and adds one troop, then hands turn over. 
	 * 
	 * Pre-Conditions: currentPhase = claim, territory.getControllingPlayer = "",
	 * currentPlayer = player.	 
	 * @param territory
	 * @param player
	 * @return Updated board.
	 */
	public Board claimTerritory(String territory, String player){
		//Check to see if in the right phase.
		if(currentPhase.equals("claim")){
			//Check to see if node is uncontrolled.
			if(board.getNode(territory).getControllingPlayer().isEmpty()){
				//Check to see if its the current players turn.
				if(currentPlayer.getName().equals(player)){
					board.changeController(territory, player);
					board.changeTroops(territory, 1);	
					//Change players army size.
					currentPlayer.setArmy(currentPlayer.getArmy() - 1);
					claimCounter++;
					//Automatically go to next players turn.
					nextPhase();
				}
			}
		}
		return board;
	}
	
	/**
	 * Adds troops to territory. Goes to next phase if player has run out of troops
	 * or game is in setup stage.
	 * 
	 * Pre-conditions: currentPhase = reinforce, territory.ControllingPlayer = currentPlayer,
	 * currentPlayer.army >= troops.
	 * @param territory
	 * @param troops
	 * @return updated board.
	 */
	public Board reinforce(String territory, int troops){
		//Check it's reinforce phase of the game.
		if(currentPhase.equals("reinforce")){
			//Check current player controls territory.
			if(board.getControllingPlayer(territory).equals(currentPlayer.getName())){		
				//Check player has big enough army.
				if(!(currentPlayer.getArmy() < troops)){
					board.changeTroops(territory, troops);
					currentPlayer.setArmy(currentPlayer.getArmy() - troops);
					//Check to see if the player can't place any more troops, then move to next phase.
					//Or still in setup phase then need to switch to next player.
					if((currentStage.equals("game") && currentPlayer.getArmy() == 0) || currentStage.equals("setup")){
						nextPhase();
					}	
				}
			}			
		}
		return board;
	}
	
	/**	  
	 * Moves troops from startTerritory to targetTerritoy
	 * 
	 * Pre-conditions: start/targetTerritoy.controllingPlayer = currentPlayer,
	 * startTerritory.getTroops > troops, currentPhase = fortify.
	 * @param startTerritory
	 * @param targetTerritory
	 * @param troops
	 * @return updated board.
	 */
	public Board fortify(String startTerritory, String targetTerritory, int troops){
		if(currentPhase.equals("fortify")){
			//Check to see if player controlls both territories.
			if(board.getControllingPlayer(startTerritory).equals(currentPlayer.getName()) &&
					board.getControllingPlayer(targetTerritory).equals(currentPlayer.getName())
					&& board.getControllingPlayer(startTerritory).equals(currentPlayer.getName())){
				//Check to see if startTerritory has enough troops to transfer.
				if(board.getTroops(startTerritory) > troops){				
					board.fortify(startTerritory, targetTerritory, troops);	
				}
			}
		}
		return board;
	}	
	/**
	 * Calculates the attack then changes the troops for each territory accordingly
	 * and changes controller if required.
	 * 
	 * Pre-conditions: attackingTerritory/defendingTerritory are adjacent, 
	 * attackingTerritory.getController = currentPlayer, currentPhase = attack.
	 * 
	 * @param attackingTerritory
	 * @param defendingTerritory
	 */
	public Board attack(String attackingTerritory, String defendingTerritory, int aArmy, int dArmy){		
		//Create variables required to calculate an attack.		
		int[] aRolls;
		int[] dRolls;		
		//Attack logic.
		//Territories are adjacent check.
		if(currentPhase.equals("attack")){
			if(board.isAdj(attackingTerritory, defendingTerritory)){		
				//Territories are controlled by different players check.
				if(board.getControllingPlayer(attackingTerritory).equals(currentPlayer.getName())
						&& !board.getControllingPlayer(defendingTerritory).equals(currentPlayer.getName())){				
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
						board.changeController(defendingTerritory, currentPlayer.getName());
					}
				}							
			}				
		}
		return board;
	}	
	//******************************************************
	//*************Utility Functions************************
	//******************************************************
	
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
	/** Calculates how many troops a player gets at the start of their turn.
	 *	For MVP just returning how many territories they control.
	 * @param player Current player who just started a new turn.
	 * @return int to add to their army.
	 */
	private int calcNewArmy(Player player){
		ArrayList<Node> nodes = board.getBoard();
		int newArmy = 0;
		for(Node n : nodes){
			if(n.getControllingPlayer().equals(player.getName())){
				newArmy++;
			}
		}
		return newArmy;
	}
	/** 
	 * @return true if all players armies == 0.
	 */
	private boolean outOfArmies(){
		for(Player p : playerList){
			if(p.getArmy() != 0){
				return false;
			}
		}
		return true;
	}
}
