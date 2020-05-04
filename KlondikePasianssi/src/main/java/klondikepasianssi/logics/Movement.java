package klondikepasianssi.logics;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import klondikepasianssi.gui.Card;

/**
 * Luokka vastaa korttien liikuttelusta.
 */
public final class Movement {

    private int x = 1;
    private double y;
    private int targetIndex;
    private int sourceIndex;
    private int sourceCard;
    private int targetCard;
    private final Card card;
    private final MiddlePileManager manager;
    private final UpperLeftPileManager upperLeft;
    private final UpperRightPileManager upperRight;
    private final ValidateMove validateMove;

    public Movement(Card card, MiddlePileManager middlePileManager, UpperLeftPileManager upperLeftPileManager, UpperRightPileManager upperRightPileManager) {
        validateMove = new ValidateMove();
        this.card = card;
        this.manager = middlePileManager;
        this.upperLeft = upperLeftPileManager;
        this.upperRight = upperRightPileManager;

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

        makeClickableAfterSideChange();
    }

    private void dragDetected(MouseEvent event) {
        Card source = (Card) event.getSource();
        boolean suitIsNeutral = source.getSuit() == Card.Suit.NEUTRAL;
        if (card == null || !card.getFaceUp() || suitIsNeutral || source.getRank() == 0) {
            event.consume();
            return;
        }

        Dragboard dragboard = card.startDragAndDrop(TransferMode.MOVE);
        ClipboardContent content = new ClipboardContent();
        content.putImage(card.getImage());
        dragboard.setContent(content);
        event.consume();

    }

    private void dragOver(DragEvent event) {

        Dragboard dragboard = event.getDragboard();

        if (dragboard.hasImage()) {
            event.acceptTransferModes(TransferMode.MOVE);

        }

        event.consume();
    }

