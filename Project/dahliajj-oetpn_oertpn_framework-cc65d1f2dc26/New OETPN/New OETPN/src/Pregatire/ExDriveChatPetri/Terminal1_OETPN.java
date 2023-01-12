package Pregatire.ExDriveChatPetri;

import Components.*;
import DataObjects.DataString;
import DataObjects.DataTransfer;
import DataOnly.TransferOperation;
import Enumerations.LogicConnector;
import Enumerations.TransitionCondition;
import Enumerations.TransitionOperation;

public class Terminal1_OETPN {
    private static DataString p_display;

    public static void main(String[] args) {
        PetriNet pn = new PetriNet();
        pn.PetriNetName = "Terminal 1 Petri";
        pn.NetworkPort = 1080;

        DataString p_inp_user = new DataString();
        p_inp_user.SetName("P_input_user");
        pn.PlaceList.add(p_inp_user);

        DataString p_inp_term2 = new DataString();
        p_inp_term2.SetName("P_input_term2");
        pn.PlaceList.add(p_inp_term2);

        DataString p_aux = new DataString();
        p_aux.SetName("P_aux");
        pn.PlaceList.add(p_aux);

        p_display = new DataString();
        p_display.SetName("P_display");
        pn.PlaceList.add(p_display);

        DataTransfer p_output = new DataTransfer();
        p_output.SetName("P_output");
        p_output.Value = new TransferOperation("localhost", "1081", "P_input_term1");
        pn.PlaceList.add(p_output);

        DataString constantValue = new DataString();
        constantValue.SetName("ConstantValue");
        constantValue.SetValue("Bye");
        pn.ConstantPlaceList.add(constantValue);

        // transition T1
        PetriTransition t1 = new PetriTransition(pn);
        t1.TransitionName = "t1";
        t1.InputPlaceName.add("P_input_user");

        Condition T1Ct1 = new Condition(t1, "P_input_user", TransitionCondition.NotNull);

        GuardMapping grdT1 = new GuardMapping();
        grdT1.condition = T1Ct1;
        grdT1.Activations.add(new Activation(t1, "P_input_user", TransitionOperation.Move, "P_aux"));

        t1.GuardMappingList.add(grdT1);

        t1.Delay = 0;
        pn.Transitions.add(t1);

        // transition T2
        PetriTransition t2 = new PetriTransition(pn);
        t2.TransitionName = "t2";
        t2.InputPlaceName.add("P_aux");

        Condition T2Ct1 = new Condition(t2, "P_aux", TransitionCondition.NotNull);

        GuardMapping grdT2 = new GuardMapping();
        grdT2.condition = T2Ct1;
        grdT2.Activations.add(new Activation(t2, "P_aux", TransitionOperation.SendOverNetwork, "P_output"));

        t2.GuardMappingList.add(grdT2);
        t2.Delay = 0;
        pn.Transitions.add(t2);

        // transition T3
        PetriTransition t3 = new PetriTransition(pn);
        t3.TransitionName = "t3";
        t3.InputPlaceName.add("P_input_term2");
        t3.InputPlaceName.add("P_aux");

        Condition T3Ct1 = new Condition(t3, "P_input_term2", TransitionCondition.NotNull);

        GuardMapping grdT3 = new GuardMapping();
        grdT3.condition = T3Ct1;
        grdT3.Activations.add(new Activation(t3, "P_input_term2", TransitionOperation.Move, "P_display"));

        t3.GuardMappingList.add(grdT3);

        Condition T3Ct2 = new Condition(t3, "P_input_term2", TransitionCondition.Equal, "ConstantValue");
        Condition T3Ct3 = new Condition(t3, "P_aux", TransitionCondition.Equal, "ConstantValue");
        T3Ct2.SetNextCondition(LogicConnector.OR, T3Ct3);

        GuardMapping grdT31 = new GuardMapping();
        grdT31.condition = T3Ct2;
        grdT31.Activations.add(new Activation(t3, "", TransitionOperation.StopPetriNet, ""));

        t3.GuardMappingList.add(grdT31);

        t3.Delay = 0;
        pn.Transitions.add(t3);

        System.out.println("Exp1 started \n ------------------------------");
        pn.Delay = 3000;

        PetriNetWindow frame = new PetriNetWindow(false);
        frame.petriNet = pn;
        frame.setVisible(true);
    }

    public static String getDisplayMessage() {
        return p_display.Value;
    }
}
