package org.unibl.etf.pj2.simulacija;

import org.unibl.etf.pj2.prevoznasredstva.*;
import org.unibl.etf.pj2.iznajmljivanje.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * Klasa kojom se predstavlja simulacija iznajmljivanja prevoznih sredstava. Kao atribute imamo liste prevoznih sredstava i iznajmljjivanja, kao i mapu koja je zajednicka za sva iznajmljivanja.
 */
public class Simulacija {
    private final List<PrevoznoSredstvo> prevoznaSredstva;
    private final List<Iznajmljivanje> iznajmljivanja;
    private final Mapa mapa;
    private final AtomicBoolean running = new AtomicBoolean(true);

    /**
     * Konstruktor za klasu
     * @param prevoznaSredstva Lista ucitanih prevoznih sredstava
     * @param iznajmljivanja Lista ucitanih iznajmljivanja
     * @param mapa Mapa na kojoj prikazujemo iznajmljivanje
     */
    public Simulacija(List<PrevoznoSredstvo> prevoznaSredstva, List<Iznajmljivanje> iznajmljivanja, Mapa mapa) {
        this.prevoznaSredstva = prevoznaSredstva;
        this.iznajmljivanja = iznajmljivanja;
        this.mapa = mapa;
    }
    
    /**
     * Metoda za postavljanje running flaga na false/efektivno zaustavljanje simulacije
     */
    public void stopSimulation() {
        running.set(false); 
    }
    
    /**
     * Metoda za provjeru da li je simulacija pokrenuta
     * @return Vrijednost u zavisnosti od toga da li je trenutno pokrenuta
     */
    public boolean isRunning() {
        return running.get();
    }
    
    /**
     * Metoda za pokretanje simulacije za upareno prevozno sredstvo i iznajmljivanje. Metoda prvo grupise iznajmljivanja po datumima i tako pravi grupe za svako vrijeme.
     */
    public void pokreniSimulaciju() {
        // Grupisanje iznajmljivanja po datumu i vremenu i sortiranje
        Map<LocalDateTime, List<Iznajmljivanje>> grupisanaIznajmljivanja = iznajmljivanja.stream()
                .collect(Collectors.groupingBy(Iznajmljivanje::getVrijemeIznajmljivanja, TreeMap::new, Collectors.toList()));

        for (Map.Entry<LocalDateTime, List<Iznajmljivanje>> entry : grupisanaIznajmljivanja.entrySet()) {
            List<Thread> niti = new ArrayList<>();
            List<Iznajmljivanje> iznajmljivanjaZaTrenutnoVrijeme = entry.getValue();
            
            System.out.println("Pocetak iznajmljivanja za vrijeme: " + entry.getKey());

            for (Iznajmljivanje iznajmljivanje : iznajmljivanjaZaTrenutnoVrijeme) {
                PrevoznoSredstvo odgovarajucePS = prevoznaSredstva.stream()
                        .filter(ps -> ps.getId().equals(iznajmljivanje.getIdPrevoznogSredstva()))
                        .findFirst()
                        .orElse(null);

                if (odgovarajucePS != null && !iznajmljivanje.isPokvarenoPs()) {
                    IznajmljivanjeNit nit = new IznajmljivanjeNit(iznajmljivanje, mapa, odgovarajucePS, running);
                    niti.add(new Thread(nit));
                } else {
                    System.out.println("Nije pronadjeno odgovarajuce prevozno sredstvo za iznajmljivanje, ili je ono pokvareno - sa ID-om: " + iznajmljivanje.getIdPrevoznogSredstva());
                }
            }

            // Pokretanje niti za trenutni datum i vrijeme
            for (Thread nit : niti) {
                nit.start();
            }

            // Cekanje da se sve niti zavrse
            for (Thread nit : niti) {
                try {
                    nit.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            
            if (!running.get()) {
                break; // Zavrsetak ako se simulacija zaustavi
            }
            
            System.out.println("Završetak iznajmljivanja za vrijeme: " + entry.getKey());
            
            // Pauza od 5 sekundi nakon zavrsetka simulacije za trenutni datum i vrijeme
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            mapa.resetMap();
        }
    }
}
