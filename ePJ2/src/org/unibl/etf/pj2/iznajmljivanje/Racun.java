package org.unibl.etf.pj2.iznajmljivanje;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.unibl.etf.pj2.prevoznasredstva.PrevoznoSredstvo;

/**
 * Klasa kojom se predstavlja jedan racun sa svim bitnim podacima za iznajmljivanje, a koja generise tekstualne datoteke kao potvrde
 */
public class Racun {
    private Iznajmljivanje iznajmljivanje;
    private PrevoznoSredstvo prevoznoSredstvo;
    private Properties properties;

    // Staticke varijable za sumiranje vrednosti
    private static double ukupanPrihod = 0;
    private static double ukupanPopust = 0;
    private static double ukupnePromocije = 0;
    private static double ukupnoUziDeoGrada = 0;
    private static double ukupnoSiriDeoGrada = 0;
    private static double ukupnePopravke = 0;
    
    private static int brojIznajmljivanja;
    
    private static Map<LocalDate, DnevniRezultat> rezultatiPoDatumu = new HashMap<>();
    
    /**
     * Metoda kojom se azuriraju rezultati dnevnog poslovanja za svako iznajmljivanje, usko povezana sa klasom DnevniRezultat
     * @param datum Datum za koji se vezuju rezultati poslovanja
     * @param iznosPopusta Iznos popusta koji se dodaje na datum iznajmljivanja
     * @param ukupanPrihod Ukupan prihod koji se dodaje na datum iznajmljivanja
     * @param ukupnoPromocije Ukupan iznos promocija koje se dodaju na datum iznajmljivanja
     * @param siriDio Ukupan iznos za voznje koje su se odvijale na sirem dijelu grada, koji se dodaje na datum iznajmljivanja
     * @param uziDio Ukupan iznos za voznje koje su se odvijale na uzem dijelu grada, koji se dodaje na datum iznajmljivanja
     * @param popravke Iznos popravki prevoznog sredstva koje se su se desile za datum iznajmljivanja
     */
    public static void azurirajRezultat(LocalDate datum, double iznosPopusta, double ukupanPrihod, 
            double ukupnoPromocije, double siriDio, double uziDio, double popravke) {
			rezultatiPoDatumu.merge(datum, 
			new DnevniRezultat(datum, iznosPopusta, ukupanPrihod, ukupnoPromocije, siriDio, uziDio, popravke), 
			(stariRezultat, noviRezultat) -> {
			stariRezultat.azuriraj(iznosPopusta, ukupanPrihod, ukupnoPromocije, siriDio, uziDio, popravke);
			return stariRezultat;
});
}
    /**
     * Metoda koja vraca mapu dnevnih rezultata poslovanja za postojeci datum
     * @return Mapa po datumu za dnevne rezultate poslovanja
     */
    public static Map<LocalDate, DnevniRezultat> getRezultatiPoDatumu() {
    	return rezultatiPoDatumu;
    }
    
    /**
     * Konstruktor koji pravi objekat tipa Racun, ucitava properties parametre iz ulaznog fajla, i inkrementuje staticku varijablu brojIznajmljivanja svaki put kada bude pozvan
     * @param iznajmljivanje Objekat iznajmljivanja za koje je neophodno generisati racun
     * @param prevoznoSredstvo Objekat prevoznog sredstva koje se koristu pri iznajmljivanju
     */
    public Racun(Iznajmljivanje iznajmljivanje, PrevoznoSredstvo prevoznoSredstvo) {
        this.iznajmljivanje = iznajmljivanje;
        this.prevoznoSredstvo = prevoznoSredstvo;
        this.properties = new Properties();
        loadProperties();
        
        Racun.brojIznajmljivanja++;
    }
    
