/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rytmipeli.pisteet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * Tämä luokka hallinnoi Käyttöliittymän tableview-oliota. Muokattu :
 * https://stackoverflow.com/questions/34889111/how-to-sort-a-tableview-programmatically.
 *
 * @author Leo Niemi
 */
public class HighScoreManager {

    private TableView tableview;
    private ObservableList<Piste> data;
    private TableColumn nimiColumn;
    private FileOutputStream fileOutputStream;

/**
 * Luokka hallinnoi highscore-listan kirjoitus- ja luku  toimintoja.
 * 
 */
    public HighScoreManager() {

        tableview = new TableView();
        tableview.setPrefSize(480, 160);
        nimiColumn = new TableColumn<>("nimi");
        TableColumn<Piste, String> pisteColumn = new TableColumn<>("pisteet");
        nimiColumn.setCellValueFactory(new PropertyValueFactory<>("nimi"));
        pisteColumn.setCellValueFactory(new PropertyValueFactory<>("pisteet"));

        tableview.getColumns().add(nimiColumn);
        tableview.getColumns().add(pisteColumn);

        data = FXCollections.observableArrayList();
        tableview.setItems(data);
        alustaCSV();
        update();
        
        // Järjestys pistemäärän mukaan:
        pisteColumn.setSortType(TableColumn.SortType.DESCENDING);
        tableview.getSortOrder().addAll(pisteColumn);
    }

    /**
     * Lukee juurikansiossa sijaitsevan pisteet.txt-tiedoston (CSV-tiedosto).
     */
    public final void lueCSV() {
        String relativePath = "./pisteet.txt";
        try {
            FileReader fr = new FileReader(relativePath);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            String[] tempArr;
            while ((line = br.readLine()) != null) {
                tempArr = line.split(",");
                Piste piste = new Piste(tempArr[0], Integer.valueOf(tempArr[1]));
                data.add(piste);
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Virhe CSV-tiedoston lukuvaiheessa: " + e.getMessage());
        }
    }

    /**
     * Luo paikallisen highscore-tiedoston juurikansioon. Naiivi ratkaisu mutta
     * aikaraja tulee vastaan.
     */
    public final void alustaCSV() {
        try {
            fileOutputStream = new FileOutputStream("./pisteet.txt", true);
            writeCSV("Ennatykseni:", 62);
        } catch (Exception e) {
            System.out.println("Virhe alustaCSV_metodissa: " + e.getMessage());
        }

    }

    /**
     * Lisää resources-kansion pojot.txt-tiedostoon uuden rivin CSV-muodossa.
     *
     * @param nimi Pelaajan nimi
     * @param pisteet Pelaajan pisteet
     */
    public final void writeCSV(String nimi, int pisteet) {

        String lisattava = nimi + "," + pisteet + "\n";

        try {
            fileOutputStream = new FileOutputStream("./pisteet.txt", true);
            fileOutputStream.write(lisattava.getBytes());
            System.out.println("Kirjoitettu: " + lisattava);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            System.out.println("Write error: " + e.getMessage());
        }
        update();
    }

    public TableView getTableView() {
        return this.tableview;
    }

    public int getPieninHighscore() {
        return -1;
    }
/**
 * Metodi päivittää csv-tiedoston.
 * 
 */
    public final void update() {
        data.removeAll(data);
        lueCSV();
        nimiColumn.setSortType(TableColumn.SortType.DESCENDING);
        tableview.getSortOrder().addAll(nimiColumn);
    }
/**
 * 
 * TODO: Poista tämä.
 * @param tiedostonimi
 * @return 
 */
    private InputStream getFileFromResouce(String tiedostonimi) {
        ClassLoader classloader = getClass().getClassLoader();
        InputStream inputstream = classloader.getResourceAsStream(tiedostonimi);
        if (inputstream == null) {
            throw new IllegalArgumentException("Tiedostoa " + tiedostonimi + " ei löytynyt");
        } else {
            return inputstream;
        }
    }

    public ObservableList<Piste> getData() {
        return data;
    }
    
}
