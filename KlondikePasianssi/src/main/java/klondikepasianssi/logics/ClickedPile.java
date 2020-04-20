package klondikepasianssi.logics;

import java.util.Stack;
import javafx.scene.layout.StackPane;
import klondikepasianssi.gui.Card;

public class ClickedPile extends StackPane {

    private Stack<Card> changedPile = new Stack<>();
    private Stack<Card> pile = new Stack<>();
    private int pileSize;

    public ClickedPile() {

    }

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

    public void addCard(Card card) {
        this.pile.push(card);
        //System.out.println(this.pile.size() + " " + this.pile.peek().toString());
    }

    public int getChancedPileSize() {
        return this.changedPile.size();
    }

    public void deletetest() {
        this.pile.remove(12);
        this.pile.remove(13);
        this.pile.remove(21);
    }

}
