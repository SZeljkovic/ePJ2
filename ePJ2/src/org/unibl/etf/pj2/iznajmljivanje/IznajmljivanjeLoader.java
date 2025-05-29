package org.unibl.etf.pj2.iznajmljivanje;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Klasa kojoj je osnovni zadatak da procita podatke o iznajmljivanjima iz ulaznog CSV fajla, i na osnovu toga formira listu sortiranih iznajmljivanja
 */
public class IznajmljivanjeLoader {
	
	/**
	 * Glavna funkcija ove klase, sluzi da na osnovu naziva fajla, skenira sve podatke o iznajmljivanju i formira objekte iznajmljivanja
	 * @param filePath Putanja i naziv fajla CSV fajla iz kojeg zelimo procitati podatke o iznajmljivanjima
	 * @return Lista iznajmljivanja formirana na osnovu ulaznog fajla
	 */
    public static List<Iznajmljivanje> ucitajIznajmljivanja(String filePath) {
        Queue<Iznajmljivanje> iznajmljivanjaQueue = new PriorityQueue<>((a, b) -> a.getVrijemeIznajmljivanja().compareTo(b.getVrijemeIznajmljivanja()));
        Map<String, List<LocalDateTime>> idVrijemeMap = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy HH:mm");

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Preskoci prvi red (naslovi kolona)

            while ((line = br.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, "\",", true);
                List<String> fields = new ArrayList<>();
                StringBuilder currentField = new StringBuilder();
                boolean insideQuotes = false;

                while (tokenizer.hasMoreTokens()) {
                    String token = tokenizer.nextToken();

                    if (token.equals("\"")) {
                        insideQuotes = !insideQuotes;
                    } else if (token.equals(",")) {
                        if (insideQuotes) {
                            currentField.append(token);
                        } else {
                            fields.add(currentField.toString().trim());
                            currentField.setLength(0);
                        }
                    } else {
                        currentField.append(token);
                    }
                }
                // Dodaj posljednje polje
                fields.add(currentField.toString().trim());

                if (fields.size() != 8) {  // Provjera formata
                    System.out.println("Neispravan format reda, red će biti ignorisan: " + line);
                    continue;
                }

                try {
                    LocalDateTime vrijemeIznajmljivanja = LocalDateTime.parse(fields.get(0), formatter);
                    String idPrevoznogSredstva = fields.get(2);

                    // Dodaj vrijeme u listu za određeni ID prevoznog sredstva
                    List<LocalDateTime> vremena = idVrijemeMap.getOrDefault(idPrevoznogSredstva, new ArrayList<>());
                    
                    if (vremena.stream().noneMatch(vrijeme -> vrijeme.equals(vrijemeIznajmljivanja))) {
                        // Ako vreme nije prisutno, dodaj novo vreme i kreiraj objekat
                        vremena.add(vrijemeIznajmljivanja);
                        idVrijemeMap.put(idPrevoznogSredstva, vremena);

                        String korisnik = fields.get(1);
                        String pocetnaLokacija = fields.get(3);
                        String destinacija = fields.get(4);
                        int trajanje = Integer.parseInt(fields.get(5));
                        boolean kvar = fields.get(6).equalsIgnoreCase("da");
                        boolean promocija = fields.get(7).equalsIgnoreCase("da");

                        Iznajmljivanje iznajmljivanje;
                        
                        if (idPrevoznogSredstva.startsWith("A")) {
                            LicnaKarta licnaKarta = new LicnaKarta(generisiImePrezime(), generisiBroj()); // Simulirano
                            iznajmljivanje = new IznajmljivanjeAutomobila(vrijemeIznajmljivanja, korisnik, idPrevoznogSredstva, pocetnaLokacija, destinacija, trajanje, promocija, kvar, licnaKarta, generisiBroj());
                        } else {
                            iznajmljivanje = new Iznajmljivanje(vrijemeIznajmljivanja, korisnik, idPrevoznogSredstva, pocetnaLokacija, destinacija, trajanje, promocija, kvar);
                        }

                        iznajmljivanjaQueue.offer(iznajmljivanje);
                    }
                    
                } catch (Exception e) {
                    System.out.println("Greska prilikom obrade reda, red će biti ignorisan: " + line);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<>(iznajmljivanjaQueue);
    }
    
    private static String generisiImePrezime() {
        // Generisanje nasumicnog stringa oblika Ime_Prezime (17 karaktera)
        String alf = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder imePrezime = new StringBuilder();
        Random random = new Random();

        // Generisanje za "Ime"
        for (int i = 0; i < 7; i++) {
            imePrezime.append(alf.charAt(random.nextInt(alf.length())));
        }

        imePrezime.append("_");

        // Generisanje za "Prezime"
        for (int i = 0; i < 10; i++) {
            imePrezime.append(alf.charAt(random.nextInt(alf.length())));
        }

        return imePrezime.toString();
    }

    private static String generisiBroj() {
        // Generisanje nasumicnog stringa od 9 karaktera (slova i brojevi)
        String baza = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder broj = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 9; i++) {
            broj.append(baza.charAt(random.nextInt(baza.length())));
        }

        return broj.toString();
    }
}
