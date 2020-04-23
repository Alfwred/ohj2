/**
 * 
 */
package pepe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Tietorakenne kuntoluokituksille, jotka auttavat asettamaan pelille kuntotiedot
 * @author Anssi Lepikko
 * @version 23.4.2020
 * 
 */
public class Kunto implements Iterable<Kuntoluokitus> {
    
    /** Taulukko Kunto-olioista */
    private final List<Kuntoluokitus> alkiot = new ArrayList<Kuntoluokitus>();

    /**
     * Kuntoluokitusten luonti. Kovakoodatut oliot ja niiden ominaisuudet
     */
    public Kunto() {
        this.alkiot.add(new Kuntoluokitus(3, "MINT"));
        this.alkiot.add(new Kuntoluokitus(2, "OK"));
        this.alkiot.add(new Kuntoluokitus(1, "HUONO"));
    }


    @Override
    public Iterator<Kuntoluokitus> iterator() {
        return alkiot.iterator();
    }


    /**
     * Hakee luokitusta vastaavan kuvaavan Kunto-olion
     * @param arvo Peliin sidottu kunnon arvo
     * @return kuntoa vastaava Kunto-olio
     * @example
     * <pre name="test">
     * Kunto luokitukset = new Kunto();
     * luokitukset.haeLuokitus(3).toString() === "MINT";
     * luokitukset.haeLuokitus(2).toString() === "OK";
     * luokitukset.haeLuokitus(1).toString() === "HUONO";
     * </pre>
     */
    public Kuntoluokitus haeLuokitus(int arvo) {
        for (Kuntoluokitus kunto : alkiot) {
            if (kunto.getLuokitus() == arvo)
                return kunto;
        }
        return new Kuntoluokitus(0, "Kunto ei löytynyt");
    }

    
    /**
     * @return Lista kuntoluokituksista
     */
    public List<Kuntoluokitus> annaKuntoluokitukset() {
        return alkiot;
    }
    

    /**
     * Testipääohjelma
     * @param args Ei käytössä
     */
    public static void main(String[] args) {
        Pepe pepe = new Pepe();
        System.out.println(pepe.annaKuntoluokitukset());
    }
}
