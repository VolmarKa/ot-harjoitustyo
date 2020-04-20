package klondikePasianssi.logics;

import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import klondikePasianssi.gui.Card;
import klondikePasianssi.gui.Card.Suit;

public class Movement {

    private final Card card;

    public Movement(Card card) {
        this.card = card;

        this.card.setOnDragDetected(e -> {
            dragDetected(e);
        });

        this.card.setOnDragOver(e -> {
            dragOver(e);
        });

        this.card.setOnDragDropped(e -> {
            dragDropped(e);
        });

        this.card.setOnDragDone(e -> {
            dragDone(e);
        });
    }

    private void dragDetected(MouseEvent event) {

        if (card == null) {
            event.consume();
            return;
        }
        //System.out.println("detected");
        Dragboard dragboard = card.startDragAndDrop(TransferMode.MOVE);
        ClipboardContent content = new ClipboardContent();
        content.putImage(card.getImage());
        content.putString(card.toString());
        dragboard.setContent(content);
        event.consume();

    }

    private void dragOver(DragEvent event) {

        Dragboard dragboard = event.getDragboard();

        if (dragboard.hasImage() && dragboard.hasString()) {
            event.acceptTransferModes(TransferMode.MOVE);
            // System.out.println("over");
        }

        event.consume();
    }

    private void dragDropped(DragEvent event) {

        Dragboard db = event.getDragboard();

        if (db.hasString() && db.hasImage() && event.getGestureTarget().getClass() == Card.class) {

            String[] properties = db.getString().split(" ");
            Image image = db.getImage();
            Suit suit = card.getCardProperties().checkSuit(properties[0]);
            Card cardd = new Card(suit, image, Integer.valueOf(properties[1]));
            System.out.println(cardd.toString());
            System.out.println(event.getGestureTarget().toString());

            event.setDropCompleted(true);
        } else {
            event.setDropCompleted(false);

        }

        event.consume();
    }

    private void dragDone(DragEvent e) {
        TransferMode modeUsed = e.getTransferMode();

        if (modeUsed == TransferMode.MOVE) {

        }
        e.consume();
    }
    
    private void move(){
        
    }

}
