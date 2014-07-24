package ServerClasses;

public class Node {
	private String territoy;
	private String controllingPlayer;
	private String continent;
	private int troops;
	private String[] adjacentNodes;

        public Node(String territory, String player, String continent, int troops, String[] adjacentNodes){
		this.setTerritoy(territory);
		this.setControllingPlayer(player);
		this.setContinent(continent);
		this.setTroops(troops);
		this.setAdjacentNodes(adjacentNodes);
	}

	public String getControllingPlayer() {
		return controllingPlayer;
	}

	public void setControllingPlayer(String controllingPlayer) {
		this.controllingPlayer = controllingPlayer;
	}

	public String getTerritoy() {
		return territoy;
	}

	public void setTerritoy(String territoy) {
		this.territoy = territoy;
	}

	public int getTroops() {
		return troops;
	}

	public void setTroops(int troops) {
		this.troops = troops;
	}

	public String[] getAdjacentNodes() {
		return adjacentNodes;
	}

	public void setAdjacentNodes(String[] adjacentNodes) {
		this.adjacentNodes = adjacentNodes;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	public String getContinent() {
		return continent;
	}
        

        public void generateAdjacentNodes(){  
                //FUNCTION FOR GENERATING ADJACENT NODES
            
                //need a case for every territory
                if (this.getTerritoy() == "Castle Black"){
                    //set the adjacent nodes depending on the territory (will need to look at map and finalise later)
                    String[] adjacentNodes = {"Craster's Keep", "Fist of the First Men"};
                }
                else{
                    String[] adjacentNodes = null;
                }
                
                //set that shit 
                this.setAdjacentNodes(adjacentNodes);
                
                //done generating adjacent nodes
                return;
        }
}
