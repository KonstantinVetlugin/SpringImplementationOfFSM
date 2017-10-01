package ru.niias.fsm;

import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.statemachine.StateMachine;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;


public class SpringImpTest
    extends TestCase
{
    @Test
    public void testFDM() throws Exception{
        SpringImp si = new SpringImp();
        StateMachine<States,Events> stateMachine = si.buildMachine();
        stateMachine.start();
        assertThat(stateMachine.getState().getIds(), containsInAnyOrder(States.INIT));
        stateMachine.sendEvent(Events.REQUEST);
        assertThat(stateMachine.getState().getIds(), containsInAnyOrder(States.REQUEST));




    }
}
