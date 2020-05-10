package klondikepasianssi.logics;

import klondikepasianssi.gui.Card;
import klondikepasianssi.gui.CardImage;
import klondikepasianssi.gui.UpperRightPileView;

/**
 * Luokka vastaa oikean yläkulman pinojen toiminnallisuuksista.
 */
public class UpperRightPileManager {

    private final UpperRightPileView[] piles = new UpperRightPileView[4];
    private final CardImage cardImage = new CardImage();

    public UpperRightPileManager() {
        init();
    }

    private void init() {
        for (int i = 0; i <= 3; i++) {
            this.piles[i] = new UpperRightPileView();
        }
        Card diamonds = new Card(Card.Suit.DIAMONDS, "b", 3);
        Card hearts = new Card(Card.Suit.HEARTS, "b", 4);
        Card spades = new Card(Card.Suit.SPADES, "b", 2);
        Card clubs = new Card(Card.Suit.CLUBS, "b", 1);
        diamonds.setRank(0);
        hearts.setRank(0);
        spades.setRank(0);
        clubs.setRank(0);
        this.piles[0].getChildren().add(clubs);
        this.piles[1].getChildren().add(spades);
        this.piles[2].getChildren().add(diamonds);
        this.piles[3].getChildren().add(hearts);
        this.piles[0].getPile().push(clubs);
        this.piles[1].getPile().push(spades);
        this.piles[2].getPile().push(diamonds);
        this.piles[3].getPile().push(hearts);

    }

    /**
     * Metodi tarkistaa onko peli loppunut.
     * @return Palauttaa totuusarvon pelin loppumisesta.
     */
    public boolean gameEnded() {
        int stacksCompleted = 0;
        for (int i = 0; i <= 3; i++) {
            if (this.piles[i].getPile().size() == 14) {
                stacksCompleted++;
            }
        }
        return stacksCompleted == 4;
    }

    public UpperRightPileView[] getPiles() {
        return this.piles;
    }

}
