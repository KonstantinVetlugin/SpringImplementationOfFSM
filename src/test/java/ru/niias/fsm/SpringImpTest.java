package ru.niias.fsm;

import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.statemachine.StateMachine;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;


public class SpringImpTest
    extends TestCase
{
    //Создание и тестирование конечного автомата
    @Test
    public void testFDM() throws Exception{
        SpringImp si = new SpringImp();
        StateMachine<States,Events> stateMachine = si.buildMachine();
        stateMachine.start();
        assertThat(stateMachine.getState().getIds(), containsInAnyOrder(States.INIT));
        stateMachine.sendEvent(Events.REQUEST);
        assertThat(stateMachine.getState().getIds(), containsInAnyOrder(States.REQUEST));
        stateMachine.sendEvent(Events.UPDATE);
        assertThat(stateMachine.getState().getIds(), containsInAnyOrder(States.FORM_UPDATED));
        stateMachine.sendEvent(Events.LOAD_ERROR);
        assertThat(stateMachine.getState().getIds(), containsInAnyOrder(States.LOAD_ERROR));
        stateMachine.sendEvent(Events.LOAD);
        assertThat(stateMachine.getState().getIds(), containsInAnyOrder(States.LOADED));
        stateMachine.sendEvent(Events.TIMER);
        assertThat(stateMachine.getState().getIds(), containsInAnyOrder(States.LOADED));
        stateMachine.sendEvent(Events.CONFIRM_ERROR);
        assertThat(stateMachine.getState().getIds(), containsInAnyOrder(States.CONFIRM_ERROR));
        stateMachine.sendEvent(Events.FINISH);
        assertThat(stateMachine.getState().getIds(), containsInAnyOrder(States.FINISHED));
        stateMachine.sendEvent(Events.REQUEST);
        assertThat(stateMachine.getState().getIds(), containsInAnyOrder(States.REQUEST));
    }
}
