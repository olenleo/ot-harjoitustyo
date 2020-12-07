package rytmipeli.kayttoliittyma;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
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

    private SovellusLogiikka sl;
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
        // YLEISHYÖDYLLISIÄ MUUTTUJIA
        sl = new SovellusLogiikka();
        score = sl.getLuku();
        guiStage = primaryStage;
        Insets inset = new Insets(10, 10, 10, 10);

        // TAUSTAVÄRI
        BackgroundFill taustavari = new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(taustavari);

        // IKKUNAN KOKO
        canvas = new Canvas();
        canvas.setHeight(180);
        canvas.setWidth(800);

        //VALIKKO- JA PELINÄKYMÄT
        sceneGame = new Scene(new Group());
        sceneMenu = new Scene(new Group());
        sceneMenu.getStylesheets().add(getClass().getResource("/buttonCSS.css").toExternalForm());
        sceneGame.getStylesheets().add(getClass().getResource("/buttonCSS.css").toExternalForm());

        // LUODAAN ELEMENTIT: sceneMenu
        newGame = new Nappi("Uusi Peli");
        newGame.getStyleClass().add("red");
        newGame.setMinSize(160, 160);
        newGame.setPadding(inset);
        newGame.setOnAction(e -> {
            sl.alustaPeli();
            guiStage.setScene(sceneGame);
            primaryStage.show();
        });
        tekstikenttaMenu = new Label();
        tekstikenttaMenu.setMinSize(320, 160);
        tekstikenttaMenu.setAlignment(Pos.CENTER);
        tekstikenttaMenu.setText("Onnea matkaan!!");

        highScore = new Nappi("High Score");
        highScore.getStyleClass().add("green");
        highScore.setMinSize(160, 160);
        highScore.setPadding(inset);
        highScore.setOnAction(e -> tekstikenttaMenu.setText("Ennätykseni: 62\nPystytkö parempaan?"));
        // Yhdistetään yhdeksi elementiksi
        menuInterface = new HBox(newGame, tekstikenttaMenu, highScore);
        menuInterface.setPadding(inset);
        menuInterface.setBackground(background);

        // LUODAAN ELEMENTIT: sceneGame
        scoreField = new Label("Score: " + score);
        state = new Label("Onnea matkaan!");

        tahtiButton = new Nappi("[1/4]", "TAHTI", sl, scoreField, state, this);
        tahtiButton.getStyleClass().add("red");
        tahtiButton.setMinSize(160, 160);
        tahtiButton.setPadding(inset);

        nextButton = new Nappi("SKIP", "SEURAAVA", sl, scoreField, state, this);
        nextButton.setMinSize(160, 160);
        nextButton.getStyleClass().add("orange");
        nextButton.setPadding(inset);

        laattaButton = new Nappi("7", "LAATTA", sl, scoreField, state, this);
        laattaButton.setMinSize(160, 160);
        laattaButton.getStyleClass().add("yellow");
        laattaButton.setPadding(inset);

        molemmatButton = new Nappi("*", "MOLEMMAT", sl, scoreField, state, this);
        molemmatButton.setMinSize(160, 160);
        molemmatButton.getStyleClass().add("green");
        molemmatButton.setPadding(inset);

        // INFO tulostaa pisteet ja mahdollisia viestejä pelaajalle.
        info = new VBox(scoreField, state);
        info.setMinSize(160, 160);

        gameInterface = new HBox(tahtiButton, nextButton, laattaButton, molemmatButton, info);
        gameInterface.setPadding(inset);
        gameInterface.setBackground(background);

        ((Group) sceneMenu.getRoot()).getChildren().add(menuInterface);
        ((Group) sceneGame.getRoot()).getChildren().add(gameInterface);

        guiStage.setTitle("Rytmipeli");
        guiStage.setScene(sceneMenu);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public Label getMid() {
        return this.tekstikenttaMenu;
    }

}
