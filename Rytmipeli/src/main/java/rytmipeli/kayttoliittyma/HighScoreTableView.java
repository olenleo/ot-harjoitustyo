package rytmipeli.kayttoliittyma;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import rytmipeli.pisteet.HighScoreManager;
import rytmipeli.pisteet.Score;

/**
 * Luokka hallinnoi käyttöliittymän tableview-oliota.
 *
 * @author Leo Niemi
 */
public class HighScoreTableView {

    private TableView tableview;
    private ObservableList<Score> data;
    private TableColumn nameColumn, scoreColumn;

    
    
    /**
     * Konstruktori tableview-olion luovalle luokalle.
     *
     * @param tiedostopolku CSV-tiedoston nimi ilman päätettä
     * @param hsm HighScoreManager-olio
     */
    public HighScoreTableView(String tiedostopolku, HighScoreManager hsm) {

        this.data = hsm.getData();
        tableview = new TableView(data);
        tableview.setPrefSize(480, 160);
        nameColumn = new TableColumn<>("nimi");
        scoreColumn = new TableColumn<>("pisteet");
        scoreColumn.setCellValueFactory(new PropertyValueFactory<Score, Integer>("playerScoreFactory"));
        nameColumn.setSortable(false);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        scoreColumn.setSortType(TableColumn.SortType.DESCENDING);
        tableview.getSortOrder().add(scoreColumn);
        nameColumn.setMinWidth(250);
        scoreColumn.setMinWidth(230);
        tableview.getColumns().addAll(nameColumn, scoreColumn);
        tableview.sort();
    }

    public TableView getTableView() {
        return this.tableview;
    }

    /**
     * Kutsuu tableview:n sort-metodia.
     */
    public void sortTableView() {
        tableview.sort();
    }

}
