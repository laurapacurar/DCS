package Morass;
import Components.*;
import DataObjects.DataCar;
import DataObjects.DataCarQueue;
import DataObjects.DataString;
import DataObjects.DataTransfer;
import DataOnly.TransferOperation;
import Enumerations.LogicConnector;
import Enumerations.TransitionCondition;
import Enumerations.TransitionOperation;

public class LanesIntersectionLeft {
	public static void main(String[] args) {

        PetriNet pn = new PetriNet();
        pn.PetriNetName = "Lanes Intersection1";

        pn.NetworkPort = 1080;

        DataString green = new DataString();
        green.Printable = false;
        green.SetName("green");
        green.SetValue("green");
        pn.ConstantPlaceList.add(green);

        DataString full = new DataString();
        full.Printable = false;
        full.SetName("full");
        full.SetValue("full");
        pn.ConstantPlaceList.add(full);

        // -------------------------------------------------------------------
        // -------------------------------Lane1--------------------------------
        // --------------------------------------------------------------------

        DataCar p1 = new DataCar();
        p1.SetName("P_a1");
        pn.PlaceList.add(p1);

        DataCarQueue p2 = new DataCarQueue();
        p2.Value.Size = 3;
        p2.SetName("P_x1");
        pn.PlaceList.add(p2);

        DataString p3 = new DataString();
        p3.SetName("P_TL1");
        pn.PlaceList.add(p3);

        DataCar p4 = new DataCar();
        p4.SetName("P_b1");
        pn.PlaceList.add(p4);

        DataTransfer op1 = new DataTransfer();
        op1.SetName("oP1");
        op1.Value = new TransferOperation("localhost", "1082" , "P_in1");
        pn.PlaceList.add(op1);

        // -------------------------------------------------------------------------------------
        // --------------------------------Lane2left-----------------------------------------------
        // -------------------------------------------------------------------------------------

        DataCar p5 = new DataCar();
        p5.SetName("P_a2");
        pn.PlaceList.add(p5);

        DataCarQueue p6 = new DataCarQueue();
        p6.Value.Size = 3;
        p6.SetName("P_x2");
        pn.PlaceList.add(p6);

        DataString p7 = new DataString();
        p7.SetName("P_TL2");
        pn.PlaceList.add(p7);

        DataCar p8 = new DataCar();
        p8.SetName("P_b2");
        pn.PlaceList.add(p8);

        DataTransfer op2 = new DataTransfer();
        op2.SetName("oP2");
        op2.Value = new TransferOperation("localhost", "1082" , "P_in2");
        pn.PlaceList.add(op2);

        // -------------------------------------------------------------------------------------
        // --------------------------------Lane3right-----------------------------------------------
        // -------------------------------------------------------------------------------------

        DataCar p9 = new DataCar();
        p9.SetName("P_a3");
        pn.PlaceList.add(p9);

        DataCarQueue p10 = new DataCarQueue();
        p10.Value.Size = 3;
        p10.SetName("P_x3");
        pn.PlaceList.add(p10);

        DataString p11 = new DataString();
        p11.SetName("P_TL3");
        pn.PlaceList.add(p11);

        DataCar p12 = new DataCar();
        p12.SetName("P_b3");
        pn.PlaceList.add(p12);

        DataTransfer op3 = new DataTransfer();
        op3.SetName("oP3");
        op3.Value = new TransferOperation("localhost", "1082" , "P_in3");
        pn.PlaceList.add(op3);

        // ----------------------------------------------------------------------------
        // ----------------------------Exit lane 1-------------------------------------
        // ----------------------------------------------------------------------------

        DataCarQueue p17 = new DataCarQueue();
        p17.Value.Size = 3;
        p17.SetName("P_o1");
        pn.PlaceList.add(p17);

        DataCar p18 = new DataCar();
        p18.SetName("P_o1Exit");
        pn.PlaceList.add(p18);

        // ----------------------------------------------------------------------------
        // ----------------------------Exit lane 2 left-------------------------------------
        // ----------------------------------------------------------------------------

        DataCarQueue p19 = new DataCarQueue();
        p19.Value.Size = 3;
        p19.SetName("P_o2");
        pn.PlaceList.add(p19);

        DataCar p20 = new DataCar();
        p20.SetName("P_o2Exit");
        pn.PlaceList.add(p20);

        // ----------------------------------------------------------------------------
        // ----------------------------Exit lane 3 right-------------------------------------
        // ----------------------------------------------------------------------------

        DataCar p21 = new DataCar();
        p21.SetName("P_o3");
        pn.PlaceList.add(p21);

        DataTransfer p22 = new DataTransfer();
        p22.SetName("P_o3Exit");
        p22.Value = new TransferOperation("localhost", "1081", "P_a2");
        pn.PlaceList.add(p22);

        // -------------------------------------------------------------------------------------------
        // --------------------------------Intersection-----------------------------------------------
        // -------------------------------------------------------------------------------------------

        DataCarQueue p25 = new DataCarQueue();
        p25.Value.Size = 3;
        p25.SetName("P_I");
        pn.PlaceList.add(p25);

        // T1 ------------------------------------------------
        PetriTransition t1 = new PetriTransition(pn);
        t1.TransitionName = "T_u1";
        t1.InputPlaceName.add("P_a1");
        t1.InputPlaceName.add("P_x1");

        Condition T1Ct1 = new Condition(t1, "P_a1", TransitionCondition.NotNull);
        Condition T1Ct2 = new Condition(t1, "P_x1", TransitionCondition.CanAddCars);
        T1Ct1.SetNextCondition(LogicConnector.AND, T1Ct2);

        GuardMapping grdT1 = new GuardMapping();
        grdT1.condition = T1Ct1;
        grdT1.Activations.add(new Activation(t1, "P_a1", TransitionOperation.AddElement, "P_x1"));
        t1.GuardMappingList.add(grdT1);

        Condition T1Ct3 = new Condition(t1, "P_a1", TransitionCondition.NotNull);
        Condition T1Ct4 = new Condition(t1, "P_x1", TransitionCondition.CanNotAddCars);
        T1Ct3.SetNextCondition(LogicConnector.AND, T1Ct4);

        GuardMapping grdT1_1 = new GuardMapping();
        grdT1_1.condition = T1Ct3;
        grdT1_1.Activations.add(new Activation(t1, "full", TransitionOperation.SendOverNetwork, "oP1"));
        grdT1_1.Activations.add(new Activation(t1, "P_a1", TransitionOperation.Copy, "P_a1"));
        t1.GuardMappingList.add(grdT1_1);

        t1.Delay = 0;
        pn.Transitions.add(t1);

        // T2 ------------------------------------------------
        PetriTransition t2 = new PetriTransition(pn);
        t2.TransitionName = "T_e1";
        t2.InputPlaceName.add("P_x1");
        t2.InputPlaceName.add("P_TL1");

        Condition T2Ct1 = new Condition(t2, "P_TL1", TransitionCondition.Equal, "green");
        Condition T2Ct2 = new Condition(t2, "P_x1", TransitionCondition.HaveCar);
        T2Ct1.SetNextCondition(LogicConnector.AND, T2Ct2);

        GuardMapping grdT2 = new GuardMapping();
        grdT2.condition = T2Ct1;
        grdT2.Activations.add(new Activation(t2, "P_x1", TransitionOperation.PopElementWithoutTarget, "P_b1"));
        grdT2.Activations.add(new Activation(t2, "P_TL1", TransitionOperation.Move, "P_TL1"));

        t2.GuardMappingList.add(grdT2);

        t2.Delay = 0;
        pn.Transitions.add(t2);

        // T3 ------------------------------------------------
        PetriTransition t3 = new PetriTransition(pn);
        t3.TransitionName = "T_u2";
        t3.InputPlaceName.add("P_a2");
        t3.InputPlaceName.add("P_x2");

        Condition T3Ct1 = new Condition(t3, "P_a2", TransitionCondition.NotNull);
        Condition T3Ct2 = new Condition(t3, "P_x2", TransitionCondition.CanAddCars);
        T3Ct1.SetNextCondition(LogicConnector.AND, T3Ct2);

        GuardMapping grdT3 = new GuardMapping();
        grdT3.condition = T3Ct1;
        grdT3.Activations.add(new Activation(t3, "P_a2", TransitionOperation.AddElement, "P_x2"));
        t3.GuardMappingList.add(grdT3);

        Condition T3Ct3 = new Condition(t3, "P_a2", TransitionCondition.NotNull);
        Condition T3Ct4 = new Condition(t3, "P_x2", TransitionCondition.CanNotAddCars);
        T3Ct3.SetNextCondition(LogicConnector.AND, T3Ct4);

        GuardMapping grdT3_1 = new GuardMapping();
        grdT3_1.condition = T3Ct3;
        grdT3_1.Activations.add(new Activation(t3, "full", TransitionOperation.SendOverNetwork, "oP2"));
        grdT3_1.Activations.add(new Activation(t3, "P_a2", TransitionOperation.Copy, "P_a2"));
        t3.GuardMappingList.add(grdT3_1);

        t3.Delay = 0;
        pn.Transitions.add(t3);

        // T4 ------------------------------------------------
        PetriTransition t4 = new PetriTransition(pn);
        t4.TransitionName = "T_e2";
        t4.InputPlaceName.add("P_x2");
        t4.InputPlaceName.add("P_TL2");

        Condition T4Ct1 = new Condition(t4, "P_TL2", TransitionCondition.Equal, "green");
        Condition T4Ct2 = new Condition(t4, "P_x2", TransitionCondition.HaveCar);
        T4Ct1.SetNextCondition(LogicConnector.AND, T4Ct2);

        GuardMapping grdT4 = new GuardMapping();
        grdT4.condition = T4Ct1;
        grdT4.Activations.add(new Activation(t4, "P_x2", TransitionOperation.PopElementWithoutTarget, "P_b2"));
        grdT4.Activations.add(new Activation(t4, "P_TL2", TransitionOperation.Move, "P_TL2"));
        t4.GuardMappingList.add(grdT2);

        t4.Delay = 0;
        pn.Transitions.add(t4);

        // T5 ------------------------------------------------
        PetriTransition t5 = new PetriTransition(pn);
        t5.TransitionName = "T_u3";
        t5.InputPlaceName.add("P_a3");
        t5.InputPlaceName.add("P_x3");

        Condition T5Ct1 = new Condition(t5, "P_a3", TransitionCondition.NotNull);
        Condition T5Ct2 = new Condition(t5, "P_x3", TransitionCondition.CanAddCars);
        T5Ct1.SetNextCondition(LogicConnector.AND, T5Ct2);

        GuardMapping grdT5 = new GuardMapping();
        grdT5.condition = T5Ct1;
        grdT5.Activations.add(new Activation(t5, "P_a3", TransitionOperation.AddElement, "P_x3"));
        t5.GuardMappingList.add(grdT5);

        Condition T5Ct3 = new Condition(t5, "P_a3", TransitionCondition.NotNull);
        Condition T5Ct4 = new Condition(t5, "P_x3", TransitionCondition.CanNotAddCars);
        T5Ct3.SetNextCondition(LogicConnector.AND, T5Ct4);

        GuardMapping grdT5_1 = new GuardMapping();
        grdT5_1.condition = T5Ct3;
        grdT5_1.Activations.add(new Activation(t5, "full", TransitionOperation.SendOverNetwork, "oP3"));
        grdT5_1.Activations.add(new Activation(t5, "P_a3", TransitionOperation.Copy, "P_a3"));
        t5.GuardMappingList.add(grdT5_1);

        t5.Delay = 0;
        pn.Transitions.add(t5);

        // T6 ------------------------------------------------
        PetriTransition t6 = new PetriTransition(pn);
        t6.TransitionName = "T_e3";
        t6.InputPlaceName.add("P_x3");
        t6.InputPlaceName.add("P_TL3");

        Condition T6Ct1 = new Condition(t6, "P_TL3", TransitionCondition.Equal, "green");
        Condition T6Ct2 = new Condition(t6, "P_x3", TransitionCondition.HaveCar);
        T6Ct1.SetNextCondition(LogicConnector.AND, T6Ct2);

        GuardMapping grdT6 = new GuardMapping();
        grdT6.condition = T6Ct1;
        grdT6.Activations.add(new Activation(t6, "P_x3", TransitionOperation.PopElementWithoutTarget, "P_b3"));
        grdT6.Activations.add(new Activation(t6, "P_TL3", TransitionOperation.Move, "P_TL3"));
        t6.GuardMappingList.add(grdT6);

        t6.Delay = 0;
        pn.Transitions.add(t6);

        // T9-EXIT1----------------------------------------------------------------

        PetriTransition t9 = new PetriTransition(pn);
        t9.TransitionName = "T_g1Exit";
        t9.InputPlaceName.add("P_o1");

        Condition T9Ct1 = new Condition(t9, "P_o1", TransitionCondition.HaveCar);

        GuardMapping grdT9 = new GuardMapping();
        grdT9.condition = T9Ct1;
        grdT9.Activations.add(new Activation(t9, "P_o1", TransitionOperation.PopElementWithoutTarget, "P_o1Exit"));
        t9.GuardMappingList.add(grdT9);

        t9.Delay = 0;
        pn.Transitions.add(t9);

        // T10-EXIT2----------------------------------------------------------------

        PetriTransition t10 = new PetriTransition(pn);
        t10.TransitionName = "T_g2Exit";
        t10.InputPlaceName.add("P_o2");

        Condition T10Ct1 = new Condition(t10, "P_o2", TransitionCondition.HaveCar);

        GuardMapping grdT10 = new GuardMapping();
        grdT10.condition = T10Ct1;
        grdT10.Activations.add(new Activation(t10, "P_o2", TransitionOperation.PopElementWithoutTarget, "P_o2Exit"));
        t10.GuardMappingList.add(grdT10);

        t10.Delay = 0;
        pn.Transitions.add(t10);

        // T11-EXIT3----------------------------------------------------------------

        PetriTransition t11 = new PetriTransition(pn);
        t11.TransitionName = "T_g3Exit";
        t11.InputPlaceName.add("P_o3");

        Condition T11Ct1 = new Condition(t11, "P_o3", TransitionCondition.NotNull);

        GuardMapping grdT11 = new GuardMapping();
        grdT11.condition = T11Ct1;
        grdT11.Activations.add(new Activation(t11, "P_o3", TransitionOperation.SendOverNetwork, "P_o3Exit"));
        t11.GuardMappingList.add(grdT11);

        t11.Delay = 0;
        pn.Transitions.add(t11);

        // --------------------------------------firstpart-------------------------------------------

        // T13-INTERSECTION1------------------------------------------------
        PetriTransition t13 = new PetriTransition(pn);
        t13.TransitionName = "T_i1";
        t13.InputPlaceName.add("P_b1");
        t13.InputPlaceName.add("P_I");

        Condition T13Ct1 = new Condition(t13, "P_b1", TransitionCondition.NotNull);
        Condition T13Ct2 = new Condition(t13, "P_I", TransitionCondition.CanAddCars);
        T13Ct1.SetNextCondition(LogicConnector.AND, T13Ct2);

        GuardMapping grdT13 = new GuardMapping();
        grdT13.condition = T13Ct1;
        grdT13.Activations.add(new Activation(t13, "P_b1", TransitionOperation.AddElement, "P_I"));
        t13.GuardMappingList.add(grdT13);

        t13.Delay = 0;
        pn.Transitions.add(t13);

        // T14-INTERSECTION1-EXIT-----------------------------------------------------------
        PetriTransition t14 = new PetriTransition(pn);
        t14.TransitionName = "T_g1";
        t14.InputPlaceName.add("P_I");
        t14.InputPlaceName.add("P_o1");

        Condition T14Ct1 = new Condition(t14, "P_I", TransitionCondition.HaveCarForMe);
        Condition T14Ct2 = new Condition(t14, "P_o1", TransitionCondition.CanAddCars);
        T14Ct1.SetNextCondition(LogicConnector.AND, T14Ct2);

        GuardMapping grdT14 = new GuardMapping();
        grdT14.condition = T14Ct1;
        grdT14.Activations.add(new Activation(t14, "P_I", TransitionOperation.PopElementWithTargetToQueue, "P_o1"));
        t14.GuardMappingList.add(grdT14);

        t14.Delay = 2;
        pn.Transitions.add(t14);

        // ---------------------------------SecondPart-------------------------------------------

        // T15-INTERSECTION2------------------------------------------------
        PetriTransition t15 = new PetriTransition(pn);
        t15.TransitionName = "T_i2";
        t15.InputPlaceName.add("P_b2");
        t15.InputPlaceName.add("P_I");

        Condition T15Ct1 = new Condition(t15, "P_b2", TransitionCondition.NotNull);
        Condition T15Ct2 = new Condition(t15, "P_I", TransitionCondition.CanAddCars);
        T15Ct1.SetNextCondition(LogicConnector.AND, T15Ct2);

        GuardMapping grdT15 = new GuardMapping();
        grdT15.condition = T15Ct1;
        grdT15.Activations.add(new Activation(t15, "P_b2", TransitionOperation.AddElement, "P_I"));
        t15.GuardMappingList.add(grdT15);

        t15.Delay = 0;
        pn.Transitions.add(t15);

        // T16-INTERSECTION2-EXIT-----------------------------------------------------------
        PetriTransition t16 = new PetriTransition(pn);
        t16.TransitionName = "T_g2";
        t16.InputPlaceName.add("P_I");
        t16.InputPlaceName.add("P_o2");

        Condition T16Ct1 = new Condition(t16, "P_I", TransitionCondition.HaveCarForMe);
        Condition T16Ct2 = new Condition(t16, "P_o2", TransitionCondition.CanAddCars);
        T16Ct1.SetNextCondition(LogicConnector.AND, T16Ct2);

        GuardMapping grdT16 = new GuardMapping();
        grdT16.condition = T16Ct1;
        grdT16.Activations.add(new Activation(t16, "P_I", TransitionOperation.PopElementWithTargetToQueue, "P_o2"));
        t16.GuardMappingList.add(grdT16);

        t16.Delay = 2;
        pn.Transitions.add(t16);

        // ----------------------ThirdPart----------------------------------------------------------------

        // T17-INTERSECTION3------------------------------------------------
        PetriTransition t17 = new PetriTransition(pn);
        t17.TransitionName = "T_i3";
        t17.InputPlaceName.add("P_b3");
        t17.InputPlaceName.add("P_I");

        Condition T17Ct1 = new Condition(t17, "P_b3", TransitionCondition.NotNull);
        Condition T17Ct2 = new Condition(t17, "P_I", TransitionCondition.CanAddCars);
        T17Ct1.SetNextCondition(LogicConnector.AND, T17Ct2);

        GuardMapping grdT17 = new GuardMapping();
        grdT17.condition = T17Ct1;
        grdT17.Activations.add(new Activation(t17, "P_b3", TransitionOperation.AddElement, "P_I"));
        t17.GuardMappingList.add(grdT17);

        t17.Delay = 0;
        pn.Transitions.add(t17);

        // T18-INTERSECTION3-EXIT--------------------------------------------------------

        PetriTransition t18 = new PetriTransition(pn);
        t18.TransitionName = "T_g3";
        t18.InputPlaceName.add("P_I");
        t18.InputPlaceName.add("P_o3");

        Condition T18Ct1 = new Condition(t18, "P_I", TransitionCondition.HaveCarForMe);

        GuardMapping grdT18 = new GuardMapping();
        grdT18.condition = T18Ct1;
        grdT18.Activations.add(new Activation(t18, "P_I", TransitionOperation.PopElementWithTarget, "P_o3"));
        t18.GuardMappingList.add(grdT18);

        t18.Delay = 2;
        pn.Transitions.add(t18);

        // -------------------------------------------------------------------------------------
        // ----------------------------PNStart-------------------------------------------------
        // -------------------------------------------------------------------------------------

        System.out.println("Exp1 started \n ------------------------------");
        pn.Delay = 2000;
        // pn.Start();

        PetriNetWindow frame = new PetriNetWindow(false);
        frame.petriNet = pn;
        frame.setVisible(true);
    }
}
