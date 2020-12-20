package rytmipeli.kayttoliittyma;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import rytmipeli.pisteet.HighScoreManager;
import rytmipeli.pisteet.Piste;

/**
 * Luokka hallinnoi käyttöliittymän tableview-oliota.
 *
 * @author Leo Niemi
 */
public class HighScoreTableView {

    private TableView tableview;
    private ObservableList<Piste> data;
    private TableColumn nimiColumn, pisteColumn;

    /**
     * Konstruktori tableview-olion luovalle luokalle.
     *
     *
     */
    public HighScoreTableView(String tiedostopolku, HighScoreManager hsm) {

        this.data = hsm.getData();
        tableview = new TableView(data);
        tableview.setPrefSize(480, 160);
        nimiColumn = new TableColumn<>("nimi");
        pisteColumn = new TableColumn<>("pisteet");
        pisteColumn.setCellValueFactory(new PropertyValueFactory<Piste, Integer>("pisteetFactory"));
        nimiColumn.setSortable(false);
        nimiColumn.setCellValueFactory(new PropertyValueFactory<>("nimi"));
        pisteColumn.setSortType(TableColumn.SortType.DESCENDING);
        tableview.getSortOrder().add(pisteColumn);
        nimiColumn.setMinWidth(250);
        pisteColumn.setMinWidth(230);
        tableview.getColumns().addAll(nimiColumn, pisteColumn);
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
