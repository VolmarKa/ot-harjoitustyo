package klondikePasianssi.logics;

import java.util.Collections;
import java.util.Stack;

public class Deck {

    private final Stack<Card> deck;

    public Deck() {
        this.deck = new Stack<>();

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

    //24 korttipinoon ja yht 28 pinoihin pöydälle
    public Stack<Card> dealUpperPile() {
        Stack<Card> upperPile = new Stack<>();
        for (int i = 1; i <= 24; i++) {
            upperPile.push(this.deck.pop());
        }
        return upperPile;
    }


    /*
    public Card pop(){
        if(!this.deck.isEmpty()){
            return this.deck.pop();
        }
        return
    } */
    public void shuffle() {
        Collections.shuffle(this.deck);
    }

    public Stack<Card> getDeck() {
        return this.deck;
    }
}
