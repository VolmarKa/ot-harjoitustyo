package klondikepasianssi.logics;

import java.util.Stack;
import klondikepasianssi.gui.Card;
import klondikepasianssi.gui.MiddlePileView;

public class MiddlePile extends Stack<Card> {

    private MiddlePileView pile;
    private int top;

    public MiddlePile(MiddlePileView pile) {
        this.pile = pile;
        updateSize();
    }

    public void updateSize() {
        this.top = this.pile.getChildren().size();
    }

}
