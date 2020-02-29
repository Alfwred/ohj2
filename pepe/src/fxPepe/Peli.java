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
    
    private int pTunniste;
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
     * @param t Pelille annettava pTunniste
     */
    public void taytaTestiPeliTiedoilla(int t) {
        pTunniste = t;
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
        out.println(pTunniste + "|" + nimi);
    }
    
    
    /**
     * Tulostetaan henkilön tiedot
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }
    
    
    /**
     * StringGridiin lisaysta varten metodi, joka luo String[] pelistä
     * @param peli Peli
     * @return String[]-taulukko pelin tiedoista
     */
    public String[] getKenttia(Peli peli) {
        String[] t = {Integer.toString(peli.getTunniste()), peli.getNimi()};
        return t;
    }
    
    
     /**
      * Antaa joko pelin nimen tai tunnisteen merkkijonona
      * 1 => nimi
      * 0 => tunniste
     * @param peli Peli-olio
     * @param k 1 tai tai 0
     * @return Merkkijono
     */
    public String anna(Peli peli, int k) {
         try {
             if ( k == 1 ) return peli.getNimi();
             if (k == 0 ) return Integer.toString(peli.getTunniste());
             return "";
         } catch (Exception ex) {
             return "";
         }
     }
    
    /**
     * Antaa pelille seuraavan tunnistenumeron.
     * @return pTunniste Uusi tunnistenumero
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
        pTunniste = seuraavaTunniste;
        seuraavaTunniste++;
        return pTunniste;
    }
    
    
    /**
     * Palauttaa pelin tunnisteen
     * @return pelin pTunniste
     */
    public int getTunniste() {
        return pTunniste;
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
