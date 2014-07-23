package server;

import java.util.ArrayList;

public class GameController {

	/**
	 * @param args
	 */
	private static String currentPlayer=null;
	public static void main(String[] args) {
		int numberPlayers = args.length;
		ArrayList<String> players = new ArrayList();
		for(int x=0;x<numberPlayers;x++){
			players.add(args[x]);
		}
		
		//testing stuff ***********
		System.out.println(currentPlayer);
		nextPlayer(players);
		System.out.println(currentPlayer);
		nextPlayer(players);
		System.out.println(currentPlayer);
		nextPlayer(players);
		System.out.println(currentPlayer);
		nextPlayer(players);
		System.out.println(currentPlayer);
		nextPlayer(players);
		System.out.println(currentPlayer);
		// **************
		
	}
	
	private static void nextPlayer(ArrayList<String> players)
	{
		int playerPos=0;
		if(currentPlayer==null)
		{
			String returnPlayer = players.get(playerPos);
			currentPlayer= returnPlayer;			
		}
		
		playerPos=players.indexOf(currentPlayer);
		if(playerPos<(players.size()-1))
		{
			playerPos++;
		}
		else
		{
			playerPos=0;
		}
		String returnPlayer = players.get(playerPos);
		currentPlayer= returnPlayer;
	}

}
