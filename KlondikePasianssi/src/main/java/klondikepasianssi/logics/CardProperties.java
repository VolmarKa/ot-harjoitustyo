package klondikepasianssi.logics;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import klondikepasianssi.gui.Card;
import klondikepasianssi.gui.Card.Suit;
import klondikepasianssi.gui.CardImage;

/**
 * Luokka vastaa korttien ominaisuuksiin liittyvistä toiminnoista.
 */
public class CardProperties {

    private final String idleButton = "-fx-background-color: transparent; "
            + "-fx-padding: 5, 5, 5, 5;";
    private final String enteredButton = "-fx-background-color: transparent; "
            + "-    fx-padding: 6, 4, 4, 6;";
    private final Card card;
    private final Image backImage;
    private final CardImage cardImage = new CardImage();
    private Movement mv;

    public CardProperties(Card card) {
        this.card = card;
        this.backImage = cardImage.backOfTheCard();

    }

    /**
     * Luo kortille kuvan.
     *
     * @param image kortin kuva
     */
    public void init(Image image) {
        card.setStyle(idleButton);
        card.setGraphic(new ImageView(image));
    }

    /**
     * Metodi vaihtaa kortin kuvapuolen alas.
     *
     */
    public void setFaceDown() {
        this.card.setGraphic(new ImageView(this.backImage));
        this.card.setFaceDown();
    }

    /**
     * Metodi vaihtaa kortin kuvapuolen ylös.
     *
     */
    public void setFaceUp() {
        this.card.setGraphic(new ImageView(this.card.getImage()));
        this.card.setFaceUp();
    }

    /**
     * Metodi antaa kortille toiminnon vaihtaa puolta klikkaamalla sitä.
     *
     */
    public void changeSide() {
        ImageView ima2 = new ImageView(card.getImage());
        if (!card.getFaceUp()) {
            card.setOnMouseClicked(event -> {
                card.setGraphic(ima2);
                card.setFaceUp();
            });
        }
    }

    /**
     * Metodi kertoo, mikä merkkijonoa vastaava maa on.
     *
     * @param suitAsString Käyttäjän antama syöte.
     *
     * @return merkkijonoa vastaan maan.
     */
    public Suit checkSuit(String suitAsString) {
        if (suitAsString.equals("CLUBS")) {
            return Suit.CLUBS;
        }

        if (suitAsString.equals("SPADES")) {
            return Suit.SPADES;
        }

        if (suitAsString.equals("DIAMONDS")) {
            return Suit.DIAMONDS;

        } else {
            return Suit.HEARTS;
        }
    }

    /**
     * Metodi tekee kortista liikuteltavan.
     *
     * @param manager Keskimmäisten pinojen toiminnasta vastaava luokka.
     * @param upper Vasemassa yläkulmassa olevan pinon toiminnasta vastaava
     * luokka.
     */
    public void makeMovable(MiddlePileManager manager, UpperLeftPile upper) {
        this.mv = new Movement(this.card, manager, upper);
    }

}
