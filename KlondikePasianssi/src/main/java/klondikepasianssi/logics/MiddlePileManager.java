package klondikepasianssi.logics;

import klondikepasianssi.gui.Card;
import klondikepasianssi.gui.MiddlePileView;
import klondikepasianssi.gui.Card.Suit;

/**
 * Luokka vastaa keskimmäisten pinojen toiminnallisuuksista.
 */
public class MiddlePileManager {

    private double y;
    private int index;
    private final MiddlePileView[] piles = new MiddlePileView[7];

    public MiddlePileManager(Deck deck) {
        init();
        dealCards(deck);
    }

    private void init() {
        for (int i = 0; i <= 6; i++) {
            this.piles[i] = new MiddlePileView();
        }
    }

    private void dealCards(Deck deck) {

        deck.setEveryFaceDown();
        this.index = 1;
        for (int a = 0; a <= 6; a++) {
            Card bottom = new Card(Suit.NEUTRAL, "bottom", 19);
            bottom.setRank(a + 19);
            this.piles[a].getChildren().add(bottom);
            this.piles[a].getPile().push(bottom);
            bottom.setTranslateY(y + 10);
            for (int i = 1; i <= this.index; i++) {

                Card card = deck.getDeck().pop();
                this.y = card.getTranslateY();
                this.piles[a].getPile().push(card);
                this.piles[a].getChildren().add(card);
                card.setTranslateY(y + i * 10);

            }
            this.index++;
        }

        setFirstCardFaceUp();

    }

    private void setFirstCardFaceUp() {
        for (int i = 0; i <= 6; i++) {
            this.piles[i].getPile().peek().getCardProperties().setFaceUp();
            this.piles[i].getPile().peek().setHasBeenClicked();

        }
    }

    /**
     * Metodi mahdollistaa jokaisessa pinossa olevan päällimäisen kortin puolen
     * vaihtamisen.
     *
     */
    public void changeSideUpdate() {

        for (int i = 0; i <= 6; i++) {
            if (!this.piles[i].getPile().isEmpty()) {
                this.piles[i].getPile().peek().getCardProperties().changeSide();
            }
        }
    }

    public MiddlePileView[] getPiles() {
        return this.piles;
    }

}
