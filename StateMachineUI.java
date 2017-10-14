package ru.niias.fsm;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.springframework.statemachine.StateMachine;

public class StateMachineUI extends Application {
    //Компонент для отображения состояний конечного автомата
    final static TextArea textArea = new TextArea();

    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(final Stage primaryStage) throws Exception {
        primaryStage.setTitle("State machine");

        //Инициализация конечного автомата
        SpringImp si = new SpringImp();



        //Event buttons
        Button startButton;
        Button stopButton;
        Button requestButton;
        Button updateButton;
        Button loadButton;
        Button loadErrorButton;
        Button loadAndConfirmButton;
        Button confirmButton;
        Button confirmErrorButton;
        Button finishButton;

        startButton = new Button();
        startButton.setText("Start");
        startButton.setPrefSize(170, 20);
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                stateMachine.start();
                textArea.appendText("State machine was started\n");
                textArea.appendText("Current state of FSM is " + SpringImp.getCurrentState(stateMachine).toString() + "\n");
            }
        });

        stopButton = new Button();
        stopButton.setText("Stop");
        stopButton.setPrefSize(170, 20);
        stopButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                stateMachine.stop();
                textArea.appendText("State machine was stopped\n");
                textArea.appendText("Current state of FSM is " + SpringImp.getCurrentState(stateMachine).toString() + "\n");
            }
        });

        requestButton = new Button();
        requestButton.setText("REQUEST");
        requestButton.setPrefSize(170, 20);
        requestButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                textArea.appendText("Events.REQUEST was occur\n");
                stateMachine.sendEvent(Events.REQUEST);
                textArea.appendText("Current state of FSM is " + SpringImp.getCurrentState(stateMachine).toString() + "\n");
            }
        });



        updateButton = new Button();
        updateButton.setText("UPDATE");
        updateButton.setPrefSize(170, 20);
        updateButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                textArea.appendText("Events.UPDATE was occur\n");
                stateMachine.sendEvent(Events.UPDATE);
                textArea.appendText("Current state of FSM is " + SpringImp.getCurrentState(stateMachine).toString() + "\n");
            }
        });

        loadButton = new Button();
        loadButton.setText("LOAD");
        loadButton.setPrefSize(170, 20);
        loadButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                textArea.appendText("Events.LOAD was occur\n");
                stateMachine.sendEvent(Events.LOAD);
                textArea.appendText("Current state of FSM is " + SpringImp.getCurrentState(stateMachine).toString() + "\n");
            }
        });

        loadErrorButton = new Button();
        loadErrorButton.setText("LOAD ERROR");
        loadErrorButton.setPrefSize(170, 20);
        loadErrorButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                textArea.appendText("Events.LOAD_ERROR was occur\n");
                stateMachine.sendEvent(Events.LOAD_ERROR);
                textArea.appendText("Current state of FSM is " + SpringImp.getCurrentState(stateMachine).toString() + "\n");
            }
        });

        loadAndConfirmButton = new Button();
        loadAndConfirmButton.setText("LOAD AND CONFIRM");
        loadAndConfirmButton.setPrefSize(170, 20);
        loadAndConfirmButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                textArea.appendText("Events.LOAD_AND_CONFIRM was occur\n");
                stateMachine.sendEvent(Events.LOAD_AND_CONFIRM);
                textArea.appendText("Current state of FSM is " + SpringImp.getCurrentState(stateMachine).toString() + "\n");
            }
        });

        confirmButton = new Button();
        confirmButton.setText("CONFIRM");
        confirmButton.setPrefSize(170, 20);
        confirmButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                textArea.appendText("Events.CONFIRM was occur\n");
                stateMachine.sendEvent(Events.CONFIRM);
                textArea.appendText("Current state of FSM is " + SpringImp.getCurrentState(stateMachine).toString() + "\n");
            }
        });

        confirmErrorButton = new Button();
        confirmErrorButton.setText("CONFIRM ERROR");
        confirmErrorButton.setPrefSize(170, 20);
        confirmErrorButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                textArea.appendText("Events.CONFIRM_ERROR was occur\n");
                stateMachine.sendEvent(Events.CONFIRM_ERROR);
                textArea.appendText("Current state of FSM is " + SpringImp.getCurrentState(stateMachine).toString() + "\n");
            }
        });

        finishButton = new Button();
        finishButton.setText("FINISH");
        finishButton.setPrefSize(170, 20);
        finishButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                textArea.appendText("Events.FINISH was occur\n");
                stateMachine.sendEvent(Events.FINISH);
                textArea.appendText("Current state of FSM is " + SpringImp.getCurrentState(stateMachine).toString() + "\n");
            }
        });




        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        ColumnConstraints columnWidth1 = new ColumnConstraints();
        columnWidth1.setPercentWidth(30);
        ColumnConstraints columnWidth2 = new ColumnConstraints();
        columnWidth2.setPercentWidth(70);
        gridPane.getColumnConstraints().addAll(columnWidth1, columnWidth2);


        RowConstraints rowHeight1 = new RowConstraints();
        rowHeight1.setPercentHeight(100);
        gridPane.getRowConstraints().addAll(rowHeight1);


        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.getChildren().addAll(
                startButton
                , stopButton
                , requestButton
                , updateButton
                , loadButton
                , loadErrorButton
                , loadAndConfirmButton
                , confirmButton
                , confirmErrorButton
                , finishButton);

        gridPane.add(vBox, 0, 0);
        gridPane.add(textArea, 1, 0);


        Scene scene = new Scene(gridPane, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>(){
            public void handle(WindowEvent we){
                //stateMachine.stop();
                //System.out.println("Close");
                //primaryStage.close();
            }
        });
    }

}