    /**
     * Metoda koja sluzi da se ucitaju properties parametri
     */
    private void loadProperties() {
        try (FileInputStream input = new FileInputStream("parametri.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Metoda koja na osnovu naziva klase kombinuje tu klasu, tacnije tip prevoznog sredstva, sa nazivom za jedinicnu cijenu iz properties fajla, i vraca taj naziv
     * @param vehicleClassName Naziv klase za koju je potrebno vratiti jedinicnu cijenu
     * @return Vraca naziv za jedinicnu cijenu iz mape za ulazni string koji predstavlja jedno od 3 tipa prevoznog sredstva, a sluzi za properties parametre
     */
    private String getKeyForVehicle(String vehicleClassName) {
        Map<String, String> vehicleKeyMap = new HashMap<>();
        vehicleKeyMap.put("Automobil", "CAR_UNIT_PRICE");
        vehicleKeyMap.put("Trotinet", "SCOOTER_UNIT_PRICE");
        vehicleKeyMap.put("Bicikl", "BIKE_UNIT_PRICE");
        
        return vehicleKeyMap.get(vehicleClassName);
    }

    /**
     * Metoda za racunanje cijene iznajmljivanja prevoznog sredstva, a koja zavisi od atributa iznajmljivanja, kao i od svih parametara properties fajla
     * @return Vraca ukupnu cijenu za iznajmljivanje prevoznog sredstva
     */
    public double izracunajCijenu() {
    	
    	double dnevniPopust = 0;
    	double dnevnaPromocija = 0;
    	double dnevniSiri=0;
    	double dnevniUzi=0;
    	double dnevnePopravke = 0;

        String key = getKeyForVehicle(prevoznoSredstvo.getClass().getSimpleName());
        double osnovnaCijena = Double.parseDouble(properties.getProperty(key)) * iznajmljivanje.getTrajanje();
        double ukupnaCijena = osnovnaCijena;

        // Provjera da li se radi o sirem ili uzem dijelu grada
        if (jeLiSiriGrad(iznajmljivanje.getPocetnaLokacija()) || jeLiSiriGrad(iznajmljivanje.getDestinacija())) {
            ukupnaCijena *= Double.parseDouble(properties.getProperty("DISTANCE_WIDE"));
            ukupnoSiriDeoGrada += ukupnaCijena;
            dnevniSiri = ukupnaCijena;
        } else {
            ukupnaCijena *= Double.parseDouble(properties.getProperty("DISTANCE_NARROW"));
            ukupnoUziDeoGrada += ukupnaCijena;
            dnevniUzi = ukupnaCijena;
        }

        // Primjena popusta i promocija ako postoje
        double popust = Double.parseDouble(properties.getProperty("DISCOUNT", "1.0"));
        double promocija = Double.parseDouble(properties.getProperty("DISCOUNT_PROM", "1.0"));
        
        if(brojIznajmljivanja%10==0 && popust<1) {
        	double iznosPopusta = ukupnaCijena * popust;
        	dnevniPopust = iznosPopusta;
            ukupanPopust += iznosPopusta;
            ukupnaCijena -= iznosPopusta;
            
        }
        
        if(iznajmljivanje.isPromocija() && promocija<1) {
            ukupnePromocije += (ukupnaCijena * promocija);
            dnevnaPromocija = ukupnaCijena * promocija;
            ukupnaCijena = ukupnaCijena - (ukupnaCijena * promocija);
        }
        
        LocalDate datumZaAzuriranje = iznajmljivanje.getVrijemeIznajmljivanja().toLocalDate();
        
        if (prevoznoSredstvo.isPokvaren()) {
            ukupnaCijena = 0;
            dnevnePopravke = dnevnePopravke + prevoznoSredstvo.izracunajCijenuPopravke();
            datumZaAzuriranje = prevoznoSredstvo.getKvar().getVrijemeKvara().toLocalDate();
            System.out.println("Kvar datum: " + prevoznoSredstvo.getKvar().getVrijemeKvara());

        }

        ukupanPrihod += ukupnaCijena;
        
        
        azurirajRezultat(datumZaAzuriranje, dnevniPopust, ukupnaCijena, dnevnaPromocija, dnevniSiri, dnevniUzi, dnevnePopravke);
        
        return ukupnaCijena;
    }
    
    /**
     * Metoda koja pravi tekstualni fajl racuna koji zavisi od putanje navedene u properties fajlu. Pored same ukupne cijene, racun sadrzi i parametre iznajmljivanja.
     */
    public void generisiRacun() {
    	
    	String receiptFolder = properties.getProperty("RECEIPT_PATH", "racuni"); // Podrazumijevana vrijednost je "racuni" ako nema u properties
        // Kreiranje folder ako ne postoji
        File folder = new File(receiptFolder);
        if (!folder.exists()) {
            folder.mkdir();
        }
        
        // Definisanje naziva fajla sa datumom i vremenom
        String timestamp = iznajmljivanje.getVrijemeIznajmljivanja().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String psId = iznajmljivanje.getIdPrevoznogSredstva();
        String nazivFajla = "racuni/racun_" + iznajmljivanje.getKorisnik().replaceAll(" ", "_") + "_" + timestamp + "_" + psId + ".txt";
        
        double popustIspis = Double.parseDouble(properties.getProperty("DISCOUNT", "1.0"));
        double promocijaIspis = Double.parseDouble(properties.getProperty("DISCOUNT_PROM", "1.0"));
        
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nazivFajla))) {
            writer.write("Racun za iznajmljivanje\n");
            writer.write("===============================\n");
            writer.write("ID korisnika: " + iznajmljivanje.getKorisnik() + "\n");
            writer.write("Vrijeme iznajmljivanja: " + iznajmljivanje.getVrijemeIznajmljivanja().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")) + "\n");
            writer.write("Pocetna lokacija: " + iznajmljivanje.getPocetnaLokacija() + "\n");
            writer.write("Destinacija: " + iznajmljivanje.getDestinacija() + "\n");
            writer.write("Trajanje: " + iznajmljivanje.getTrajanje() + " sekundi\n");
            if(brojIznajmljivanja % 10 == 0) {
            	writer.write("Popust: Da, " + popustIspis*100 + "% \n");
            }else {
            	writer.write("Popust: Ne \n");
            }
            if(iznajmljivanje.isPromocija()) {
            	writer.write("Promocija: Da, " + promocijaIspis*100 + "% \n");
            }else {
            	writer.write("Promocija: Ne \n");
            }
            writer.write("Ukupna cijena: " + izracunajCijenu() + " KM\n");
            
            // Dodavanje specificne informacije za IznajmljivanjeAutomobila
            if (iznajmljivanje instanceof IznajmljivanjeAutomobila) {
                IznajmljivanjeAutomobila autoIznajmljivanje = (IznajmljivanjeAutomobila) iznajmljivanje;
                writer.write("Vozacka dozvola: " + autoIznajmljivanje.getVozacka() + "\n");
                if (autoIznajmljivanje.getPasos() != null) {
                    writer.write("Pasos: " + autoIznajmljivanje.getPasos().getBroj() + "\n");
                }
                if (autoIznajmljivanje.getLicnaKarta() != null) {
                    writer.write("Licna Karta: " + autoIznajmljivanje.getLicnaKarta().getBroj() + "\n");
                }
            }
            
            writer.write("===============================\n");
            writer.write("Hvala sto koristite nase usluge!\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda koja provjerava da li se vozilo tokom svoje putanje nalazilo ikako u sirem dijelu grada, a zavisi od lokacije koja joj se proslijedu. Sluzi za izracun cijene iznajmljivanja, jer postoje razlicite tarife za uzi i siri dio grada.
     * @param lokacija
     * @return True ili false vrijednost u zavisnosti od toga da li je proslijedjena lokacija u sirem dijelu grada. (5,5 do 14,14 je uzi dio grada)
     */
    private boolean jeLiSiriGrad(String lokacija) {
        if (lokacija != null) {
            String[] koordinate = lokacija.split(",");
            int x = Integer.parseInt(koordinate[0].trim());
            int y = Integer.parseInt(koordinate[1].trim());
            
            return (x < 5 || x > 14 || y < 5 || y > 14);
        }
        return false; 
    }

    /**
     * Metoda za dobijanje ukupnog prihoda za sumarni izvjestaj
     * @return Ukupan prihod iznajmljivanja
     */
    public static double getUkupanPrihod() {
        return ukupanPrihod;
    }
    
    /**
     * Metoda za dobijanje ukupne kolicine popusta za sumarni izvjestaj
     * @return Ukupan popust za iznajmljivanja
     */
    public static double getUkupanPopust() {
        return ukupanPopust;
    }
    
    /**
     * Metoda za dobijanje ukupnog iznosa promocija za sumarni izvjestaj
     * @return Ukupni iznos promocija
     */
    public static double getUkupnePromocije() {
        return ukupnePromocije;
    }
    
    /**
     * Metoda za dobijanje ukupnog iznosa voznji u uzem dijelu grada za sumarni izvjestaj
     * @return Ukupni iznos za uzi dio grada
     */
    public static double getUkupnoUziDeoGrada() {
        return ukupnoUziDeoGrada;
    }
    
    /**
     * Metoda za dobijanje ukupnog iznosa voznji u sirem dijelu grada za sumarni izvjestaj
     * @return Ukupni iznos za siri dio grada
     */
    public static double getUkupnoSiriDeoGrada() {
        return ukupnoSiriDeoGrada;
    }
    
    /**
     * Metoda za dobijanje ukupnog iznosa za odrzavanje za sumarni izvjestaj
     * @return Po specifikaciji ukupan prihod pomnozen sa 0.2 (20%)
     */
    public static double getUkupnoOdrzavanje() {
        return ukupanPrihod * 0.2;
    }
    
    /**
     * Metoda za dobijanje ukupnog iznosa za popravke za sumarni izvjestaj
     * @return Ukupni iznos za popravke
     */
    public static double getUkupnePopravke() {
        return ukupnePopravke;
    }
    
    /**
     * Metoda za dobijanje ukupnog iznosa troskova za sumarni izvjestaj
     * @return Po specifikaciji ukupan prihod pomnozen sa 0.2 (20%)
     */
    public static double getUkupniTroskovi() {
        return ukupanPrihod * 0.2;
    }

    /**
     * Metoda za dobijanje ukupnog iznosa poreza za sumarni izvjestaj
     * @return Po specifikaciji ukupan prihod - ukupno odrzavanje - ukupne popravke - ukupni troskovi, sve pomnozeno sa 0.1 (10%)
     */
    public static double getUkupanPorez() {
        return (ukupanPrihod - getUkupnoOdrzavanje() - getUkupnePopravke() - getUkupniTroskovi()) * 0.1;
    }
}
