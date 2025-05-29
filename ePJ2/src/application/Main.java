package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.unibl.etf.pj2.gui.MapaController;
import org.unibl.etf.pj2.iznajmljivanje.Iznajmljivanje;
import org.unibl.etf.pj2.iznajmljivanje.IznajmljivanjeLoader;
import org.unibl.etf.pj2.prevoznasredstva.Automobil;
import org.unibl.etf.pj2.prevoznasredstva.Bicikl;
import org.unibl.etf.pj2.prevoznasredstva.Kvar;
import org.unibl.etf.pj2.prevoznasredstva.Trotinet;
import org.unibl.etf.pj2.prevoznasredstva.PodaciTabele;
import org.unibl.etf.pj2.prevoznasredstva.PrevoznaSredstvaLoader;
import org.unibl.etf.pj2.prevoznasredstva.PrevoznoSredstvo;
import org.unibl.etf.pj2.simulacija.Mapa;
import org.unibl.etf.pj2.simulacija.Simulacija;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Main klasa u kojoj se nalazi sve potrebno za pokretanje simulacije iznajmljivanja prevoznih sredstava
 * @author Srdjan Zeljkovic - 1118/21
 * @version 5.9.2024.
 */
public class Main extends Application {

	private Simulacija simulacija;
	/**
	 * Standardna start metoda koja se koristi uz JavaFX za pokretanje programa u mainu.
	 */
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/unibl/etf/pj2/gui/Mapa.fxml"));
        Parent root = loader.load();

        MapaController controller = loader.getController();

        // Ucitavanje prevoznih sredstava iz CSV datoteke
        List<PrevoznoSredstvo> prevoznaSredstva = PrevoznaSredstvaLoader.ucitajPrevoznaSredstva("PJ2 - projektni zadatak 2024 - Prevozna sredstva.csv");
        System.out.println("Ucitana prevozna sredstva:");
        for (PrevoznoSredstvo ps : prevoznaSredstva) {
            System.out.println(ps);
        }
        
        List<Automobil> automobili = prevoznaSredstva.stream()
                .filter(ps -> ps instanceof Automobil)
                .map(ps -> (Automobil) ps)
                .collect(Collectors.toList());

        // Postavljanje automobila u listu automobila klase PodaciTabele
        PodaciTabele.setAutomobili(automobili);
        
        List<Bicikl> bicikli = prevoznaSredstva.stream()
                .filter(ps -> ps instanceof Bicikl)
                .map(ps -> (Bicikl) ps)
                .collect(Collectors.toList());
        
        PodaciTabele.setBicikli(bicikli);
        
        List<Trotinet> trotineti = prevoznaSredstva.stream()
                .filter(ps -> ps instanceof Trotinet)
                .map(ps -> (Trotinet) ps)
                .collect(Collectors.toList());
        
        PodaciTabele.setTrotineti(trotineti);

        // Ucitavanje iznajmljivanja iz CSV datoteke
        List<Iznajmljivanje> iznajmljivanja = IznajmljivanjeLoader.ucitajIznajmljivanja("PJ2 - projektni zadatak 2024 - Iznajmljivanja.csv");
        System.out.println("Ucitana iznajmljivanja:");
        for (Iznajmljivanje i : iznajmljivanja) {
            System.out.println(i);
        }
        
        for (Iznajmljivanje iznajmljivanje : iznajmljivanja) {
            if (iznajmljivanje.isPokvarenoPs()) {
                String idPrevoznogSredstva = iznajmljivanje.getIdPrevoznogSredstva();
                
                // Pronalazimo prevozno sredstvo sa odgovarajucim ID-em
                for (PrevoznoSredstvo ps : prevoznaSredstva) {
                    if (ps.getId().equals(idPrevoznogSredstva)) {
                        ps.setPokvaren(true); // Postavljamo da je pokvaren
                        Kvar kvar = new Kvar("Kvar prevoznog sredstva " + iznajmljivanje.getIdPrevoznogSredstva(), iznajmljivanje.getVrijemeIznajmljivanja());
                        ps.setKvar(kvar);
                        break; // Prekidamo petlju jer smo nasli odgovarajuce prevozno sredstvo
                    }
                }
            }
        }
        
        for (PrevoznoSredstvo ps : prevoznaSredstva) {
        	System.out.println("Nakon provjere da li su pokvarena prevozna sredstva: ");
        	System.out.println(ps);
        }
        
        //Izdvajanje pokvarenih prevoznih sredstava u listu
        List<PrevoznoSredstvo> pokvarenaPrevoznaSredstva = prevoznaSredstva.stream()
                .filter(PrevoznoSredstvo::isPokvaren)
                .collect(Collectors.toList());

        PodaciTabele.setPokvarenaPrevoznaSredstva(pokvarenaPrevoznaSredstva);

        
        Mapa mapa = controller.getMapa();

        // Kreiranje i pokretanje simulacije
        simulacija = new Simulacija(prevoznaSredstva, iznajmljivanja, mapa);

        // Postavljanje scene i prikaz prozora
        Scene scene = new Scene(root);
        primaryStage.setTitle("Simulacija Iznajmljivanja");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        new Thread(simulacija::pokreniSimulaciju).start();
        
        primaryStage.setOnCloseRequest(event -> {
            simulacija.stopSimulation(); // Zaustavljanje simulacije
            Platform.exit(); // Izlaz iz aplikacije
        });
    }

    /**
     * Main funkcija
     * @param args Default argumenti
     */
    public static void main(String[] args) {
        launch(args);
    }
}