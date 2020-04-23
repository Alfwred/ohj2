package pepe;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;
import fxPepe.SailoException;

/**
 * Tietorakenneluokka nimikkeille.
 * @author Anssi Lepikko
 * @version 23.4.2020
 *
 */
public class Nimikkeet {
    
    private static final int MAX_NIMIKKEITA     = 5;
    private int              lkm                = 0;
    private Nimike           alkiot[]           = new Nimike[MAX_NIMIKKEITA];

    /**
     * Oletusmuodostaja
     */
    public Nimikkeet() {
        // Attribuuttien oma alustus riittää
    }
    
    
    /**
     * Lisää uuden nimikkeen tietorakenteeseen. Ottaa nimikkeen omistukseensa.
     * @param nimike Lisättävän nimikkeen viite. Huom tietorakenne muuttuu omistajaksi.
     * @throws SailoException jos tietorakenne on jo täynnä
     * @example
     * <pre name="test">
     * #THROWS SailoException
     * #import fxPepe.SailoException;
     * Nimikkeet nimikkeet = new Nimikkeet();
     * Nimike cnc = new Nimike(), ra = new Nimike();
     * nimikkeet.getLkm() === 0;
     * nimikkeet.lisaa(cnc); nimikkeet.getLkm() === 1;
     * nimikkeet.lisaa(ra); nimikkeet.getLkm() === 2;
     * nimikkeet.lisaa(cnc); nimikkeet.getLkm() === 3;
     * nimikkeet.anna(0) === cnc;
     * nimikkeet.anna(1) === ra;
     * nimikkeet.anna(2) === cnc;
     * nimikkeet.anna(1) == cnc === false;
     * nimikkeet.anna(1) == ra === true;
     * nimikkeet.anna(3) === cnc; #THROWS IndexOutOfBoundsException 
     * nimikkeet.lisaa(cnc); nimikkeet.getLkm() === 4;
     * nimikkeet.lisaa(cnc); nimikkeet.getLkm() === 5;
     * </pre>
     */
    public void lisaa(Nimike nimike) throws SailoException {
        if (lkm >= alkiot.length) 
        {
            alkiot = Arrays.copyOf(alkiot, lkm * 2);
        }
        alkiot[lkm] = nimike;
        lkm++;
    }
    
    
    /**
     * Poistaa nimikkeen tunnisteen perusteella
     * @param tunniste Nimikkeen tunniste
     * <pre name="test">
     * #THROWS SailoException
     * Nimikkeet nimikkeet = new Nimikkeet();
     * Nimike cnc = new Nimike(), ra = new Nimike();
     * nimikkeet.getLkm() === 0;
     * nimikkeet.lisaa(cnc); nimikkeet.getLkm() === 1;
     * nimikkeet.lisaa(ra); nimikkeet.getLkm() === 2;
     * nimikkeet.poista(ra.getTunniste()); nimikkeet.getLkm() === 1;
     * nimikkeet.poista(cnc.getTunniste()); nimikkeet.getLkm() === 1;
     * </pre>
     */
    public void poista(int tunniste) { 
        for (int i = 0; i < lkm; i++) {
            if (alkiot[i] == null) return;
            if (tunniste == alkiot[i].getTunniste()) alkiot[i] = null;
        }
        lkm--;
    }

    
    /**
     * Palauttaa viitteen i:teen nimikkeeseen
     * @param i monennenko nimikkeen viite halutaan
     * @return viite nimikkeeseen, jonka indeksi on i
     * @throws IndexOutOfBoundsException jos i ei ole sallitulla alueella
     * <pre name="test">
     * #THROWS SailoException
     * Nimikkeet nimikkeet = new Nimikkeet();
     * Nimike cnc = new Nimike("CNC"), ra = new Nimike("RA"), d2 = new Nimike("D2");
     * nimikkeet.getLkm() === 0;
     * nimikkeet.lisaa(cnc); nimikkeet.getLkm() === 1;
     * nimikkeet.lisaa(ra); nimikkeet.getLkm() === 2;
     * nimikkeet.lisaa(d2); nimikkeet.getLkm() === 3;
     * nimikkeet.anna(0).getNimi() === "CNC";
     * nimikkeet.anna(1).getNimi() === "RA";
     * nimikkeet.anna(2).getNimi() === "D2";
     * </pre>
     */
    public Nimike anna(int i) throws IndexOutOfBoundsException {
        if (i < 0 || lkm <= i)
            throw new IndexOutOfBoundsException("Laiton indeksi: " + i);
        return alkiot[i];
    }
    
    
    /**
     * Palauttaa viitteen pelin nimikkeeseen
     * @param tunniste Pelin tietoihin sidottu tunniste, jonka mukaan nimikettä haetaan
     * @return viite nimikkeeseen
     */
    public Nimike annaNimike(int tunniste) {
        for (Nimike nimike : alkiot) {
            // Continue sen takia, koska rakenteessa on välissä NULL-alkioita ja
            // ne fakuppaa tuon alemman nimikkeen etsinnän. Purkkaratkaisu?
            if (nimike == null) continue;
            if (nimike.getTunniste() == tunniste) return nimike;
        }
        return null;
    }
    
    
    /**
     * Palauttaa viitteen pelin nimikkeeseen
     * @param merkkijono Nimikkeen haku nimikkeen nimen mukaan
     * @return Olioviite nimikkeeseen
     */
    public Nimike annaNimike(String merkkijono) {
        for (Nimike nimike : alkiot) {
            // Continue sen takia, koska rakenteessa on välissä NULL-alkioita ja
            // ne fakuppaa tuon alemman nimikkeen etsinnän. Purkkaratkaisu?
            if (nimike == null) continue;
            if (nimike.getNimi().equalsIgnoreCase(merkkijono)) return nimike;
        }
        return null;
    }
    
    
    /**
     * Etsii merkkijonoa vastaavan nimikkeen
     * @param merkkijono Merkkijono, jota verrataan Pepen nimikkeisiin
     * @return Nimike-olio
     */
    public Nimike vertaaNimike(String merkkijono) {
        for (Nimike nimike : alkiot) if (nimike.getNimi().equalsIgnoreCase(merkkijono)) return nimike;
        return null;
    }
    
    
    /**
     * Luetaan nimikkeet tiedostosta
     * @throws SailoException Virhe, jos ongelma
     */
    public void lueTiedostosta() throws SailoException {
        File tiedosto = new File("nimikkeet.txt");
        try (Scanner sc = new Scanner(new FileInputStream(tiedosto),StandardCharsets.UTF_8)) {
            while (sc.hasNextLine()) {
                String rivi = sc.nextLine();
                var uusi = new Nimike();
                uusi.parsiNimike(rivi);
                lisaa(uusi);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Tallentaa nimikkeet tiedostoon   
     * @throws SailoException jos talletus epäonnistuu
     */
    public void tallenna() throws SailoException {
        try (PrintWriter pw = new PrintWriter(new FileOutputStream("nimikkeet.txt", false))) {
            for (int i = 0; i < this.getLkm(); i++) {
                Nimike nimike = this.anna(i);
                pw.println(nimike.toString());
            }
        } catch (IOException ex) {
            System.err.println("Virhe luettaessa tiedostoa! " + ex.getMessage());
            return;
        }

    }
    

    /**
     * Palauttaa nimikkeiden lukumäärän
     * @return jäsenten lukumäärä
     */
    public int getLkm() {
        return lkm;
    }

    
    /**
     * Testiohjelma nimikkeille
     * @param args ei käytössä
     */
    public static void main(String args[]) {   
        Nimikkeet nimikkeet = new Nimikkeet();
        try {
            nimikkeet.lueTiedostosta();
            
            for (int i = 0; i < nimikkeet.getLkm(); i++) {
                Nimike nimike = nimikkeet.anna(i);
                nimike.tulosta(System.out);
            }
        } catch (SailoException e) {
            e.printStackTrace();
        }
        
        Nimike d1 = new Nimike();
        Nimike d2 = new Nimike();
        d1.rekisteroi();
        d1.taytaTestiNimikeTiedoilla();
        d2.rekisteroi();
        d2.taytaTestiNimikeTiedoilla();

        try {
            nimikkeet.lisaa(d1);
            nimikkeet.lisaa(d2);

            for (int i = 0; i < nimikkeet.getLkm(); i++) {
                Nimike nimike = nimikkeet.anna(i);
                System.out.println("Peli nro: " + i);
                nimike.tulosta(System.out);
            }

        } catch (SailoException ex) {
            System.out.println(ex.getMessage());
        } 
    }
}
