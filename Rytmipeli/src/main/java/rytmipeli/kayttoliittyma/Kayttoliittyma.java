package rytmipeli.kayttoliittyma;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import rytmipeli.pisteet.Piste;
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
    private ObservableList<Piste> list;
    private TableView tableview;
    private ObservableList<Piste> data;
    private int pieninHighScore=0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // YLEISHYÖDYLLISIÄ MUUTTUJIA
        sl = new SovellusLogiikka();
        score = sl.getLuku();
        guiStage = primaryStage;
        Insets inset = new Insets(10, 10, 10, 10);

        //TEST: Luodaan tietokanta
//        PisteToiminnallisuus pt = new PisteToiminnallisuus();
//        pt.addScore("Leo", 62);
//        System.out.println(pt.getHighscoreString());
        // TAUSTAVÄRI
        BackgroundFill taustavari = new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(taustavari);

        //HIGHSCORE (Eriytetään erilliseen metodiin myöhemmin)
        //        https://stackoverflow.com/questions/34889111/how-to-sort-a-tableview-programmatically
        tableview = new TableView();
        tableview.setPrefSize(320, 160);
        TableColumn<Piste, String> nimiColumn = new TableColumn<>("nimi");
        TableColumn<Piste, String> pisteColumn = new TableColumn<>("pisteet");
        nimiColumn.setCellValueFactory(new PropertyValueFactory<>("nimi"));
        pisteColumn.setCellValueFactory(new PropertyValueFactory<>("pisteet"));
        tableview.getColumns().add(nimiColumn);
        tableview.getColumns().add(pisteColumn);
        data = FXCollections.observableArrayList();
        tableview.setItems(data);
        lueCSV();
        nimiColumn.setSortType(TableColumn.SortType.DESCENDING);
        tableview.getSortOrder().addAll(nimiColumn);
        pieninHighScore = data.get(0).getPisteet();
        System.out.println("Pienin listalla: " + pieninHighScore);
        // IKKUNAN KOKO
        canvas = new Canvas();
        canvas.setHeight(180);
        canvas.setWidth(800);

        //VALIKKO- JA PELINÄKYMÄT
        sceneGame = new Scene(new Group());
        sceneMenu = new Scene(new Group());

        //LISÄTÄÄN STYLESHEET
        sceneMenu.getStylesheets().add(getClass().getResource("/buttonCSS.css").toExternalForm());
        sceneGame.getStylesheets().add(getClass().getResource("/buttonCSS.css").toExternalForm());

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
        highScore.setOnAction(e -> tableview.sort());
        // YHDISTETÄÄN
        menuInterface = new HBox(newGame, tableview, highScore);
        menuInterface.setPadding(inset);
        menuInterface.setBackground(background);

        scoreField = new Label("Score: " + score);
        state = new Label("Onnea matkaan!");

        // LUODAAN ELEMENTIT: sceneGame
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

        // YHDISTETÄÄN:
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

    public static Stage getStage() {
        return guiStage;
    }

    public void setMid(String text) {
        this.tekstikenttaMenu.setText(text);
    }

    public Label getMid() {
        return this.tekstikenttaMenu;
    }

    /**
     * Asettaa scorefield- ja state-labelit alkuarvoihin
     */
    public void alustaLabelit() {
        scoreField.setText("Score: " + score);
        state.setText("Onnea matkaan!");
    }

   
    

    private void lueCSV() {
        String CsvFile = getClass().getResource("/pojot.txt").getFile();
        String FieldDelimiter = ",";
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(CsvFile));
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(FieldDelimiter);
                Piste piste = new Piste(Integer.valueOf(fields[1]), fields[0]);
                data.add(piste);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("FileNotFound: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
