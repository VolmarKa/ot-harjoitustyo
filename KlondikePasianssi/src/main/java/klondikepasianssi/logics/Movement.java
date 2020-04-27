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

    private int x = 1;
    private double y;
    private int index;
    private final Card card;
    private final MiddlePileManager manager;
    private final UpperLeftPile upperLeft;

    public Movement(Card card, MiddlePileManager m, UpperLeftPile u) {
        this.card = card;
        this.manager = m;
        this.upperLeft = u;

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
        Card target = (Card) event.getGestureTarget();
        if (db.hasString() && db.hasImage() && this.card.getFaceUp()
                && checkIfInTheSamePile(event)) {
            y = target.getTranslateY();
            String[] properties = db.getString().split(" ");
            Image image = db.getImage();
            Suit suit = card.getCardProperties().checkSuit(properties[0]);
            Card cardd = new Card(suit, image, Integer.valueOf(properties[1]));
            cardd.getCardProperties().makeMovable(manager, upperLeft);
            if (!cardd.toString().equals(event.getGestureTarget().toString())
                    && isTopCard(target)) {              
                upperLeft.wholedeck();
                if (upperLeft.isInThePile(cardd)) {
                    deleteAndAddOriginalCard2();
                } else {
                    deleteAndAddOriginalCard(cardd);
                }
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
                    if (a < size - 1) {
                        System.out.println("a on " + a);
                        for (int z = a; z <= size - 1; z++) {
                            System.out.println("alkuperäisen pinon koko on " + manager.getPiles()[i].getPile().size() + " ja poistettava on " + manager.getPiles()[i].getPile().get(a));
                            Card d = manager.getPiles()[i].getPile().get(a);
                            manager.getPiles()[i].getChildren().remove(a + 1);
                            manager.getPiles()[this.index].getPile().push(
                                    manager.getPiles()[i].getPile().remove(a));
                            System.out.println("target koko " + manager.getPiles()[index].getPile().size()
                                    + " ja ensimmäinen kortti on " + manager.getPiles()[index].getPile().peek().toString());
                            manager.getPiles()[index].getChildren().add(d);
                            d.setTranslateY(y + x * 20);
                            x++;

                        }

                    } else {
                        manager.getPiles()[i].getChildren().remove(a + 1);
                        this.manager.getPiles()[this.index].getChildren().add(card);
                        this.manager.getPiles()[this.index].getPile().push(
                                this.manager.getPiles()[i].getPile().pop());
                        card.setTranslateY(y + 20);

                    }
                    manager.changeSideUpdate();
                    x = 1;
                    return;
                }
            }
        }
    }

    private boolean checkIfInTheSamePile(DragEvent event) {
        boolean same = false;
        boolean same2 = false;
        Card targetCard = (Card) event.getGestureTarget();
        Card sourceCard = (Card) event.getGestureSource();

        for (int i = 0; i <= 6; i++) {
            int size = manager.getPiles()[i].getPile().size();
            for (int a = 0; a <= size - 1; a++) {
                if (manager.getPiles()[i].getPile().get(a).toString()
                        .equals(targetCard.toString())) {
                    same = true;
                }

                if (manager.getPiles()[i].getPile().get(a).toString()
                        .equals(sourceCard.toString())) {
                    same2 = true;
                }

                if (same2 && same) {
                    return false;
                }
            }
            same = false;
            same2 = false;
        }
        return true;
    }

    private boolean isTopCard(Card target) {

        for (int i = 0; i <= 6; i++) {
            int size = manager.getPiles()[i].getPile().size();
            for (int a = 0; a <= size - 1; a++) {
                if (manager.getPiles()[i].getPile().get(a).toString().equals(
                        target.toString()) && a == size - 1) {
                    this.index = i;
                    return true;
                }
            }
        }
        return false;
    }
    
    private void deleteAndAddOriginalCard2(){
        Card originalCard = upperLeft.getClickedPile().peek();
        upperLeft.getChildren().remove(upperLeft.getClickedPile().pop());
        manager.getPiles()[index].getChildren().add(originalCard);
        manager.getPiles()[index].getPile().add(originalCard);
        originalCard.setTranslateY(y + 20);
        
    }

}
