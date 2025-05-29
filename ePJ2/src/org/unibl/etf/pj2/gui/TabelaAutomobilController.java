package org.unibl.etf.pj2.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import java.time.LocalDate;
import java.util.List;
import org.unibl.etf.pj2.prevoznasredstva.Automobil;
import org.unibl.etf.pj2.prevoznasredstva.PodaciTabele;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

/**
 * Klasa kontroler za scenu TabelaAutomobil, a kojom se prikazuje lista svih automobila.
 */
public class TabelaAutomobilController {
	@FXML
	private Button nazadautomobil;
	@FXML
	private AnchorPane automobilAnchor;
	@FXML
    private TableView<Automobil> tableView;
    @FXML
    private TableColumn<Automobil, String> idColumn;
    @FXML
    private TableColumn<Automobil, String> proizvodjacColumn;
    @FXML
    private TableColumn<Automobil, String> modelColumn;
    @FXML
    private TableColumn<Automobil, LocalDate> datumNabavkeColumn;
    @FXML
    private TableColumn<Automobil, Double> cijenaNabavkeColumn;
    @FXML
    private TableColumn<Automobil, String> opisColumn;
    @FXML
    private TableColumn<Automobil, Boolean> pokvarenColumn;
	
    /**
     * Metoda za inicijalizaciju/popunjavanje tabele automobila.
     */
    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        proizvodjacColumn.setCellValueFactory(new PropertyValueFactory<>("proizvodjac"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        datumNabavkeColumn.setCellValueFactory(new PropertyValueFactory<>("datumNabavke"));
        cijenaNabavkeColumn.setCellValueFactory(new PropertyValueFactory<>("cijenaNabavke"));
        opisColumn.setCellValueFactory(new PropertyValueFactory<>("opis"));
        pokvarenColumn.setCellValueFactory(new PropertyValueFactory<>("pokvaren"));
        loadAutomobili();
    }
	
    /**
     * Metoda kojom se ucitavaju svi automobili iz klase PodaciTabele, a koja se koristi pri inicijalizaciji.
     */
    private void loadAutomobili() {
        List<Automobil> automobili = PodaciTabele.getAutomobili();
        ObservableList<Automobil> observableAutomobili = FXCollections.observableArrayList(automobili);
        tableView.setItems(observableAutomobili);
    }

	/**
	 * Metoda koja sluzi za povratak na scenu PrevoznaSredstva.fxml upotrebom buttona nazadautomobil.
	 * @param event Na klik
	 */
	@FXML
	public void btnNazadAutomobil(ActionEvent event) {
		SceneLoader.loadScene("PrevoznaSredstva.fxml", automobilAnchor);
	}
	
}
