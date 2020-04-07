package klondikePasianssi.gui;

import javafx.scene.image.Image;

public class CardImage {

    public CardImage() {

    }

    public Image createImage(String imageName, int imageNumber) {
        Image image = new Image("/" + imageNumber + imageName + ".gif");

        return image;
    }

    public Image backOfTheCard() {
        return createImage("back", 1);
    }

    public Image bottomImage() {
        return createImage("bottom", 1);
    }

}
