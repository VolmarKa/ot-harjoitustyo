package klondikepasianssi.logics;

import java.util.Stack;
import javafx.scene.layout.StackPane;
import klondikepasianssi.gui.Card;

/**
 * Luokka vastaa klikatun pinon toiminnoista.
 */
public class ClickedPile extends StackPane {

    private Stack<Card> changedPile = new Stack<>();
    private Stack<Card> pile = new Stack<>();
    private int pileSize;

    public ClickedPile() {

    }

    /**
     * Metodi päivittää klikatun pinon.
     *
     */
    public void updateStack() {
        this.pileSize = this.pile.size();
        if (!changedPile.empty()) {
            this.changedPile.clear();
        }
        for (int i = 1; i <= this.pileSize; i++) {
            this.changedPile.push(this.pile.pop());
        }
        this.getChildren().clear();
    }

    public Stack<Card> returnStack() {
        return this.changedPile;
    }

    /**
     * Metodi lisää kortin klikattuun pinoon.
     *
     * @param card Lisättävä kortti.
     */
    public void addCard(Card card) {
        this.pile.push(card);
        //System.out.println(this.pile.size() + " " + this.pile.peek().toString());
    }

    public Stack<Card> getPile() {
        return this.pile;
    }

    public int getChangedPileSize() {
        return this.changedPile.size();
    }

}
