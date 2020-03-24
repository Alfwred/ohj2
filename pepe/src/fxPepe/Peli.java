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
public class Peli {
    
    private int     pTunniste;              // Pelin oma id
    private int     nTunniste;              // Pelin nimikkeen id
    private int     aTunniste;              // Pelin alustan id
    private int     julkaisuvuosi;          // Pelin julkaisuvuosi
    private int     hankintavuosi;          // Pelin hankintavuosi
    private int     levy;                   // Levyn kunto 0-2
    private int     kotelo;                 // Kotelon kunto 0-2
    private int     ohjekirja;              // Ohjekirjan kunto 0-2
    private String  lisatiedot;             // Lisätiedot pelille (muistiinpanoja)
    
    private static int seuraavaTunniste = 0;

    /**
     * 
     */
    public Peli() {
        // Myöhemmin
    }
    
    /**
     * Alustetaan tietty peli
     * @param nTunniste Nimikkeeseen viittaava tunniste
     * @param aTunniste Alustaan viittaava tunniste
     */
    public Peli(int nTunniste, int aTunniste) {
        this.nTunniste = nTunniste;
        this.aTunniste = aTunniste;
    }
    
    /**
     * Alustetaan tietty peli syötetyllä tunnisteella
     * @param pTunniste Pelin oma tunniste id
     */
    public Peli(int pTunniste) {
        this.pTunniste = pTunniste;
    }
    
    
    /**
     * Apumetodi, jolla saadaan täytettyä testiarvot pelille
     * @param nt Nimikkeen id, joka haetaan pelille
     * @param at Alustan id, joka haetaan alustalle
     */
    public void taytaTestiPeliTiedoilla(int nt, int at) {
        nTunniste = nt;
        aTunniste = at;
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
        int t = random.nextInt(100 - 1 + 1) + 100;
        int a = random.nextInt(100 - 1 + 1) + 1000;
          
        taytaTestiPeliTiedoilla(t, a);
    }
    
    
    /**
    * Tulostetaan pelin tiedot
    * @param out tietovirta johon tulostetaan
    */
    public void tulosta(PrintStream out) {
        out.println("Pelin tunniste: " + pTunniste + " Nimikkeen tunniste: " + nTunniste + " Alustan tunniste: " + aTunniste + " |  " + julkaisuvuosi + ", " + hankintavuosi + ", " + levy + ", " + kotelo + ", " + ohjekirja + ", " + lisatiedot); ;
    }
    
    
    /**
    * Tulostetaan peli
    * @param os tietovirta johon tulostetaan
    */
    public void tulosta(OutputStream os) {
    tulosta(new PrintStream(os));
    }
    
    /**
     * Antaa peliTiedolle seuraavan tunnistenumeron.
     * @return pTunniste Uusi tunnistenumero
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
        pTunniste = seuraavaTunniste;
        seuraavaTunniste++;
        return pTunniste;
    }
    
    
    /**
     * Palauttaa pelin pTunniste id:n
     * @return id
     */
    public int getTunniste() {
        return pTunniste;
    }
    
    
    /**
     * Palauttaa peliin sidotun alustan nimikkeen tunnisteen (nTunniste)
     * @return peliin sidotun nimikkeen id
     */
    public int getNimike() {
        return nTunniste;
    }
    
    
    /**
     * Palauttaa peliin sidotun alustan tunnisteen (aTunniste)
     * @return peliin sidotun alustan id
     */
    public int getAlusta() {
        return aTunniste;
    }
    
    
    /**
     * @param args Ei käytössä
     */
    public static void main(String[] args) {
        Peli p1 = new Peli();
        Peli p2 = new Peli();
        p1.rekisteroi();
        p2.rekisteroi();
        p1.taytaPeliTiedoilla();
        p2.taytaPeliTiedoilla();
        p1.tulosta(System.out);
        p2.tulosta(System.out);
        
        Integer.toString(p1.getTunniste());
    
    }

}
