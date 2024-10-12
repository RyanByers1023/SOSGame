package sos.ryanbyers.sosjavafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class SOSGUI extends Application  {

    @Override
    public void start(Stage primaryStage) {

        //Create a label
        Label label = new Label("Example text");

        //draw some lines
        Line line1 = new Line(50, 50, 200, 50);
        Line line2 = new Line(50, 100, 200, 100);

        //create a checkbox
        CheckBox checkBox = new CheckBox("Click me!");

        //create some radio buttons
        RadioButton option1 = new RadioButton("Option 1");
        RadioButton option2 = new RadioButton("Option 2");
        RadioButton option3 = new RadioButton("Option 3");

        //group radio buttons
        ToggleGroup radioGroup = new ToggleGroup();
        option1.setToggleGroup(radioGroup);
        option2.setToggleGroup(radioGroup);
        option3.setToggleGroup(radioGroup);

        //utilize VBox to organize layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(15));
        layout.getChildren().addAll(label, line1, line2, checkBox, option1, option2, option3);

        //set scene
        Scene scene = new Scene(layout, 400, 300);

        //set stage
        primaryStage.setTitle("SOS");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}