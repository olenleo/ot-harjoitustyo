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
        File f = new File("buttonCSS.css"); // muokattu lähteestä http://fxexperience.com/2011/12/styling-fx-buttons-with-css/
        scene.getStylesheets().clear();
        scene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));

        Insets buttonInset = new Insets(10, 10, 10, 10);

        Button tahtiButton = new Button("[1/4]");
        tahtiButton.getStyleClass().add("red");
        tahtiButton.setMinSize(160, 160);

        tahtiButton.setPadding(buttonInset);

        Button nextButton = new Button("SKIP");
        nextButton.setMinSize(160, 160);
        nextButton.getStyleClass().add("orange");

        nextButton.setPadding(buttonInset);

        Button laattaButton = new Button("7");
        laattaButton.setMinSize(160, 160);
        laattaButton.getStyleClass().add("yellow");
        laattaButton.setPadding(buttonInset);

        Button molemmatButton = new Button("*");
        molemmatButton.setMinSize(160, 160);
        molemmatButton.getStyleClass().add("green");
        molemmatButton.setPadding(buttonInset);

        HBox buttons = new HBox(tahtiButton, nextButton, laattaButton, molemmatButton);

        buttons.setPadding(new Insets(10, 10, 10, 10));
        ((Group) scene.getRoot()).getChildren().add(canvas);
        ((Group) scene.getRoot()).getChildren().add(buttons);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    
    
    public static void main(String[] args) {
        launch(args);
    }

}
