package Project3;

import Components.*;
import DataObjects.DataCar;
import DataObjects.DataCarQueue;
import DataObjects.DataString;
import DataObjects.DataTransfer;
import DataOnly.TransferOperation;
import Enumerations.LogicConnector;
import Enumerations.TransitionCondition;
import Enumerations.TransitionOperation;

public class LaneModelUpgrade {
	public static void main (String[] arg) {
		//--------------------------------------------------------------------
		//-------------------------------Lane1--------------------------------
		//--------------------------------------------------------------------

		PetriNet pn = new PetriNet();
		pn.PetriNetName = "Lane Petri";
		pn.NetworkPort = 1080;

		DataCar p_a = new DataCar();
		p_a.SetName("P_a");
		pn.PlaceList.add(p_a);

		DataCarQueue p_x = new DataCarQueue();
		p_x.Value.Size = 3;
		p_x.SetName("P_x");
		pn.PlaceList.add(p_x);

		DataString TL = new DataString();
		TL.SetName("P_TL");
		pn.PlaceList.add(TL);

		DataCar p_b = new DataCar();
		p_b.SetName("P_b");
		pn.PlaceList.add(p_b);

		// Implementing OP1 as an output channel connected to the controller
		DataTransfer OP1 = new DataTransfer();
		OP1.SetName("OP1");
		OP1.Value = new TransferOperation("localhost", "1081", "p3");
		pn.PlaceList.add(OP1);

		DataString full = new DataString();
		full.SetName("full");
		full.SetValue("full");
		pn.ConstantPlaceList.add(full);


		DataString green= new DataString();
		green.SetName("green");
		green.SetValue("green");
		green.Printable= false;
		pn.ConstantPlaceList.add(green);

		// t_u transition
		PetriTransition t1 = new PetriTransition(pn);
		t1.TransitionName = "T_u";
		t1.InputPlaceName.add("P_a");
		t1.InputPlaceName.add("P_x");

		Condition T1Ct1 = new Condition(t1, "P_a", TransitionCondition.NotNull);
		Condition T1Ct2 = new Condition(t1, "P_x", TransitionCondition.CanAddCars);
		T1Ct1.SetNextCondition(LogicConnector.AND, T1Ct2);

		GuardMapping grdT1 = new GuardMapping();
		grdT1.condition= T1Ct1;
		grdT1.Activations.add(new Activation(t1, "P_a", TransitionOperation.AddElement, "P_x"));
		t1.GuardMappingList.add(grdT1);

		Condition T1Ct3 = new Condition(t1, "P_a", TransitionCondition.NotNull);
		Condition T1Ct4 = new Condition(t1, "P_x", TransitionCondition.CanNotAddCars);
		T1Ct3.SetNextCondition(LogicConnector.AND, T1Ct4);

		GuardMapping grdT11 = new GuardMapping();
		grdT11.condition= T1Ct3;
		grdT11.Activations.add(new Activation(t1, "P_a", TransitionOperation.SendOverNetwork, "OP1"));
		grdT11.Activations.add(new Activation(t1, "P_a", TransitionOperation.Copy, "P_a"));
		t1.GuardMappingList.add(grdT11);

		t1.Delay = 0;
		pn.Transitions.add(t1);

		// t_e transition
		PetriTransition t2 = new PetriTransition(pn);
		t2.TransitionName = "T_e";
		t2.InputPlaceName.add("P_x");
		t2.InputPlaceName.add("P_TL");


		Condition T2Ct1 = new Condition(t2, "P_TL", TransitionCondition.Equal,"green");
		Condition T2Ct2 = new Condition(t2, "P_x", TransitionCondition.HaveCar);
		T2Ct1.SetNextCondition(LogicConnector.AND, T2Ct2);

		GuardMapping grdT2 = new GuardMapping();
		grdT2.condition= T2Ct1;
		grdT2.Activations.add(new Activation(t2, "P_x", TransitionOperation.PopElementWithoutTarget, "P_b"));
		grdT2.Activations.add(new Activation(t2, "P_TL", TransitionOperation.Move, "P_TL"));

		t2.GuardMappingList.add(grdT2);

		t2.Delay = 0;
		pn.Transitions.add(t2);



		//-------------------------------------------------------------------------------------
		//----------------------------PN Start-------------------------------------------------
		//-------------------------------------------------------------------------------------

		System.out.println("Exp1 started \n ------------------------------");
		pn.Delay = 2000;
		//pn.Start();

		PetriNetWindow frame = new PetriNetWindow(false);
		frame.petriNet = pn;
		frame.setVisible(true);	
	}

}
