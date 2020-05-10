package klondikepasianssi.gui;

import javafx.scene.layout.StackPane;
import klondikepasianssi.logics.UpperRightPile;

public class UpperRightPileView extends StackPane {

    private UpperRightPile pile;

    public UpperRightPileView() {
        this.pile = new UpperRightPile(this);
    }

    public UpperRightPile getPile() {
        return this.pile;
    }
}
