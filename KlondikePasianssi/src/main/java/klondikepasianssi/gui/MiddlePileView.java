package klondikepasianssi.gui;

import javafx.scene.layout.StackPane;
import klondikepasianssi.logics.MiddlePile;

public class MiddlePileView extends StackPane {

    private MiddlePile pile = new MiddlePile(this);

    public MiddlePileView() {

    }

    public MiddlePile getPile() {
        return this.pile;
    }

}
