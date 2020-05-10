package klondikepasianssi.logics;

import java.util.Stack;
import klondikepasianssi.gui.Card;

/**
 * Luokka vastaa tehdyn liikkeen perumisesta.
 */
public class ReverseMove {

    private Stack sourceIndexes = new Stack<>();
    private Stack targetIndexes = new Stack<>();
    private Stack sourceCards = new Stack<>();
    private Stack targetCards = new Stack<>();
    private final MiddlePileManager manager;
    private final UpperLeftPileManager upperLeft;
    private final UpperRightPileManager upperRight;
    private MovementLogics middlePileMovement;

    public ReverseMove(MiddlePileManager middlePileManager, UpperLeftPileManager upperLeftPileManager, UpperRightPileManager upperRightPileManager) {
        this.manager = middlePileManager;
        this.upperLeft = upperLeftPileManager;
        this.upperRight = upperRightPileManager;
        middlePileMovement = new MovementLogics(manager, upperLeft, upperRight, this);

    }

    /**
     * Peruu viimeiseksi tehdyn liikkeen.
     */
    public void reverseMove() {
        if (upperLeft.getPileClicked().isEmpty() || upperRight.gameEnded()) {
            return;
        }
        int move = (int) upperLeft.getPileClicked().pop();
        if (move == 1) {
            reversePileClick();
        } else if (move == 3) {
            reverseSideChange();
        } else if (move == 4) {
            reverseDoubleClick();
        } else if ((int) sourceIndexes.peek() == -1) {
            revereseMoveFromUpperLeftPile();
        } else if ((int) targetIndexes.peek() > 6 && (int) sourceIndexes.peek() <= 6) {
            reverseMoveToUpperRightPile();
        } else if ((int) sourceIndexes.peek() > 6) {
            reverseMoveFromUpperRightPile();
        } else {
            reverseMoveInMiddlePile();
        }
    }

    private void reversePileClick() {
        if (upperLeft.getPile().size() >= 2) {
            Card previousCard = upperLeft.getPile().remove(1);
            Card clickedCard = upperLeft.getPile().remove(0);
            upperLeft.getPile().push(clickedCard);
            upperLeft.getPile().add(0, previousCard);
            if (!upperLeft.getPileClicked().isEmpty() && !upperLeft.getView().getClickedCards().getChildren().contains(previousCard)) {
                if ((int) upperLeft.getPileClicked().peek() == 1) {
                    upperLeft.getView().getClickedCards().getChildren().add(previousCard);
                }
            }
            upperLeft.getView().getClickedCards().getChildren().remove(clickedCard);
            if (clickedCard.getTagged()) {
                clickedCard.setNotTagged();
                if (!upperLeft.getView().getClickedCards().getChildren().isEmpty()) {
                    upperLeft.getView().getClickedCards().getChildren().remove(0);
                }
                upperLeft.setNotRecycled();
                for (int i = upperLeft.getPile().size() - 1; i >= 0; i--) {
                    upperLeft.getView().getClickedCards().getChildren().add(upperLeft.getPile().get(i));
                }
            }
            if (previousCard.getLastCard() && !upperLeft.getView().getClickedCards().getChildren().isEmpty()) {
                upperLeft.getView().setBottomImage();
            } else {
                upperLeft.getView().setBackImage();
            }
        } else {
            Card clickedCard = upperLeft.getPile().remove(0);
            upperLeft.getPile().push(clickedCard);
            upperLeft.getView().getClickedCards().getChildren().remove(clickedCard);
            upperLeft.getView().setBackImage();
        }
    }

    private void revereseMoveFromUpperLeftPile() {
        if ((int) targetIndexes.peek() <= 6) {
            Card movedCard = manager.getPiles()[(int) targetIndexes.peek()].getPile().peek();
            checkIfFirstOrLastCard(movedCard);
            manager.getPiles()[(int) targetIndexes.peek()].getChildren().remove(manager.getPiles()[(int) targetIndexes.pop()].getPile().pop());
            upperLeft.getPile().add(0, movedCard);
            upperLeft.getView().getClickedCards().getChildren().add(movedCard);
            movedCard.setTranslateY(0.0);

        } else {
            Card movedCard = upperRight.getPiles()[(int) targetIndexes.peek() - 7].getPile().peek();
            checkIfFirstOrLastCard(movedCard);
            upperRight.getPiles()[(int) targetIndexes.peek() - 7].getChildren().remove(upperRight.getPiles()[(int) targetIndexes.pop() - 7].getPile().pop());
            upperLeft.getPile().add(0, movedCard);
            upperLeft.getView().getClickedCards().getChildren().add(movedCard);
            movedCard.setTranslateY(0.0);
        }

        sourceIndexes.pop();
        sourceCards.pop();
        targetCards.pop();

    }

