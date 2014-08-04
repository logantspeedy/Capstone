package serverClasses;

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
}
