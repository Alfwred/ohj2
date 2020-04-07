/**
 * 
 */
package pepe;

import java.util.ArrayList;
import java.util.List;

/**
 * @author anssi
 * @version 7 Apr 2020
 *
 */
public class Kuntoluokitus {
    
    /** Taulukko Kunto-olioista */
    private final List<Kunto> alkiot = new ArrayList<Kunto>();
    
    private Kunto heikko;
    private Kunto tyydyttava;
    private Kunto hyva;

    
    /**
     * Kuntoluokitusten luonti
     */
    public Kuntoluokitus() {
        this.heikko = new Kunto(1, "Heikko");
        this.tyydyttava = new Kunto(2, "Tyydyttävä");
        this.hyva = new Kunto(3, "Hyvä");
    }

    /**
     * @param args Ei käytössä
     */
    public static void main(String[] args) {
    // TODO Auto-generated method stub
    
    }

}
