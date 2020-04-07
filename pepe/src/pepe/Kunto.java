/**
 * 
 */
package pepe;

/**
 * @author anssi
 * @version 7 Apr 2020
 *
 */
public class Kunto {
    
    private int luokitus;
    private String maaritelma;

    
    /**
     * Kuntoluokka
     * @param luokitus Luokitus numeroina 1-3
     * @param maaritelma Kuvaava määritelmä merkkijonona
     * 
     */
    public Kunto(int luokitus, String maaritelma) {
        this.luokitus = luokitus;
        this.maaritelma = maaritelma;
    }
    
    
    /**
     * Aseta luokitus asteikolla 1-3
     * @param luokitus Luokitus numerona
     */
    public void setLuokitus(int luokitus) {
        this.luokitus = luokitus;
    }
    
    
    /**
     * Aseta kuntoluokituksen maaritelma
     * @param maaritelma Kuvaava maaritelma kunnosta
     */
    public void setMaaritelma(String maaritelma) {
        this.maaritelma = maaritelma;
    }

    
    /**
     * @param args Ei käytössä
     */
    public static void main(String[] args) {
    // TODO Auto-generated method stub
    
    }

}
