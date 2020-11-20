/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rytmipeli.kayttoliittyma;

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

/**
 *
 * @author Leo Niemi
 */
public class Kayttoliittyma extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Canvas canvas = new Canvas();
        canvas.setHeight(160);
        canvas.setWidth(800);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.DARKGREY);
        gc.fillRect(0, 0, 800, 160);
        Scene scene = new Scene(new Group());
        scene.getStylesheets().add("test.css");
        String redCSS = "-fx-background-color: linear-gradient(#FF8830, #D55A00),"
                + "radial-gradient(center 50% -40%, "
                + "radius 200%, "
                + "#FF3200 45%, " // yläosa
                + "#FF6D07 50%); " // alaosa
                + "-fx-background-radius: 2, 20;    "
                + "-fx-background-insets: 25, 10;"
                + "-fx-effect: dropshadow( three-pass-box, rgba(0,0,0,0.9),5,0.0,0,1);"
                + "-fx-text-fill: #395306;";

        String yellowCSS = "-fx-background-color: linear-gradient(#FF8830, #D55A00),"
                + "radial-gradient(center 50% -40%, "
                + "radius 200%, "
                + "#FFB200 45%, " // yläosa
                + "#FF6D07 50%); " // alaosa
                + "-fx-background-radius: 2, 20;    "
                + "-fx-background-insets: 25, 10;"
                + "-fx-effect: dropshadow( three-pass-box, rgba(0,0,0,0.9),5,0.0,0,1);"
                + "-fx-text-fill: #395306;";

        String limeCSS = "-fx-background-color: linear-gradient(#b8ee36, #80c800),"
                + "radial-gradient(center 50% -40%, "
                + "radius 200%, "
                + "#CDFF00 45%,"
                + "#80c800 50%); "
                + "-fx-background-radius: 2, 20;"
                + "-fx-background-insets: 25, 10;"
                + "-fx-effect: dropshadow( three-pass-box, rgba(0,0,0,0.4),5,0.0,0,1);"
                + "-fx-text-fill: #395306;";
        
        String greenCSS = "-fx-background-color: linear-gradient(#b8ee36, #80c800),"
                + "radial-gradient(center 50% -40%, "
                + "radius 200%, "
                + "#4EFF00 45%,"
                + "#80c800 50%); "
                + "-fx-background-radius: 2, 20;"
                + "-fx-background-insets: 25, 10;"
                + "-fx-effect: dropshadow( three-pass-box, rgba(0,0,0,0.4),5,0.0,0,1);"
                + "-fx-text-fill: #395306;";

        Insets buttonInset = new Insets(10, 10, 10, 10);

        Button tahtiButton = new Button("[1/4]");
        tahtiButton.setMinSize(160, 160);
        tahtiButton.setStyle(redCSS);
        tahtiButton.setPadding(buttonInset);
        
        Button nextButton = new Button("SKIP");
        nextButton.setMinSize(160, 160);
        nextButton.setStyle(yellowCSS);
        nextButton.setPadding(buttonInset);
        
        Button laattaButton = new Button("7");
        laattaButton.setMinSize(160, 160);
        laattaButton.setStyle(limeCSS);
        laattaButton.setPadding(buttonInset);
        
        Button molemmatButton = new Button("*");
        molemmatButton.setMinSize(160, 160);
        molemmatButton.setStyle(greenCSS);
        molemmatButton.setPadding(buttonInset);


        HBox buttons = new HBox(tahtiButton, nextButton, laattaButton, molemmatButton);
        buttons.setPadding(new Insets(1, 1, 1, 1));
        ((Group) scene.getRoot()).getChildren().add(canvas);
        ((Group) scene.getRoot()).getChildren().add(buttons);

//       
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
