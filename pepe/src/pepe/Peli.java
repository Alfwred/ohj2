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
    private int     ohje;              // Ohjekirjan kunto 0-2
    private String  lisatiedot;             // Lisätiedot pelille (muistiinpanoja)
    
    private static int seuraavaTunniste = 0;

    /**
     * Parametriton
     */
    public Peli() {
        // Parametriton
    }
    
    
    /**
     * Alustetaan peli oletusarvoilla
     * @param nt Pelin nimikkeen tunniste
     * @param at Pelin alustan tunniste
     */
    public Peli(int nt, int at) {
        this.nTunniste = nt;
        this.aTunniste = at;
        this.julkaisuvuosi = 2020;
        this.hankintavuosi = 2020;
        this.levy = 2;
        this.kotelo = 2;
        this.ohje = 2;
        this.lisatiedot = "";
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
        this.ohje = 2;
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
        // https://regex101.com/r/YgveGF/3/
        Pattern esiintyma = Pattern.compile("^(\\d+)\\|(\\d+)\\|(\\d+)\\|(\\d+)\\|(\\d+)\\|(\\d+)\\|(\\d+)\\|(\\d+)\\|(.+|)$");
        Matcher etsija = esiintyma.matcher(merkkijono);

        if (etsija.find()) {
            setTunniste(Integer.parseInt(etsija.group(1)));
            this.nTunniste = Integer.parseInt(etsija.group(2));
            this.aTunniste = Integer.parseInt(etsija.group(3));
            this.julkaisuvuosi = Integer.parseInt(etsija.group(4));
            this.hankintavuosi = Integer.parseInt(etsija.group(5));
            this.levy = Integer.parseInt(etsija.group(6));
            this.kotelo = Integer.parseInt(etsija.group(7));
            this.ohje = Integer.parseInt(etsija.group(8));
            this.lisatiedot = etsija.group(9);
        }
    }
    
    
    /**
    * Tulostetaan pelin tiedot
    * @param out tietovirta johon tulostetaan
    */
    public void tulosta(PrintStream out) {
        out.println("Pelin tunniste: " + pTunniste + " Nimikkeen tunniste: " + nTunniste + " Alustan tunniste: " + aTunniste + " | " + julkaisuvuosi + ", " + hankintavuosi + ", " + levy + ", " + kotelo + ", " + ohje + ", " + lisatiedot); ;
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
     * Palauttaa pelin ohjeen kunnon asteikolla 0-2
     * @return Pelin kokelon kunto
     */
    public int getOhje() {
        return this.ohje;
    }
    
    
    /**
     * Palauttaa pelin lisätiedot
     * @return Pelin lisätiedot
     */
    public String getLisatiedot() {
        return this.lisatiedot;
    }
    
    
    /**
     * Asettaa pelille julkaisuvuoden
     * @param vuosi Vuosiluku
     */
    public void setJulkaisuvuosi(int vuosi) {
        this.julkaisuvuosi = vuosi;
    }
    
    
    /**
     * Asettaa pelille hankintavuoden
     * @param vuosi Vuosiluku
     */
    public void setHankintavuosi(int vuosi) {
        this.hankintavuosi = vuosi;
    }
    
    
    /**
     * Asettaa pelille nimikkeen tunnisteen perusteella
     * @param tunniste Nimikkeen tunniste
     */
    public void setNimike(int tunniste) {
        this.nTunniste = tunniste;
    }
    
    
    /**
     * Asettaa pelille alustan tunnisteen perusteella
     * @param tunniste Alustan tunniste
     */
    public void setAlusta(int tunniste) {
        this.aTunniste = tunniste;
    }
    
    
    /**
     * Asettaa pelille lisätiedo
     * @param merkkijono Lisätiedot
     */
    public void setLisatiedot(String merkkijono) {
        this.lisatiedot = merkkijono;
    }
    
    
    /**
     * Parsii pelille kuntopisteet merkkijonosta
     * @param kunto Merkkijono
     */
    public void setKunto(String kunto) {
        this.levy = Integer.parseInt(kunto, 0, 1, 10);
        this.ohje = Integer.parseInt(kunto, 1, 2, 10);
        this.kotelo  = Integer.parseInt(kunto, 2, 3, 10);
    }
    
    
    @Override
    public String toString() {
        return this.getTunniste() + "|" + 
               this.getNimike() + "|" + 
               this.getAlusta() + "|" + 
               this.julkaisuvuosi + "|" + 
               this.hankintavuosi + "|" + 
               this.levy + "|" + 
               this.kotelo + "|" + 
               this.ohje + "|" + 
               this.lisatiedot +"|";
    }
    
    
    /**
     * Tuloste olion tallentamista varten
     * @return Merkkijono oliosta
     */
    public String annaTallennusMuoto() {
        return this.getTunniste() + "|" + 
                this.getNimike() + "|" + 
                this.getAlusta() + "|" + 
                this.julkaisuvuosi + "|" + 
                this.hankintavuosi + "|" + 
                this.levy + "|" + 
                this.kotelo + "|" + 
                this.ohje + "|" + 
                this.lisatiedot;
    }
    
    
    /**
     * Tarkistaa syötetyn merkkijonon oikeellisuuden
     * @param merkkijono Mitä tarkastellaan
     * @return True => oikeellinen, false => virheellinen
     */
    public boolean tarkista(String merkkijono) {
        // Tyhjän merkkijonon käsittely
        if (merkkijono.equalsIgnoreCase("")) return false;
        
        // Estetään, että ei voi asettaa tolppaa nimen seassa
        Pattern esiintyma = Pattern.compile("\\|");
        Matcher etsija = esiintyma.matcher(merkkijono);

        if (etsija.find()) return false;
        return true;
    }
    
    
    /**
     * Tarkistaa syötetyn vuosiluvun oikeellisuden
     * @param merkkijono Tarkasteltava merkkijono
     * @return True => oikeellinen, false => virheellinen
     */
    public boolean tarkistaJulkaisuvuosi(String merkkijono) {
        // Estetään väärä syöte
        // https://regex101.com/r/BQpUZC/1
        Pattern esiintyma = Pattern.compile("^(19|20)\\d{2}$");
        Matcher etsija = esiintyma.matcher(merkkijono);
        if (etsija.find() && Integer.parseInt(merkkijono) < this.getHankintavuosi()) return true;
        return false;
    }
    
    
    /**
     * Tarkistaa syötetyn vuosiluvun oikeellisuden
     * @param merkkijono Tarkasteltava merkkijono
     * @return True => oikeellinen, false => virheellinen
     */
    public boolean tarkistaHankintavuosi(String merkkijono) {
        // Estetään väärä syöte
        // https://regex101.com/r/BQpUZC/1
        Pattern esiintyma = Pattern.compile("^(19|20)\\d{2}$");
        Matcher etsija = esiintyma.matcher(merkkijono);
        if (etsija.find() && Integer.parseInt(merkkijono) > this.getJulkaisuvuosi()) return true;
        return false;
    }
    
    
    /**
     * Tarkistaa syötetyn merkkijonon oikeellisuuden
     * @param merkkijono Mitä tarkastellaan
     * @return True => oikeellinen, false => virheellinen
     */
    public boolean tarkistaLisatiedot(String merkkijono) {
        // Estetään, että ei voi asettaa tolppaa nimen seassa
        Pattern esiintyma = Pattern.compile("\\|");
        Matcher etsija = esiintyma.matcher(merkkijono);

        if (etsija.find()) return false;
        return true;
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
