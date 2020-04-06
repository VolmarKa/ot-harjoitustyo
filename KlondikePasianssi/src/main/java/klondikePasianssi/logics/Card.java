package klondikePasianssi.logics;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public final class Card extends Button {

    public enum Suit {
        CLUBS, DIAMONDS, SPADES, HEARTS;
    }

    final String IDLE_BUTTON_STYLE = "-fx-background-color: transparent; "
            + "-fx-padding: 5, 5, 5, 5;";
    final String ENTERED_BUTTON_STYLE = "-fx-background-color: transparent; "
            + "-fx-padding: 6, 4, 4, 6;";
    private final Suit suit;
    private final int rank;
    private final CardImage cardImage;
    private boolean faceUp;
    private final Image image;

    public Card(Suit suit, String imageName, int imageNumber) {
        this.faceUp = true;
        this.suit = suit;
        this.rank = imageNumber;
        this.cardImage = new CardImage();
        this.image = cardImage.createImage(imageName, imageNumber);
        this.setStyle(IDLE_BUTTON_STYLE);
        this.setGraphic(new ImageView(image));
        effect();
        changeSide();

    }

    public void changeSide() {
        ImageView ima2 = new ImageView(this.image);

        if (!faceUp) {
            this.setOnMouseClicked(event -> {
                this.setGraphic(ima2);
                this.faceUp = true;
            });
        }
    }

    public void effect() {
        this.setOnMouseEntered(event -> {
            this.setStyle(ENTERED_BUTTON_STYLE);
        });

        this.setOnMouseExited(event -> {
            this.setStyle(IDLE_BUTTON_STYLE);
        });
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
