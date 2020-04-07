/**
 * 
 */
package pepe;

/**
 * @author anssi
 * @version 7 Apr 2020
 *
 */
public class Kuntoluokitus {
    
    private int luokitus;
    private String maaritelma;

    
    /**
     * Kuntoluokka
     * @param luokitus Luokitus numeroina 1-3
     * @param maaritelma Kuvaava määritelmä merkkijonona
     * 
     */
    public Kuntoluokitus(int luokitus, String maaritelma) {
        this.luokitus = luokitus;
        this.maaritelma = maaritelma;
    }
    
    
    /**
     * Hakee kunnon luokituksen
     * @return Luokitus numeraalisena väliltä 1-3
     */
    public int getLuokitus() {
        return this.luokitus;
    }
    
    
    /**
     * Hakee kunnon määritelmän
     * @return Kuvaava maaritelma kunnosta
     */
    public String getMaaritelma() {
        return this.maaritelma;
    }

    
    @Override
    public String toString() {
        return this.maaritelma;
    }
    
    
    /**
     * @param args Ei käytössä
     */
    public static void main(String[] args) {
    // TODO Auto-generated method stub
    
    }

}
