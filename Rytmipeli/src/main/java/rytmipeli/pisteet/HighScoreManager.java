/*
 *
 */
package rytmipeli.pisteet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Tämä luokka lukee ja kirjoittaa csv-tiedostoja.
 *
 * @author Leo Niemi
 */
public class HighScoreManager {

    private TableView tableview;
    private ObservableList<Score> data;
    private FileOutputStream fileOutputStream;
    private String pathToFile;

    /**
     * Luokka hallinnoi highscore-listan kirjoitus- ja luku toimintoja.
     *
     * @param pathToFile .csv-tiedoston nimi ilman päätteitä.
     */
    public HighScoreManager(String pathToFile) {
        this.pathToFile = pathToFile;
        data = FXCollections.observableArrayList();
        File file = new File(pathToFile);
        readCSV();
    }

    /**
     * Lukee juurikansiossa sijaitsevan pisteet.txt-tiedoston (CSV-tiedosto).
     */
    public final void readCSV() {

        try {
            FileReader fr = new FileReader(pathToFile);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            String[] tempArr;

            while ((line = br.readLine()) != null) {
                tempArr = line.split(",");
                SimpleIntegerProperty temp = new SimpleIntegerProperty((Integer.valueOf(tempArr[1])));
                Score score = new Score(tempArr[0], temp);
                data.add(score);
            }
            br.close();

        } catch (FileNotFoundException ex) {
           Logger.getLogger(HighScoreManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HighScoreManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Lisää resources-kansion playerScore.txt-tiedostoon uuden rivin CSV-muodossa.
     *
     * @param playerName Pelaajan playerName
     * @param playerScore Pelaajan playerScore
     */
    public final void writeCSV(String playerName, int playerScore) {

        try {
            String lisattava = playerName + "," + playerScore + "\n";
            fileOutputStream = new FileOutputStream(pathToFile, true);
            fileOutputStream.write(lisattava.getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HighScoreManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HighScoreManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public TableView getTableView() {
        return this.tableview;
    }

    public ObservableList<Score> getData() {
        return data;
    }

    /**
     * Metodi tyhjentää ObservableList datan ja alustaa sen uudelleen. Näin saadaan uusimmat muutokset mukaan tableview:hin.
     * 
     */
    public void refreshHighScore() {
        data.clear();
        readCSV();
    }

}
