/**
 * 
 */
package fxPepe;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Random;

/**
 * @author anssi
 * @version 18 Feb 2020
 *
 */
public class PeliTieto {
    
    private int     tTunniste;              // Tiedon id
    private int     pTunniste;              // Id, jolla sidotaan tieto peliin
    private int     alusta;                 // Pelin alusta
    private int     julkaisuvuosi;          // Pelin julkaisuvuosi
    private int     hankintavuosi;          // Pelin hankintavuosi
    private int     levy;                   // Levyn kunto 0-2
    private int     kotelo;                 // Kotelon kunto 0-2
    private int     ohjekirja;              // Ohjekirjan kunto 0-2
    private String  lisatiedot;             // Lisätiedot pelille (muistiinpanoja)
    
    private static int seuraavaTunniste = 1;

    /**
     * 
     */
    public PeliTieto() {
        // Myöhemmin
    }
    
    /**
     * Alustetaan tietyn pelin peliTiedot
     * @param pTunniste pelin viite eli tunniste
     */
    public PeliTieto(int pTunniste) {
        this.pTunniste = pTunniste;
    }
    
    
    /**
     * Apumetodi, jolla saadaan täytettyä testiarvot pelille
     * @param pt pelin tunniste, joka annetaan peliTiedolle talteen
     */
    public void taytaTestiPeliTietoTiedoilla(int pt) {
        pTunniste = pt;
        alusta = 5;
        julkaisuvuosi = 1997;
        hankintavuosi = 1999;
        levy = 2;
        kotelo = 2;
        ohjekirja = 2;
        lisatiedot = "Sain tämän Jarilta 1999";
    }
    
    
    /**
    * Apumetodi, jolla saadaan täytettyä testiarvot peliTiedolle
    * Tunniste arvotaan, jotta kahdella peliTiedolla olisi
    * samoja tietoja.
    */
    public void taytaPeliTiedoilla() {
        Random random = new Random();
        int t = random.nextInt(100 - 1 + 1) + 1;;
        taytaTestiPeliTietoTiedoilla(t);
    }
    
    
    /**
    * Tulostetaan pelin tiedot
    * @param out tietovirta johon tulostetaan
    */
    public void tulosta(PrintStream out) {
        out.println("Tiedon tunniste: " + tTunniste + " Pelin tunniste: " + pTunniste + " | " + alusta + ", " + julkaisuvuosi + ", " + hankintavuosi + ", " + levy + ", " + kotelo + ", " + ohjekirja + ", " + lisatiedot); ;
    }
    
    
    /**
    * Tulostetaan peliTiedot
    * @param os tietovirta johon tulostetaan
    */
    public void tulosta(OutputStream os) {
    tulosta(new PrintStream(os));
    }
    
    /**
     * Antaa peliTiedolle seuraavan tunnistenumeron.
     * @return tTunniste Uusi tunnistenumero
     * @example
     * <pre name="test">
     *   PeliTieto ra1 = new PeliTieto();
     *   ra1.getPelinTunniste() === 0;
     *   ra1.rekisteroi();
     *   PeliTieto ra2 = new PeliTieto();
     *   ra2.rekisteroi();
     *   int n1 = ra1.getPelinTunniste();
     *   int n2 = ra2.getPelinTunniste();
     *   n1 === n2-1;
     * </pre>
     */
    public int rekisteroi() {
        tTunniste = seuraavaTunniste;
        seuraavaTunniste++;
        return tTunniste;
    }
    
    
    /**
     * Väliaikainen viritys!
     * StringGridiin lisaysta varten metodi, joka luo String[] pelistä
     * @param peli Peli
     * @return String[]-taulukko pelin tiedoista
     */
    public String[] getKenttia(Peli peli) {
        String[] t = {Integer.toString(peli.getTunniste()), peli.getNimi()};
        return t;
    }
    
    
    /**
     * Palauttaa peliTiedon oman tunniste id
     * @return peliTiedon tTunniste id
     */
    public int getPeliTietoTunniste() {
        return tTunniste;
    }
    
    
    /**
     * Palautetaan mille pelille peliTieto kuuluu
     * @return pelin id
     */
    public int getPelinTunniste() {
        return pTunniste;
    }
    
    
    /**
     * @param args Ei käytössä
     */
    public static void main(String[] args) {
        PeliTieto tiedot1 = new PeliTieto(), tiedot2 = new PeliTieto();
        tiedot1.rekisteroi();
        tiedot2.rekisteroi();
        tiedot1.tulosta(System.out);
        tiedot1.taytaPeliTiedoilla();
        tiedot1.tulosta(System.out);
        tiedot2.tulosta(System.out);
        tiedot2.taytaPeliTiedoilla();
        tiedot2.tulosta(System.out);
    
    }

}
