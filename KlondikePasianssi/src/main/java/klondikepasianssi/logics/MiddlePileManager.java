package klondikepasianssi.logics;

import javafx.scene.control.Button;
import klondikepasianssi.gui.Card;
import klondikepasianssi.gui.CardImage;
import klondikepasianssi.gui.MiddlePileView;

/**
 * Luokka vastaa keskimmäisten pinojen toiminnallisuuksista.
 */
public class MiddlePileManager {

    private double y;
    private int b;
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
        CardImage c = new CardImage();
        deck.setEveryFaceDown();
        this.b = 1;
        for (int a = 0; a <= 6; a++) {
            Button button = c.createButton("bottom", 1);
            this.piles[a].getChildren().add(button);
            button.setTranslateY(y + 10);
            for (int i = 1; i <= b; i++) {

                Card card = deck.getDeck().pop();
                this.y = card.getTranslateY();
                this.piles[a].getPile().push(card);
                //System.out.println(this.piles[a].getPile().toString());
                this.piles[a].getChildren().add(card);
                card.setTranslateY(y + i * 10);

            }
            this.b++;
        }

        setFirstCardFaceUp();

    }

    private void setFirstCardFaceUp() {
        for (int i = 0; i <= 6; i++) {
            this.piles[i].getPile().peek().getCardProperties().setFaceUp();
        }
    }

    /**
     * Metodi mahdollistaa jokaisessa pinossa olevan päällimäisen kortin 
     * puolen vaihtamisen.
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
