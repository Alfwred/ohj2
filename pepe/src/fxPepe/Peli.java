/**
 * 
 */
package fxPepe;

import java.io.OutputStream;
import java.io.PrintStream;

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
     * Peli tr2 = new Peli;
     * tr2.taytaTestiPeliTiedoilla();
     * tr2.GetNimi() =R= "Tomb Raider 2"
     * </pre>
     */
    public String getNimi() {
        return this.nimi;
    }
    
    
    /**
     * Apumetodi, jolla saadaan täytettyä testiarvot pelille
     */
    public void taytaTestiPeliTiedoilla() {
        nimi = "Tomb Raider 2";
    }
    
    
    /**
    * Apumetodi, jolla saadaan täytettyä testiarvot pelille
    * Tunniste arvotaan, jotta kahdella jäsenellä ei olisi
    * samoja tietoja.
    */
    public void taytaPeliTiedoilla() {
        taytaTestiPeliTiedoilla();
    }

    
    /**
    * Tulostetaan henkilön tiedot
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
     * Antaa pelille seuraavan tunnistenumeron.
     * @return tunniste Uusi tunnistenumero
     * @example
     * <pre name="test">
     *   Jasen ra = new Jasen();
     *   ra.getTunnusNro() === 0;
     *   ra.rekisteroi();
     *   Jasen ra2 = new Jasen();
     *   ra2.rekisteroi();
     *   int n1 = ra1.getTunnusNro();
     *   int n2 = ra2.getTunnusNro();
     *   n1 === n2-1;
     * </pre>
     */
    public int lisaa() {
        tunniste = seuraavaTunniste;
        seuraavaTunniste++;
        return tunniste;
    }
    
    
    /**
     * Palauttaa jäsenen tunnusnumeron.
     * @return jäsenen tunnusnumero
     */
    public int getTunniste() {
        return tunniste;
    }
    
    
    /**
     * @param args Ei käytössä
     */
    public static void main(String[] args) {
        Peli ra = new Peli(), cnc = new Peli();
        ra.lisaa();
        cnc.lisaa();
        ra.tulosta(System.out);
        ra.taytaPeliTiedoilla();
        ra.tulosta(System.out);
        cnc.tulosta(System.out);
        cnc.taytaPeliTiedoilla();
        cnc.tulosta(System.out);
    
    }

}
