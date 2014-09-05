package ServerClasses;

/**
 * @author Patrick
 * Board class serves as a data structure for the game.
 * Board will change elements of a node with the methods:
 * changeTroops, changeController.
 * Board will offer methods for retrieving data from nodes.
 * Such as getAdjNodes. 
 */

import java.util.ArrayList;

public class Board {
	
	private ArrayList<Node> nodes;
	
	public Board(ArrayList<Node> nodes){
		this.nodes = nodes;		
	}
	
	public Board(){			
	}
	
	public ArrayList<Node> getBoard(){
		return nodes;
	}
	
	public void setBoard(ArrayList<Node> nodes){
		this.nodes = nodes;
	}
	
	public Node getNode(String territory){	
		for(Node n : nodes){
			if(n.getTerritoy().equals(territory)){
				return n;
			}
		}
		return null;
	}		
	
	public void changeController(String territory, String newControler){
		Node node = getNode(territory);	
		node.setControllingPlayer(newControler);
	}
	
	public void fortify(String startTerritory, String targetTerritory, int troops){
		Node startNode = getNode(startTerritory);
		Node targetNode = getNode(targetTerritory);
		startNode.setTroops(startNode.getTroops() - troops);
		targetNode.setTroops(targetNode.getTroops() + troops);
	}
	
	public String[] getAdjNodes(String territory){
		Node node = getNode(territory);
		return node.getAdjacentNodes();
	}
	
	public String getControllingPlayer(String territory){
		Node node = getNode(territory);
		return node.getControllingPlayer();
	}
	
	public int getTroops(String territory){
		Node node = getNode(territory);
		return node.getTroops();
	}
	
	public void changeTroops(String territory, int troops){
		Node node = getNode(territory);		
		node.setTroops(node.getTroops() + troops);
	}
	
	public boolean isAdj(String territoryA, String territoryB){				
		String[] adjNodes = getAdjNodes(territoryA);
		for(int i = 0; i < adjNodes.length; i++){
			if(adjNodes[i].equals(territoryB)){
				return true;
			}
		}
		return false;
	}
	
	public void resetCanAttack(){
		for(Node n : nodes){
			n.setAttack(true);
		}
	}
        public Board setStartingHouses(Board board, ArrayList<Player> playerList){
		for(Player p : playerList){			
			switch (p.getHouse()){
				case "Stark":{
					board.changeController("Winterfell", p.getName());
					board.changeController("Barrowlands", p.getName());
					board.changeController("Widows Watch", p.getName());
					break;
				}
				case "Lannister":{
					board.changeController("Kings Landing", p.getName());
					board.changeController("The Reach", p.getName());
					board.changeController("Harrenhal", p.getName());
					break;
				}
				case "Greyjoy":{
					board.changeController("The Twins", p.getName());
					board.changeController("Pyke", p.getName());
					board.changeController("Westerlands", p.getName());
					break;
				}
				case "Baratheon":{
					board.changeController("Dragon Stone", p.getName());
					board.changeController("Ashford", p.getName());
					board.changeController("Stormlands", p.getName());
					break;
				}
				case "Targaryen":{
					board.changeController("Ghisear", p.getName());
					board.changeController("The Red Waste", p.getName());
					board.changeController("Qarth Island", p.getName());
					break;
				}
				case "Dothraki":{
					board.changeController("Northern Dathraki Sea", p.getName());
					board.changeController("Bhonash", p.getName());
					board.changeController("Village of Lhazareen", p.getName());
					break;
				}
			}
		
		}
		return board;	
	}
}
