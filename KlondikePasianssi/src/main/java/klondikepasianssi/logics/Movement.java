package klondikepasianssi.logics;

import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import klondikepasianssi.gui.Card;
import klondikepasianssi.gui.Card.Suit;

public class Movement {

    private int index;
    private final Card card;
    private MiddlePileManager manager;

    public Movement(Card card, MiddlePileManager m) {
        this.card = card;
        this.manager = m;

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

        if (card == null || !card.getFaceUp()) {
            event.consume();
            return;
        }

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

        }

        event.consume();
    }

    private void dragDropped(DragEvent event) {

        Dragboard db = event.getDragboard();

        if (db.hasString() && db.hasImage()) {

            String[] properties = db.getString().split(" ");
            Image image = db.getImage();
            Suit suit = card.getCardProperties().checkSuit(properties[0]);
            Card cardd = new Card(suit, image, Integer.valueOf(properties[1]));
            cardd.getCardProperties().makeMovable(manager);
            for (int i = 0; i <= 6; i++) {
                int size = manager.getPiles()[i].getPile().size();
                for (int a = 0; a <= size - 1; a++) {
                    if (manager.getPiles()[i].getPile().get(a).toString().equals(event.
                            getGestureTarget().toString())) {
                        this.index = i;

                    }
                }
            }
            if (!cardd.toString().equals(event.getGestureTarget().toString())) {
                deleteAndAddOriginalCard(cardd);
            }
            event.setDropCompleted(true);
        } else {
            event.setDropCompleted(false);

        }

        event.consume();
    }

    private void dragDone(DragEvent e) {
        TransferMode modeUsed = e.getTransferMode();

        if (modeUsed == TransferMode.MOVE) {

            e.consume();
        }
    }

    private void deleteAndAddOriginalCard(Card card) {
        for (int i = 0; i <= 6; i++) {
            int size = manager.getPiles()[i].getPile().size();
            for (int a = 0; a <= size - 1; a++) {
                if (manager.getPiles()[i].getPile().get(a).toString().
                        equals(card.toString())) {
                    System.out.println(manager.getPiles()[i].getPile().get(a).toString());
                    System.out.println(a);
                    manager.getPiles()[i].getChildren().remove(a+1);

                    this.manager.getPiles()[this.index].getChildren().add(card);
                    this.manager.getPiles()[this.index].getPile().push(
                            this.manager.getPiles()[i].getPile().pop());
                    manager.changeSideUpdate();
                    return;
                }
            }
        }
    }
}
