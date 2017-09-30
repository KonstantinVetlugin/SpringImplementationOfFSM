package ru.niias.fsm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;

public class FSMApp {

    @Autowired
    StateMachine<States, Events> stateMachine;

    void doSignals() {
        stateMachine.sendEvent(Events.REQUEST);
        stateMachine.sendEvent(Events.UPDATE);
        stateMachine.sendEvent(Events.LOAD);
        stateMachine.sendEvent(Events.LOAD_ERROR);
        stateMachine.sendEvent(Events.LOAD_AND_CONFIRM);
        stateMachine.sendEvent(Events.CONFIRM);
        stateMachine.sendEvent(Events.CONFIRM_ERROR);
        stateMachine.sendEvent(Events.FINISH);
        stateMachine.sendEvent(Events.TIMER);
    }
     public static void main (String args[])
    {
        FSMApp fsm = new FSMApp();
        //fsm.doSignals();

    }
}


