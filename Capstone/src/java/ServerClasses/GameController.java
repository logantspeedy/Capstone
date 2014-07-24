package serverClasses;

import java.util.ArrayList;

public class GameController {
	
	private String currentPlayer;		
	private ArrayList<String> playerList;
	provate int turnStage;
	
	public GameController(String[] players){
		
		currentPlayer = null;		
		playerList = new ArrayList();			
		
		for(int i = 0;i < players.length; i++){
			playerList.add(players[i]);
		}		
	}

	public String getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(String currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
	
	/** 
	 * @param players, ArrayList of players.
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
	
	Public Board getBoard()
	{
		//function to return the games board for when the game state needs to be updated client side
		Board gameBoard = new Board();	
		return;
	}
	
	Private void NewTurn()
	{
		//sets current player to new player
		//set turn stage to 1
		//updates each country with auto reinforcements (random reinforcements)
		//decides quantity for reinforce stage of turn
		
		int x =0;
	}
	
	Public void NextStage()
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
	
	Public void Attack(String countryA, String countryB)
	{
		//function to run a single attack on countryB from countryA
		int x =0;
	
	}
	
	Public void Fortify(String countryA, String countryB, int quantity)
	{
		//function to move quantity number of troops from countryA to countryB
		int x=0;
	}
	
	Public void Reinforce(String countryA)
	{
		//function to add an army to countryA as part of the reinforcemnet stage of the turn
		//could also be passed in as an array of countries and quantities to be reinforced
		int x=0;
	}
	
	//****************************************************************************
}
