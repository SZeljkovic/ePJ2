package org.unibl.etf.pj2.gui;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import org.unibl.etf.pj2.prevoznasredstva.Automobil;
import org.unibl.etf.pj2.prevoznasredstva.Bicikl;
import org.unibl.etf.pj2.prevoznasredstva.Kvar;
import org.unibl.etf.pj2.prevoznasredstva.PodaciTabele;
import org.unibl.etf.pj2.prevoznasredstva.PrevoznoSredstvo;
import org.unibl.etf.pj2.prevoznasredstva.Trotinet;

import javafx.event.ActionEvent;

import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;


/**
 * Klasa kontroler za scenu CijenePopravki.fxml, a radi se o prikazu cijena popravki pokvarenih prevoznih sredstava.
 */
public class CijenePopravkiController {
	@FXML
	private AnchorPane cijenePopravkiAnchor;
	@FXML
	private Button nazadcijenepopravki;
	@FXML
	private Button serijalizujcijenepopravki;
	@FXML
	private Button deserijalizujcijenepopravki;
	@FXML
	private TableView tableView;
	@FXML
	private TableColumn<PrevoznoSredstvo, String> idColumn;
	@FXML
	private TableColumn<PrevoznoSredstvo, String> proizvodjacColumn;
	@FXML
	private TableColumn<PrevoznoSredstvo, String> modelColumn;
	@FXML
	private TableColumn<PrevoznoSredstvo, Double> cijenaNabavkeColumn;
	@FXML
	private TableColumn<PrevoznoSredstvo, String> opisKvaraColumn;
	@FXML
	private TableColumn<PrevoznoSredstvo, String> vrijemeKvaraColumn;
	@FXML
	private TableColumn<PrevoznoSredstvo, Double> dometColumn;
	@FXML
	private TableColumn<PrevoznoSredstvo, Double> maksimalnaBrzinaColumn;
	@FXML
	private TableColumn<PrevoznoSredstvo, String> opisColumn;
	@FXML
	private TableColumn<PrevoznoSredstvo, Double> cijenaPopravkeColumn;
	
	/**
	 * Standardna FXML initialize funkcija koja se koristi za populaciju tabele u kojoj se prikazuju cijene popravki prevoznih sredstava.
	 */
	@FXML
	public void initialize() {
	    idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
	    proizvodjacColumn.setCellValueFactory(new PropertyValueFactory<>("proizvodjac"));
	    modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
	    cijenaNabavkeColumn.setCellValueFactory(new PropertyValueFactory<>("cijenaNabavke"));
	    opisKvaraColumn.setCellValueFactory(cellData -> {
	        Kvar kvar = cellData.getValue().getKvar();
	        return new SimpleStringProperty(kvar != null ? kvar.getOpisKvara() : "");
	    });
	    vrijemeKvaraColumn.setCellValueFactory(cellData -> {
	        Kvar kvar = cellData.getValue().getKvar();
	        return new SimpleStringProperty(kvar != null ? kvar.getVrijemeKvara().toString() : "");
	    });
	    dometColumn.setCellValueFactory(cellData -> {
	        if (cellData.getValue() instanceof Bicikl) {
	            return new SimpleDoubleProperty(((Bicikl) cellData.getValue()).getAutonomija()).asObject();
	        } else {
	            return null;
	        }
	    });
	    maksimalnaBrzinaColumn.setCellValueFactory(cellData -> {
	        if (cellData.getValue() instanceof Trotinet) {
	            return new SimpleDoubleProperty(((Trotinet) cellData.getValue()).getMaksimalnaBrzina()).asObject();
	        } else {
	            return null;
	        }
	    });
	    opisColumn.setCellValueFactory(cellData -> {
	        if (cellData.getValue() instanceof Automobil) {
	            return new SimpleStringProperty(((Automobil) cellData.getValue()).getOpis());
	        } else {
	            return null;
	        }
	    });
	    cijenaPopravkeColumn.setCellValueFactory(cellData -> {
	        double cijenaPopravke = cellData.getValue().izracunajCijenuPopravke();
	        return new SimpleDoubleProperty(cijenaPopravke).asObject();
	    });
	}

	

	/**
	 * Metoda koja vraca scenu na scenu Kvarovi.fxml pritiskom na button nazadcijenepopravki
	 * @param event Na klik
	 */
	@FXML
	public void btnNazadCijenePopravki(ActionEvent event) {
		SceneLoader.loadScene("Kvarovi.fxml",cijenePopravkiAnchor);
	}
	/**
	 * Metoda koja serijalizuje listu pokvarenih prevoznih sredstava zajedno sa njihovom cijenom popravke u binarni fajl.
	 * @param event Na klik
	 */
	@FXML
	public void btnSerijalizujCijenePopravki(ActionEvent event) {
	    List<PrevoznoSredstvo> pokvarenaPrevoznaSredstva = PodaciTabele.getPokvarenaPrevoznaSredstva();
	    
	    File folder = new File("folder_za_serijalizaciju");
        if (!folder.exists()) {
            folder.mkdir();
        }
	    
	    for (PrevoznoSredstvo sredstvo : pokvarenaPrevoznaSredstva) {
	        double cijenaPopravke = sredstvo.izracunajCijenuPopravke();
	        
	        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("folder_za_serijalizaciju/" + sredstvo.getId() + ".bin"))) {
	            oos.writeObject(sredstvo);
	            oos.writeDouble(cijenaPopravke);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}

	/**
	 * Metoda kojom se ucitavaju/deserijalizuju podaci koji su prethodno serijalizovani.
	 * @param event Na klik
	 */
	@FXML
	public void btnDeserijalizujCijenePopravki(ActionEvent event) {
	    File folder = new File("folder_za_serijalizaciju");
	    File[] files = folder.listFiles((dir, name) -> name.endsWith(".bin"));

	    if (files != null) {
	        for (File file : files) {
	            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
	                PrevoznoSredstvo sredstvo = (PrevoznoSredstvo) ois.readObject();
	                tableView.getItems().add(sredstvo);
	            } catch (IOException | ClassNotFoundException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}


}