    private void dragDropped(DragEvent event) {
        Dragboard db = event.getDragboard();
        findTargetAndSource(event);
        Card target = (Card) event.getGestureTarget();
        Card source = (Card) event.getGestureSource();
        if ((!validateMove.SuitsAreDifferent(source, target) && targetIndex <= 6)
                || (!validateMove.ranksAreDescending(source, target) && targetIndex <= 6) || (!validateMove.moveToUpperRightPileIsAllowed(source, target) && targetIndex > 6)) {
            return;
        }
        if (db.hasImage() && this.card.getFaceUp()
                && !checkIfInTheSamePile() && !(sourceIndex > 6 && targetIndex > 6)
                && targetIndex != -1 && targetIsTopCard()) {

            //System.out.println("pile " + this.sourceIndex + " card " + this.sourceCard + " target pile " + this.targetIndex + " card " + this.targetCard + " size " + manager.getPiles()[targetIndex].getPile().size());
            y = target.getTranslateY();
            //upperLeft.printWholePile();
            if (sourceIndex == -1) {
                moveCardFromClickedPile();

            } else if (sourceIndex <= 6 && targetIndex > 6) {
                moveCardToUpperRightPile();
            } else if (sourceIndex > 6) {
                moveCardFromUpperRightPile();
            } else {
                moveCardInMiddlePile();
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

    private void moveCardInMiddlePile() {
        int size = manager.getPiles()[sourceIndex].getPile().size();
        for (int i = sourceCard; i <= size - 1; i++) {
            //System.out.println("alkuperäisen pinon koko on " + manager.getPiles()[sourceIndex].getPile().size() + " ja poistettava on " + manager.getPiles()[sourceIndex].getPile().get(sourceCard));
            Card cardd = manager.getPiles()[sourceIndex].getPile().get(sourceCard);
            manager.getPiles()[sourceIndex].getChildren().remove(sourceCard);
            manager.getPiles()[targetIndex].getPile().push(manager.getPiles()[sourceIndex].getPile().remove(sourceCard));
            // System.out.println("target koko " + manager.getPiles()[targetIndex].getPile().size()
            //                    + " ja ensimmäinen kortti on " + manager.getPiles()[targetIndex].getPile().peek().toString());
            manager.getPiles()[targetIndex].getChildren().add(cardd);
            if (manager.getPiles()[targetIndex].getPile().get(targetCard).getSuit() == Card.Suit.NEUTRAL && i == sourceCard) {
                cardd.setTranslateY(y);

            } else {
                cardd.setTranslateY(y + x * 20);
                x++;
            }

        }

        manager.changeSideUpdate();
        x = 1;

    }

    private boolean checkIfInTheSamePile() {
        if (targetIndex == sourceIndex && (sourceIndex > 6 && targetIndex > 6
                || sourceIndex <= 6 && targetIndex <= 6)) {
            return true;
        }
        return false;
    }

    private boolean targetIsTopCard() {
        if (targetIndex <= 6) {
            return targetCard == manager.getPiles()[targetIndex].getPile().size() - 1;
        } else {
            return targetCard == upperRight.getPiles()[targetIndex - 7].getPile().size() - 1;
        }

    }

    private boolean sourceIsTopCard() {
        if (sourceIndex == -1) {
            return true;
        }
        if(sourceIndex == -2){
            return false;
        }
        return sourceCard == manager.getPiles()[sourceIndex].getPile().size() - 1;
    }

    private void moveCardFromClickedPile() {
        Card originalCard = upperLeft.getClickedPile().peek();
        upperLeft.getView().getChildren().remove(upperLeft.getClickedPile().pop());
        if (targetIndex > 6) {
            upperRight.getPiles()[targetIndex - 7].getChildren().add(originalCard);
            upperRight.getPiles()[targetIndex - 7].getPile().add(originalCard);
        } else {
            manager.getPiles()[targetIndex].getChildren().add(originalCard);
            manager.getPiles()[targetIndex].getPile().add(originalCard);
            if (manager.getPiles()[targetIndex].getPile().get(targetCard).getSuit() == Card.Suit.NEUTRAL) {
                originalCard.setTranslateY(y);
            } else {
                originalCard.setTranslateY(y + 20);
            }
        }

    }

    private void moveCardToUpperRightPile() {
        if (sourceIsTopCard()) {
            Card originalCard = manager.getPiles()[sourceIndex].getPile().peek();
            manager.getPiles()[sourceIndex].getChildren().remove(sourceCard);
            upperRight.getPiles()[targetIndex - 7].getPile().push(manager.getPiles()[sourceIndex]
                    .getPile().pop());
            upperRight.getPiles()[targetIndex - 7].getPile().print();
            upperRight.getPiles()[targetIndex - 7].getChildren().add(originalCard);
            originalCard.setTranslateY(y);
            originalCard.getCardProperties().makeMovable(manager, upperLeft, upperRight);
            manager.changeSideUpdate();
        }

    }

    private void moveCardFromUpperRightPile() {
        Card originalCard = upperRight.getPiles()[sourceIndex - 7].getPile().peek();
        upperRight.getPiles()[sourceIndex - 7].getChildren().remove(upperRight
                .getPiles()[sourceIndex - 7].getPile().pop());
        manager.getPiles()[targetIndex].getPile().push(originalCard);
        manager.getPiles()[targetIndex].getChildren().add(originalCard);
        if (manager.getPiles()[targetIndex].getPile().get(targetCard).getSuit() == Card.Suit.NEUTRAL) {
            originalCard.setTranslateY(y);
        } else {
            originalCard.setTranslateY(y + 20);
        }
    }

    private void moveOnDoubleClick() {
        for (int i = 0; i <= 3; i++) {
            Card topCard = upperRight.getPiles()[i].getPile().peek();
            if (validateMove.moveToUpperRightPileIsAllowed(this.card, topCard)) {
                if (sourceIndex == -1) {
                    upperLeft.getClickedPileManager().getView().getChildren().remove(upperLeft.getClickedPile().pop());
                    upperRight.getPiles()[i].getPile().add(this.card);
                    upperRight.getPiles()[i].getChildren().add(this.card);
                    System.out.println(upperLeft.getClickedPile().peek());

                } else {
                    manager.getPiles()[sourceIndex].getChildren().remove(manager
                            .getPiles()[sourceIndex].getPile().pop());
                    upperRight.getPiles()[i].getChildren().add(this.card);
                    upperRight.getPiles()[i].getPile().push(this.card);
                    this.card.setTranslateY(topCard.getTranslateY());
                    manager.changeSideUpdate();
                    System.out.println(manager.getPiles()[sourceIndex].getPile().peek());
                }

                return;
            }

        }

    }

    private void findTargetAndSource(DragEvent event) {
        Card source = (Card) event.getGestureSource();
        Card target = (Card) event.getGestureTarget();
        for (int i = 0; i <= 6; i++) {
            int size = manager.getPiles()[i].getPile().size();
            for (int a = 0; a <= size - 1; a++) {
                if (manager.getPiles()[i].getPile().get(a).toString().equals(
                        target.toString())) {
                    this.targetIndex = i;
                    this.targetCard = a;
                }

                if (manager.getPiles()[i].getPile().get(a).toString().equals(
                        source.toString())) {
                    this.sourceIndex = i;
                    this.sourceCard = a;
                }

            }
            if (i <= 3) {

                for (int a = 0; a <= upperRight.getPiles()[i].getPile().size() - 1; a++) {
                    if (upperRight.getPiles()[i].getPile().get(a).toString().equals(
                            target.toString())) {
                        this.targetIndex = i + 7;
                        this.targetCard = a;
                    }

                    if (upperRight.getPiles()[i].getPile().get(a).toString().equals(
                            source.toString())) {
                        this.sourceIndex = i + 7;
                        this.sourceCard = a;
                    }
                }
            }
        }

        if (upperLeft.getClickedPile().contains(source)) {
            this.sourceIndex = -1;
        }
        if (upperLeft.getClickedPile().contains(target)) {
            this.targetIndex = -1;
        }
    }

    private void findSourceOnClick() {
        for (int i = 0; i <= 6; i++) {
            if (manager.getPiles()[i].getPile().peek().toString().equals(this.card.toString())) {
                sourceIndex = i;
                sourceCard = manager.getPiles()[i].getPile().size()-1;
                return;
            }
        }
        if(upperLeft.getClickedPile().contains(this.card)){
            sourceIndex = -1;
            return;
        }
        sourceIndex = -2;
        
    }

    public void makeClickableAfterSideChange() {
        this.card.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                findSourceOnClick();
                if (!sourceIsTopCard()) {
                    return;
                }
                System.out.println(sourceIndex);
                if (event.getClickCount() == 2) {
                    moveOnDoubleClick();
                    System.out.println("yeah");

                }
            }
        });
    }

}
