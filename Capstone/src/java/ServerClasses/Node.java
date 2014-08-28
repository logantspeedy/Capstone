package ServerClasses;
/**
 * @author Patrick
 * Node class to serve as a data structure for the board.
 */
public class Node {
	private String territoy;
	private String controllingPlayer;
	private String continent;
	private int troops;
	private String[] adjacentNodes;
	private boolean canAttack;

	public Node(String territory, String player, String continent, int troops, String[] adjacentNodes){
		this.setTerritoy(territory);
		this.setControllingPlayer(player);
		this.setContinent(continent);
		this.setTroops(troops);
		this.setAdjacentNodes(adjacentNodes);
		this.setAttack(true);
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

	public boolean canAttack() {
		return canAttack;
	}

	public void setAttack(boolean attackable) {
		this.canAttack = attackable;
	}	
}
