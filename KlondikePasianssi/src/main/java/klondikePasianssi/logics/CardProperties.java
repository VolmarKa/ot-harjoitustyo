package klondikePasianssi.logics;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import klondikePasianssi.gui.Card;

public class CardProperties {

    protected final String idleButton = "-fx-background-color: transparent; "
            + "-fx-padding: 5, 5, 5, 5;";
    protected final String enteredButton = "-fx-background-color: transparent; "
            + "-fx-padding: 6, 4, 4, 6;";
    private Card card;

    public CardProperties(Card card) {
        this.card = card;
    }

    public void init(Image image) {
        card.setStyle(idleButton);
        card.setGraphic(new ImageView(image));
    }

    public void changeSide(Image image) {
        ImageView ima2 = new ImageView(image);

        if (!card.getFaceUp()) {
            card.setOnMouseClicked(event -> {
                card.setGraphic(ima2);
                card.setFaceUp();
            });
        }
    }

    public void effect() {
        card.setOnMouseEntered(event -> {
            card.setStyle(enteredButton);
        });

        card.setOnMouseExited(event -> {
            card.setStyle(idleButton);
        });
    }

}
