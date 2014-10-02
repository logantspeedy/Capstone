package ServerClasses;

import java.util.ArrayList;

public class CreateGameBoard {

	private ArrayList<Node> nodes;
	private String[] territories;
	private String[][] adjTerritories;	

	public CreateGameBoard(){
		territories = new String[]{"The Lands of Always Winter","The Haunted Forest","Bay of Seals","Castle Black","Wolfswood","Winterfell",
				"Karhold","The Rills","Barrowlands","Widows Watch","The Twins","The Eyrie","Pyke","Dragon Stone","Westerlands","Harrenhal",
				"Kings Landing","The Reach","Ashford","Brightwater Keep","Skyreach","Stormlands","Dorne","Braavos","Braavosian Coastlands",
				"The Flatlands","The Golden Fields","The Disputed Lands","Hills of Norvos","Forest of Lohor","Volantis","Northern Dathraki Sea",
				"Bhonash","Valyria","Vaes Dorthrak","Village of Lhazareen","Ghisear","Palt of Illen","Eastern Dathraki Sea","Bayasabhad",
				"The Red Waste","Qarth Island"};
		adjTerritories = new String[][]{{"The Haunted Forest","Wolfswood"},{"The Lands of Always Winter","Bay of Seals","Castle Black"},
				{"The Haunted Forest","Karhold"},{"The Haunted Forest","Wolfswood","Winterfell","Karhold"},
				{"The Lands of Always Winter","Castle Black","The Rills","Castle Black", "Winterfell"},{"Castle Black","Wolfswood","Karhold","Barrowlands","Widows Watch"},
				{"Bay of Seals","Castle Black","Winterfell"},{"Wolfswood","Barrowlands"},{"Winterfell","The Rills","Widows Watch","The Twins"},
				{"Winterfell","Barrowlands","The Eyrie"},{"Barrowlands","Pyke","The Eyrie"},{"Dragon Stone","Widows Watch","The Twins","Westerlands","Harrenhal"},
				{"The Twins","Westerlands"},{"The Eyrie","Harrenhal","Kings Landing","Braavosian Coastlands"},{"Pyke","Harrenhal","Westerlands","The Reach",},
				{"The Eyrie","Dragon Stone","Westerlands","Kings Landing","The Reach"},{"Harrenhal","The Reach","Ashford","Stormlands"},
				{"Westerlands","Harrenhal","Kings Landing","Ashford","Brightwater Keep"},{"The Reach","Kings Landing","Stormlands","Brightwater keep","Skyreach"},
				{"Ashford","The Reach","Skyreach"},{"Brightwater Keep","Ashford","The Reach","Dorne"}, {"Ashford","Kings Landing"},
				{"Skyreach","The Disputed Lands"},{"The Flatlands","Hills of Norvos","Braavosian Coastlands"},{"Dragon Stone","The Flatlands","Braavos"},
				{"Braavosian Coastlands","The Golden Fields","Hills of Norvos"},{"The Flatlands","Hills of Norvos","Volantis","The Disputed Lands"},
				{"The Golden Fields","Volantis","Dorne"},{"Braavos","Forest of Lohor","Volantis","The Flatlands", "The Golden Fields"},
				{"Hills of Norvos","Northern Dathraki Sea","Volantis"},{"The Golden Fields","The Disputed Lands","Bhonash","Forest of Lohor","Northern Dathraki Sea"},
				{"Forest of Lohor","Volantis","Bhonash","Village of Lhazareen","Vaes Dorthrak"},{"Valyria","Volantis","Village of Lhazareen","Northern Dathraki Sea"},
				{"Bhonash"},{"Eastern Dathraki Sea","Palt of Illen","Village of Lhazareen","Northern Dathraki Sea","Bayasabhad"},
				{"Northern Dathraki Sea","Vaes Dorthrak","Bayasabhad","Bhonash","The Red Waste","Ghisear"},{"Village of Lhazareen","The Red Waste"},
				{"Vaes Dorthrak"}, {"Vaes Dorthrak"},{"Vaes Dorthrak","Village of Lhazareen","The Red Waste","Qarth Island"},
				{"Ghisear","Village of Lhazareen","Bayasabhad","Qarth Island"},{"The Red Waste","Bayasabhad"}};
		nodes = new ArrayList<Node>();
		for(int i = 0; i < 42; i++){	
                    if(i < 5 || i == 6 || (i > 22 && i < 29)){
                        nodes.add(new Node(territories[i], "Nomad", " ", 1, adjTerritories[i]));
                    }
                    else{
                        nodes.add(new Node(territories[i], " ", " ", 0, adjTerritories[i]));
                    }
		}
	}
	
	public ArrayList<Node> getNodes() {
		return nodes;
	}
	
}
