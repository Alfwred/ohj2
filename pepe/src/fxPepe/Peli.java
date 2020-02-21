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
    
    private int     tunniste;
    private String  nimi            = "";
    private int     alusta          = 0;
    private int     julkaisuvuosi   = 0;
    private int     ostovuosi       = 0;
    private int     kunto           = 0;
    private String  lisatiedot      = "";
    
    
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
     * @param t Aputunniste, joka annetaan pelille
     */
    public void taytaTestiPeliTiedoilla(int t) {
        tunniste = t;
        nimi = "Tomb Raider 2";
        alusta = 2;
        julkaisuvuosi = 1997;
        ostovuosi = 1999;
        kunto = 222;
        lisatiedot = "Ei lisatietoja";
    }
    
    
    /**
    * Apumetodi, jolla saadaan täytettyä testiarvot pelille
    * Tunniste arvotaan, jotta kahdella jäsenellä ei olisi
    * samoja tietoja.
    */
    public void taytaPeliTiedoilla() {
        int aputunniste = arvoTunniste();
        taytaTestiPeliTiedoilla(aputunniste);
    }

    
    /**
    * Tulostetaan henkilön tiedot
    * @param os tietovirta johon tulostetaan
    */
    public void tulosta(OutputStream os) {
    tulosta(new PrintStream(os));
    }
    
    
    /**
     * @param args Ei käytössä
     */
    public static void main(String[] args) {
    // TODO Auto-generated method stub
    
    }

}
