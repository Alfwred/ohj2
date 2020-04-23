/**
 * 
 */
package pepe;

/**
 * Kuntoluokitus-luokka, joka auttaa kuvailemaan pelin kuntoa.
 * @author Anssi Lepikko
 * @version 23.4.2020
 *
 */
public class Kuntoluokitus {
    
    private int luokitus;           // Luokituksen tunniste
    private String maaritelma;      // Luokituksen määritelmä

    
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
        Kuntoluokitus kunto1 = new Kuntoluokitus(1, "MINT");
        Kuntoluokitus kunto2 = new Kuntoluokitus(2, "OK");
        Kuntoluokitus kunto3 = new Kuntoluokitus(3, "HUONO");
        
        System.out.println(kunto1.toString());
        System.out.println(kunto2.toString());
        System.out.println(kunto3.toString());
    }

}
