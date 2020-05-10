package klondikepasianssi.logics;

import klondikepasianssi.gui.Card;

/**
 * Luokka vastaa korttien liikuttelun sääntöjen mukaisuudesta.
 */
public class ValidateMove {

    public ValidateMove() {

    }

    /**
     * Metodi testaa ovatko korttien maat erit.
     *
     * @param source Liikutettava kortti.
     * @param target Kohdekortti.
     * @return Palauttaa totuusarvon.
     */
    public boolean SuitsAreDifferent(Card source, Card target) {
        if (target.getSuit() == Card.Suit.NEUTRAL) {
            return true;
        }
        if ((source.getSuit() == Card.Suit.CLUBS || source.getSuit() == Card.Suit.SPADES)
                && (target.getSuit() == Card.Suit.DIAMONDS || target.getSuit() == Card.Suit.HEARTS)) {
            return true;
        }
        if ((target.getSuit() == Card.Suit.CLUBS || target.getSuit() == Card.Suit.SPADES)
                && (source.getSuit() == Card.Suit.DIAMONDS || source.getSuit() == Card.Suit.HEARTS)) {
            return true;
        }

        return false;

    }

    /**
     * Metodi testaa onko liikuteltavan kortin numero yhtä pienempi kuin
     * kohdekortin numero.
     *
     * @param source Liikutettava kortti.
     * @param target Kohdekortti.
     * @return Palauttaa totuusarvon.
     */
    public boolean ranksAreDescending(Card source, Card target) {
        if (target.getSuit() == Card.Suit.NEUTRAL && source.getRank() == 13) {
            return true;
        }

        if (source.getRank() == target.getRank() - 1) {
            return true;
        }
        return false;
    }

    /**
     * Metodi testaa onko liike loppupinoon sallittu,
     *
     * @param source Liikutettava kortti.
     * @param target Kohdekortti.
     * @return Palauttaa totuusarvon.
     */
    public boolean moveToUpperRightPileIsAllowed(Card source, Card target) {
        if (target.getSuit() == source.getSuit() && ranksAreAscending(source, target)) {
            return true;
        }
        return false;
    }

    /**
     * Metodi testaa onko liikuteltavan kortin numero yhtä suurempi kuin
     * kohdekortin numero.
     *
     * @param source Liikutettava kortti.
     * @param target Kohdekortti.
     * @return Palauttaa totuusarvon.
     */
    public boolean ranksAreAscending(Card source, Card target) {
        if (source.getRank() == target.getRank() + 1) {
            return true;
        }
        return false;
    }

}
