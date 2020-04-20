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
 * 
 * @author Anssi Lepikko
 * @version 18 Feb 2020
 *
 */
public class Nimike {
    
    private int nTunniste;
    private String  nimi = "";
    
    private static int seuraavaTunniste = 0;
     
    
    /**
     * @param tunniste Tunista
     * @param nimi Nimi
     */
    public Nimike (int tunniste, String nimi) {
        this.nTunniste = tunniste;
        this.nimi = nimi;
    }
    
    
    /**
     *  Muodostaa nimikkeen annetulla nimellä
     * @param nimi Nimikkeen (pelin) nimi
     */
    public Nimike(String nimi) {
        this.nimi = nimi;
    }
    
    
    /**
     *  Parametriton
     */
    public Nimike() {
        // Parametriton
    }
    
    
    /**
     * Apumetodi, jolla saadaan täytettyä testiarvot nimikkeelle
     */
    public void taytaTestiNimikeTiedoilla() {
            nimi = "Testipelin nimi";
    }
    
    
    /**
     * Lukee nimikkeen merkkijonosyötteestä
     * @param merkkijono Syote, mikä muutetaan Nimike-olioksi
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
     * Antaa nimikkeelle seuraavan tunnistenumeron.
     * @return nTunniste Uusi tunnistenumero
     * @example
     * <pre name="test">
     *   Nimike n1 = new Nimike();
     *   n1.getTunniste() === 0;
     *   n1.rekisteroi();
     *   Nimike n2 = new Nimike();
     *   n2.rekisteroi();
     *   int nimi1 = n1.getTunniste();
     *   int nimi2 = n2.getTunniste();
     *   nimi1 === nimi2-1;
     * </pre>
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
     * @return nimikkeen nTunniste
     */
    public int getTunniste() {
        return nTunniste;
    }
    
    
    /**
     * @return pelin nimike
     * @example
     * <pre name="test">
     * Nimike tr2 = new Nimike();
     * tr2.taytaNimikeTiedoilla();
     * tr2.getNimi() =R= "Tomb Raider 2"
     * </pre>
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
