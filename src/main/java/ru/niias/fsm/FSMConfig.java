package ru.niias.fsm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import java.util.EnumSet;

@Configuration
@EnableStateMachine
public class FSMConfig extends EnumStateMachineConfigurerAdapter<States, Events>
{
    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states) throws Exception {
        states
                .withStates()
                .initial(States.INIT)
                .states(EnumSet.allOf(States.class));

    }
    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions) throws Exception {
        transitions
                .withExternal()
                .source(States.INIT)
                .target(States.REQUEST)
                .event(Events.REQUEST)
                .and()
                .withExternal()
                .source(States.INIT)
                .target(States.FORM_UPDATED)
                .event(Events.UPDATE)
                .and()

                .withExternal()
                .source(States.REQUEST)
                .target(States.FORM_UPDATED)
                .event(Events.UPDATE)
                .and()
                .withExternal()
                .source(States.REQUEST)
                .target(States.REQUEST)
                .event(Events.TIMER)
                .and()
                .withExternal()
                .source(States.REQUEST)
                .target(States.INIT)
                .event(Events.FINISH)
                .and()

                .withExternal()
                .source(States.FORM_UPDATED)
                .target(States.FORM_UPDATED)
                .event(Events.TIMER)
                .and()
                .withExternal()
                .source(States.FORM_UPDATED)
                .target(States.REQUEST)
                .event(Events.REQUEST)
                .and()
                .withExternal()
                .source(States.FORM_UPDATED)
                .target(States.CONFIRMED)
                .event(Events.LOAD_AND_CONFIRM)
                .and()
                .withExternal()
                .source(States.FORM_UPDATED)
                .target(States.LOADED)
                .event(Events.LOAD)
                .and()
                .withExternal()
                .source(States.FORM_UPDATED)
                .target(States.LOAD_ERROR)
                .event(Events.LOAD_ERROR)
                .and()
                .withExternal()
                .source(States.FORM_UPDATED)
                .target(States.INIT)
                .event(Events.FINISH)
                .and()

                .withExternal()
                .source(States.LOADED)
                .target(States.LOADED)
                .event(Events.TIMER)
                .and()
                .withExternal()
                .source(States.LOADED)
                .target(States.REQUEST)
                .event(Events.REQUEST)
                .and()
                .withExternal()
                .source(States.LOADED)
                .target(States.CONFIRMED)
                .event(Events.CONFIRM)
                .and()
                .withExternal()
                .source(States.LOADED)
                .target(States.CONFIRM_ERROR)
                .event(Events.CONFIRM_ERROR)
                .and()
                .withExternal()
                .source(States.LOADED)
                .target(States.FORM_UPDATED)
                .event(Events.UPDATE)
                .and()
                .withExternal()
                .source(States.LOADED)
                .target(States.FINISHED)
                .event(Events.FINISH)
                .and()

                .withExternal()
                .source(States.LOAD_ERROR)
                .target(States.LOADED)
                .event(Events.LOAD)
                .and()
                .withExternal()
                .source(States.LOAD_ERROR)
                .target(States.REQUEST)
                .event(Events.REQUEST)
                .and()
                .withExternal()
                .source(States.LOAD_ERROR)
                .target(States.INIT)
                .event(Events.FINISH)
                .and()
                .withExternal()
                .source(States.LOAD_ERROR)
                .target(States.FORM_UPDATED)
                .event(Events.UPDATE)
                .and()

                .withExternal()
                .source(States.CONFIRMED)
                .target(States.CONFIRMED)
                .event(Events.TIMER)
                .and()
                .withExternal()
                .source(States.CONFIRMED)
                .target(States.REQUEST)
                .event(Events.REQUEST)
                .and()
                .withExternal()
                .source(States.CONFIRMED)
                .target(States.FINISHED)
                .event(Events.FINISH)
                .and()
                .withExternal()
                .source(States.CONFIRMED)
                .target(States.FORM_UPDATED)
                .event(Events.UPDATE)
                .and()


                .withExternal()
                .source(States.CONFIRM_ERROR)
                .target(States.REQUEST)
                .event(Events.REQUEST)
                .and()
                .withExternal()
                .source(States.CONFIRM_ERROR)
                .target(States.FINISHED)
                .event(Events.FINISH)
                .and()
                .withExternal()
                .source(States.CONFIRM_ERROR)
                .target(States.FORM_UPDATED)
                .event(Events.UPDATE)
                .and()

                .withExternal()
                .source(States.FINISHED)
                .target(States.FINISHED)
                .event(Events.TIMER)
                .and()
                .withExternal()
                .source(States.FINISHED)
                .target(States.REQUEST)
                .event(Events.REQUEST)
                .and()
                .withExternal()
                .source(States.FINISHED)
                .target(States.FORM_UPDATED)
                .event(Events.UPDATE);
    }
    //Создание и установка слушателя конечного автомата
    @Override
    public void configure(StateMachineConfigurationConfigurer<States, Events> config) throws Exception {
        config
                .withConfiguration()
                .autoStartup(true)
                .listener(new StateMachineListener());
    }

    private static final class StateMachineListener extends StateMachineListenerAdapter<States, Events> {
        @Override
        public void stateChanged(State<States, Events> from, State<States, Events> to) {
            System.out.println("Order state changed to " + to.getId());
        }
    }

}
