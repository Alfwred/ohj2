/**
 * 
 */
package fxPepe;

/**
 * Pepen pelistö joka osaa mm. lisätä uuden pelin
 * @author Anssi Lepikko
 * @version 18 Feb 2020
 *
 */
public class Nimikkeet {
    
    private static final int MAX_NIMIKKEITA     = 5;
    private int              lkm                = 0;
    private String           tiedostonNimi      = "";
    private Nimike           alkiot[]           = new Nimike[MAX_NIMIKKEITA];

    /**
     * Oletusmuodostaja
     */
    public Nimikkeet() {
        // Attribuuttien oma alustus riittää
    }
    
    
    /**
     * Lisää uuden nimikkeen tietorakenteeseen. Ottaa nimikkeen omistukseensa.
     * @param nimike Lisättävän nimikkeen viite. Huom tietorakenne muuttuu omistajaksi.
     * @throws SailoException jos tietorakenne on jo täynnä
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * Nimikkeet nimikkeet = new Nimikkeet();
     * Nimike cnc = new Nimike(), ra = new Nimike();
     * nimikkeet.getLkm() === 0;
     * nimikkeet.lisaa(cnc); nimikkeet.getLkm() === 1;
     * nimikkeet.lisaa(ra); nimikkeet.getLkm() === 2;
     * nimikkeet.lisaa(cnc); nimikkeet.getLkm() === 3;
     * nimikkeet.anna(0) === cnc;
     * nimikkeet.anna(1) === ra;
     * nimikkeet.anna(2) === cnc;
     * nimikkeet.anna(1) == cnc === false;
     * nimikkeet.anna(1) == ra === true;
     * nimikkeet.anna(3) === cnc; #THROWS IndexOutOfBoundsException 
     * nimikkeet.lisaa(cnc); nimikkeet.getLkm() === 4;
     * nimikkeet.lisaa(cnc); nimikkeet.getLkm() === 5;
     * nimikkeet.lisaa(cnc);  #THROWS SailoException
     * </pre>
     */
    public void lisaa(Nimike nimike) throws SailoException {
        if (lkm >= alkiot.length) throw new SailoException("Liikaa alkioita");
        alkiot[lkm] = nimike;
        lkm++;
    }
    
    
    /**
     * Palauttaa viitteen i:teen nimikkeeseen
     * @param i monennenko nimikkeen viite halutaan
     * @return viite nimikkeeseen, jonka indeksi on i
     * @throws IndexOutOfBoundsException jos i ei ole sallitulla alueella  
     */
    public Nimike anna(int i) throws IndexOutOfBoundsException {
        if (i < 0 || lkm <= i)
            throw new IndexOutOfBoundsException("Laiton indeksi: " + i);
        return alkiot[i];
    }
    
    
    /**
     * Lukee nimikkeet tiedostosta. Kesken.
     * @param hakemisto tiedoston hakemisto
     * @throws SailoException jos lukeminen epäonnistuu
     */
    public void lueTiedostosta(String hakemisto) throws SailoException {
        tiedostonNimi = hakemisto + "/nimikkeet.dat";
        throw new SailoException("Ei osata vielä lukea tiedostoa " + tiedostonNimi);
    }


    /**
     * Tallentaa nimikkeet tiedostoon. Kesken.
     * @throws SailoException jos talletus epäonnistuu
     */
    public void talleta() throws SailoException {
        throw new SailoException("Ei osata vielä tallettaa tiedostoa " + tiedostonNimi);
    }
    

    /**
     * Palauttaa nimikkeiden lukumäärän
     * @return jäsenten lukumäärä
     */
    public int getLkm() {
        return lkm;
    }

    
    /**
     * Testiohjelma nimikkeille
     * @param args ei käytössä
     */
    public static void main(String args[]) {
        Nimikkeet nimikkeet = new Nimikkeet();

        Nimike d1 = new Nimike();
        Nimike d2 = new Nimike();
        d1.rekisteroi();
        d1.taytaNimikeTiedoilla();
        d2.rekisteroi();
        d2.taytaNimikeTiedoilla();

        try {
            nimikkeet.lisaa(d1);
            nimikkeet.lisaa(d2);

            for (int i = 0; i < nimikkeet.getLkm(); i++) {
                Nimike nimike = nimikkeet.anna(i);
                System.out.println("Peli nro: " + i);
                nimike.tulosta(System.out);
            }

        } catch (SailoException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
