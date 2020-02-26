/**
 * 
 */
package fxPepe;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Random;

/**
 * Pelirekisterin peli
 * 
 * @author Anssi Lepikko
 * @version 18 Feb 2020
 *
 */
public class Peli {
    
    private int tunniste;
    private String  nimi = "";
    
    private static int seuraavaTunniste;
    
    /**
     * @return pelin nimi
     * @example
     * <pre name="test">
     * Peli tr2 = new Peli();
     * tr2.taytaPeliTiedoilla();
     * tr2.getNimi() =R= "Tomb Raider 2"
     * </pre>
     */
    public String getNimi() {
        return this.nimi;
    }
    
    
    /**
     * Apumetodi, jolla saadaan täytettyä testiarvot pelille
     * @param t Pelille annettava tunniste
     */
    public void taytaTestiPeliTiedoilla(int t) {
        tunniste = t;
        nimi = "Tomb Raider 2";
    }
    
    
    /**
    * Apumetodi, jolla saadaan täytettyä testiarvot pelille
    * Tunniste arvotaan, jotta kahdella pelillä ei olisi
    * samoja tietoja.
    */
    public void taytaPeliTiedoilla() {
        Random random = new Random();
        int t = random.nextInt(100 - 1 + 1) + 1;;
        taytaTestiPeliTiedoilla(t);
    }

    
    /**
    * Tulostetaan pelin tiedot
    * @param out tietovirta johon tulostetaan
    */
    public void tulosta(PrintStream out) {
        out.println(tunniste + "|" + nimi);
    }
    
    
    /**
     * Tulostetaan henkilön tiedot
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }
    
    /**
     * Palauttaa Peli-olion merkkijonona
     * @param peli Peli
     * @return Merkkijono
     */
    public String getString(Peli peli) {
        return peli.getTunniste() + peli.getNimi();
        
    }
    
    
    /**
     * Antaa pelille seuraavan tunnistenumeron.
     * @return tunniste Uusi tunnistenumero
     * @example
     * <pre name="test">
     *   Peli ra1 = new Peli();
     *   ra1.getTunniste() === 0;
     *   ra1.rekisteroi();
     *   Peli ra2 = new Peli();
     *   ra2.rekisteroi();
     *   int n1 = ra1.getTunniste();
     *   int n2 = ra2.getTunniste();
     *   n1 === n2-1;
     * </pre>
     */
    public int rekisteroi() {
        tunniste = seuraavaTunniste;
        seuraavaTunniste++;
        return tunniste;
    }
    
    
    /**
     * Palauttaa pelin tunnisteen
     * @return pelin tunniste
     */
    public int getTunniste() {
        return tunniste;
    }
    
    
    /**
     * @param args Ei käytössä
     */
    public static void main(String[] args) {
        Peli ra = new Peli(), cnc = new Peli();
        ra.rekisteroi();
        cnc.rekisteroi();
        ra.tulosta(System.out);
        ra.taytaPeliTiedoilla();
        ra.tulosta(System.out);
        cnc.tulosta(System.out);
        cnc.taytaPeliTiedoilla();
        cnc.tulosta(System.out);
    
    }

}
