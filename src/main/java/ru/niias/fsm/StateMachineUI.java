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
import org.springframework.statemachine.StateMachine;

public class StateMachineUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("State machine");

        //Инициализация конечного автомата
        SpringImp si = new SpringImp();
        final StateMachine<States,Events> stateMachine = si.buildMachine();
        stateMachine.start();

        //Компонент для отображения состояний конечного автомата
        final TextArea textArea = new TextArea();
        textArea.appendText("State machine was started\n");
        textArea.setEditable(false);

        //Event buttons
        Button requestButton;
        Button updateButton;
        Button loadButton;
        Button loadErrorButton;
        Button loadAndConfirmButton;
        Button confirmButton;
        Button confirmErrorButton;
        Button finishButton;

        requestButton = new Button();
        requestButton.setText("REQUEST");
        requestButton.setPrefSize(170, 20);
        requestButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                textArea.appendText("Events.REQUEST was occur\n");
                stateMachine.sendEvent(Events.REQUEST);
                textArea.appendText(SpringImp.getCurrentState(stateMachine).toString());
            }
        });



        updateButton = new Button();
        updateButton.setText("UPDATE");
        updateButton.setPrefSize(170, 20);

        loadButton = new Button();
        loadButton.setText("LOAD");
        loadButton.setPrefSize(170, 20);

        loadErrorButton = new Button();

        String loadErrorStr = "LOAD ERROR";
        loadErrorButton.setText(loadErrorStr);
        loadErrorButton.setPrefSize(170, 20);

        loadAndConfirmButton = new Button();
        loadAndConfirmButton.setText("LOAD AND CONFIRM");
        loadAndConfirmButton.setPrefSize(170, 20);

        confirmButton = new Button();
        confirmButton.setText("CONFIRM");
        confirmButton.setPrefSize(170, 20);

        confirmErrorButton = new Button();
        confirmErrorButton.setText("CONFIRM ERROR");
        confirmErrorButton.setPrefSize(170, 20);

        finishButton = new Button();
        finishButton.setText("FINISH");
        finishButton.setPrefSize(170, 20);




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
                requestButton
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
    }
}
