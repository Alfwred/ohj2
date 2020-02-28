package fxPepe;

import java.util.List;

/**
 * Pepe-luokka, joka huolehtii pelikannasta. Pääosin kaikki metodit
 * ovat vain "välittäjämetodeja" pelikantaan.
 * @author anssi
 * @version 23 Feb 2020
 *
 */
public class Pepe {
    private final Pelit pelit = new Pelit();
    private final PeliTiedot peliTiedot = new PeliTiedot(); 


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
     * Lisää pepeen uuden pelin
     * @param peli lisättävä peli
     * @throws SailoException jos lisäystä ei voida tehdä
     * @example
     * <pre name="test">
     * #THROWS SailoException
     * Pepe pepe = new Pepe();
     * Peli cnc = new Peli(), d2 = new Peli();
     * cnc.rekisteroi(); d2.rekisteroi();
     * pepe.getPeleja() === 0;
     * pepe.lisaa(cnc); pepe.getPeleja() === 1;
     * pepe.lisaa(d2); pepe.getPeleja() === 2;
     * pepe.lisaa(cnc); pepe.getPeleja() === 3;
     * pepe.getPeleja() === 3;
     * pepe.annaPeli(0) === cnc;
     * pepe.annaPeli(1) === d2;
     * pepe.annaPeli(2) === cnc;
     * pepe.annaPeli(3) === cnc; #THROWS IndexOutOfBoundsException 
     * pepe.lisaa(cnc); pepe.getPeleja() === 4;
     * pepe.lisaa(cnc); pepe.getPeleja() === 5;
     * pepe.lisaa(cnc);            #THROWS SailoException
     * </pre>
     */
    public void lisaa(Peli peli) throws SailoException {
        pelit.lisaa(peli);
    }
   

    /**
     * Listään uusi peliTieto Pepeen
     * @param tieto lisättävä peliTieto 
     */
    public void lisaa(PeliTieto tieto) {
        peliTiedot.lisaa(tieto);
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
     * Haetaan kaikki jäsen harrastukset
     * @param peli jäsen jolle harrastuksia haetaan
     * @return tietorakenne jossa viiteet löydetteyihin harrastuksiin
     * @example
     * <pre name="test">
     * #import java.util.*;
     * 
     *  Kerho kerho = new Kerho();
     *  Jasen aku1 = new Jasen(), aku2 = new Jasen(), aku3 = new Jasen();
     *  aku1.rekisteroi(); aku2.rekisteroi(); aku3.rekisteroi();
     *  int id1 = aku1.getTunnusNro();
     *  int id2 = aku2.getTunnusNro();
     *  Harrastus pitsi11 = new Harrastus(id1); kerho.lisaa(pitsi11);
     *  Harrastus pitsi12 = new Harrastus(id1); kerho.lisaa(pitsi12);
     *  Harrastus pitsi21 = new Harrastus(id2); kerho.lisaa(pitsi21);
     *  Harrastus pitsi22 = new Harrastus(id2); kerho.lisaa(pitsi22);
     *  Harrastus pitsi23 = new Harrastus(id2); kerho.lisaa(pitsi23);
     *  
     *  List<Harrastus> loytyneet;
     *  loytyneet = kerho.annaHarrastukset(aku3);
     *  loytyneet.size() === 0; 
     *  loytyneet = kerho.annaHarrastukset(aku1);
     *  loytyneet.size() === 2; 
     *  loytyneet.get(0) == pitsi11 === true;
     *  loytyneet.get(1) == pitsi12 === true;
     *  loytyneet = kerho.annaHarrastukset(aku2);
     *  loytyneet.size() === 3; 
     *  loytyneet.get(0) == pitsi21 === true;
     * </pre> 
     */
    public List<PeliTieto> annaPeliTiedot(Peli peli) {
        return peliTiedot.annaPeliTiedot(peli.getTunniste());
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
            // Pelien testiluonti
            // pepe.lueTiedostosta("kelmit")
            Peli cnc = new Peli(), d2 = new Peli();
            cnc.rekisteroi();
            cnc.taytaPeliTiedoilla();
            d2.rekisteroi();
            d2.taytaPeliTiedoilla();

            pepe.lisaa(cnc);
            pepe.lisaa(d2);
            
            // PeliTietojen testiluonti ja sitominen peleihin
            int id1 = cnc.getTunniste();
            int id2 = d2.getTunniste();
            PeliTieto tieto1 = new PeliTieto(id1);
            tieto1.taytaTestiPeliTietoTiedoilla(id1);
            pepe.lisaa(tieto1);
            PeliTieto tieto2 = new PeliTieto(id2);
            tieto2.taytaTestiPeliTietoTiedoilla(id2);
            pepe.lisaa(tieto2);


            System.out.println("============= Pepen testi =================");

            for (int i = 0; i < pepe.getPeleja(); i++) {
                Peli peli = pepe.annaPeli(i);
                System.out.println("Jäsen paikassa: " + i);
                peli.tulosta(System.out);
                
                List<PeliTieto> peliTieto = pepe.annaPeliTiedot(peli); 
                for (PeliTieto tieto:peliTieto) 
                    tieto.tulosta(System.out); 
            }

        } catch (SailoException ex) {
            System.out.println(ex.getMessage());
        }
    }

}