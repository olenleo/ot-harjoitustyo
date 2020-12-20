/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rytmipeli.pisteet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader; 
import java.io.IOException;
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
    private ObservableList<Piste> data;
    private FileOutputStream fileOutputStream;
    private String tiedostopolku;

    /**
     * Luokka hallinnoi highscore-listan kirjoitus- ja luku toimintoja.
     * @param tiedostopolku .csv-tiedoston nimi ilman päätteitä.
     */
    public HighScoreManager(String tiedostopolku) {
        this.tiedostopolku = tiedostopolku;
        data = FXCollections.observableArrayList();
        File file = new File(tiedostopolku);
        if (file.exists()) {
            try {
                fileOutputStream = new FileOutputStream("./" + this.tiedostopolku, true);
            } catch (FileNotFoundException e) {
                System.out.println("VIRHE: Olematon tiedosto " + e.getMessage());
            }
        }

        lueCSV();
    }

    /**
     * Lukee juurikansiossa sijaitsevan pisteet.txt-tiedoston (CSV-tiedosto).
     */
    public final void lueCSV() {

        try {
            FileReader fr = new FileReader(tiedostopolku);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            String[] tempArr;

            while ((line = br.readLine()) != null) {
                System.out.println(line);
                tempArr = line.split(",");
                SimpleIntegerProperty temp = new SimpleIntegerProperty((Integer.valueOf(tempArr[1])));
                Piste piste = new Piste(tempArr[0], temp);
                data.add(piste);
            }
            br.close();

        } catch (FileNotFoundException e) {
            System.out.println("Virhe [FileNotFound] CSV-tiedoston lukuvaiheessa: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Virhe [IOException] CSV-tiedoston lukuvaiheessa: " + e.getMessage());
        }
    }

    /**
     * Lisää resources-kansion pisteet.txt-tiedostoon uuden rivin CSV-muodossa.
     *
     * @param nimi Pelaajan nimi
     * @param pisteet Pelaajan pisteet
     */
    public final void writeCSV(String nimi, int pisteet) {

        String lisattava = nimi + "," + pisteet + "\n";
        try {
            fileOutputStream = new FileOutputStream(tiedostopolku, true);
            fileOutputStream.write(lisattava.getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            System.out.println("Write error: " + e.getMessage());
        }

    }

    public TableView getTableView() {
        return this.tableview;
    }

    public ObservableList<Piste> getData() {
        return data;
    }

}
