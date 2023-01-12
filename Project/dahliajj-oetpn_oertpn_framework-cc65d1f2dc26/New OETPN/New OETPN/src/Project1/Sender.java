package Project1;

import java.util.ArrayList;

import Components.Activation;
import Components.Condition;
import Components.GuardMapping;
import Components.PetriNet;
import Components.PetriNetWindow;
import Components.PetriTransition;
import DataObjects.DataFloat;
import DataObjects.DataTransfer;
import DataOnly.TransferOperation;
import Enumerations.LogicConnector;
import Enumerations.TransitionCondition;
import Enumerations.TransitionOperation;

public class Sender {
	public static void main(String[] args) {
		
		PetriNet pn = new PetriNet();
		pn.PetriNetName = "Sender";
		pn.NetworkPort = 1080;
		
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
        
        DataFloat p4 = new DataFloat();
        p4.SetName("p4");
        pn.PlaceList.add(p4);

        DataTransfer p5 = new DataTransfer();
        p5.SetName("p5");
        p5.Value = new TransferOperation("localhost", "1081", "p2");
        pn.PlaceList.add(p5);
        
        DataFloat subConstantValue = new DataFloat();
        subConstantValue.SetName("subConstantValue");
        subConstantValue.SetValue(0.02f);
        pn.ConstantPlaceList.add(subConstantValue);
        
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

        Condition T1Ct1 = new Condition(t1, "p1", TransitionCondition.NotNull);
        Condition T1Ct2 = new Condition(t1, "p2", TransitionCondition.MoreThan, "zeroConst");
        T1Ct1.SetNextCondition(LogicConnector.AND, T1Ct2);

        GuardMapping grdT1 = new GuardMapping();
        grdT1.condition = T1Ct1;

		/*
		 * ArrayList<String> listInput = new ArrayList<String>(); listInput.add("p1");
		 * listInput.add("p2");
		 */
        ArrayList<String> lstInput = new ArrayList<String>();
		lstInput.add("p1");
		lstInput.add("p2");

        grdT1.Activations.add(new Activation(t1, lstInput, TransitionOperation.Prod, "p3"));
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

        ArrayList<String> sum = new ArrayList<>();
        sum.add("p3");
        sum.add("subConstantValue");
        
        grdT2.Activations.add(new Activation(t2, sum, TransitionOperation.Add, "p4"));
        t2.GuardMappingList.add(grdT2);
        t2.Delay = 0;

        pn.Transitions.add(t2);
        
        // t3
        PetriTransition t3 = new PetriTransition(pn);
        t3.TransitionName = "t3";
        t3.InputPlaceName.add("p4");
        
        Condition T3Ct1 = new Condition(t3, "p4", TransitionCondition.NotNull);
        
        GuardMapping grdT3 = new GuardMapping();
        grdT3.condition = T3Ct1;
        
        grdT3.Activations.add(new Activation(t3, "p4", TransitionOperation.SendOverNetwork, "p5"));
        grdT3.Activations.add(new Activation(t3, "p4", TransitionOperation.Move, "p1"));
		t3.GuardMappingList.add(grdT3);
		t3.Delay = 0;
		
		pn.Transitions.add(t3);
		
		//--------------------------------------------------
		
		System.out.println("Exp1 started \n ------------------------------");
		pn.Delay = 3000;

		PetriNetWindow frame = new PetriNetWindow(false);
		frame.petriNet = pn;
		frame.setVisible(true);
	}

}
