package klondikepasianssi.logics;

import java.util.Collections;
import java.util.Stack;
import klondikepasianssi.gui.Card;

/**
 * Luokka vastaa pakkaan liittyvistä toiminnallisuuksista.
 */
public class Deck {

    private final Stack<Card> deck;

    public Deck() {
        this.deck = new Stack<>();
        createWholeSuit("c", Card.Suit.CLUBS);
        createWholeSuit("s", Card.Suit.SPADES);
        createWholeSuit("d", Card.Suit.DIAMONDS);
        createWholeSuit("h", Card.Suit.HEARTS);
        shuffle();
    }

    private void createWholeSuit(String suitLetter, Card.Suit suit) {
        for (int a = 1; a <= 13; a++) {
            this.deck.push(new Card(suit, suitLetter, a));
        }

    }

    /**
     * Vaihtaa jokaisen kortin kuvapuolen alas pakassa.
     *
     */
    public void setEveryFaceDown() {
        for (Card card : this.deck) {
            card.getCardProperties().setFaceDown();
        }
    }

    /**
     * Jakaa vasemmassa yläkulmassa olevaan pinoon pakasta kortteja.
     *
     * @return 24 korttia.
     */
    public Stack<Card> dealUpperPile() {

        Stack<Card> upperPile = new Stack<>();
        for (int i = 1; i <= 24; i++) {
            upperPile.push(this.deck.pop());
        }
        return upperPile;
    }

    /**
     * Sekottaa pakan.
     *
     */
    public final void shuffle() {
        Collections.shuffle(this.deck);
    }

    public Stack<Card> getDeck() {
        return this.deck;
    }
}
