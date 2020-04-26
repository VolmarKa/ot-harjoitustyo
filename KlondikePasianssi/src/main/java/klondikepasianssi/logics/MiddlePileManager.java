package klondikepasianssi.logics;

import klondikepasianssi.gui.Card;
import klondikepasianssi.gui.CardImage;
import klondikepasianssi.gui.MiddlePileView;

public class MiddlePileManager {

    private double y;
    private int b;
    private final MiddlePileView[] piles = new MiddlePileView[7];

    public MiddlePileManager(Deck deck) {
        for (Card k : deck.getDeck()) {
            k.getCardProperties().makeMovable(this);
        }
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
            this.piles[a].getChildren().add(c.createButton("bottom", 1));
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
    //ei toimi jos pakka on "tyhjä"
    public void changeSideUpdate() {

        for (int i = 0; i <= 6; i++) {
            if (!this.piles[i].getChildren().isEmpty()) {
                this.piles[i].getPile().peek().getCardProperties().changeSide();
            }
        }
    }

    public MiddlePileView[] getPiles() {
        return this.piles;
    }

}
