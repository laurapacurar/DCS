package Project1;

import Components.*;
import DataObjects.DataFloat;
import DataObjects.DataTransfer;
import DataOnly.TransferOperation;
import Enumerations.LogicConnector;
import Enumerations.TransitionCondition;
import Enumerations.TransitionOperation;

public class Receiver {
	 public static void main(String[] args) {
		 PetriNet pn = new PetriNet();
	     pn.PetriNetName = "Receiver";
	     pn.NetworkPort = 1081;
	     
	     //------------ Locations ---------------------
	     DataFloat p1 = new DataFloat();
	     p1.SetName("p1");
	     p1.SetValue(1.0f);
	     pn.PlaceList.add(p1);
	     
	     DataFloat p2 = new DataFloat();
	     p2.SetName("p2");
	     pn.PlaceList.add(p2);
	     
	     DataFloat p3 = new DataFloat();
	     p3.SetName("p3");
	     pn.PlaceList.add(p3);
	     
	     DataFloat zeroConst = new DataFloat();
	        zeroConst.SetName("zeroConst");
	        zeroConst.SetValue(0.0f);
	        pn.ConstantPlaceList.add(zeroConst);

	     //------------ Transitions ---------------------
	     // t1
	     PetriTransition t1 = new PetriTransition(pn);
	     t1.TransitionName = "t1";
	     t1.InputPlaceName.add("p1");
	     t1.InputPlaceName.add("p2");

	     Condition T1Ct1 = new Condition(t1, "p2", TransitionCondition.MoreThan, "zeroConst");
	     Condition T1Ct2 = new Condition(t1, "p1", TransitionCondition.NotNull);
	     T1Ct1.SetNextCondition(LogicConnector.AND, T1Ct2);

	     GuardMapping grdT1 = new GuardMapping();
	     grdT1.condition = T1Ct1;

	     grdT1.Activations.add(new Activation(t1, "p2", TransitionOperation.Move, "p3"));
	     t1.GuardMappingList.add(grdT1);
	     t1.Delay = 0;

	     pn.Transitions.add(t1);
	     
	     // t2
	     PetriTransition t2 = new PetriTransition(pn);
	     t2.TransitionName = "t2";
	     t2.InputPlaceName.add("p3");

	     Condition T2Ct1 = new Condition(t2, "p3", TransitionCondition.NotNull);

	     GuardMapping grdT2 = new GuardMapping();
	     grdT2.condition = T2Ct1;

	     grdT2.Activations.add(new Activation(t2, "p3", TransitionOperation.Move, "p1"));
	     t2.GuardMappingList.add(grdT2);
	     t2.Delay = 0;

	     pn.Transitions.add(t2);
	     
	     //--------------------------------------------------

	     System.out.println("Exp1 started \n ------------------------------");
	     pn.Delay = 3000;

	     PetriNetWindow frame = new PetriNetWindow(false);
	     frame.petriNet = pn;
	     frame.setVisible(true);
	 }
}
