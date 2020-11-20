/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rytmipeli.kayttoliittyma;

import java.io.File;
import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Leo Niemi
 */
public class Kayttoliittyma extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Canvas canvas = new Canvas();
        canvas.setHeight(180);
        canvas.setWidth(800);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.LIGHTGREY);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        Scene scene = new Scene(new Group());
        File f = new File("buttonCSS.css");
        scene.getStylesheets().clear();
        scene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        

        String orangeCSS = "-fx-background-color: linear-gradient(#FF8830, #D55A00),"
                + "radial-gradient(center 25% -40%, "
                + "radius 200%, "
                + "#FFC080 25%, " // yläosa
                + "#FF8000 50%); " // alaosa
                + "-fx-background-radius: 2, 20;    "
                + "-fx-background-insets: 25, 10;"
                + "-fx-effect: dropshadow( three-pass-box, rgba(0,0,0,0.9),5,0.0,0,1);"
                + "-fx-text-fill: #395306;"
                + "-fx-font-size: 2em;";

        String yellowCSS = "-fx-background-color: linear-gradient(#b8ee36, #80c800),"
                + "radial-gradient(center 25% -40%, "
                + "radius 200%, "
                + "#FFFF80 25%,"
                + "#FFFF00 50%); "
                + "-fx-background-radius: 2, 20;"
                + "-fx-background-insets: 25, 10;"
                + "-fx-effect: dropshadow( three-pass-box, rgba(0,0,0,0.4),5,0.0,0,1);"
                + "-fx-text-fill: #395306;"
                + "-fx-font-size: 2em;";

        String greenCSS = "-fx-background-color: linear-gradient(#b8ee36, #80c800),"
                + "radial-gradient(center 25% -40%, "
                + "radius 200%, "
                + "#C0FF80 25%,"
                + "#80FF00 50%); "
                + "-fx-background-radius: 2, 20;"
                + "-fx-background-insets: 25, 10;"
                + "-fx-effect: dropshadow( three-pass-box, rgba(0,0,0,0.4),5,0.0,0,1);"
                + "-fx-text-fill: #395306;"
                + "-fx-font-size: 2em;";

        Insets buttonInset = new Insets(10, 10, 10, 10);

        Button tahtiButton = new Button("[1/4]");
        tahtiButton.getStyleClass().add("red");
        tahtiButton.setMinSize(160, 160);

        tahtiButton.setPadding(buttonInset);

        Button nextButton = new Button("SKIP");
        nextButton.setMinSize(160, 160);
        nextButton.setStyle(orangeCSS);
        nextButton.setPadding(buttonInset);

        Button laattaButton = new Button("7");
        laattaButton.setMinSize(160, 160);
        laattaButton.setStyle(yellowCSS);
        laattaButton.setPadding(buttonInset);

        Button molemmatButton = new Button("*");
        molemmatButton.setMinSize(160, 160);
        molemmatButton.setStyle(greenCSS);
        molemmatButton.setPadding(buttonInset);

        HBox buttons = new HBox(tahtiButton, nextButton, laattaButton, molemmatButton);

        buttons.setPadding(new Insets(10, 10, 10, 10));
        ((Group) scene.getRoot()).getChildren().add(canvas);
        ((Group) scene.getRoot()).getChildren().add(buttons);

//       
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void buttonClicked(Button button) {

        button.setOnAction((event) -> {
            button.getStyleClass().removeAll();
            FadeTransition ft = new FadeTransition(Duration.millis(3000), button.getStyleableNode());
            ft.setFromValue(1.0);
            ft.setToValue(0.1);
            ft.setCycleCount(Timeline.INDEFINITE);
            ft.setAutoReverse(true);
            ft.play();
        });

    }

    public static void main(String[] args) {
        launch(args);
    }

}
