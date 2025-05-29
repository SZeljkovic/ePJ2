package org.unibl.etf.pj2.prevoznasredstva;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Klasa koja sluzi da ucitava prevozna sredstva na osnovu ulaznog CSV fajla.
 */
public class PrevoznaSredstvaLoader {

	/**
	 * Glavna metoda klase koja je zaduzena da na osnovu parametra skenira ulazni CSV fajl i formira listu prevoznih sredstava
	 * @param csvFilePath Naziv/Putanja ulaznog fajla iz kojeg je neophodno ucitati prevozna sredstva
	 * @return Lista ucitanih prevoznih sredstava
	 */
    public static List<PrevoznoSredstvo> ucitajPrevoznaSredstva(String csvFilePath) {
        List<PrevoznoSredstvo> prevoznaSredstva = new ArrayList<>();
        Set<String> idSet = new HashSet<>(); // Set za pracenje jedinstvenih ID-ova
        String line;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy.");

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            br.readLine(); // Preskace prvu liniju (zaglavlje)

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                String id = values[0];
                if (idSet.contains(id)) {
                    System.out.println("Dupli ID pronadjen: " + id + ". Ovaj unos ce biti ignorisan.");
                    continue; // Ignorisemo red sa duplim ID-om
                }
                idSet.add(id);

                String proizvodjac = values[1];
                String model = values[2];
                double cijenaNabavke = values[4].isEmpty() ? 0 : Double.parseDouble(values[4]);
                String vrsta = values[8].trim();

                switch (vrsta.toLowerCase()) {
                    case "automobil":
                        LocalDate datumNabavke = values[3].isEmpty() ? null : LocalDate.parse(values[3], formatter);
                        String opis = values[7];
                        PrevoznoSredstvo automobil = new Automobil(id, proizvodjac, model, cijenaNabavke, datumNabavke, opis);
                        prevoznaSredstva.add(automobil);
                        break;

                    case "bicikl":
                        double domet = values[5].isEmpty() ? 0 : Double.parseDouble(values[5]);
                        PrevoznoSredstvo bicikl = new Bicikl(id, proizvodjac, model, cijenaNabavke, domet);
                        prevoznaSredstva.add(bicikl);
                        break;

                    case "trotinet":
                        double maxBrzina = values[6].isEmpty() ? 0 : Double.parseDouble(values[6]);
                        PrevoznoSredstvo trotinet = new Trotinet(id, proizvodjac, model, cijenaNabavke, maxBrzina);
                        prevoznaSredstva.add(trotinet);
                        break;

                    default:
                        System.out.println("Nepoznata vrsta prevoznog sredstva: " + vrsta);
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return prevoznaSredstva;
    }
}
