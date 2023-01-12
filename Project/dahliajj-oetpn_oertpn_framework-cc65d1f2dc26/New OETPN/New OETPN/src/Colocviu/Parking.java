package Colocviu;

import Components.*;
import DataObjects.DataCar;
import DataObjects.DataTransfer;
import DataOnly.TransferOperation;
import Enumerations.LogicConnector;
import Enumerations.TransitionCondition;
import Enumerations.TransitionOperation;

public class Parking {

	public static void main(String[] args) {
		PetriNet pn = new PetriNet();
		pn.PetriNetName = "Park";
		pn.NetworkPort = 1080;
		
		// PARKING SPACES, INPUT, OUTPUT
		DataCar input = new DataCar();
        input.SetName("input");
        pn.PlaceList.add(input);
		
		DataCar p0 = new DataCar();
        p0.SetName("P0");
        pn.PlaceList.add(p0);
	    
		DataCar p1 = new DataCar();
        p1.SetName("P1");
        pn.PlaceList.add(p1);
        
        DataCar p2 = new DataCar();
        p2.SetName("P21");
        pn.PlaceList.add(p2);
        
        DataCar p3 = new DataCar();
        p3.SetName("P3");
        pn.PlaceList.add(p1);
        
        DataCar p4 = new DataCar();
        p4.SetName("P4");
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
        
        DataCar p9 = new DataCar();
        p9.SetName("P9");
        pn.PlaceList.add(p9);
        
        DataCar output = new DataCar();
        output.SetName("output");
        pn.PlaceList.add(output);
	    
        
        //----------------------------------------
        //output channels of input transitions
        DataTransfer ui1 = new DataTransfer();
        ui1.SetName("OP1");
        ui1.Value = new TransferOperation("localhost", "1081" , "in1");
		pn.PlaceList.add(ui1);
		
		DataTransfer ui2 = new DataTransfer();
		ui2.SetName("OP2");
		ui2.Value = new TransferOperation("localhost", "1081" , "in1");
		pn.PlaceList.add(ui2);
		
		DataTransfer ui3 = new DataTransfer();
		ui3.SetName("OP3");
		ui3.Value = new TransferOperation("localhost", "1081" , "in1");
		pn.PlaceList.add(ui3);
		
		DataTransfer ui4 = new DataTransfer();
		ui4.SetName("OP4");
		ui4.Value = new TransferOperation("localhost", "1081" , "in1");
		pn.PlaceList.add(ui1);
		
		DataTransfer ui5 = new DataTransfer();
		ui5.SetName("OP5");
		ui5.Value = new TransferOperation("localhost", "1081" , "in1");
		pn.PlaceList.add(ui5);
		
		DataTransfer ui6 = new DataTransfer();
		ui6.SetName("OP6");
		ui6.Value = new TransferOperation("localhost", "1081" , "in1");
		pn.PlaceList.add(ui6);
		
		DataTransfer ui7 = new DataTransfer();
		ui7.SetName("OP7");
		ui7.Value = new TransferOperation("localhost", "1081" , "in1");
		pn.PlaceList.add(ui7);
		
		DataTransfer ui8 = new DataTransfer();
		ui8.SetName("OP8");
		ui8.Value = new TransferOperation("localhost", "1081" , "in1");
		pn.PlaceList.add(ui8);
		
		DataTransfer ui9 = new DataTransfer();
		ui9.SetName("OP9");
		ui9.Value = new TransferOperation("localhost", "1081" , "in1");
		pn.PlaceList.add(ui9);
        
		//------------------------------------------------
		//input channels of exit transitions
		DataTransfer uo1 = new DataTransfer();
		uo1.SetName("OP1_exit");
		uo1.Value = new TransferOperation("localhost", "1081" , "out1");
		pn.PlaceList.add(uo1);
		
		DataTransfer uo2 = new DataTransfer();
		uo2.SetName("OP2_exit");
		uo2.Value = new TransferOperation("localhost", "1081" , "out1");
		pn.PlaceList.add(uo2);
		
		DataTransfer uo3 = new DataTransfer();
		uo3.SetName("OP3_exit");
		uo3.Value = new TransferOperation("localhost", "1081" , "out1");
		pn.PlaceList.add(uo3);
		
		DataTransfer uo4 = new DataTransfer();
		uo4.SetName("OP4_exit");
		uo4.Value = new TransferOperation("localhost", "1081" , "out1");
		pn.PlaceList.add(uo4);
		
		DataTransfer uo5 = new DataTransfer();
		uo5.SetName("OP5_exit");
		uo5.Value = new TransferOperation("localhost", "1081" , "out1");
		pn.PlaceList.add(uo5);
		
		DataTransfer uo6 = new DataTransfer();
		uo6.SetName("OP6_exit");
		uo6.Value = new TransferOperation("localhost", "1081" , "out1");
		pn.PlaceList.add(uo6);
		
		DataTransfer uo7 = new DataTransfer();
		uo7.SetName("OP7_exit");
		uo7.Value = new TransferOperation("localhost", "1081" , "out1");
		pn.PlaceList.add(uo7);
		
		DataTransfer uo8 = new DataTransfer();
		uo8.SetName("OP8_exit");
		uo8.Value = new TransferOperation("localhost", "1081" , "out1");
		pn.PlaceList.add(uo8);
		
		DataTransfer uo9 = new DataTransfer();
		uo9.SetName("OP9_exit");
		uo9.Value = new TransferOperation("localhost", "1081" , "out1");
		pn.PlaceList.add(uo9);
		
		//------------------------ INPUT TRANSITIONS ------------------------------------------
		//T0
		
		PetriTransition t0 = new PetriTransition(pn);
		t0.TransitionName = "t0";
		t0.InputPlaceName.add("input");

        Condition T0Ct1 = new Condition(t0, "input", TransitionCondition.HaveCar);

        GuardMapping grdT0 = new GuardMapping();
        grdT0.condition = T0Ct1;
        //grdT0.Activations.add(new Activation(t0, "input", TransitionOperation.SendOverNetwork, "in1"));
        grdT0.Activations.add(new Activation(t0, "input", TransitionOperation.Move, "P0"));

        t0.GuardMappingList.add(grdT0);

        t0.Delay = 5;
        pn.Transitions.add(t0);
        
        //T1
        PetriTransition t1 = new PetriTransition(pn);
        t1.TransitionName = "t1";
        t1.InputPlaceName.add("P0");

        Condition T1Ct1 = new Condition(t1, "P0", TransitionCondition.HaveCar);

        GuardMapping grdT1 = new GuardMapping();
        grdT1.condition = T1Ct1;
        grdT1.Activations.add(new Activation(t1, "P0", TransitionOperation.Move, "P1"));

        t1.GuardMappingList.add(grdT1);
        
      //T2
        PetriTransition t2 = new PetriTransition(pn);
        t2.TransitionName = "t2";
        t2.InputPlaceName.add("P0");

        Condition T2Ct1 = new Condition(t2, "P0", TransitionCondition.HaveCar);

        GuardMapping grdT2 = new GuardMapping();
        grdT2.condition = T2Ct1;
        grdT2.Activations.add(new Activation(t2, "P0", TransitionOperation.Move, "P2"));

        t2.GuardMappingList.add(grdT2);
        
        
      //T3
        PetriTransition t3 = new PetriTransition(pn);
        t3.TransitionName = "t3";
        t3.InputPlaceName.add("P0");

        Condition T3Ct1 = new Condition(t3, "P0", TransitionCondition.HaveCar);

        GuardMapping grdT3 = new GuardMapping();
        grdT3.condition = T3Ct1;
        grdT3.Activations.add(new Activation(t3, "P0", TransitionOperation.Move, "P3"));

        t3.GuardMappingList.add(grdT3);
        
      //T4
        PetriTransition t4 = new PetriTransition(pn);
        t4.TransitionName = "t4";
        t4.InputPlaceName.add("P0");

        Condition T4Ct1 = new Condition(t4, "P0", TransitionCondition.HaveCar);

        GuardMapping grdT4 = new GuardMapping();
        grdT4.condition = T4Ct1;
        grdT4.Activations.add(new Activation(t4, "P0", TransitionOperation.Move, "P4"));

        t4.GuardMappingList.add(grdT4);
        
      //T5
        PetriTransition t5 = new PetriTransition(pn);
        t5.TransitionName = "t5";
        t5.InputPlaceName.add("P0");

        Condition T5Ct1 = new Condition(t5, "P0", TransitionCondition.HaveCar);

        GuardMapping grdT5 = new GuardMapping();
        grdT5.condition = T5Ct1;
        grdT5.Activations.add(new Activation(t5, "P0", TransitionOperation.Move, "P5"));

        t5.GuardMappingList.add(grdT5);
        
      //T6
        PetriTransition t6 = new PetriTransition(pn);
        t6.TransitionName = "t6";
        t6.InputPlaceName.add("P0");

        Condition T6Ct1 = new Condition(t6, "P0", TransitionCondition.HaveCar);

        GuardMapping grdT6 = new GuardMapping();
        grdT6.condition = T6Ct1;
        grdT6.Activations.add(new Activation(t6, "P0", TransitionOperation.Move, "P6"));

        t6.GuardMappingList.add(grdT6);
        
      //T7
        PetriTransition t7 = new PetriTransition(pn);
        t7.TransitionName = "t7";
        t7.InputPlaceName.add("P0");

        Condition T7Ct1 = new Condition(t7, "P0", TransitionCondition.HaveCar);

        GuardMapping grdT7 = new GuardMapping();
        grdT7.condition = T7Ct1;
        grdT7.Activations.add(new Activation(t7, "P0", TransitionOperation.Move, "P7"));

        t7.GuardMappingList.add(grdT7);
        
      //T8
        PetriTransition t8 = new PetriTransition(pn);
        t8.TransitionName = "t8";
        t8.InputPlaceName.add("P0");

        Condition T8Ct1 = new Condition(t8, "P0", TransitionCondition.HaveCar);

        GuardMapping grdT8 = new GuardMapping();
        grdT8.condition = T8Ct1;
        grdT8.Activations.add(new Activation(t8, "P0", TransitionOperation.Move, "P8"));

        t8.GuardMappingList.add(grdT8);
        
      //------------------------ OUTPUT TRANSITIONS ------------------------------------------
                //T1_exit
        PetriTransition t1_exit = new PetriTransition(pn);
        t1_exit.TransitionName = "t1_exit";
        t1_exit.InputPlaceName.add("P1");

        Condition T1eCt1 = new Condition(t1_exit, "P1", TransitionCondition.CanAddCars);
        Condition T1eCt2 = new Condition(t1_exit, "uo1", TransitionCondition.NotNull);
        T1eCt1.SetNextCondition(LogicConnector.AND, T1eCt2);

        GuardMapping grdT1e = new GuardMapping();
        grdT1e.condition = T1eCt1;
        grdT1e.Activations.add(new Activation(t1_exit, "P1", TransitionOperation.Move, "P9"));

        t1_exit.GuardMappingList.add(grdT1e);

        //T2_exit
        PetriTransition t2_exit = new PetriTransition(pn);
        t2_exit.TransitionName = "t2_exit";
        t2_exit.InputPlaceName.add("P2");

        Condition T2eCt1 = new Condition(t2_exit, "P2", TransitionCondition.CanAddCars);
        Condition T2eCt2 = new Condition(t2_exit, "uo2", TransitionCondition.NotNull);
        T2eCt1.SetNextCondition(LogicConnector.AND, T2eCt2);

        GuardMapping grdT2e = new GuardMapping();
        grdT2e.condition = T2eCt1;
        grdT2e.Activations.add(new Activation(t2_exit, "P2", TransitionOperation.Move, "P9"));

        t2_exit.GuardMappingList.add(grdT2e);


        //T3_exit
        PetriTransition t3_exit = new PetriTransition(pn);
        t3_exit.TransitionName = "t3_exit";
        t3_exit.InputPlaceName.add("P3");

        Condition T3eCt1 = new Condition(t3_exit, "P3", TransitionCondition.CanAddCars);
        Condition T3eCt2 = new Condition(t3_exit, "uo3", TransitionCondition.NotNull);
        T3eCt1.SetNextCondition(LogicConnector.AND, T3eCt2);

        GuardMapping grdT3e = new GuardMapping();
        grdT3e.condition = T3eCt1;
        grdT3e.Activations.add(new Activation(t3_exit, "P3", TransitionOperation.Move, "P9"));

        t3_exit.GuardMappingList.add(grdT3e);

        //T4_exit
        PetriTransition t4_exit = new PetriTransition(pn);
        t4_exit.TransitionName = "t4_exit";
        t4_exit.InputPlaceName.add("P4");

        Condition T4eCt1 = new Condition(t4_exit, "P4", TransitionCondition.CanAddCars);
        Condition T4eCt2 = new Condition(t2_exit, "uo4", TransitionCondition.NotNull);
        T4eCt1.SetNextCondition(LogicConnector.AND, T4eCt2);

        GuardMapping grdT4e = new GuardMapping();
        grdT4e.condition = T4eCt1;
        grdT4e.Activations.add(new Activation(t4_exit, "P4", TransitionOperation.Move, "P9"));

        t4_exit.GuardMappingList.add(grdT4e);

        //T5_exit
        PetriTransition t5_exit = new PetriTransition(pn);
        t5_exit.TransitionName = "t5_exit";
        t5_exit.InputPlaceName.add("P5");

        Condition T5eCt1 = new Condition(t5_exit, "P5", TransitionCondition.CanAddCars);
        Condition T5eCt2 = new Condition(t5_exit, "uo5", TransitionCondition.NotNull);
        T5eCt1.SetNextCondition(LogicConnector.AND, T5eCt2);
        

        GuardMapping grdT5e = new GuardMapping();
        grdT5e.condition = T5eCt1;
        grdT5e.Activations.add(new Activation(t5_exit, "P5", TransitionOperation.Move, "P9"));

        t5_exit.GuardMappingList.add(grdT5e);

        //T6_exit
        PetriTransition t6_exit = new PetriTransition(pn);
        t6_exit.TransitionName = "t6_exit";
        t6_exit.InputPlaceName.add("P6");

        Condition T6eCt1 = new Condition(t6_exit, "P6", TransitionCondition.CanAddCars);
        Condition T6eCt2 = new Condition(t6_exit, "uo6", TransitionCondition.NotNull);
        T6eCt1.SetNextCondition(LogicConnector.AND, T6eCt2);

        GuardMapping grdT6e = new GuardMapping();
        grdT6e.condition = T6eCt1;
        grdT6e.Activations.add(new Activation(t6_exit, "P6", TransitionOperation.Move, "P9"));

        t6_exit.GuardMappingList.add(grdT6e);

        //T7_exit
        PetriTransition t7_exit = new PetriTransition(pn);
        t7_exit.TransitionName = "t7_exit";
        t7_exit.InputPlaceName.add("P7");

        Condition T7eCt1 = new Condition(t7_exit, "P7", TransitionCondition.CanAddCars);
        Condition T7eCt2 = new Condition(t7_exit, "uo7", TransitionCondition.NotNull);
        T7eCt1.SetNextCondition(LogicConnector.AND, T7eCt2);

        GuardMapping grdT7e = new GuardMapping();
        grdT7e.condition = T7eCt1;
        grdT7e.Activations.add(new Activation(t7_exit, "P7", TransitionOperation.Move, "P9"));

        t7_exit.GuardMappingList.add(grdT7e);

        //T8_exit
        PetriTransition t8_exit = new PetriTransition(pn);
        t8_exit.TransitionName = "t8_exit";
        t8_exit.InputPlaceName.add("P8");

        Condition T8eCt1 = new Condition(t8_exit, "P8", TransitionCondition.CanAddCars);
        Condition T8eCt2 = new Condition(t8_exit, "uo8", TransitionCondition.NotNull);
        T8eCt1.SetNextCondition(LogicConnector.AND, T8eCt2);

        GuardMapping grdT8e = new GuardMapping();
        grdT8e.condition = T8eCt1;
        grdT8e.Activations.add(new Activation(t8_exit, "P8", TransitionOperation.Move, "P9"));

        t8_exit.GuardMappingList.add(grdT8e);
        
      //T9

        PetriTransition t9 = new PetriTransition(pn);
        t9.TransitionName = "t9";
        t9.InputPlaceName.add("P9");

        Condition T9Ct1 = new Condition(t9, "P9", TransitionCondition.HaveCar);

        GuardMapping grdT9 = new GuardMapping();
        grdT9.condition = T9Ct1;
        //grdT0.Activations.add(new Activation(t0, "input", TransitionOperation.SendOverNetwork, "in1"));
        grdT9.Activations.add(new Activation(t9, "P9", TransitionOperation.Move, "output"));

        t9.GuardMappingList.add(grdT9);

        t9.Delay = 5;
        pn.Transitions.add(t9);
        
        
        
        
        
        
        
        
        
        
        
        System.out.println("Exp1 started \n ------------------------------");
        pn.Delay = 3000;

        PetriNetWindow frame = new PetriNetWindow(false);
        frame.petriNet = pn;
        frame.setVisible(true);
	}
}
