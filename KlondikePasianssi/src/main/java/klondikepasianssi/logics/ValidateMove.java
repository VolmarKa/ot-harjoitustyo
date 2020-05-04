package klondikepasianssi.logics;

import klondikepasianssi.gui.Card;

public class ValidateMove {

    public ValidateMove() {

    }

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

    public boolean ranksAreDescending(Card source, Card target) {
        if (target.getSuit() == Card.Suit.NEUTRAL && source.getRank() == 13) {
            return true;
        }

        if (source.getRank() == target.getRank() - 1) {
            return true;
        }
        return false;
    }

    public boolean moveToUpperRightPileIsAllowed(Card source, Card target) {
        if (target.getSuit() == source.getSuit() && ranksAreAscending(source, target)) {
            return true;
        }
        return false;
    }

    public boolean ranksAreAscending(Card source, Card target) {
        if (source.getRank() == target.getRank() + 1) {
            return true;
        }
        return false;
    }

}
