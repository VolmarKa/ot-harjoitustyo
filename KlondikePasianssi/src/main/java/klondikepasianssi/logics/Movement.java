package klondikepasianssi.logics;

import klondikepasianssi.gui.UpperRightPileManager;
import klondikepasianssi.gui.MiddlePileManager;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import klondikepasianssi.gui.Card;

/**
 * Luokka vastaa hiiren avulla tehdyistä tapahtumista.
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
    private ReverseMove reverseMove;
    private MovementLogics middlePileMovement;

    public Movement(Card card, MiddlePileManager middlePileManager, UpperLeftPileManager upperLeftPileManager, UpperRightPileManager upperRightPileManager, ValidateMove validateMove, ReverseMove reverseMove, MovementLogics movementLogics) {
        this.validateMove = validateMove;
        this.reverseMove = reverseMove;
        this.card = card;
        this.manager = middlePileManager;
        this.upperLeft = upperLeftPileManager;
        this.upperRight = upperRightPileManager;
        this.middlePileMovement = movementLogics;

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

            y = target.getTranslateY();
            reverseMove.getSourceIndexes().push(sourceIndex);
            reverseMove.getTargetIndexes().push(targetIndex);
            reverseMove.getSourceCards().push(sourceCard);
            reverseMove.getTargetCards().push(targetCard);

            makeMove();

            event.setDropCompleted(true);
            upperLeft.getPileClicked().push(2);

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

    private boolean checkIfInTheSamePile() {
        if (targetIndex == sourceIndex && ((sourceIndex > 6 && targetIndex > 6)
                || (sourceIndex <= 6 && targetIndex <= 6))) {
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
        if (sourceIndex == -2) {
            return false;
        }
        return sourceCard == manager.getPiles()[sourceIndex].getPile().size() - 1;
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
        if (upperLeft.getPile().contains(source)) {
            this.sourceIndex = -1;
        }
        if (upperLeft.getPile().contains(target)) {
            this.targetIndex = -1;
        }
    }

    /**
     * Metodi löytää klikattavan kortin ja sen pinon hiiren klikkauksella.
     *
     */
    public void findSourceOnClick() {
        for (int i = 0; i <= 6; i++) {
            if (manager.getPiles()[i].getPile().peek().toString().equals(this.card.toString())) {
                sourceIndex = i;
                sourceCard = manager.getPiles()[i].getPile().size() - 1;
                if (!manager.getPiles()[sourceIndex].getPile().peek().getHasBeenClicked()) {
                    upperLeft.getPileClicked().push(3);
                    reverseMove.getSourceIndexes().push(sourceIndex);
                }
                return;
            }
        }
        if (upperLeft.getPile().contains(this.card)) {
            sourceIndex = -1;
            return;
        }
        sourceIndex = -2;

    }

    /**
     * Tekee kortista klikattavan sen puolen vaihtamisen jälkeen.
     *
     */
    public void makeClickableAfterSideChange() {
        this.card.setOnMouseClicked((MouseEvent event) -> {

            findSourceOnClick();
            if (!sourceIsTopCard()) {
                return;
            }
            if (event.getClickCount() == 2) {
                reverseMove.getSourceIndexes().push(sourceIndex);
                middlePileMovement.moveOnDoubleClick(validateMove, sourceIndex, this.card);

            }
        });
    }

    private void makeMove() {
        if (upperRight.gameEnded()) {
            return;
        }
        if (sourceIndex == -1) {
            middlePileMovement.moveCardFromClickedPile(targetIndex, targetCard, y);

        } else if (sourceIndex <= 6 && targetIndex > 6) {
            if (sourceIsTopCard()) {
                middlePileMovement.moveCardToUpperRightPile(sourceIndex, sourceCard, targetIndex, y);
            }
        } else if (sourceIndex > 6) {
            middlePileMovement.moveCardFromUpperRightPile(sourceIndex, targetIndex, targetCard, y);

        } else {
            middlePileMovement.moveCardInMiddlePile(targetIndex, sourceIndex, targetCard, sourceCard, y, x);

        }
    }

}
