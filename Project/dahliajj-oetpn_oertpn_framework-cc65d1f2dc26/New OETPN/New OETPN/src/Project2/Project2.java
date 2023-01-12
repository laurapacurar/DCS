package Project2;

import Components.*;
import DataObjects.DataCar;
import DataObjects.DataCarQueue;
import Enumerations.LogicConnector;
import Enumerations.TransitionCondition;
import Enumerations.TransitionOperation;

public class Project2 {
	public static void main(String[] args) {
        PetriNet pn = new PetriNet();
        pn.PetriNetName = "Main Petri";
        pn.NetworkPort = 1081;

        DataCar p1 = new DataCar();
        p1.SetName("P1");
        pn.PlaceList.add(p1);

        DataCarQueue p2 = new DataCarQueue();
        p2.SetName("P2");
        p2.Value.Size = 3;
        pn.PlaceList.add(p2);

        DataCarQueue p3 = new DataCarQueue();
        p3.SetName("P3");
        p3.Value.Size = 3;
        pn.PlaceList.add(p3);

        DataCarQueue p4 = new DataCarQueue();
        p4.SetName("P4");
        p4.Value.Size = 3;
        pn.PlaceList.add(p4);

        DataCar p5 = new DataCar();
        p5.SetName("P5");
        pn.PlaceList.add(p5);

        DataCar p6 = new DataCar();
        p6.SetName("P6");
        pn.PlaceList.add(p6);

        DataCar p7 = new DataCar();
        p7.SetName("P7");
        pn.PlaceList.add(p7);

        DataCar p8 = new DataCar();
        p8.SetName("P8");
        pn.PlaceList.add(p8);

        // transition t1
        PetriTransition t1 = new PetriTransition(pn);
        t1.TransitionName = "T1";
        t1.InputPlaceName.add("P1");

        Condition T1Ct1 = new Condition(t1, "P1", TransitionCondition.NotNull);
        Condition T1Ct2 = new Condition(t1, "P2", TransitionCondition.CanAddCars);
        T1Ct1.SetNextCondition(LogicConnector.AND, T1Ct2);

        GuardMapping grdT1 = new GuardMapping();
        grdT1.condition= T1Ct1;
        grdT1.Activations.add(new Activation(t1, "P1", TransitionOperation.AddElement, "P2"));
        t1.GuardMappingList.add(grdT1);

        t1.Delay = 0;
        pn.Transitions.add(t1);

        // transition t2
        PetriTransition t2 = new PetriTransition(pn);
        t2.TransitionName = "T2";
        t2.InputPlaceName.add("P2");

        Condition T2Ct1 = new Condition(t2, "P2", TransitionCondition.HaveCarForMe);
        Condition T2Ct2 = new Condition(t2, "P3", TransitionCondition.CanAddCars);
        T2Ct1.SetNextCondition(LogicConnector.AND, T2Ct2);

        GuardMapping grdT2 = new GuardMapping();
        grdT2.condition= T2Ct1;
        grdT2.Activations.add(new Activation(t2, "P2", TransitionOperation.PopElementWithTargetToQueue, "P3"));
        t2.GuardMappingList.add(grdT2);

        t2.Delay = 0;
        pn.Transitions.add(t2);

        // transition t3
        PetriTransition t3 = new PetriTransition(pn);
        t3.TransitionName = "T3";
        t3.InputPlaceName.add("P3");

        Condition T3Ct1 = new Condition(t3, "P3", TransitionCondition.HaveCarForMe);
        Condition T3Ct2 = new Condition(t3, "P4", TransitionCondition.CanAddCars);
        T3Ct1.SetNextCondition(LogicConnector.AND, T3Ct2);

        GuardMapping grdT3 = new GuardMapping();
        grdT3.condition= T3Ct1;
        grdT3.Activations.add(new Activation(t3, "P3", TransitionOperation.PopElementWithTargetToQueue, "P4"));
        t3.GuardMappingList.add(grdT3);

        t3.Delay = 0;
        pn.Transitions.add(t3);

        // transition t4
        PetriTransition t4 = new PetriTransition(pn);
        t4.TransitionName = "T4";
        t4.InputPlaceName.add("P4");

        Condition T4Ct1 = new Condition(t4, "P4", TransitionCondition.HaveCarForMe);
        Condition T4Ct2 = new Condition(t4, "P2", TransitionCondition.CanAddCars);
        T4Ct1.SetNextCondition(LogicConnector.AND, T4Ct2);

        GuardMapping grdT4 = new GuardMapping();
        grdT4.condition= T4Ct1;
        grdT4.Activations.add(new Activation(t4, "P4", TransitionOperation.PopElementWithTargetToQueue, "P2"));
        t4.GuardMappingList.add(grdT4);

        t4.Delay = 0;
        pn.Transitions.add(t4);

        // transition t1 exit
        PetriTransition t1_exit = new PetriTransition(pn);
        t1_exit.TransitionName = "T1E";
        t1_exit.InputPlaceName.add("P2");

        Condition T1ECt1 = new Condition(t1_exit, "P2", TransitionCondition.HaveCarForMe);

        GuardMapping grdT1E = new GuardMapping();
        grdT1E.condition= T1ECt1;
        grdT1E.Activations.add(new Activation(t1_exit, "P2", TransitionOperation.PopElementWithTarget, "P6"));
        t1_exit.GuardMappingList.add(grdT1E);

        t1_exit.Delay = 0;
        pn.Transitions.add(t1_exit);

        // transition t2 exit
        PetriTransition t2_exit = new PetriTransition(pn);
        t2_exit.TransitionName = "T2E";
        t2_exit.InputPlaceName.add("P3");

        Condition T2ECt1 = new Condition(t2_exit, "P3", TransitionCondition.HaveCarForMe);

        GuardMapping grdT2E = new GuardMapping();
        grdT2E.condition= T2ECt1;
        grdT2E.Activations.add(new Activation(t2_exit, "P3", TransitionOperation.PopElementWithTarget, "P7"));
        t2_exit.GuardMappingList.add(grdT2E);

        t2_exit.Delay = 0;
        pn.Transitions.add(t2_exit);

        // transition t3 exit
        PetriTransition t3_exit = new PetriTransition(pn);
        t3_exit.TransitionName = "T3E";
        t3_exit.InputPlaceName.add("P4");

        Condition T3ECt1 = new Condition(t3_exit, "P4", TransitionCondition.HaveCarForMe);

        GuardMapping grdT3E = new GuardMapping();
        grdT3E.condition= T3ECt1;
        grdT3E.Activations.add(new Activation(t3_exit, "P4", TransitionOperation.PopElementWithTarget, "P8"));
        t3_exit.GuardMappingList.add(grdT3E);

        t3_exit.Delay = 0;
        pn.Transitions.add(t3_exit);

        // transition t4 exit
        PetriTransition t4_exit = new PetriTransition(pn);
        t4_exit.TransitionName = "T4E";
        t4_exit.InputPlaceName.add("P2");

        Condition T4ECt1 = new Condition(t4_exit, "P2", TransitionCondition.HaveCarForMe);

        GuardMapping grdT4E = new GuardMapping();
        grdT4E.condition= T4ECt1;
        grdT4E.Activations.add(new Activation(t4_exit, "P2", TransitionOperation.PopElementWithTarget, "P5"));
        t4_exit.GuardMappingList.add(grdT4E);

        t4_exit.Delay = 0;
        pn.Transitions.add(t4_exit);

        System.out.println("Exp1 started \n ------------------------------");
        pn.Delay = 2000;
        //pn.Start();

        PetriNetWindow frame = new PetriNetWindow(false);
        frame.petriNet = pn;
        frame.setVisible(true);
    }
}
