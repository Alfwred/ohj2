package fxPepe;

/**
 * Pepe-luokka, joka huolehtii pelikannasta. Pääosin kaikki metodit
 * ovat vain "välittäjämetodeja" pelikantaan.
 * @author anssi
 * @version 23 Feb 2020
 *
 */
public class Pepe {
    private final Pelit pelit = new Pelit();


    /**
     * Palautaa pepen pelien määrän
     * @return pelien määrä
     */
    public int getPeleja() {
        return pelit.getLkm();
    }


    /**
     * Poistaa pelikannasta ja peliTiedoista ne joilla on nro. Kesken.
     * @param nro viitenumero, jonka mukaan poistetaan
     * @return montako pelia poistettiin
     */
    public int poista(@SuppressWarnings("unused") int nro) {
        return 0;
    }


    /**
     * Lisää pepeen uuden jäsenen
     * @param peli lisättävä peli
     * @throws SailoException jos lisäystä ei voida tehdä
     * @example
     * <pre name="test">
     * #THROWS SailoException
     * Pepe pepe = new Pepe();
     * Jasen cnc = new Jasen(), d2 = new Jasen();
     * cnc.rekisteroi(); d2.rekisteroi();
     * pepe.getJasenia() === 0;
     * pepe.lisaa(cnc); pepe.getJasenia() === 1;
     * pepe.lisaa(d2); pepe.getJasenia() === 2;
     * pepe.lisaa(cnc); pepe.getJasenia() === 3;
     * pepe.getJasenia() === 3;
     * pepe.annaJasen(0) === cnc;
     * pepe.annaJasen(1) === d2;
     * pepe.annaJasen(2) === cnc;
     * pepe.annaJasen(3) === cnc; #THROWS IndexOutOfBoundsException 
     * pepe.lisaa(cnc); pepe.getJasenia() === 4;
     * pepe.lisaa(cnc); pepe.getJasenia() === 5;
     * pepe.lisaa(cnc);            #THROWS SailoException
     * </pre>
     */
    public void lisaa(Peli peli) throws SailoException {
        pelit.lisaa(peli);
    }


    /**
     * Palauttaa i:n pelin
     * @param i monesko peli palautetaan
     * @return viite i:teen peliin
     * @throws IndexOutOfBoundsException jos i väärin
     */
    public Peli annaPeli(int i) throws IndexOutOfBoundsException {
        return pelit.anna(i);
    }


    /**
     * Lukee pepen tiedot tiedostosta
     * @param nimi jota käyteään lukemisessa
     * @throws SailoException jos lukeminen epäonnistuu
     */
    public void lueTiedostosta(String nimi) throws SailoException {
        pelit.lueTiedostosta(nimi);
    }


    /**
     * Tallettaa pepen tiedot tiedostoon
     * @throws SailoException jos tallettamisessa ongelmia
     */
    public void talleta() throws SailoException {
        pelit.talleta();
        // TODO: yritä tallettaa toinen vaikka toinen epäonnistuisi
    }


    /**
     * Testiohjelma pepesta
     * @param args ei käytössä
     */
    public static void main(String args[]) {
        Pepe pepe = new Pepe();

        try {
            // pepe.lueTiedostosta("kelmit");

            Peli cnc = new Peli(), d2 = new Peli();
            cnc.lisaa();
            cnc.taytaPeliTiedoilla();
            d2.lisaa();
            d2.taytaPeliTiedoilla();

            pepe.lisaa(cnc);
            pepe.lisaa(d2);

            System.out.println("============= Pepen testi =================");

            for (int i = 0; i < pepe.getPeleja(); i++) {
                Peli peli = pepe.annaPeli(i);
                System.out.println("Jäsen paikassa: " + i);
                peli.tulosta(System.out);
            }

        } catch (SailoException ex) {
            System.out.println(ex.getMessage());
        }
    }

}