package ServerClasses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
	
	private ArrayList<Node> nodes;
	
	public Board(ArrayList<Node> nodes){
		this.nodes = nodes;		
	}
        public Board() {
            ArrayList<Node> nodes = null;
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
        
        public void createNewBoard(int players){
                //FUNCTION FOR GENERATING THE NEW BOARD
            
            
                //this is the default hasbro rules for amount of troops per player, can change this later
                int troopNumber = 0;
                if (players == 2){
                    troopNumber = 25;
                }
                else if (players == 3){
                    troopNumber = 35;
                }
                else if (players == 4){
                    troopNumber = 30;
                }
                else if (players == 5){
                    troopNumber = 25;
                }
                else if (players == 6){
                    troopNumber = 20;
                }    
                
                //this is for each territory
                //we have a choice of either having a default for territories given to each player or generating it randomly, or it being picked. the difficulty of implementation increasing respectively
                Node castleBlack = new Node("Castle Black", "Player 1", "North of The Wall", troopNumber, null);
                //generate the surrounding nodes
                castleBlack.generateAdjacentNodes();
               
                //there will be many more of these to come, but map needs to be finalised
                
                //put them all into a list
                List<Node> nodeList = Arrays.asList(castleBlack);
                
                
                
                
                
                //give the list to the board to create
                this.setBoard((ArrayList<Node>) nodeList);    
                
                //new board generated
                return; 
        }

}
