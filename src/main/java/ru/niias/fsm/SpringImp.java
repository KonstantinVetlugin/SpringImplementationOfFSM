package ru.niias.fsm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.StaticListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import java.util.EnumSet;

public class SpringImp {

    @Configuration
    @EnableStateMachine
    static class Config extends EnumStateMachineConfigurerAdapter<States, Events> {
            @Override
            public void configure (StateMachineStateConfigurer < States, Events > states) throws Exception {
            states.withStates()
                    .initial(States.INIT)
                    .states(EnumSet.allOf(States.class));
            }

            @Override
            public void configure (StateMachineTransitionConfigurer < States, Events > transitions) throws Exception {
            transitions.withExternal()
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
        }

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

        @WithStateMachine
        static class MyBean{
            @OnTransition(target = "States.REQUEST")
            void toRequest(){

            }
        }

        static class MyApp{
            @Autowired
            private StateMachine<States, Events> stateMachine;


            void doSignals(){
                //stateMachine.start();
                stateMachine.sendEvent(Events.REQUEST);
                //System.out.println("It is work!");
            }

            public static void main(String[] args) {
                MyApp ma = new MyApp();
                ma.doSignals();
                /*SpringImp si = new SpringImp();
                si.stateMachine.start();
                si.stateMachine.sendEvent(Events.REQUEST);*/
                    // MyApp.doSignal();
                    //System.out.println("It is work!");
            }
        }


        /*public static void main(String[] args) {
            SpringImp si = new SpringImp();
            si.stateMachine.start();
            si.stateMachine.sendEvent(Events.REQUEST);
           // MyApp.doSignal();
            System.out.println("It is work!");
        }*/

       /* @Bean
        public Action<States, Events> timerAction(){
            return new Action<States, Events>() {
                public void execute(StateContext<States, Events> context) {
                    //System.out.println("Timer from " + context.getSource().getId());
                    //StateMachineUI.textArea.appendText("Current state of FSM is " + context.getSource().getId() + "\n");
                   // do something in every 1 sec
               }
           };
       }*/


    /*
        //Метод, воздращающий текущее состояние конечного автомата
        static States getCurrentState(StateMachine<States,Events> sm){
            return sm.getState().getId();
        }*/
    }
}
