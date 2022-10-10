package main;

import main.classes.*;
import main.enums.*;
import main.helpers.*;
import main.interfaces.*;

import java.util.HashMap;

public class FSMMachineController {

    public static MachineStateInterface currentState = new StateZero();

    public static void main(String[] args){}

    // MAP STATE ENUM TO THE CORRESPONDING CLASS
    public static MachineStateInterface mapStateToClass(State state) {
        HashMap <State, MachineStateInterface> stateMapper = new HashMap();

        stateMapper.put(State.STATE_ZERO, new StateZero());
        stateMapper.put(State.STATE_ONE, new StateOne());
        stateMapper.put(State.STATE_TWO, new StateTwo());

        return stateMapper.get(state);
    }

    // LOOP THROUGH BINARY VALUE RETURNS END RESULT
    public double startState(String value) {

        if (!StateHelpers.containsNonNumericals(value)) { throw new IllegalArgumentException(); }

        for (var i = 0; i < value.length(); i++) {
            String letter = Character.toString(value.charAt(i));
            transitionState(currentState, letter);
        }

        int tmpState = currentState.getValue();

        // RESET
        currentState = mapStateToClass(State.STATE_ZERO);

        // RETURN
        return tmpState;
    }

    // CALLS DO WORK FUNCTION IN CLASS CORRESPONDING TO STATE PARAMETER
    static void transitionState(MachineStateInterface state, String binaryValue) {
        state.doWork(state, binaryValue);
    }
}

