/**
 * 
 */
package pepe;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Pelirekisterin yksittäisen pelin nimike eli nimi
 * Keräämällä pelien nimet omaan tietorakenteeseen vältetään,
 * että ei jouduta toistamaan samaa nimeä useaan kertaan
 * @author Anssi Lepikko
 * @version 23.4.2020
 *
 */
public class Nimike {
    
    private int nTunniste;
    private String  nimi = "";
    
    private static int seuraavaTunniste = 0;
     
    
    /**
     * Muodostaja
     * @param tunniste Nimikkeen tunniste
     * @param nimi Nimikkeen (pelin) nimi
     */
    public Nimike (int tunniste, String nimi) {
        this.nTunniste = tunniste;
        this.nimi = nimi;
    }
    
    
    /**
     * Muodostaja
     * @param nimi Nimikkeen (pelin) nimi
     */
    public Nimike(String nimi) {
        this.nimi = nimi;
    }
    
    
    /**
     *  Oletusmuodostaja
     */
    public Nimike() {
        // Ei käytössä
    }
    
    
    /**
     * Apumetodi, jolla saadaan täytettyä testiarvot nimikkeelle
     */
    public void taytaTestiNimikeTiedoilla() {
            nimi = "Testipelin nimi";
    }
    
    
    /**
     * Lukee nimikkeen merkkijonosyötteestä
     * @param merkkijono Merkkijono, mikä muutetaan Nimike-olion tiedoiksi
     * <pre name="test">
     * Nimike nimike = new Nimike();
     * nimike.parsiNimike("1|Testi Peli");
     * nimike.getTunniste() === 1;
     * nimike.getNimi() === "Testi Peli";
     * </pre>
     */
    public void parsiNimike(String merkkijono) {
        // https://regex101.com/r/HCcqzC/1/
        Pattern esiintyma = Pattern.compile("^(\\d+)\\|(.+)$");
        Matcher etsija = esiintyma.matcher(merkkijono);

        if (etsija.find()) {
            setTunniste(Integer.parseInt(etsija.group(1)));
            this.nimi = etsija.group(2);
        }
    }

    
    /**
    * Tulostetaan nimikkeen id ja nimi
    * @param out tietovirta johon tulostetaan
    */
    public void tulosta(PrintStream out) {
        out.println(nTunniste + "|" + nimi);
    }
    
    
    /**
     * Tulostetaan nimike
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }
    

    /**
     * Rekisteröi nimikkeelle seuraavan tunnisteen
     * @return Palauttaa rekisteröidyn tunnisteen
     */
    public int rekisteroi() {
        nTunniste = seuraavaTunniste;
        seuraavaTunniste++;
        return nTunniste;
    }
    
    
    /**
     * Asettaa tunnusnumeron ja samalla varmistaa että
     * seuraava numero on aina suurempi kuin tähän mennessä suurin.
     * @param id asetettava tunnusnumero
     */
    private void setTunniste(int id) {
        nTunniste = id;
        if ( nTunniste >= seuraavaTunniste ) seuraavaTunniste = nTunniste + 1;
    }
     
    /**
     * Palauttaa nimikkeen tunnisteen
     * @return nimikkeen tunniste
     */
    public int getTunniste() {
        return nTunniste;
    }
    
  
    /**
     * Palauttaa nimikkeen nimen
     * @return Nimikkeen nimi
     */
    public String getNimi() {
        return this.nimi;
    }
    
    
    @Override
    public String toString() {
        return this.getTunniste() + "|" + this.getNimi();
    }
    
    
    /**
     * Tarkistaa syötetyn merkkijonon oikeellisuuden
     * @param merkkijono Mitä tarkastellaan
     * @return Tosi, jos oikeellinen ja epätosi, jos väärä
     * @example
     * <pre name="test">
     * Alusta alusta = new Alusta();
     * alusta.tarkista("|") === false;
     * alusta.tarkista("") === false;
     * alusta.tarkista("Final Fantasy 7 Remake|") === false;
     * alusta.tarkista("Final Fantasy 7 Remake") === true;
     * </pre>
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
     * Vertaa merkkijonoa nimikkeeseen. Vertailun onnistumiseen riittää, että
     * nimike alkaa hakuehdolla.
     * @param merkkijono Merkkijono, jolla haetaan
     * @return Totuusarvo siitä onko nimike hakuehdon mukainen
     * @example
     * <pre name="test">    
     *  Nimike nimike = new Nimike("Tomb Raider");
     *  nimike.vertaa("T")         === true;
     *  nimike.vertaa("To")        === true;
     *  nimike.vertaa("Tom")       === true;
     *  nimike.vertaa("Tomb")      === true;
     *  nimike.vertaa("R")         === true;
     *  nimike.vertaa("Ra")        === true;
     *  nimike.vertaa("Rai")       === true;
     *  nimike.vertaa("Raid")      === true;
     *  nimike.vertaa("Raide")     === true;
     *  nimike.vertaa("Raider")    === true;
     *  nimike.vertaa("")          === true;
     *  nimike.vertaa(" ")         === true;
     *  nimike.vertaa("  ")        === false;
     *  nimike.vertaa("Topi")      === false;
     * </pre>
     */
    public boolean vertaa(String merkkijono) {
        return this.getNimi().toLowerCase().contains(merkkijono.toLowerCase());
    }

    
    /**
     * Testipääohjelma
     * @param args Ei käytössä
     */
    public static void main(String[] args) {
        Nimike nimi1 = new Nimike();
        Nimike nimi2 = new Nimike();
        nimi1.rekisteroi();
        nimi2.rekisteroi();
        nimi1.tulosta(System.out);
        nimi2.tulosta(System.out);
        nimi1.taytaTestiNimikeTiedoilla();
        nimi2.taytaTestiNimikeTiedoilla();
        nimi1.tulosta(System.out);
        nimi2.tulosta(System.out);
    }

}
