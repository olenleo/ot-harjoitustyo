/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rytmipeli.kayttoliittyma;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import rytmipeli.sovelluslogiikka.SovellusLogiikka;

/**
 *
 * @author Leo Niemi
 */
public class Kayttoliittyma extends Application {

    private SovellusLogiikka sl = new SovellusLogiikka();
    private int score;
    private Label scoreField, state, tekstikenttaMenu;
    private HBox gameInterface, menuInterface;
    private VBox info;
    private Nappi tahtiButton, nextButton, laattaButton, molemmatButton, newGame, highScore;
    protected static Scene sceneGame, sceneMenu;
    private static Stage guiStage;
    private Canvas canvas;
    private Stage primaryStage;

    public static Stage getStage() {
        return guiStage;
    }

    public void setMid(String text) {
        this.tekstikenttaMenu.setText(text);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        guiStage = primaryStage;
        score = sl.getLuku();
        scoreField = new Label("Score: " + score);
        state = new Label("Onnea matkaan!");
        tekstikenttaMenu = new Label();
        canvas = new Canvas();
        canvas.setHeight(180);
        canvas.setWidth(800);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.LIGHTGREY);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        info = new VBox(scoreField, state);
        sceneGame = new Scene(new Group());
        sceneMenu = new Scene(new Group());
        sceneMenu.getStylesheets().add(getClass().getResource("/buttonCSS.css").toExternalForm());
        sceneGame.getStylesheets().add(getClass().getResource("/buttonCSS.css").toExternalForm());

        Insets buttonInset = new Insets(10, 10, 10, 10);

        newGame = new Nappi("Uusi Peli");
        newGame.getStyleClass().add("red");
        newGame.setMinSize(160, 160);
        newGame.setPadding(buttonInset);
        newGame.setOnAction(e -> {
            sl.alustaPeli();
            guiStage.setScene(sceneGame);
            primaryStage.show();
        });

        tekstikenttaMenu.setMinSize(320, 160);
        tekstikenttaMenu.setAlignment(Pos.CENTER);
        tekstikenttaMenu.setText("Onnea matkaan!!");

        highScore = new Nappi("High Score");
        highScore.getStyleClass().add("green");
        highScore.setMinSize(160, 160);
        highScore.setPadding(buttonInset);
        highScore.setOnAction(e -> tekstikenttaMenu.setText("Ennätykseni: 62\nPystytkö parempaan?"));

        menuInterface = new HBox(newGame, tekstikenttaMenu, highScore, info);

        tahtiButton = new Nappi("[1/4]", "TAHTI", sl, scoreField, state, this);
        tahtiButton.getStyleClass().add("red");
        tahtiButton.setMinSize(160, 160);
        tahtiButton.setPadding(buttonInset);
       
        nextButton = new Nappi("SKIP", "SEURAAVA", sl, scoreField, state, this);
        nextButton.setMinSize(160, 160);
        nextButton.getStyleClass().add("orange");
        nextButton.setPadding(buttonInset);
     
        laattaButton = new Nappi("7", "LAATTA", sl, scoreField, state, this);
        laattaButton.setMinSize(160, 160);
        laattaButton.getStyleClass().add("yellow");
        laattaButton.setPadding(buttonInset);
     
        molemmatButton = new Nappi("*", "MOLEMMAT", sl, scoreField, state, this);
        molemmatButton.setMinSize(160, 160);
        molemmatButton.getStyleClass().add("green");
        molemmatButton.setPadding(buttonInset);
      
        gameInterface = new HBox(tahtiButton, nextButton, laattaButton, molemmatButton, info);
        gameInterface.setPadding(new Insets(10, 10, 10, 10));
        ((Group) sceneMenu.getRoot()).getChildren().add(canvas);
        ((Group) sceneMenu.getRoot()).getChildren().add(menuInterface);
        ((Group) sceneGame.getRoot()).getChildren().add(gameInterface);
        primaryStage.setTitle("Rytmipeli");
        primaryStage.setScene(sceneMenu);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public Label getMid() {
        return this.tekstikenttaMenu;
    }

}
