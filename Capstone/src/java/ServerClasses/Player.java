package ServerClasses;

import java.util.Objects;

/** 
 * @author Patrick
 * Class for manipulating and storing player objects.
 */

public class Player {
	
	private String name;
	private String house;
	private int army;
	
	public Player(String name, int troops){
		this.setName(name);
		this.setArmy(troops);
                this.setHouse("");
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
                this.name = name.trim();
	}
        
        public String getHouse() {
		return house;
	}

	public void setHouse(String house) {
                System.out.println("In Player:" +this.name+" set House, Target house to set:"+house);
		this.house = house;
	}

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.name);
        return hash;
        
    }@Override
    public String toString() {
        String p = "name: " + this.name + " house: " + this.house + " army: " +  this.army;
        return p;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Player other = (Player) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }


        


}