/**
 * 
 */
package pepe;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
     * Parametriton
     */
    public Peli() {
        
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
        this.nTunniste = nt;
        this.aTunniste = at;
        this.julkaisuvuosi = 1997;
        this.hankintavuosi = 1999;
        this.levy = 2;
        this.kotelo = 2;
        this.ohjekirja = 2;
        this.lisatiedot = "Testipelin lisatiedot";
    }
    
    
    /**
    * Apumetodi, jolla saadaan täytettyä testiarvot peliTiedolle
    * Tunniste arvotaan, jotta kahdella peliTiedolla olisi
    * samoja tietoja.
    */
    public void taytaPeliTiedoilla() {
        Random random = new Random();
        int t = random.nextInt(100 - 1 + 1);
        int a = random.nextInt(100 - 1 + 1);
          
        taytaTestiPeliTiedoilla(t, a);
    }
    
    
    /**
     * Lukee nimikkeen merkkijonosyötteestä
     * @param merkkijono Syote, mikä muutetaan Nimike-olioksi
     */
    public void parsiPeli(String merkkijono) {
        // https://regex101.com/r/YgveGF/1/
        Pattern esiintyma = Pattern.compile("^(\\d+)\\|(\\d+)\\|(\\d+)\\|(\\d+)\\|(\\d+)\\|(\\d+)\\|(\\d+)\\|(\\d+)\\|(.+|)\\|$");
        Matcher etsija = esiintyma.matcher(merkkijono);

        if (etsija.find()) {
            setTunniste(Integer.parseInt(etsija.group(1)));
            this.nTunniste = Integer.parseInt(etsija.group(2));
            this.aTunniste = Integer.parseInt(etsija.group(3));
            this.julkaisuvuosi = Integer.parseInt(etsija.group(4));
            this.hankintavuosi = Integer.parseInt(etsija.group(5));
            this.levy = Integer.parseInt(etsija.group(6));
            this.kotelo = Integer.parseInt(etsija.group(7));
            this.ohjekirja = Integer.parseInt(etsija.group(8));
            this.lisatiedot = etsija.group(9);
        }
    }
    
    
    /**
    * Tulostetaan pelin tiedot
    * @param out tietovirta johon tulostetaan
    */
    public void tulosta(PrintStream out) {
        out.println("Pelin tunniste: " + pTunniste + " Nimikkeen tunniste: " + nTunniste + " Alustan tunniste: " + aTunniste + " | " + julkaisuvuosi + ", " + hankintavuosi + ", " + levy + ", " + kotelo + ", " + ohjekirja + ", " + lisatiedot); ;
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
        this.pTunniste = seuraavaTunniste;
        seuraavaTunniste++;
        return pTunniste;
    }
    
    
    /**
     * Asettaa tunnusnumeron ja samalla varmistaa että
     * seuraava numero on aina suurempi kuin tähän mennessä suurin.
     * @param id Asetettava tunnusnumero
     */
    private void setTunniste(int id) {
        this.pTunniste = id;
        if ( pTunniste >= seuraavaTunniste ) seuraavaTunniste = pTunniste + 1;
    }

    
    /**
     * Palauttaa pelin pTunniste id:n
     * @return Pelin tunniste id
     */
    public int getTunniste() {
        return this.pTunniste;
    }
    
    
    /**
     * Palauttaa peliin sidotun alustan nimikkeen tunnisteen (nTunniste)
     * @return Peliin sidotun nimikkeen id
     */
    public int getNimike() {
        return this.nTunniste;
    }
    
    
    /**
     * Palauttaa peliin sidotun alustan tunnisteen (aTunniste)
     * @return Peliin sidotun alustan id
     */
    public int getAlusta() {
        return this.aTunniste;
    }
    
    
    /**
     * Palauttaa pelin julkaisuvuoden
     * @return Pelin julkaisuvuosi
     */
    public int getJulkaisuvuosi() {
        return this.julkaisuvuosi;
    }
    
    
    /**
     * Palauttaa pelin hankintavuoden
     * @return Pelin hankintavuosi
     */
    public int getHankintavuosi() {
        return this.hankintavuosi;
    }
    
    
    /**
     * Palauttaa pelin levyn kunto asteikolla 0-2
     * @return Pelin levyn kunto
     */
    public int getLevy() {
        return this.levy;
    }
    
    
    /**
     * Palauttaa pelin kotelon kunto asteikolla 0-2
     * @return Pelin kotelon kunto
     */
    public int getKotelo() {
        return this.kotelo;
    }
    
    
    /**
     * Palauttaa pelin ohjekirjan kunnon asteikolla 0-2
     * @return Pelin kokelon kunto
     */
    public int getOhjekirja() {
        return this.ohjekirja;
    }
    
    
    /**
     * Palauttaa pelin lisätiedot
     * @return Pelin lisätiedot
     */
    public String getLisatiedot() {
        return this.lisatiedot;
    }
    
    
    @Override
    public String toString() {
        return this.getTunniste() + "|" + this.getNimike() + "|" + this.getAlusta() +
                "|" + this.julkaisuvuosi + "|" + this.hankintavuosi + "|" + this.levy +
                "|" + this.kotelo + "|" + this.ohjekirja + "|" + this.lisatiedot +"|";
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
