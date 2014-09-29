package ServerClasses;

/** 
 * Class to control game logic.
 */



import java.util.ArrayList;
import java.util.Arrays;


public class Game {
	
	private Board board;	
	public Player currentPlayer;		
	private ArrayList<Player> playerList;
        private String currentStage;
	private String currentPhase;
        
	private int playerPos;
	private int phaseStage;
	final int noOfTerritories;
	private int claimCounter;
        private String startingPlayer;  
        private int captureCounter;
	final int[] startingTroops = new int[]{40, 35, 30, 25, 20};	
	
	final String[] possiblePhase = new String[]{"reinforce", "attack", "fortify"};
	
	public Game(String[] players){		
		currentPhase = "claim";
		currentStage = "setup";
		currentPlayer = null;		
		claimCounter = 0;   
		captureCounter = 1;
		playerPos = 0;
                //*********Change back to 42 for full game************
		noOfTerritories =10;
                //****************************************************
		playerList = new ArrayList<Player>();		
		board = new Board();
		int troops = startingTroops[players.length - 2];
		
		CreateGameBoard gameBoard = new CreateGameBoard();			
		board.setBoard(gameBoard.getNodes());	
				
		for(int i = 0;i < players.length; i++){
			Player player = new Player(players[i], troops);
			playerList.add(player);
		}
		
		board = gameBoard.setStartingHouses(board, playerList);
		
                startingPlayer = randomPlayer(players.length).getName();
		setCurrentPlayer(startingPlayer);		
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
        //added by jack
        public void setCurrentPlayersHouse(String house){
            this.currentPlayer.setHouse(house);
        }
	//
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
		if(currentPlayer == null){
			currentPlayer = playerList.get(playerPos);
			return currentPlayer.getName();						
		}		
		playerPos = playerList.indexOf(currentPlayer);
                
                
        if(playerPos < (playerList.size()-1)){
			playerPos++;
		}
        else{
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
			if(claimCounter >= noOfTerritories && currentPhase.equals("claim")){                
				phaseStage = 0;
				currentPhase = "reinforce";
			}
			//Everyone out of armies and ready to move to gameStage.
			else if(currentPhase.equals("reinforce") && outOfArmies()){
				currentStage = "game";
                                currentPlayer = getPlayer(startingPlayer);
                                currentPlayer.setArmy(calcNewArmy(currentPlayer));
			}
			//If current player army = 0, change to a player still with an 
			//army.
			else while(currentPlayer.getArmy() == 0){
				nextPlayer();
			}
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
			if(phaseStage == 2){				
                            newTurn();
                            phaseStage = 0;
                            currentPhase = "reinforce";
                            captureCounter = 1;
                            currentPlayer.setArmy(calcNewArmy(currentPlayer));
                            board.resetCanAttack();
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
                System.out.println("In game.claimTer: "+territory+" "+player);
		//Check to see if in the right phase.
		if(currentPhase.equals("claim")){
			//Check to see if node is uncontrolled.
			if(board.getNode(territory).getControllingPlayer().equals(" ")){
				//Check to see if its the current players turn.
				if(currentPlayer.getName().equals(player)){
                    currentPlayer = playerList.get(playerList.indexOf(currentPlayer));                                        
					board.changeController(territory, player);
					board.changeTroops(territory, 1);						
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
                                    currentPlayer = playerList.get(playerList.indexOf(currentPlayer));                                   
                                    board.changeTroops(territory, troops);
                                    currentPlayer.setArmy(currentPlayer.getArmy() - troops);                                  
                                    //Check to see if the player can't place any more troops, then move to next phase.
                                    //Or still in setup phase then need to switch to next player.
                                    if((currentStage.equals("game") && currentPlayer.getArmy() == 0) || currentStage.equals("setup")){
                                        System.out.println("next player");
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
	 * startTerritory.getTroops > troops, currentPhase = fortify, start and target
	 * territories need to be adjacent.
	 * @param startTerritory
	 * @param targetTerritory
	 * @param troops
	 * @return updated board.
	 */
	public Board fortify(String startTerritory, String targetTerritory, int troops){
		//Check in right phase and territories are adjacent.
		if(currentPhase.equals("fortify") && board.isAdj(startTerritory, targetTerritory)){
			//Check to see if player controlls both territories.
			if(board.getControllingPlayer(startTerritory).equals(currentPlayer.getName()) &&
					board.getControllingPlayer(targetTerritory).equals(currentPlayer.getName())
					&& board.getControllingPlayer(startTerritory).equals(currentPlayer.getName())){
				//Check to see if startTerritory has enough troops to transfer.				
				if(board.getTroops(startTerritory) > troops && captureCounter > 0){				
					board.fortify(startTerritory, targetTerritory, troops);
					captureCounter--;
                    nextPhase();
				}
			}
		}
		//Go to next player.		
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
	 * @param aRolls
	 * @param dRolls
	 */
	public Board attack(String attackingTerritory, String defendingTerritory){
                System.out.print("IN GAME ATTACK");
                System.out.println("controller of attacker is"+board.getControllingPlayer(attackingTerritory));
                System.out.println("controller of defender is"+board.getControllingPlayer(defendingTerritory));
		//Territories are adjacent check.
		if(currentPhase.equals("attack")){
			if(board.isAdj(attackingTerritory, defendingTerritory)){	
                                System.out.println("ARE ADJ");
				//Territories are controlled by different players check.
				if(board.getControllingPlayer(attackingTerritory).equals(currentPlayer.getName())
						&& !board.getControllingPlayer(defendingTerritory).equals(currentPlayer.getName())){
					if(board.getNode(attackingTerritory).canAttack()){
						
						//Work out army sizes.
						int aArmy = calcArmySize(attackingTerritory, true);
						int dArmy = calcArmySize(defendingTerritory, false);
						
						//Roll dice.
						int[] aRolls = rollDice(attackingTerritory, aArmy);
						int[] dRolls =  rollDice(defendingTerritory, dArmy);
						
                                                //See if defender has defending bonus and change army size back.
                                                if(getPlayer(defender).homeTerritory.equals((defendingTerritory))){
                                            dArmy--;
                                        }
                                                
                                                

						//Change troops in each territory.
						board.changeTroops(attackingTerritory, -aArmy);
						board.changeTroops(defendingTerritory, -dArmy);					
						//Compare results.					
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
						//Check to see if player now controls territory and change accordingly.
						if(dArmy == 0 && board.getTroops(defendingTerritory) == 0){
							board.changeController(defendingTerritory, currentPlayer.getName());
							board.changeTroops(defendingTerritory, aArmy);
							board.getNode(defendingTerritory).setAttack(false);
							captureCounter++;
						}
						//Update territories troops.
						else{
							board.changeTroops(attackingTerritory, aArmy);
							board.changeTroops(defendingTerritory, dArmy);
						}
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
	public int[] rollDice(String territory, int armySize){				
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
		int controlledTerritories = 0;
		for(Node n : nodes){
			if(n.getControllingPlayer().equals(player.getName())){
				controlledTerritories++;
			}
		}
		return Math.round(controlledTerritories / 2);
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
	/** 
	 * @return All the important game data.
	 */
	public ArrayList<String> getGameData(){
		ArrayList<String> gameData = new ArrayList<String>();
		gameData.add(currentPhase);
		gameData.add(currentStage);
		gameData.add(currentPlayer.getName());
		gameData.add(Integer.toString(currentPlayer.getArmy()));		
		return gameData;
	}
	/**returns the army 
	 * @param territory
	 * @param attacking
	 * @return
	 */
	public int calcArmySize(String territory, boolean attacking){
		int army = board.getTroops(territory);
		if(attacking){
			army = (army > 3)?3:(army - 1);
			army = (army == 0)?1:army;
		}
		else{
			army = (army > 2)?2:(army - 1);
			army = (army == 0)?1:army;
		}
		return army;
	}
        
        public Player randomPlayer(int noOfPlayers){
            Player random = playerList.get((int)(noOfPlayers * Math.random()));            
            return random;
        }
        public void setStartingHouses(){
		for(Player p : this.playerList){			
			switch (p.getHouse()){
                            
				case "Stark":{
					this.board.changeController("Winterfell", p.getName());                                        
					this.board.changeController("Barrowlands", p.getName());
					this.board.changeController("Widows Watch", p.getName());
                                        this.claimCounter += 3;
					break;
				}
				case "Lannister":{
					this.board.changeController("Kings Landing", p.getName());
					this.board.changeController("The Reach", p.getName());
					this.board.changeController("Harrenhal", p.getName());
                                        this.claimCounter += 3;
					break;
				}
				case "Greyjoy":{
					this.board.changeController("The Twins", p.getName());
					this.board.changeController("Pyke", p.getName());
					this.board.changeController("Westerlands", p.getName());
                                        this.claimCounter += 3;
					break;
				}
				case "Baratheon":{
					this.board.changeController("Dragon Stone", p.getName());
					this.board.changeController("Ashford", p.getName());
					this.board.changeController("Stormlands", p.getName());
                                        this.claimCounter += 3;
					break;
				}
				case "Targaryen":{
					this.board.changeController("Ghisear", p.getName());
					this.board.changeController("The Red Waste", p.getName());
					this.board.changeController("Qarth Island", p.getName());
                                        this.claimCounter += 3;
					break;
				}
				case "Dothraki":{
					this.board.changeController("Northern Dathraki Sea", p.getName());
					this.board.changeController("Bhonash", p.getName());
					this.board.changeController("Village of Lhazareen", p.getName());
                                        this.claimCounter += 3;
					break;
				}
			}
		
		}
//		return board;	
	}

        
        private void setControllingHouses(Player p, int housePos){
            String[][] startingHouses = new String[][]{{"Winterfell", "Barrowlands","Widows Watch"},{"Kings Landing", "The Reach", "Harrenhal"},
            {"Pyke","The Twins", "Westerlands"}, {"Dragon Stone","Ashford","Stormlands"}, {"Bayasabhad", "Ghisear", "Qarth Island"},
            {"Northern Dathraki Sea", "Bhonash", "Village of Lhazareen"}};
            String name = p.getName(); 
            p.homeTerritory = startingHouses[housePos][0];
            for(int i = 0; i < 3; i++){  
                currentPlayer = p;
                this.claimTerritory(startingHouses[housePos][i], name);
            }
        }
        //Used for when a player leaves the game before they have lost all territories.
        public void removePlayer(String player){
            Player inactive = getPlayer(player);
            inactive.active = false;
            if(inactive.equals(currentPlayer)){
                while(!currentPlayer.active){
                    nextPhase();
                }
            }
        }
}
