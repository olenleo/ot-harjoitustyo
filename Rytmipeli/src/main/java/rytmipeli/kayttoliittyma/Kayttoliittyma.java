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
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import rytmipeli.sovelluslogiikka.SovellusLogiikka;

/**
 *
 * @author Leo Niemi
 */
public class Kayttoliittyma extends Application {
    SovellusLogiikka sl = new SovellusLogiikka();
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Setup: Taustalle harmaahko canvas
        Canvas canvas = new Canvas();
        canvas.setHeight(180);
        canvas.setWidth(800);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.LIGHTGREY);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        
        // Luodaan scene-olio, lisätään .css-tiedosto
        Scene scene = new Scene(new Group());
        
         // stylesheet muokattu lähteestä http://fxexperience.com/2011/12/styling-fx-buttons-with-css/,
         // ja lisätty mukaan tiedostomuodossa jotta käyttäjä voisi modailla helposti.
         // Tai tämä ainakin tavoitteena, tulen jossain vaiheessa kyselemään .jar-tiedostoista yms pajassa :)
        File f = new File("buttonCSS.css");
        scene.getStylesheets().clear();
        scene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));

        Insets buttonInset = new Insets(10, 10, 10, 10);
        
        Nappi tahtiButton = new Nappi("[1/4]", "TAHTI");
        tahtiButton.getStyleClass().add("red");
        tahtiButton.setMinSize(160, 160);
        tahtiButton.setPadding(buttonInset);

        Button nextButton = new Nappi("SKIP", "SEURAAVA");
        nextButton.setMinSize(160, 160);
        nextButton.getStyleClass().add("orange");
        nextButton.setPadding(buttonInset);

        Button laattaButton = new Nappi("7", "LAATTA");
        laattaButton.setMinSize(160, 160);
        laattaButton.getStyleClass().add("yellow");
        laattaButton.setPadding(buttonInset);

        Button molemmatButton = new Nappi("*", "MOLEMMAT");
        molemmatButton.setMinSize(160, 160);
        molemmatButton.getStyleClass().add("green");
        molemmatButton.setPadding(buttonInset);

        TextField score = new TextField("Score: " + Integer.toString(sl.getLuku()));
        VBox info = new VBox(score);
        HBox userInterface = new HBox(tahtiButton, nextButton, laattaButton, molemmatButton, info);
        
        
        
        userInterface.setPadding(new Insets(10, 10, 10, 10));
        ((Group) scene.getRoot()).getChildren().add(canvas);
        ((Group) scene.getRoot()).getChildren().add(userInterface);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
