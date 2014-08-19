package serverClasses;

import java.util.ArrayList;

public class CreateGameBoard {

	private ArrayList<Node> nodes;
	private String[] territories;
	private String[][] adjTerritories;	

	public CreateGameBoard(){
		territories = new String[]{"The lands of Always Winter","The Haunted forest","Bay of Seals","Castle Black","Wolfswood","Winterfell",
				"Karhold","The Rills","Barrowlands","Widow’s Watch","The Twins","The Eyrie","Pyke","Dragon Stone","Westerlands","Harrenhal",
				"Kingslanding","The Reach","Ashford","Brightwater Keep","Skyreach","Stormlands","Dorne","Braavos","Braavosian Coastlands",
				"The Flatlands","The Golden Fields","The Disputed Lands","Hills of Norvos","Forest of Lohor","Volantis","Northern Dathraki Sea",
				"Bhonash","Valyria","Vaes Dorthrak","Village of Lhazareen","Ghisear","Palt of Illen","Eastern Dathraki Sea","Bayasabhad",
				"The Red Waste","Qarth Island"};
		adjTerritories = new String[][]{{"The Haunted forest","Wolfswood"},{"The lands of Always Winter","Bay of Seals","Castle Black"},
				{"The Haunted forest","Karhold"},{"The Haunted forest","Wolfswood","Winterfell","Karhold"},
				{"The lands of Always Winter","Castle Black","The Rills","Castle Black"},{"Castle Black","Wolfswood","Karhold","Barrowlands","Widow’s Watch"},
				{"Bay of Seals","Castle Black","Winterfell"},{"Wolfswood","Barrowlands"},{"Winterfell","The Rills","Widow’s Watch","The Twins"},
				{"Winterfell","Barrowlands","The Eyrie"},{"Barrowlands","Pyke","The Eyrie"},{"Dragon Stone","Widow’s Watch","The Twins","Westerlands","Harrenhal"},
				{"The Twins","Westerlands"},{"The Eyrie","Harrenhal","Kingslanding","The Flatlands"},{"Pyke","Harrenhal","Westerlands","The Reach",},
				{"The Eyrie","Dragon Stone","Westerlands","Kingslanding","The Reach"},{"Harrenhal","The Reach","Ashford","Stormlands"},
				{"Westerlands","Harrenhal","Kingslanding","Ashford","Brightwater Keep"},{"The Reach","Kingslanding","Stormlands","Brightwater keep","Skyreach"},
				{"Ashford","The Reach","Skyreach"},	{"Brightwater Keep","Ashford","The Reach","Dorne"},	{"Ashford","Kingslanding"},
				{"Skyreach","The Disputed Lands"},{"The Flatlands","Hills of Norvos","Braavosian Coastlands"},{"Dragon Stone","The Flatlands","Braavos"},
				{"Braavosian Coastlands","The Golden Fields","Hills of Norvos"},{"The Flatlands","Hills of Norvos","Volantis","The Disputed Lands"},
				{"The Golden Fields","Volantis","Dorne"},{"Braavos","Forest of Lohor","Volantis","The Flatlands"},
				{"Hills of Norvos","Northen Dathraki Sea","Volantis"},{"The Golden Fields","The Disputed Lands","Bhonash","Forest of Lohor","Northen Dathraki Sea"},
				{"Forest of Lohor","Volantis","Bhonash","Village of Lhazareen","Vaes Dorthrak"},{"Valyria","Volantis","Village of Lhazareen","Northen Dathraki Sea"},
				{"Bhonash"},{"Eastern Dathraki Sea","Palt of Illen","Village of Lhazareen","Northen Dathraki Sea","Bayasabhad"},
				{"Northen Dathraki Sea","Vaes Dorthrak","Bayasabhad","Bhonash","The Red Waste","Ghisear"},{"Village of Lhazareen","The Red Waste"},
				{"Vaes Dorthrak"}, {"Vaes Dorthrak"},{"Vaes Dorthrak","Village of Lhazareen","The Red Waste","Qarth Island"},
				{"Ghisear","Village of Lhazareen","Bayasabhad","Qarth Island"},{"The Red Waste","Bayasabhad"}};
		nodes = new ArrayList<Node>();
		for(int i = 0; i < 42; i++){			
			nodes.add(new Node(territories[i], " ", " ", 0, adjTerritories[i]));
		}

		



	}

	public ArrayList<Node> getNodes() {
		return nodes;
	}
}
