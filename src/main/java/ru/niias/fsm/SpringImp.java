package ru.niias.fsm;

import org.springframework.beans.factory.support.StaticListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.StateMachineBuilder;

import java.util.EnumSet;

public class SpringImp{
    @Bean
    public Action<States, Events> timerAction(){
        return new Action<States, Events>() {
            public void execute(StateContext<States, Events> context) {
                //System.out.println("Timer from " + context.getSource().getId());
                StateMachineUI.textArea.appendText("Current state of FSM is " + context.getSource().getId() + "\n");
               // do something in every 1 sec
           }
       };
   }

       public StateMachine<States, Events> buildMachine() throws Exception {
        StateMachineBuilder.Builder<States, Events> builder = StateMachineBuilder.builder();
        builder.configureStates()
                .withStates()
                .initial(States.INIT)
                .states(EnumSet.allOf(States.class));
        builder.configureTransitions()
                //From INIT
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
                //From REQUEST
                .withExternal()
                    .source(States.REQUEST)
                    .target(States.FORM_UPDATED)
                    .event(Events.UPDATE)
                    .and()
                /*.withInternal()
                    .source(States.REQUEST)
                    .action(timerAction())
                    .timer(1000)
                    .and()*/
                .withExternal()
                    .source(States.REQUEST)
                    .target(States.INIT)
                    .event(Events.FINISH)
                    .and()

                /*.withInternal()
                    .source(States.FORM_UPDATED)
                    .action(timerAction())
                    .timer(1000)
                    .and()*/
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

                /*.withInternal()
                    .source(States.LOADED)
                    .action(timerAction())
                    .timer(1000)
                    .and()*/
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

                /*.withInternal()
                    .source(States.CONFIRMED)
                    .action(timerAction())
                    .timer(1000)
                    .and()*/
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

                /*.withInternal()
                    .source(States.FINISHED)
                    .action(timerAction())
                    .timer(1000)
                    .and()*/
                .withExternal()
                    .source(States.FINISHED)
                    .target(States.REQUEST)
                    .event(Events.REQUEST)
                    .and()
                .withExternal()
                    .source(States.FINISHED)
                    .target(States.FORM_UPDATED)
                    .event(Events.UPDATE);
        builder.configureConfiguration().withConfiguration().beanFactory(new StaticListableBeanFactory());
        return builder.build();
    }
    //Метод, воздращающий текущее состояние конечного автомата
    static States getCurrentState(StateMachine<States,Events> sm){
        return sm.getState().getId();
    }
}
