package klondikePasianssi.logics;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Card extends Button {

    public enum Suit {
        CLUBS, DIAMONDS, SPADES, HEARTS;
    }

    private final Suit suit;
    private final int rank;
    private final CardImage cardImage;
    private boolean faceUp;
    private final Image image;

    public Card(Suit suit, String imageName, int imageNumber) {
        this.faceUp = false;
        this.suit = suit;
        this.rank = imageNumber;
        this.cardImage = new CardImage();
        this.image = cardImage.createImage(imageName, imageNumber);
        this.setStyle("-fx-background-color: transparent");
        this.setGraphic(new ImageView(image));

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

    @Override
    public String toString() {
        return getSuit() + " " + getRank();
    }

}
