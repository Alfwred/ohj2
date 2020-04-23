package fxPepe;

/**
 * Poikkeusluokka tietorakenteesta aiheutuville poikkeuksille
 * @author Anssi Lepikko
 * @version 23 Feb 2020
 *
 */
public class SailoException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * Poikkeuksen muodostaja jolle tuodaan poikkeuksessa
     * käytettävä viesti
     * @param viesti Poikkeuksen viesti
     */
    public SailoException(String viesti) {
        super(viesti);
    }
}