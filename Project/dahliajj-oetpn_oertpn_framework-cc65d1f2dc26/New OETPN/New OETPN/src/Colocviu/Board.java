package Colocviu;

import Components.PetriNet;
import DataObjects.DataString;

public class Board {
	public static void main(String[] args) {
		PetriNet pn = new PetriNet();
		pn.PetriNetName = "Elctronic Board";
		pn.NetworkPort = 1081;

		DataString in1 = new DataString();
		in1.SetName("P_input_user");
		pn.PlaceList.add(in1);

		DataString out1 = new DataString();
		out1.SetName("P_output_user");
		pn.PlaceList.add(out1);
	}
}
