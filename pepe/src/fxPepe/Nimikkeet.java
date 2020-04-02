/**
 * 
 */
package fxPepe;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Pepen pelistö joka osaa mm. lisätä uuden pelin
 * @author Anssi Lepikko
 * @version 18 Feb 2020
 *
 */
public class Nimikkeet {
    
    private static final int MAX_NIMIKKEITA     = 10;
    private int              lkm                = 0;
    private String           tiedostonNimi      = "";
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
     * nimikkeet.lisaa(cnc);  #THROWS SailoException
     * </pre>
     */
    public void lisaa(Nimike nimike) throws SailoException {
        if (lkm >= alkiot.length) throw new SailoException("Liikaa alkioita");
        alkiot[lkm] = nimike;
        lkm++;
    }
    
    
    /**
     * Palauttaa viitteen i:teen nimikkeeseen
     * @param i monennenko nimikkeen viite halutaan
     * @return viite nimikkeeseen, jonka indeksi on i
     * @throws IndexOutOfBoundsException jos i ei ole sallitulla alueella  
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
            if (nimike.getTunniste() == tunniste) return nimike;
        }
        return alkiot[0]; // Ei löydy exception tai joku muu myöhemmin
    }
    
    
    /**
     * Lukee nimikkeet tiedostosta. Kesken.
     * @param hakemisto tiedoston hakemisto
     * @throws SailoException jos lukeminen epäonnistuu
     */
    public void lueTiedostostaVANHA(String hakemisto) throws SailoException {
        tiedostonNimi = hakemisto + "/nimikkeet.dat";
        throw new SailoException("Ei osata vielä lukea tiedostoa " + tiedostonNimi);
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
                if (rivi.charAt(0) == ';') continue;
                var uusi = new Nimike();
                uusi.parsiNimike(rivi);
                lisaa(uusi);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Tallentaa nimikkeet tiedostoon. Kesken.
     * @throws SailoException jos talletus epäonnistuu
     */
    public void talleta() throws SailoException {
        throw new SailoException("Ei osata vielä tallettaa tiedostoa " + tiedostonNimi);
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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        

        /**
        Nimike d1 = new Nimike();
        Nimike d2 = new Nimike();
        d1.rekisteroi();
        d1.taytaNimikeTiedoilla();
        d2.rekisteroi();
        d2.taytaNimikeTiedoilla();

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
        } */
    }
}
