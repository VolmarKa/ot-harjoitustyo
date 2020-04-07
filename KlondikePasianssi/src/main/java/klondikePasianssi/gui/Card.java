package klondikePasianssi.gui;

import klondikePasianssi.logics.*;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

public final class Card extends Button {

    public enum Suit {
        CLUBS, DIAMONDS, SPADES, HEARTS;
    }

    private final CardProperties cardproperties;
    private final Suit suit;
    private final int rank;
    private final CardImage cardImage = new CardImage();
    private boolean faceUp = true;
    private final Image image;

    public Card(Suit suit, String imageName, int imageNumber) {
        cardproperties = new CardProperties(this);
        this.suit = suit;
        this.rank = imageNumber;
        this.image = cardImage.createImage(imageName, imageNumber);
        cardproperties.init(this.image);
        cardproperties.effect();
        cardproperties.changeSide(this.image);
    }

    public Suit getSuit() {
        return suit;
    }

    public int getRank() {
        return this.rank;
    }

    public void setFaceUp() {
        this.faceUp = true;
    }

    public boolean getFaceUp() {
        return this.faceUp;
    }

    @Override
    public String toString() {
        return getSuit() + " " + getRank();
    }

    public CardProperties getCardProperties() {
        return this.cardproperties;
    }

}
