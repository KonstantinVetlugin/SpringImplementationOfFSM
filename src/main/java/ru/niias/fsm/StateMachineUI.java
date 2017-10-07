package ru.niias.fsm;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class StateMachineUI extends Application {

    public Button button;
    public Button button2;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("StateMachine");

        button = new Button();
        button.setText("Just button");

        button2 = new Button();
        button.setText("Button2");

        GridPane layout = new GridPane();

        layout.add(button2, 0, 0);
        layout.add(button, 2, 2);

        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
