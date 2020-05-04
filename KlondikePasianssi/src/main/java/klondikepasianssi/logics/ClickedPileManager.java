package klondikepasianssi.logics;

import java.util.Stack;
import klondikepasianssi.gui.Card;
import klondikepasianssi.gui.ClickedPileView;

public class ClickedPileManager {

    private final ClickedPileView clickedPileView;
    private Stack<Card> pile;
    private Stack<Card> changedPile;

    public ClickedPileManager() {
        this.clickedPileView = new ClickedPileView();
        this.pile = new Stack<>();
        changedPile = new Stack<>();

    }

    public void addCard(Card card) {
        pile.push(card);
        clickedPileView.getChildren().add(card);
    }

    public void updateStack() {
        int size = this.pile.size();
        this.changedPile.clear();

        for (int i = 1; i <= size; i++) {
            this.changedPile.push(this.pile.pop());
        }
        clickedPileView.getChildren().clear();
    }

    public Stack<Card> getChangedPile() {
        return this.changedPile;
    }

    public Stack<Card> getPile() {
        return this.pile;
    }

    public ClickedPileView getView() {
        return this.clickedPileView;
    }

    public int getDeckSize() {
        return this.pile.size();
    }

}
