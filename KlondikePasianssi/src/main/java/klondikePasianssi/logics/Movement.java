package klondikePasianssi.logics;

import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import klondikePasianssi.gui.Card;

public class Movement {

    private Card card;

    public Movement(Card card) {
        this.card = card;
        Dragboard dragboard = card.startDragAndDrop(TransferMode.MOVE);
        card.setOnDragDetected(e -> {
            dragDetected(e);
        });
    }

    private void dragDetected(MouseEvent event) {

        if (card == null) {
            event.consume();
            return;
        }

        Dragboard dragboard = card.startDragAndDrop(TransferMode.MOVE);
        ClipboardContent content = new ClipboardContent();
            
    }

}
