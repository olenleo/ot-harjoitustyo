package rytmipeli.kayttoliittyma;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import rytmipeli.aaniefektit.Aanikirjasto;
import rytmipeli.pisteet.HighScoreManager;
import rytmipeli.sovelluslogiikka.SovellusLogiikka;

/**
 *
 * @author Leo Niemi
 */
public class Kayttoliittyma extends Application {

    private SovellusLogiikka sl;
    private int score;
    private Label scoreField, state, tekstikenttaMenu;
    private HBox gameInterface, menuInterface, scoreInterface, gameOverInterface;
    private VBox info;
    private Nappi tahtiButton, nextButton, laattaButton, molemmatButton, newGame, highScore;
    protected static Scene sceneGame, sceneMenu, sceneScore, sceneGameOver;
    private static Stage guiStage;
    private Canvas canvas;
    private TableView tableview;
    private HighScoreManager highscoremanager;
    private HighScoreTableView hsTableView;
    private final String tiedosto = "pistot.txt";

    @Override
    public void start(Stage primaryStage) throws Exception {
        // YLEISHYÖDYLLISIÄ MUUTTUJIA
        sl = new SovellusLogiikka();
        score = sl.getLuku();
        guiStage = primaryStage;
        Insets inset = new Insets(10, 10, 10, 10);
        highscoremanager = new HighScoreManager(tiedosto);

        // TAUSTAVÄRI
        BackgroundFill taustavari = new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(taustavari);

        // TABLEVIEW pistetilastolle
        hsTableView = new HighScoreTableView(tiedosto, highscoremanager);
        tableview = new TableView();
        tableview = hsTableView.getTableView();

        // IKKUNAN KOKO
        canvas = new Canvas();
        canvas.setHeight(180);
        canvas.setWidth(800);

        //VALIKKO- JA PELINÄKYMÄT
        sceneGame = new Scene(new Group());
        sceneMenu = new Scene(new Group());
        sceneScore = new Scene(new Group());
        sceneGameOver = new Scene(new Group());
        //LISÄTÄÄN STYLESHEET
        sceneMenu.getStylesheets().add(getClass().getResource("/buttonCSS.css").toExternalForm());
        sceneGame.getStylesheets().add(getClass().getResource("/buttonCSS.css").toExternalForm());
        sceneScore.getStylesheets().add(getClass().getResource("/buttonCSS.css").toExternalForm());
        sceneGameOver.getStylesheets().add(getClass().getResource("/buttonCSS.css").toExternalForm());

        // LUODAAN ELEMENTIT: sceneMenu
        newGame = new Nappi("Uusi Peli");
        newGame.getStyleClass().add("red");
        newGame.setMinSize(160, 160);
        newGame.setPadding(inset);
        newGame.setOnAction(e -> {
            sl.alustaPeli();
            alustaLabelit();
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
        highScore.setOnAction(e -> {
//            hsTableView.sortTableView();
            guiStage.setScene(sceneScore);
            primaryStage.show();
        });
        // YHDISTETÄÄN
        menuInterface = new HBox(newGame, tekstikenttaMenu, highScore);
        menuInterface.setPadding(inset);
        menuInterface.setBackground(background);

        scoreField = new Label("Score: " + score);
        state = new Label("Onnea matkaan!");

        // LUODAAN ELEMENTIT: sceneGame
        tahtiButton = new Nappi("[1/4]", "TAHTI", sl, scoreField, state, this, highscoremanager);
        tahtiButton.getStyleClass().add("red");
        tahtiButton.setMinSize(160, 160);
        tahtiButton.setPadding(inset);

        nextButton = new Nappi("SKIP", "SEURAAVA", sl, scoreField, state, this, highscoremanager);
        nextButton.setMinSize(160, 160);
        nextButton.getStyleClass().add("orange");
        nextButton.setPadding(inset);

        laattaButton = new Nappi("7", "LAATTA", sl, scoreField, state, this, highscoremanager);
        laattaButton.setMinSize(160, 160);
        laattaButton.getStyleClass().add("yellow");
        laattaButton.setPadding(inset);

        molemmatButton = new Nappi("*", "MOLEMMAT", sl, scoreField, state, this, highscoremanager);
        molemmatButton.setMinSize(160, 160);
        molemmatButton.getStyleClass().add("green");
        molemmatButton.setPadding(inset);

        // INFO tulostaa pisteet ja mahdollisia viestejä pelaajalle.
        info = new VBox(scoreField, state);
        info.setMinSize(160, 160);

        // YHDISTETÄÄN:
        gameInterface = new HBox(tahtiButton, nextButton, laattaButton, molemmatButton, info);
        gameInterface.setPadding(inset);
        gameInterface.setBackground(background);

        // LUODAAN ELEMENTIT: sceneHighscore
        Nappi highScoreNewGame = new Nappi("Uusi Peli");
        highScoreNewGame.getStyleClass().add("red");
        highScoreNewGame.setMinSize(160, 160);
        highScoreNewGame.setPadding(inset);
        highScoreNewGame.setOnAction(e -> {
            sl.alustaPeli();
            alustaLabelit();
            guiStage.setScene(sceneGame);
            primaryStage.show();
        });
        scoreInterface = new HBox(highScoreNewGame, tableview);
        scoreInterface.setPadding(inset);
        scoreInterface.setBackground(background);

        // LUODAAN ELEMENTIT: gameOver
        Label syotaNimi = new Label("Syötä nimi:");
        TextField nimiField = new TextField();
        //UUSI PELI
        Nappi gameOverNew = new Nappi("Uusi Peli");
        gameOverNew.getStyleClass().add("red");
        gameOverNew.setMinSize(160, 160);
        gameOverNew.setPadding(inset);
        gameOverNew.setOnAction(e -> {
            sl.alustaPeli();
            alustaLabelit();
            guiStage.setScene(sceneGame);
            primaryStage.show();
        });
        //HIGHSCOREn syöttävä OK-nappi
        Nappi gameOverSyotaScore = new Nappi("OK");
        gameOverSyotaScore.getStyleClass().add("green");
        gameOverSyotaScore.setMinSize(160, 160);
        gameOverSyotaScore.setPadding(inset);
        gameOverSyotaScore.setOnAction(e -> {
            if (nimiField.getText().contains(",")) {
                syotaNimi.setText("Valitettavasti ',' ei ole sallittu merkki");
            } else {
                highscoremanager.writeCSV(nimiField.getText(), sl.getLuku());
                highscoremanager.lueCSV();
                sl.alustaPeli();
                hsTableView.sortTableView();
                guiStage.setScene(sceneScore);
                primaryStage.show();
            }
        });

        VBox gameOver = new VBox(syotaNimi, nimiField);

        gameOverInterface = new HBox(gameOverNew, gameOver, gameOverSyotaScore);
        gameOverInterface.setPadding(inset);
        gameOverInterface.setBackground(background);

        ((Group) sceneMenu.getRoot()).getChildren().add(menuInterface);
        ((Group) sceneGame.getRoot()).getChildren().add(gameInterface);
        ((Group) sceneScore.getRoot()).getChildren().add(scoreInterface);
        ((Group) sceneGameOver.getRoot()).getChildren().add(gameOverInterface);

        guiStage.setTitle("Rytmipeli");
        guiStage.setScene(sceneMenu);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getStage() {
        return guiStage;
    }

    public void setMidText(String text) {
        this.tekstikenttaMenu.setText(text);
    }

    public Label getMidText() {
        return this.tekstikenttaMenu;
    }

    /**
     * Asettaa scorefield- ja state-labelit alkuarvoihin
     */
    public void alustaLabelit() {
        scoreField.setText("Score: " + score);
        state.setText("Onnea matkaan!");
    }

}
