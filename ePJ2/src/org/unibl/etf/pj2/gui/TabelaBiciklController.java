package org.unibl.etf.pj2.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.List;
import org.unibl.etf.pj2.prevoznasredstva.Bicikl;
import org.unibl.etf.pj2.prevoznasredstva.PodaciTabele;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;

/**
 * Klasa kontroler za scenu TabelaBickli.fxml a na kojoj su prikazani svi bicikli.
 */
public class TabelaBiciklController {
	@FXML
	private AnchorPane biciklAnchor;
	@FXML
	private Button nazadbicikl;
	@FXML
    private TableView<Bicikl> tableView;
    @FXML
    private TableColumn<Bicikl, String> idColumn;
    @FXML
    private TableColumn<Bicikl, String> proizvodjacColumn;
    @FXML
    private TableColumn<Bicikl, String> modelColumn;
    @FXML
    private TableColumn<Bicikl, Double> cijenaNabavkeColumn;
    @FXML
    private TableColumn<Bicikl, Double> dometColumn;
    @FXML
    private TableColumn<Bicikl, Boolean> pokvarenColumn;
    
    /**
     * Metoda za inicijalizaciju/popunjavanje tabele bicikala
     */
    @FXML
    public void initialize() {
        // Povezivanje kolona sa svojstvima modela
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        proizvodjacColumn.setCellValueFactory(new PropertyValueFactory<>("proizvodjac"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        cijenaNabavkeColumn.setCellValueFactory(new PropertyValueFactory<>("cijenaNabavke"));
        dometColumn.setCellValueFactory(new PropertyValueFactory<>("autonomija"));
        pokvarenColumn.setCellValueFactory(new PropertyValueFactory<>("pokvaren"));
        
        loadBicikli();
    }
	
    /**
     * Metoda kojom se ucitavaju bicikli iz liste svih bicikala klase PodaciTabele, a koja se koristi pri inicijalizaciji tabele.
     */
    private void loadBicikli() {
        List<Bicikl> bicikli = PodaciTabele.getBicikli();
        ObservableList<Bicikl> observableBicikli = FXCollections.observableArrayList(bicikli);
        tableView.setItems(observableBicikli);
    }

	/**
	 * Metoda koja sluzi za povratak na scenu PrevoznaSredstva.fxml upotrebom buttona nazadbicikl.
	 * @param event Na klik
	 */
	@FXML
	public void btnNazadBicikl(ActionEvent event) {
		SceneLoader.loadScene("PrevoznaSredstva.fxml",biciklAnchor);
	}
	
}

