package klondikepasianssi.gui;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

public final class Card extends Button {

    public enum Suit {
        CLUBS, DIAMONDS, SPADES, HEARTS, NEUTRAL;
    }

    private final CardProperties cardproperties;
    private final Suit suit;
    private int rank;
    private final CardImage cardImage = new CardImage();
    private boolean faceUp = true;
    private final Image image;
    private boolean hasBeenClicked = false;
    private boolean lastCard = false;
    private boolean firstCard = false;
    private boolean tagged = false;

    public Card(Suit suit, String imageName, int imageNumber) {
        cardproperties = new CardProperties(this);
        this.suit = suit;
        this.rank = imageNumber;
        this.image = cardImage.createImage(imageName, imageNumber);
        cardproperties.init(this.image);
        this.setCursor(Cursor.HAND);

    }

    public Image getImage() {
        return this.image;
    }

    public Suit getSuit() {
        return suit;
    }

    public int getRank() {
        return this.rank;
    }

    public void setFaceDown() {
        this.faceUp = false;
    }

    public void setFaceUp() {
        this.faceUp = true;
    }

    public boolean getFaceUp() {
        return this.faceUp;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setHasBeenClicked() {
        this.hasBeenClicked = true;
    }

    public void setHasNotBeenClicked() {
        this.hasBeenClicked = false;
    }

    public boolean getHasBeenClicked() {
        return this.hasBeenClicked;
    }

    public void setTagged() {
        this.tagged = true;
    }

    public void setNotTagged() {
        this.tagged = false;
    }

    public boolean getTagged() {
        return this.tagged;
    }

    public void setLastCardTrue() {
        this.lastCard = true;
    }

    public void setLastCardFalse() {
        this.lastCard = false;
    }

    public boolean getLastCard() {
        return this.lastCard;
    }

    public void setFirstCardTrue() {
        this.firstCard = true;
    }

    public void setFirstCardFalse() {
        this.firstCard = false;
    }

    public boolean getFirstCard() {
        return this.firstCard;
    }

    @Override
    public String toString() {
        return getSuit() + " " + getRank();
    }

    public CardProperties getCardProperties() {
        return this.cardproperties;
    }

}
