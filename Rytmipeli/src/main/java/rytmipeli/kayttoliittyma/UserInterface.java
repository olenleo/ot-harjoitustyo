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
import rytmipeli.pisteet.HighScoreManager;
import rytmipeli.sovelluslogiikka.ApplicationLogic;

/**
 *
 * @author Leo Niemi
 */
public class UserInterface extends Application {

    private ApplicationLogic sl;
    private int score;
    private Label scoreField, state, tekstikenttaMenu;
    private HBox gameInterface, menuInterface, scoreInterface, gameOverInterface;
    private VBox info;
    private RytmipeliButton mainBeatButton, nextButton, polyrhythmBeatButton, bothButton, newGame, highScore;
    protected static Scene sceneGame, sceneMenu, sceneScore, sceneGameOver;
    private static Stage guiStage;
    private Canvas canvas;
    private TableView tableview;
    private HighScoreManager highscoremanager;
    private HighScoreTableView hsTableView;
    private final String file = "pisteet.txt";

    @Override
    public void start(Stage primaryStage) throws Exception {
        // YLEISHYÖDYLLISIÄ MUUTTUJIA
        sl = new ApplicationLogic();
        score = sl.getCurrentBeat();
        guiStage = primaryStage;
        Insets inset = new Insets(10, 10, 10, 10);
        highscoremanager = new HighScoreManager(file);

        // TAUSTAVÄRI
        BackgroundFill bgColour = new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(bgColour);

        // TABLEVIEW pistetilastolle
        hsTableView = new HighScoreTableView(file, highscoremanager);
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
        newGame = new RytmipeliButton("Uusi Peli");
        newGame.getStyleClass().add("red");
        newGame.setMinSize(160, 160);
        newGame.setPadding(inset);
        newGame.setOnAction(e -> {
            sl.initializeGame();
            alustaLabelit();
            guiStage.setScene(sceneGame);
            primaryStage.show();
        });
        tekstikenttaMenu = new Label();
        tekstikenttaMenu.setMinSize(320, 160);
        tekstikenttaMenu.setAlignment(Pos.CENTER);
        tekstikenttaMenu.setText("Onnea matkaan!!");

        highScore = new RytmipeliButton("High Score");
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
        mainBeatButton = new RytmipeliButton("[1/4]", "TAHTI", sl, scoreField, state, this, highscoremanager);
        mainBeatButton.getStyleClass().add("red");
        mainBeatButton.setMinSize(160, 160);
        mainBeatButton.setPadding(inset);

        nextButton = new RytmipeliButton("SKIP", "SEURAAVA", sl, scoreField, state, this, highscoremanager);
        nextButton.setMinSize(160, 160);
        nextButton.getStyleClass().add("orange");
        nextButton.setPadding(inset);

        polyrhythmBeatButton = new RytmipeliButton("7", "LAATTA", sl, scoreField, state, this, highscoremanager);
        polyrhythmBeatButton.setMinSize(160, 160);
        polyrhythmBeatButton.getStyleClass().add("yellow");
        polyrhythmBeatButton.setPadding(inset);

        bothButton = new RytmipeliButton("*", "MOLEMMAT", sl, scoreField, state, this, highscoremanager);
        bothButton.setMinSize(160, 160);
        bothButton.getStyleClass().add("green");
        bothButton.setPadding(inset);

        // INFO tulostaa pisteet ja mahdollisia viestejä pelaajalle.
        info = new VBox(scoreField, state);
        info.setMinSize(160, 160);

        // YHDISTETÄÄN:
        gameInterface = new HBox(mainBeatButton, nextButton, polyrhythmBeatButton, bothButton, info);
        gameInterface.setPadding(inset);
        gameInterface.setBackground(background);

        // LUODAAN ELEMENTIT: sceneHighscore
        RytmipeliButton highScoreNewGame = new RytmipeliButton("Uusi Peli");
        highScoreNewGame.getStyleClass().add("red");
        highScoreNewGame.setMinSize(160, 160);
        highScoreNewGame.setPadding(inset);
        highScoreNewGame.setOnAction(e -> {
            sl.initializeGame();
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
        RytmipeliButton gameOverNew = new RytmipeliButton("Uusi Peli");
        gameOverNew.getStyleClass().add("red");
        gameOverNew.setMinSize(160, 160);
        gameOverNew.setPadding(inset);
        gameOverNew.setOnAction(e -> {
            sl.initializeGame();
            alustaLabelit();
            guiStage.setScene(sceneGame);
            primaryStage.show();
        });
        //HIGHSCOREn syöttävä OK-nappi
        RytmipeliButton gameOverSyotaScore = new RytmipeliButton("OK");
        gameOverSyotaScore.getStyleClass().add("green");
        gameOverSyotaScore.setMinSize(160, 160);
        gameOverSyotaScore.setPadding(inset);
        gameOverSyotaScore.setOnAction(e -> {
            if (nimiField.getText().contains(",")) {
                syotaNimi.setText("Valitettavasti ',' ei ole sallittu merkki");
            } else {
                highscoremanager.writeCSV(nimiField.getText(), sl.getCurrentBeat());
                highscoremanager.refreshHighScore();
                sl.initializeGame();
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

    /**
     * Asettaa scorefield- ja state-labelit alkuarvoihin
     */
    public void alustaLabelit() {
        scoreField.setText("Score: " + score);
        state.setText("Onnea matkaan!");
    }

}
