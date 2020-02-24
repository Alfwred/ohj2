/**
 * 
 */
package fxPepe;

/**
 * Pepen jäsenistö joka osaa mm. lisätä uuden jäsenen
 * @author Anssi Lepikko
 * @version 18 Feb 2020
 *
 */
public class Pelit {
    
    private static final int MAX_PELEJA   = 5;
    private int              lkm           = 0;
    private String           tiedostonNimi = "";
    private Peli             alkiot[]      = new Peli[MAX_PELEJA];

    /**
     * Oletusmuodostaja
     */
    public Pelit() {
        // Attribuuttien oma alustus riittää
    }
    
    
    /**
     * Lisää uuden pelin tietorakenteeseen. Ottaa pelin omistukseensa.
     * @param peli lisätäävän pelin viite. Huom tietorakenne muuttuu omistajaksi.
     * @throws SailoException jos tietorakenne on jo täynnä
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * Pelit jasenet = new Pelit();
     * Peli cnc = new Peli(), ra = new Peli();
     * pelit.getLkm() === 0;
     * pelit.lisaa(cnc); pelit.getLkm() === 1;
     * pelit.lisaa(rar); pelit.getLkm() === 2;
     * pelit.lisaa(cnc); pelit.getLkm() === 3;
     * pelit.anna(0) === cnc;
     * pelit.anna(1) === ra;
     * pelit.anna(2) === cnc;
     * pelit.anna(1) == cnc === false;
     * pelit.anna(1) == ra === true;
     * pelit.anna(3) === cnc; #THROWS IndexOutOfBoundsException 
     * pelit.lisaa(cnc); pelit.getLkm() === 4;
     * pelit.lisaa(cnc); pelit.getLkm() === 5;
     * pelit.lisaa(cnc);  #THROWS SailoException
     * </pre>
     */
    public void lisaa(Peli peli) throws SailoException {
        if (lkm >= alkiot.length) throw new SailoException("Liikaa alkioita");
        alkiot[lkm] = peli;
        lkm++;
    }
    
    
    /**
     * Palauttaa viitteen i:teen jäseneen.
     * @param i monennenko jäsenen viite halutaan
     * @return viite jäseneen, jonka indeksi on i
     * @throws IndexOutOfBoundsException jos i ei ole sallitulla alueella  
     */
    public Peli anna(int i) throws IndexOutOfBoundsException {
        if (i < 0 || lkm <= i)
            throw new IndexOutOfBoundsException("Laiton indeksi: " + i);
        return alkiot[i];
    }
    
    
    /**
     * Lukee pelikannan tiedostosta. Kesken.
     * @param hakemisto tiedoston hakemisto
     * @throws SailoException jos lukeminen epäonnistuu
     */
    public void lueTiedostosta(String hakemisto) throws SailoException {
        tiedostonNimi = hakemisto + "/pelit.dat";
        throw new SailoException("Ei osata vielä lukea tiedostoa " + tiedostonNimi);
    }


    /**
     * Tallentaa pelikannan tiedostoon. Kesken.
     * @throws SailoException jos talletus epäonnistuu
     */
    public void talleta() throws SailoException {
        throw new SailoException("Ei osata vielä tallettaa tiedostoa " + tiedostonNimi);
    }
    

    /**
     * Palauttaa pelirekisterin pelien lukumäärän
     * @return jäsenten lukumäärä
     */
    public int getLkm() {
        return lkm;
    }

    
    /**
     * Testiohjelma pelikannalle
     * @param args ei käytössä
     */
    public static void main(String args[]) {
        Pelit pelit = new Pelit();

        Peli d1 = new Peli(), d2 = new Peli();
        d1.rekisteroi();
        d1.taytaPeliTiedoilla();
        d2.rekisteroi();
        d2.taytaPeliTiedoilla();

        try {
            pelit.lisaa(d1);
            pelit.lisaa(d2);

            System.out.println("============= Pelit testi =================");

            for (int i = 0; i < pelit.getLkm(); i++) {
                Peli peli = pelit.anna(i);
                System.out.println("Peli nro: " + i);
                peli.tulosta(System.out);
            }

        } catch (SailoException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
