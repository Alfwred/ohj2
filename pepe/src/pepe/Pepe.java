package pepe;

import java.util.List;
import fxPepe.SailoException;

/**
 * Pepe-luokka, joka huolehtii pelikannasta. Pääosin kaikki metodit
 * ovat vain "välittäjämetodeja" pelikantaan.
 * @author anssi
 * @version 23 Feb 2020
 *
 */
public class Pepe {
    
    private Pelit pelit = new Pelit();
    private Nimikkeet nimikkeet = new Nimikkeet();
    private Alustat alustat = new Alustat();
    private Kunto kunto = new Kunto();
    
    private Peli peliViite;
    private boolean lippu;


    /**
     * Palautaa pepen pelien määrän
     * @return pelien määrä
     */
    public int getPeleja() {
        return pelit.getLkm();
    }
    
    
    /**
     * Talletetaan viite jostain pelistä, jotta voidaan viedä se eteenpäin controllereille
     * @param peli Viitatta peli
     */
    public void setPeliViite(Peli peli) {
        this.peliViite = peli;
    }
    
    /**
     * Haetaan controlleria varten talletetun pelin viite
     * @return Viite peliin
     */
    public Peli getPeliViite() {
        return this.peliViite;
    }
    
    
    /**
     * Asetetaan lipun totuusarvo. Lippua voi käyttää välittämään tietoa tehtävien onnistumisesta.
     * True => Onnistui
     * False => Epäonnistui/ei muutoksia
     * @param totuusarvo Lipun totuusarvo
     */
    public void setLippu(boolean totuusarvo) {
        this.lippu = totuusarvo;
    }
    
    
    /**
     * Haetaan lipun totuusarvo. Lippua voi käyttää välittämään tietoa tehtävien onnistumisesta.
     * @return Lipun totuusarvo: true => Onnistui / false => Epäonnistui/ei muutoksia
     */
    public boolean getLippu() {
        return this.lippu;
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
     * Poistaa tämän pelin (ja nimikkeen, jos mikään muu peli
     * ei viittaa samaan nimikkeeseen)
     * @param peli Poistettava peli
     * @example
     * TODO paskat testit
     * <pre name="test">
     * #THROWS Exception
     *   alustaKerho();
     *   kerho.annaHarrastukset(aku1).size() === 2;
     *   kerho.poistaHarrastus(pitsi11);
     *   kerho.annaHarrastukset(aku1).size() === 1;
     */ 
    public void poista(Peli peli) {
        int lkm = annaSamatNimikkeet(peli);
        if (lkm <= 1) nimikkeet.poista(peli.getNimike());
        pelit.poista(peli);
    }
    
    /**
     * Poistaa alustan
     * @param alusta Poistettava alusta
     */
    public void poista(Alusta alusta) {
        alustat.poista(alusta);
    }
    
    
    /**
     * Etsii kuinka monessa pelissä on sama nimike
     * @param peli Peli, jonka nimikettä tarkastellaan
     * @return Nimikkeiden lukumäärä
     */
    public int annaSamatNimikkeet(Peli peli) {
        int lkm = 0;
        for (Peli loydetty : pelit)
            if (loydetty.getNimike() == peli.getNimike()) lkm++;;
            System.out.println("PEPE: Samoja nimikkeitä jäljellä: " + (lkm - 1));
        return lkm;
    }
   

    /**
     * Listään uusi nimike Pepeen
     * @param nimike lisättävä nimike
     * @throws SailoException jos lisäystä ei voida tehdä
     */
    public void lisaa(Nimike nimike) throws SailoException {
        nimikkeet.lisaa(nimike);
    }
    
    
    /**
     * Listään uusi alusta Pepeen
     * @param alusta Lisättävä alusta 
     */
    public void lisaa(Alusta alusta) {
        alustat.lisaa(alusta);
    }
        
    
    /**
     * Palauttaa pelin parametrina annetun tunnisteen perusteella
     * @param tunniste Pelin tunniste
     * @return Peli
     */
    public Peli annaPeli(int tunniste) {
        return pelit.annaPeli(tunniste);
    }
    
    
    /**
     * @return Lista peleistä
     */
    public List<Peli> annaPelit() {
        return pelit.annaPelit();
    }
    
    
    /**
     * @return Lista alustoista
     */
    public List<Alusta> annaAlustat() {
        return alustat.annaAlustat();
    }
    
    /**
     * @return Lista kuntoluokituksista
     */
    public List<Kuntoluokitus> annaKuntoluokitukset() {
        return kunto.annaKuntoluokitukset();
    }
    
    
    /**
     * Hakee luokitusta vastaavan kuvaavan Kunto-olion
     * @param arvo Peliin sidottu kunnon arvo
     * @return kuntoa vastaava Kunto-olio
     */
    public Kuntoluokitus haeLuokitus(int arvo) {
        return kunto.haeLuokitus(arvo);
    }
    
    
    /**
     * Paluttaa pelille kuuluvan nimikkeen
     * @param merkkijono Peli (tunniste), jonka nimikettä haetaan
     * @return Pelin Nimike-olio
     * @throws IndexOutOfBoundsException jos i väärin
     */
    public Nimike annaNimike(String merkkijono) throws IndexOutOfBoundsException {
        return nimikkeet.annaNimike(merkkijono);
    }
    
    
    /**
     * Paluttaa pelille kuuluvan nimikkeen
     * @param peli Peli, jonka nimikettä haetaan
     * @return Pelin nimike
     * @throws IndexOutOfBoundsException jos i väärin
     */
    public Nimike annaNimike(Peli peli) throws IndexOutOfBoundsException {
        return nimikkeet.annaNimike(peli.getNimike());
    }

    
    /**
     * Haetaan Pelin alusta
     * @param peli Peli, jonka alustaa haetaan
     * @return Pelin alusta
     */
    public Alusta annaAlusta(Peli peli) {
        return alustat.annaAlusta(peli.getAlusta());
    }
    

    /**
     * Lukee pepen tiedot tiedostosta
     * @throws SailoException jos lukeminen epäonnistuu
     */
    public void lueTiedostosta() throws SailoException {
        nimikkeet = new Nimikkeet();
        alustat = new Alustat();
        pelit = new Pelit();
        nimikkeet.lueTiedostosta();
        alustat.lueTiedostosta();
        pelit.lueTiedostosta();
    }

    
    /**
     * Tallettaa pepen tiedot tiedostoon
     * @throws SailoException jos tallettamisessa ongelmia
     */
    public void tallenna() throws SailoException {
        pelit.tallenna();
        nimikkeet.tallenna();
        alustat.tallenna();
    }
    
    
    /**
     * Luo String[] pelistä StringGridia varten
     * @param peli Peli
     * @return String[] pelin tiedoista
     */
    public String[] haeKentat(Peli peli) {
        
        // Käsittely, jos alustaa ei löydy
        String alusta = "-";
        if (annaAlusta(peli) != null) alusta = annaAlusta(peli).getLyhenne();
        
        String[] t = {
                peli.getTunniste() + "",
                annaNimike(peli).getNimi(),
                alusta,
                peli.getJulkaisuvuosi() + "",
                peli.getHankintavuosi() + "",
                kunto.haeLuokitus(peli.getLevy()).toString(),
                kunto.haeLuokitus(peli.getOhje()).toString(),
                kunto.haeLuokitus(peli.getKotelo()).toString(),
                peli.getLisatiedot()};
        return t;
    }
    
    
    /**
     * Luo String[] alustasta StringGridia varten
     * @param alusta Alusta
     * @return String[] alustan tiedoista
     */
    public String[] haeKentat(Alusta alusta) {
        String[] t = {
                alusta.getTunniste() + "",
                alusta.getLyhenne(),
                alusta.getNimi()};
        return t;
    }
    
    
    /**
     * Asettaa parametrina tuodun kentän arvon
     * @param peli Peli, minkä tietoja muutetaan
     * @param muutos Muutoksen tyyppi case-rakenteessa
     * @param merkkijono Muutos merkkijonona
     * @return OK tai virheilmoitus
     */
    public String asetaMuutokset(Peli peli, String muutos, String merkkijono) {
        switch (muutos) {
        case "nimike":
            // Luodaan uusi, jos ei löydy aikaisempaa nimikettä
            if (this.annaNimike(merkkijono) == null) {
                Nimike uusi = new Nimike(merkkijono);
                uusi.rekisteroi();
                try {
                    this.lisaa(uusi);
                } catch (SailoException e) {
                    e.printStackTrace();
                }
                
                // Oikeellisuustarkistus
                if (!uusi.tarkista(merkkijono)) {
                    nimikkeet.poista(uusi.getTunniste());
                    break;
                }
                
                // Lisätään nimike
                peli.setNimike(uusi.getTunniste());
                
                // Poistetaan aikaisempi nimike, jos se ei ole muiden pelien käytössä
                int lkm = annaSamatNimikkeet(peli);
                if (lkm <= 1) nimikkeet.poista(peli.getNimike());
                
                return "UUSI NIMIKE OK";
            }
            peli.setNimike(this.annaNimike(merkkijono).getTunniste());
            return "NIMIKE OK";
            
        case "alusta":
            peli.setAlusta(Integer.parseInt(merkkijono));
            return "ALUSTA OK";
            
        case "julkaisuvuosi":
            peli.setJulkaisuvuosi(Integer.parseInt(merkkijono));
            return "JULKAISUVUOSI OK";
            
        case "hankintavuosi":
            peli.setHankintavuosi(Integer.parseInt(merkkijono));
            return "HANKINTAVUOSI OK";
            
        case "kunto":
            peli.setKunto(merkkijono);
            return "KUNTO OK";
            
        case "lisatiedot":
            peli.setLisatiedot(merkkijono);
            return "LISATIEDOT OK";
            
        default:
            break;
        }
        return "VIRHE"; 
    }
    
    
    /**
     * Asettaa parametrina tuodun kentän arvon
     * @param alusta Alusta, minkä tietoja muutetaan
     * @param muutos Muutoksen tyyppi case-rakenteessa
     * @param merkkijono Muutos merkkijonona
     * @return OK tai virheilmoitus
     */
    public String asetaMuutokset(Alusta alusta, String muutos, String merkkijono) {
        switch (muutos) {
        
        case "lyhenne":
            if (!alusta.tarkista(merkkijono)) break;
            alusta.setLyhenne(merkkijono);
            return "LYHENNE OK";
            
        case "nimi":
            if (!alusta.tarkista(merkkijono)) break;
            alusta.setNimi(merkkijono);
            return "NIMI OK";
            
        default:
            break;
        }
        return "VIRHE"; 
    }
    
    
    /**
     * Testiohjelma pepesta
     * @param args ei käytössä
     */
    public static void main(String args[]) {
        
        Pepe pepe = new Pepe();
        
        try {
            pepe.lueTiedostosta();
            for (int i = 0; i  < pepe.getPeleja(); i++) {
                Peli loytynyt = pepe.annaPeli(i);
                loytynyt.tulosta(System.out);
            }
        } catch (SailoException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        
        /**
        try {
            
            // Alustojen testiluonti
            Alusta a1 = new Alusta();
            Alusta a2 = new Alusta();
            a1.rekisteroi();
            a2.rekisteroi();
            a1.taytaTestiAlustaTiedoilla();
            a2.taytaTestiAlustaTiedoilla();
            
            pepe.lisaa(a1);
            pepe.lisaa(a2);
            
            // Nimikkeiden testiluonti
            Nimike n1 = new Nimike();
            Nimike n2 = new Nimike();
            n1.rekisteroi();
            n2.rekisteroi();
            n1.taytaTestiNimikeTiedoilla();
            n2.taytaTestiNimikeTiedoilla();
            
            pepe.lisaa(n1);
            pepe.lisaa(n2);
            
            
            // Pelien luonti, nimikkeiden ja alustojen sitominen
            Peli peli1 = new Peli(0);
            peli1.rekisteroi();
            peli1.taytaTestiPeliTiedoilla(n1.getTunniste(), a1.getTunniste());
            pepe.lisaa(peli1);
            
            Peli peli2 = new Peli(1);
            peli2.rekisteroi();
            peli2.taytaTestiPeliTiedoilla(n2.getTunniste(), a2.getTunniste());
            pepe.lisaa(peli2);


            System.out.println("============= Pepen testi =================");
            
            // pepe.annaPeli(0).tulosta(System.out);
            // pepe.annaNimike(pepe.annaPeli(0)).tulosta(System.out);
            // pepe.annaAlusta(pepe.annaPeli(0)).tulosta(System.out);
            
            for (int i = 0; i  < pepe.getPeleja(); i++) {
                Peli loytynyt = pepe.annaPeli(i);
                loytynyt.tulosta(System.out);
                
                pepe.annaNimike(loytynyt).tulosta(System.out);
                pepe.annaAlusta(loytynyt).tulosta(System.out);
            }
            
        } catch (SailoException ex) {
            System.out.println(ex.getMessage());
        }
        */
    }

}