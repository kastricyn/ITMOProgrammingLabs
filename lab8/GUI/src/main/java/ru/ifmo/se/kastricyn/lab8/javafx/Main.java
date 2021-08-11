package ru.ifmo.se.kastricyn.lab8.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;


public class Main extends Application {
    public static void main(String... args) {
        launch(args);
    }

    public void start(Stage stage) {
        stage.setTitle("Hello");
        FlowPane root = new FlowPane();
        Label label = new Label();
        Button button = new Button("OK");
        root.getChildren().add(label);
        root.getChildren().add(button);
        button.setOnAction((ae) -> label.setText("Привет!"));
        stage.setScene(new Scene(root, 240, 120));
        stage.show();
    }
}