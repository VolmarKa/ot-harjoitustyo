package klondikePasianssi.logics;

import java.util.Collections;
import java.util.Stack;

public class Deck {

    private int b;
    private int i;
    private Stack<Card> deck;

    public Deck() {
        this.deck = new Stack<>();
        this.i = 0;
        this.b = 1;

        createWholeSuit("c", Card.Suit.CLUBS);
        createWholeSuit("s", Card.Suit.SPADES);
        createWholeSuit("d", Card.Suit.DIAMONDS);
        createWholeSuit("h", Card.Suit.HEARTS);

    }

    private void createWholeSuit(String suitLetter, Card.Suit suit) {
        for (int a = 1; a <= 13; a++) {
            this.deck.push(new Card(suit, suitLetter, a));
        }

    }

    public void shuffle() {
        Collections.shuffle(this.deck);
    }

    public Stack<Card> getDeck() {
        return this.deck;
    }
}
