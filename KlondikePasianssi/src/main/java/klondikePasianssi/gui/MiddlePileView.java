package klondikePasianssi.gui;

import javafx.scene.layout.StackPane;
import klondikePasianssi.logics.MiddlePile;

public class MiddlePileView extends StackPane {

    private MiddlePile pile = new MiddlePile(this);

    public MiddlePileView() {

    }

    public MiddlePile getPile() {
        return this.pile;
    }

}
