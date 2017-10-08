package ru.niias.fsm;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

public class StateMachineUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("StateMachine");

        Button button;
        Button button2;

        button = new Button();
        button.setText("Start");
        button.setPrefSize(100, 20);

        button2 = new Button();
        button2.setText("Button2");
        button2.setPrefSize(100, 20);

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        ColumnConstraints columnWidth1 = new ColumnConstraints();
        columnWidth1.setPercentWidth(50);
        ColumnConstraints columnWidth2 = new ColumnConstraints();
        columnWidth2.setPercentWidth(50);
        gridPane.getColumnConstraints().addAll(columnWidth1, columnWidth2);


        RowConstraints rowHeight1 = new RowConstraints();
        rowHeight1.setPercentHeight(100);
        gridPane.getRowConstraints().addAll(rowHeight1);


        VBox vBox = new VBox();
        vBox.getChildren().addAll(button);


        TextFlow textFlow = new TextFlow();
        textFlow.setStyle("-fx-background-color: lightgrey;");
        Text text = new Text("Text");
        textFlow.getChildren().add(text);





        gridPane.add(vBox, 0, 0);
        gridPane.add(textFlow, 1, 0);
        //gridPane.add(background, 0,1);

        Scene scene = new Scene(gridPane, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
