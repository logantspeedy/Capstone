package server;

import java.util.ArrayList;

public class Board {
	
	private ArrayList<Node> nodes;
	
	public Board(ArrayList<Node> nodes){
		this.nodes = nodes;		
	}
	
	public ArrayList<Node> getBoard(){
		return nodes;
	}
	
	public void setBoard(ArrayList<Node> nodes){
		this.nodes = nodes;
	}
	
	public Node getNode(String territory){	
		for(Node n : nodes){
			if(n.getTerritoy() == territory){
				return n;
			}
		}
		return null;
	}
	
	public void changeTroops(String territory, int change){
		Node node = getNode(territory);		
		node.setTroops(node.getTroops() + change);
	}
	
	public void changeControler(String territory, String newControler){
		Node node = getNode(territory);	
		node.setControllingPlayer(newControler);
	}
	
	public String[] getAdjNodes(String territory){
		Node node = getNode(territory);
		return node.getAdjacentNodes();
	}

}
