package klondikepasianssi.logics;

import java.util.Stack;
import klondikepasianssi.gui.Card;
import klondikepasianssi.gui.UpperLeftPileView;

/**
 * Luokka vastaa klikattavan pakan toiminnallisuuksista.
 */
public class UpperLeftPileManager {

    private final UpperLeftPileView upperLeftPileView;
    private Stack<Card> pile;
    private Stack pileClicked = new Stack<>();
    private boolean pileRecycled = false;

    public UpperLeftPileManager(Stack<Card> pile) {
        this.pile = pile;
        this.upperLeftPileView = new UpperLeftPileView(this);
        pile.firstElement().setLastCardTrue();
        pile.peek().setFirstCardTrue();

    }

    /**
     * Metodi vastaa pakan klikkaamisesta.
     *
     */
    public void pileClicked() {
        pileRecycled = false;
        if (pile.isEmpty() || (pile.size() == 1 && !upperLeftPileView.getClickedCards().getChildren().isEmpty())) {
            return;
        }

        pileClicked.push(1);
        Card topCard = pile.pop();
        pile.add(0, topCard);
        if (topCard.getLastCard()) {
            upperLeftPileView.setBottomImage();
        } else {
            upperLeftPileView.setBackImage();
        }
        if (getView().getClickedCards().getChildren().size() == pile.size()) {
            pileRecycled = true;
            getView().getClickedCards().getChildren().clear();
        }
        getView().getClickedCards().getChildren().add(topCard);
        if (pileRecycled) {
            pile.firstElement().setTagged();
        }
    }

    public void setNotRecycled() {
        this.pileRecycled = false;
    }

    public Stack getPileClicked() {
        return this.pileClicked;
    }

    public UpperLeftPileView getView() {
        return this.upperLeftPileView;
    }

    public Stack<Card> getPile() {
        return this.pile;
    }

}
