package klondikepasianssi.logics;

import java.util.Stack;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import klondikepasianssi.gui.Card;
import klondikepasianssi.gui.CardImage;

/**
 * Luokka vastaa vasemmassa yläkulmassa olevan pakan ominaisuuksista.
 */
public class UpperLeftPile extends HBox {

    protected int i;
    private int deckSize;
    private final CardImage cardImage = new CardImage();
    private final ImageView ima1 = new ImageView(cardImage.backOfTheCard());
    private final ImageView ima2 = new ImageView(cardImage.bottomImage());
    private final Button card = new Button();
    private final ClickedPile clickedPile = new ClickedPile();
    private Stack<Card> upperPile;

    public UpperLeftPile(Stack<Card> upperPile) {
        this.upperPile = upperPile;
        this.deckSize = this.upperPile.size();
        i = 1;
        getChildren().add(card);
        this.getChildren().add(this.clickedPile);
        this.card.setStyle("-fx-background-color: transparent");
        card.setGraphic(new ImageView(cardImage.backOfTheCard()));

        pileClicked();
    }

    private void checkIndex() {
        if (this.i == deckSize) {
            this.card.setGraphic(ima2);
        }
        if (this.i == 1) {
            this.card.setGraphic(ima1);
        }
    }

    private void pileClicked() {
        card.setOnMouseClicked(event -> {
            checkIndex();

            if (this.i <= deckSize) {
                clickedPile.addCard(this.upperPile.peek());
                clickedPile.getChildren().add(this.upperPile.pop());
                i++;

            } else {
                this.clickedPile.updateStack();
                updateDeckSize();
                this.upperPile = this.clickedPile.returnStack();
                /*for (Card k : this.upperPile) {
                    System.out.println(k.toString());
                }*/
                this.i = 1;
                checkIndex();
            }
        });
    }

    /**
     * Metodi testaa onko tietty kortti pinossa.
     *
     * @param card Testattava kortti.
     *
     * @return Palauttaa totuusarvon.
     */
    public boolean isInThePile(Card card) {
        for (Card k : this.getClickedPile()) {
            if (k.toString().equals(card.toString())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metodi tulostaa kaikki pinon jäsenet.
     *
     */
    public void printWholePile() {
        for (Card k : this.getClickedPile()) {
            System.out.println(k.toString());
        }
    }

    public Stack<Card> getClickedPile() {
        return this.clickedPile.getPile();
    }

    private void updateDeckSize() {
        this.deckSize = this.clickedPile.getChangedPileSize();
    }

    public int getDeckSize() {
        return this.deckSize;
    }

    public Button getCard() {
        return this.card;
    }

    public Stack<Card> getPile() {
        return this.upperPile;
    }

    public ImageView getBackOfTheCard() {
        return ima1;
    }

    public ImageView getBottomImage() {
        return ima2;
    }

}
