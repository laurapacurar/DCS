package Project3;

import Components.*;
import DataObjects.DataString;
import Enumerations.LogicConnector;
import Enumerations.TransitionCondition;
import Enumerations.TransitionOperation;

public class ReceiverModel {
	public static void main (String[] arg) {
		PetriNet pn = new PetriNet();
        pn.PetriNetName = "Receiver Petri";
        pn.NetworkPort = 1081;

        DataString P1 = new DataString();
        P1.SetName("p1");
        P1.SetValue("Signal");
        pn.PlaceList.add(P1);

        DataString P2 = new DataString();
        P2.SetName("p2");
        pn.PlaceList.add(P2);

        DataString P3 = new DataString();
        P3.SetName("p3");
        pn.PlaceList.add(P3);

        // transition t1
        PetriTransition t1 = new PetriTransition(pn);
        t1.TransitionName = "t1";
        t1.InputPlaceName.add("p1");
        t1.InputPlaceName.add("p3");

        Condition T1Ct1 = new Condition(t1, "p1", TransitionCondition.NotNull);
        Condition T1Ct2 = new Condition(t1, "p3", TransitionCondition.NotNull);
        T1Ct1.SetNextCondition(LogicConnector.AND, T1Ct2);

        GuardMapping grdT1 = new GuardMapping();
        grdT1.condition = T1Ct1;

        grdT1.Activations.add(new Activation(t1, "p3", TransitionOperation.Move, "p2"));
        t1.GuardMappingList.add(grdT1);

        pn.Transitions.add(t1);

        // transition t2
        PetriTransition t2 = new PetriTransition(pn);
        t2.TransitionName = "t2";
        t2.InputPlaceName.add("p2");

        Condition T2Ct1 = new Condition(t2, "p2", TransitionCondition.NotNull);

        GuardMapping grdT2 = new GuardMapping();
        grdT2.condition = T2Ct1;

        grdT2.Activations.add(new Activation(t2, "p2", TransitionOperation.Move, "p1"));
        t2.GuardMappingList.add(grdT2);

        pn.Transitions.add(t2);

        System.out.println("Exp1 started \n ------------------------------");
        pn.Delay = 3000;
        // pn.Start();

        PetriNetWindow frame = new PetriNetWindow(false);
        frame.petriNet = pn;
        frame.setVisible(true);
	}

}
