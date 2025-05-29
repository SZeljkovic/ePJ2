package org.unibl.etf.pj2.simulacija;

import java.util.concurrent.atomic.AtomicBoolean;

import org.unibl.etf.pj2.iznajmljivanje.*;
import org.unibl.etf.pj2.prevoznasredstva.PrevoznoSredstvo;

/**
 * Klasa koja sluzi da kreira nit za jedno iznajmljivanje, takodje kombinuje soc i povezuje svako iznajmljivanje sa potrebnim prevoznim sredstvom. Redefinisana je run metoda na nacin da se tu predstavlja ponasanje svakog iznajmljivanja.
 */
public class IznajmljivanjeNit implements Runnable {
    private Iznajmljivanje iznajmljivanje;
    private Mapa mapa;
    private PrevoznoSredstvo ps;
    private AtomicBoolean running;
    
    /**
     * Konstruktor za klasu
     * @param iznajmljivanje Objekat iznajmljivanja za koje pravimo nit
     * @param mapa Objekat mape koji je zajednicki za sve niti
     * @param ps Prevozno sredstvo koje se koristi za iznajmljivanje
     * @param running Flag koji se koristi za provjere i bezbjednu terminaciju programa
     */
    public IznajmljivanjeNit(Iznajmljivanje iznajmljivanje, Mapa mapa, PrevoznoSredstvo ps, AtomicBoolean running) {
        this.iznajmljivanje = iznajmljivanje;
        this.mapa = mapa;
        this.ps = ps;
        this.running=running;
        
    }
    
    /**
     * Redefinisana run metoda, u kojoj je kreiran nacin kretanja prevoznog sredstva po poljima mape, i samo njihovo ponasanje.
     */
    @Override
    public void run() {
    	
    	 System.out.println("Nit za prevozno sredstvo " + ps.getId() + " pocinje sa radom.");
    	

        String[] pocetna = iznajmljivanje.getPocetnaLokacija().split(",");
        String[] destinacija = iznajmljivanje.getDestinacija().split(",");

        int startX = Integer.parseInt(pocetna[1]);
        int startY = Integer.parseInt(pocetna[0]);
        int endX = Integer.parseInt(destinacija[1]);
        int endY = Integer.parseInt(destinacija[0]);

        int currentX = startX;
        int currentY = startY;

        int brojPolja = Math.abs(endX - startX) + Math.abs(endY - startY);
        int vrijemePoPolju = iznajmljivanje.getTrajanje() / brojPolja;
        int minimalnoVrijemePoPolju = 500; // Minimalno vrijeme zadrzavanja u ms
        
        String boja = "chartreuse"; // Default boja za automobil
        if (ps.getId().startsWith("T")) {
            boja = "orange"; // Boja za trotinet
        } else if (ps.getId().startsWith("B")) {
            boja = "coral"; // Boja za bicikl
        }

        int brojacPolja = 0;

        // Azuriranje pocetnog polja na plavo
        mapa.azurirajPolje(currentX, currentY, ps.getId(), boja, ps.getNivoBaterije());

        // Kretanje po X osi
        while (currentX != endX) {
        	
        	if (!running.get()) {
                return; // Exit if the flag indicates to stop
            }
            try {
                Thread.sleep(Math.max(vrijemePoPolju * 1000, minimalnoVrijemePoPolju));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Postavljanje prethodnog polja na bijelo
            mapa.azurirajPolje(currentX, currentY, "", "white", -1);

            if (currentX < endX) {
                currentX++;
            } else if (currentX > endX) {
                currentX--;
            }

            // Azuriranje novog polja na plavo
            System.out.println("Trenutna lokacija: (" + currentY + "," + currentX + ")");
            mapa.azurirajPolje(currentX, currentY, ps.getId(), boja, ps.getNivoBaterije());

            brojacPolja++;
            if (brojacPolja % 4 == 0) {
                ps.prazniBateriju(1);
                System.out.println("Nivo baterije smanjen: " + ps.getNivoBaterije() + "%");
            }
        }

        // Kretanje po Y osi
        while (currentY != endY) {
        	if (!running.get()) {
                return; // Zaustavljanje zavisnog od flaga
            }
        	
            try {
                Thread.sleep(Math.max(vrijemePoPolju * 1000, minimalnoVrijemePoPolju));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Postavljanje prethodnog polja na bijelo
            mapa.azurirajPolje(currentX, currentY, "", "white", -1);

            if (currentY < endY) {
                currentY++;
            } else if (currentY > endY) {
                currentY--;
            }

            // Azuriranje novog polja na plavo
            System.out.println("Trenutna lokacija: (" + currentY + "," + currentX + ")");
            mapa.azurirajPolje(currentX, currentY, ps.getId(), boja, ps.getNivoBaterije());

            brojacPolja++;
            if (brojacPolja % 4 == 0) {
                ps.prazniBateriju(1);
                System.out.println("Nivo baterije smanjen: " + ps.getNivoBaterije() + "%");
            }
        }
        
        if (running.get()) {
        	// Kada vozilo stigne na destinaciju, generisanje izvestaja
            System.out.println("Vozilo stiglo na destinaciju: " + iznajmljivanje.getDestinacija());
            Racun racun = new Racun(this.iznajmljivanje, this.ps);
            racun.generisiRacun();
            
        }
       
    }

}


