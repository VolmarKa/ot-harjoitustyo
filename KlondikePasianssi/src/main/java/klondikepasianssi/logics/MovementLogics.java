package klondikepasianssi.logics;

import klondikepasianssi.gui.Card;

/**
 * Luokka vastaa korttien liikuttelun logiikasta.
 */
public class MovementLogics {

    private MiddlePileManager manager;
    private UpperRightPileManager upperRight;
    private UpperLeftPileManager upperLeft;
    private ReverseMove reverseMove;

    public MovementLogics(MiddlePileManager manager, UpperLeftPileManager upperLeft, UpperRightPileManager upperRight, ReverseMove reverseMove) {
        this.manager = manager;
        this.upperLeft = upperLeft;
        this.upperRight = upperRight;
        this.reverseMove = reverseMove;
    }

    /**
     * Metodi suorittaa korttien ja pinojen siirtmäisen keskimmäisissä pinoissa.
     *
     * @param targetIndex Kohdepinon numero.
     * @param sourceIndex Likutettavan kortin alkuperäisen pinon numero.
     * @param sourceCard Liikuteltavan kortin numero sen alkuperäisessä pinossa.
     * @param targetCard Kohdepinon kohdekortin numero.
     * @param y Kohdekortin korkeus peliruudulla.
     * @param x Kokonaisluku.
     */
    public void moveCardInMiddlePile(int targetIndex, int sourceIndex, int targetCard, int sourceCard, double y, int x) {
        int size = manager.getPiles()[sourceIndex].getPile().size();
        for (int i = sourceCard; i <= size - 1; i++) {
            Card card = manager.getPiles()[sourceIndex].getPile().get(sourceCard);
            manager.getPiles()[sourceIndex].getChildren().remove(sourceCard);
            manager.getPiles()[targetIndex].getPile().push(manager.getPiles()[sourceIndex].getPile().remove(sourceCard));
            manager.getPiles()[targetIndex].getChildren().add(card);
            if (manager.getPiles()[targetIndex].getPile().get(targetCard).getSuit() == Card.Suit.NEUTRAL && i == sourceCard) {
                card.setTranslateY(y);

            } else {
                // Case when a move is reversed.
                if (!manager.getPiles()[targetIndex].getPile().get(targetCard).getFaceUp()) {
                    if (i == sourceCard) {
                        card.setTranslateY(y + 10);
                    } else {
                        card.setTranslateY((y + 10) + x * 20);
                        x++;
                    }
                    // Case when a move is not reversed.
                } else {
                    card.setTranslateY(y + x * 20);
                    x++;
                }

            }

        }

        manager.changeSideUpdate();
        x = 1;

    }

    /**
     * Metodi suorittaa kortin siirtämisen keskimmäisestä pinosta loppupinoon.
     *
     * @param targetIndex Kohdepinon numero.
     * @param sourceIndex Likutettavan kortin alkuperäisen pinon numero.
     * @param sourceCard Kohdepinon kohdekortin numero.
     * @param y Kohdekortin korkeus peliruudulla.
     */
    public void moveCardToUpperRightPile(int sourceIndex, int sourceCard, int targetIndex, double y) {
        Card originalCard = manager.getPiles()[sourceIndex].getPile().peek();
        manager.getPiles()[sourceIndex].getChildren().remove(sourceCard);
        upperRight.getPiles()[targetIndex - 7].getPile().push(manager.getPiles()[sourceIndex]
                .getPile().pop());
        upperRight.getPiles()[targetIndex - 7].getChildren().add(originalCard);
        originalCard.setTranslateY(y);
        manager.changeSideUpdate();

    }

    /**
     * Metodi suorittaa kortin siirtämisen klikattavasta korttipinosta.
     *
     * @param targetIndex Kohdepinon numero.
     * @param targetCard Kohdepinon kohdekortin numero.
     * @param y Kohdekortin korkeus peliruudulla.
     */
    public void moveCardFromClickedPile(int targetIndex, int targetCard, double y) {
        Card originalCard = upperLeft.getPile().firstElement();

        upperLeft.getView().getClickedCards().getChildren().remove(upperLeft.getPile().remove(0));
        if (originalCard.getFirstCard()) {
            upperLeft.getPile().peek().setFirstCardTrue();
        }
        if (originalCard.getLastCard()) {
            upperLeft.getPile().get(0).setLastCardTrue();
        }
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

    /**
     * Metodi suorittaa kortin siirtämisen loppupinoista muihin pinoihin.
     *
     * @param targetIndex Kohdepinon numero.
     * @param sourceIndex Likutettavan kortin alkuperäisen pinon numero.
     * @param targetCard Kohdepinon kohdekortin numero.
     * @param y Kohdekortin korkeus peliruudulla.
     */
    public void moveCardFromUpperRightPile(int sourceIndex, int targetIndex, int targetCard, double y) {
        Card originalCard = upperRight.getPiles()[sourceIndex - 7].getPile().peek();
        upperRight.getPiles()[sourceIndex - 7].getChildren().remove(upperRight
                .getPiles()[sourceIndex - 7].getPile().pop());
        manager.getPiles()[targetIndex].getPile().push(originalCard);
        manager.getPiles()[targetIndex].getChildren().add(originalCard);
        if (manager.getPiles()[targetIndex].getPile().get(targetCard).getSuit() == Card.Suit.NEUTRAL) {
            originalCard.setTranslateY(y);
        } else if (!manager.getPiles()[targetIndex].getPile().get(targetCard).getFaceUp()) {
            originalCard.setTranslateY(y + 10);
        } else {
            originalCard.setTranslateY(y + 20);
        }
    }

    /**
     * Metodi suorittaa kortin siirtämisen loppupinoon kaksoisklikkauksella.
     *
     * @param validateMove Korttien liikuttelun sääntöjenmukaisuudesta vastaava
     * luokka.
     * @param sourceIndex Likutettavan kortin alkuperäisen pinon numero.
     * @param card kaksoisklikattava kortti.
     */
    public void moveOnDoubleClick(ValidateMove validateMove, int sourceIndex, Card card) {
        for (int i = 0; i <= 3; i++) {
            Card topCard = upperRight.getPiles()[i].getPile().peek();
            if (validateMove.moveToUpperRightPileIsAllowed(card, topCard)) {
                int targetIndex = i;
                reverseMove.getTargetIndexes().push(targetIndex);
                if (sourceIndex == -1) {
                    Card cardToMove = upperLeft.getPile().remove(0);
                    upperLeft.getView().getClickedCards().getChildren().remove(cardToMove);
                    upperRight.getPiles()[i].getPile().add(cardToMove);
                    upperRight.getPiles()[i].getChildren().add(cardToMove);

                } else {
                    Card cardToMove = manager.getPiles()[sourceIndex].getPile().pop();
                    manager.getPiles()[sourceIndex].getChildren().remove(cardToMove);
                    upperRight.getPiles()[i].getChildren().add(cardToMove);
                    upperRight.getPiles()[i].getPile().push(cardToMove);
                    cardToMove.setTranslateY(topCard.getTranslateY());
                    manager.changeSideUpdate();

                }

                return;
            }

        }

    }

}