    private void reverseMoveToUpperRightPile() {

        // Nullifies the method changeSideUpdate.
        manager.getPiles()[(int) sourceIndexes.peek()].getPile().peek().setOnMouseClicked(event -> {

        });

        middlePileMovement.moveCardFromUpperRightPile((int) targetIndexes.pop(), (int) sourceIndexes.peek(), (int) sourceCards.pop() - 1, manager.getPiles()[(int) sourceIndexes.pop()].getPile().peek().getTranslateY());
        targetCards.pop();
    }

    private void reverseMoveInMiddlePile() {
        // Nullifies the method changeSideUpdate.
        manager.getPiles()[(int) sourceIndexes.peek()].getPile().peek().setOnMouseClicked(event -> {

        });
        middlePileMovement.moveCardInMiddlePile((int) sourceIndexes.peek(), (int) targetIndexes.pop(), (int) sourceCards.pop() - 1, (int) targetCards.pop() + 1, manager.getPiles()[((int) sourceIndexes.pop())].getPile().peek().getTranslateY(), 1);
    }

    private void reverseMoveFromUpperRightPile() {
        middlePileMovement.moveCardToUpperRightPile((int) targetIndexes.pop(), (int) targetCards.pop() + 1, (int) sourceIndexes.peek(), upperRight.getPiles()[(int) sourceIndexes.pop() - 7].getPile().peek().getTranslateY());
        sourceCards.pop();
    }

    private void reverseDoubleClick() {
        Card cardToMove = upperRight.getPiles()[(int) targetIndexes.peek()].getPile().pop();
        upperRight.getPiles()[(int) targetIndexes.pop()].getPile().remove(cardToMove);
        if ((int) sourceIndexes.peek() == -1) {
            upperLeft.getPile().add(0, cardToMove);
            upperLeft.getView().getClickedCards().getChildren().add(cardToMove);
            sourceIndexes.pop();
        } else {
            Card previousCard = manager.getPiles()[(int) sourceIndexes.peek()].getPile().peek();
            double y = manager.getPiles()[(int) sourceIndexes.peek()].getPile().peek().getTranslateY();
            // Nullifies the method changeSideUpdate.
            manager.getPiles()[(int) sourceIndexes.peek()].getPile().peek().setOnMouseClicked(event -> {

            });
            manager.getPiles()[(int) sourceIndexes.peek()].getChildren().add(manager.getPiles()[(int) sourceIndexes.pop()].getPile().push(cardToMove));
            if (previousCard.getSuit() == Card.Suit.NEUTRAL) {
                cardToMove.setTranslateY(y);
            } else if (previousCard.getFaceUp()) {
                cardToMove.setTranslateY(y + 20);
            } else {
                cardToMove.setTranslateY(y + 10);
            }

        }
    }

    private void reverseSideChange() {
        manager.getPiles()[(int) sourceIndexes.peek()].getPile().peek().getCardProperties().setFaceDown();
        manager.getPiles()[(int) sourceIndexes.peek()].getPile().peek().setHasNotBeenClicked();
        manager.getPiles()[(int) sourceIndexes.pop()].getPile().peek().getCardProperties().changeSide();

    }

    private void checkIfFirstOrLastCard(Card card) {
        if (upperLeft.getPile().isEmpty()) {
            upperLeft.getView().setBottomImage();
            return;
        }
        if (card.getFirstCard()) {
            upperLeft.getPile().peek().setFirstCardFalse();
        }
        if (card.getLastCard()) {
            upperLeft.getView().setBottomImage();
            upperLeft.getPile().firstElement().setLastCardFalse();
        }
    }

    public Stack<Integer> getTargetCards() {
        return this.targetCards;
    }

    public Stack<Integer> getSourceCards() {
        return this.sourceCards;
    }

    public Stack<Integer> getSourceIndexes() {
        return this.sourceIndexes;
    }

    public Stack<Integer> getTargetIndexes() {
        return this.targetIndexes;
    }
}
