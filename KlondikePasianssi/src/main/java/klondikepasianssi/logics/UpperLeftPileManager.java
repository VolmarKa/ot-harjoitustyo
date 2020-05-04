package klondikepasianssi.logics;

import java.util.Stack;
import klondikepasianssi.gui.Card;
import klondikepasianssi.gui.UpperLeftPileView;

public class UpperLeftPileManager {

    private final UpperLeftPileView upperLeftPileView;
    private final ClickedPileManager clickedPileManager;
    private Stack<Card> pile;

    public UpperLeftPileManager(Stack<Card> pile, ClickedPileManager clickedPileManager) {
        this.pile = pile;
        this.upperLeftPileView = new UpperLeftPileView(this);
        this.clickedPileManager = clickedPileManager;
        this.upperLeftPileView.getChildren().add(this.clickedPileManager.getView());

    }

    public void pileClicked() {
        if (pile.size() == 1) {
            upperLeftPileView.setBottomImage();
        }
        if (!pile.isEmpty()) {
            clickedPileManager.addCard(pile.pop());

        } else {
            upperLeftPileView.setBackImage();
            clickedPileManager.updateStack();
            pile = clickedPileManager.getChangedPile();
        }
    }

    public UpperLeftPileView getView() {
        return this.upperLeftPileView;
    }

    public Stack<Card> getClickedPile() {
        return this.clickedPileManager.getPile();
    }

    public Stack<Card> getPile() {
        return this.pile;
    }
    
    public ClickedPileManager getClickedPileManager(){
        return this.clickedPileManager;
    }

}
