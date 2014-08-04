package serverClasses;

/** 
 * @author Patrick
 * Class for manipulating and storing player objects.
 */

public class Player {
	
	private String name;
	private int army;
	
	public Player(String name, int troops){
		this.setName(name);
		this.setArmy(troops);
	}

	public int getArmy() {
		return army;
	}

	public void setArmy(int troops) {
		this.army = troops;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
