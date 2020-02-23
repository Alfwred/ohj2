/**
 * 
 */
package fxPepe;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * @author anssi
 * @version 18 Feb 2020
 *
 */
public class PeliTieto {
    
    private int     tunniste;
    private int     alusta          = 0;
    private int     julkaisuvuosi   = 0;
    private int     ostovuosi       = 0;
    private int     levy            = 0;
    private int     kotelo          = 0;
    private int     ohjekirja       = 0;
    private String  lisatiedot      = "";

    /**
     * 
     */
    public PeliTieto() {
        // TODO Auto-generated constructor stub
    }
    
    
    /**
     * Apumetodi, jolla saadaan täytettyä testiarvot pelille
     */
    public void taytaTestiPeliTietoTiedoilla() {
        alusta = 2;
        julkaisuvuosi = 1997;
        ostovuosi = 1999;
        levy = 3;
        kotelo = 3;
        ohjekirja = 3;
        lisatiedot = "Lisatietoja";
    }
    
    
    /**
    * Apumetodi, jolla saadaan täytettyä testiarvot peliTiedolle
    * Tunniste arvotaan, jotta kahdella jäsenellä ei olisi
    * samoja tietoja.
    */
    public void taytaPeliTiedoilla() {
        taytaTestiPeliTietoTiedoilla();
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
