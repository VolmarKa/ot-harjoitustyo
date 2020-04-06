package klondikePasianssi.logics;

import java.util.Stack;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class UpperLeftPile extends HBox {

    private int i;
    private int deckSize;
    private final CardImage cardImage = new CardImage();
    private final ImageView IMA1 = new ImageView(cardImage.backOfTheCard());
    private final ImageView IMA2 = new ImageView(cardImage.bottomImage());
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

    public void checkIndex() {
        if (this.i == deckSize) {
            this.card.setGraphic(IMA2);
        }
        if (this.i == 1) {
            this.card.setGraphic(IMA1);
        }
    }

    public void printCards() {
        for (Card k : this.upperPile) {
            System.out.println(k.toString());
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

    private void updateDeckSize() {
        this.deckSize = this.clickedPile.getChancedPileSize();
    }

    public int getDeckSize() {
        return this.deckSize;
    }

    public Stack<Card> getPile() {
        return this.upperPile;
    }

}
