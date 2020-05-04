package klondikepasianssi.logics;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import klondikepasianssi.gui.Card;
import klondikepasianssi.gui.CardImage;

/**
 * Luokka vastaa korttien ominaisuuksiin liittyvistä toiminnoista.
 */
public class CardProperties {

    private final String idleButton = "-fx-background-color: transparent; "
            + "-fx-padding: 5, 5, 5, 5;";
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
                makeClickable();
            });
        }
    }
    
    private void makeClickable(){
        getMovement().makeClickableAfterSideChange();
    }

    /**
     * Metodi tekee kortista liikuteltavan.
     *
     * @param manager Keskimmäisten pinojen toiminnasta vastaava luokka.
     * @param upperLeftManager Vasemassa yläkulmassa olevan pinon toiminnasta
     * vastaava luokka.
     * @param upperRightManager Oikeassa yläkulmassa olevien pinojen toiminnasta
     * vastaava luokka
     */
    public void makeMovable(MiddlePileManager manager, UpperLeftPileManager upperLeftManager, UpperRightPileManager upperRightManager) {
        this.mv = new Movement(this.card, manager, upperLeftManager, upperRightManager);
    }
    
    public Movement getMovement(){
        return this.mv;
    }

}
