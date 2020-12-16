/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rytmipeli.pisteet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.lang.Object;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

/**
 * Tämä luokka hallinnoi Käyttöliittymän tableview-oliota. Muokattu :
 * https://stackoverflow.com/questions/34889111/how-to-sort-a-tableview-programmatically
 *
 * @author Leo Niemi
 */
public class HighScoreManager {

    private TableView tableview;
    private ObservableList<Piste> data;
    private int pieninHighScore = 0;
    private TableColumn nimiColumn;
    private File file;
    private URL res;

    private String absolutePath;

    public HighScoreManager() {
        String CSVFile = getClass().getResource("/pojot.txt").getFile();
        absolutePath = CSVFile;
        System.out.println(absolutePath);

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

        lueCSV();
        // Järjestys pistemäärän mukaan:
        pisteColumn.setSortType(TableColumn.SortType.DESCENDING);
        tableview.getSortOrder().addAll(pisteColumn);

    }

    /**
     * Lukee resources-kansiossa sijaitsevan pojot.txt (CSV-tiedosto)
     */
    public final void lueCSV() {

        String split = ",";
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(absolutePath));
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(split);
                Piste piste = new Piste(Integer.valueOf(fields[1]), fields[0]);
                data.add(piste);
            }
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFound: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO-expection: " + e.getMessage());
        }
    }

    /**
     * Lisää resources-kansion pojot.txt-tiedostoon uuden rivin CSV-muodossa
     *
     * @param nimi Pelaajan nimi
     * @param pisteet Pelaajan pisteet
     */
    public final void writeCSV(String nimi, int pisteet) {

//        String CSVFile = getClass().getResource("/pojot.txt").getPath();
        BufferedWriter bw;
        String lisattava = "\n" + nimi + "," + pisteet + "\n";
        System.out.println("LISÄTTÄVÄ STRING: " + lisattava + " OSOITTEESEEN " + getClass().getResource("/pojot.txt").toExternalForm()); // Testausta varten
        try {
            bw = new BufferedWriter(new FileWriter(absolutePath, true));
            bw.append(lisattava);
            System.out.println("Kirjoitettu.");
            bw.close();
        } catch (Exception e) {
            System.out.println("Write error: " + e.getMessage());
        }
    }

    public TableView getTableView() {
        return this.tableview;
    }

    public int getPieninHighscore() {
        return -1;
    }

    public void update() {
        data.removeAll(data);
        lueCSV();
        nimiColumn.setSortType(TableColumn.SortType.DESCENDING);
        tableview.getSortOrder().addAll(nimiColumn);

    }
}
