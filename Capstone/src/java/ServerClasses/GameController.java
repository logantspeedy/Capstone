package serverClasses;

import java.util.ArrayList;

public class GameController {
	
	private String currentPlayer;		
	private ArrayList<String> playerList;	
	
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
}
