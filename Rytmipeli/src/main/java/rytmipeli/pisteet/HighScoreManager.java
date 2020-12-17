/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rytmipeli.pisteet;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

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

    private InputStream absolutePath;

    public HighScoreManager() {
        InputStream absolutePath = this.getClass().getClassLoader().getResourceAsStream("pojot.txt");

        System.out.println("Polku: " + absolutePath);

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
        List<Piste> lista = new ArrayList<>();
        try {
            InputStreamReader isr = new InputStreamReader(getClass().getResourceAsStream("/pojot.txt"));
            BufferedReader br = new BufferedReader(isr);
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
     * Lisää resources-kansion pojot.txt-tiedostoon uuden rivin CSV-muodossa
     *
     * @param nimi Pelaajan nimi
     * @param pisteet Pelaajan pisteet
     */
    public final void writeCSV(String nimi, int pisteet) {
//        String CSVFile = getClass().getResource("/pojot.txt").getPath();
        String lisattava = nimi + "," + pisteet + "\n";
        String filename = this.getClass().getResource("/pojot.txt").getPath();
        file = new File(filename);
        System.out.println("FILENAME: " + filename);
        try {
            FileOutputStream fileoutputstream = new FileOutputStream(filename, true);
            OutputStreamWriter osw = new OutputStreamWriter(fileoutputstream);
            osw.write(lisattava);
            System.out.println("Kirjoitettu.");
            osw.close();
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

    public void update() {
        data.removeAll(data);
        lueCSV();
        nimiColumn.setSortType(TableColumn.SortType.DESCENDING);
        tableview.getSortOrder().addAll(nimiColumn);

    }
}
