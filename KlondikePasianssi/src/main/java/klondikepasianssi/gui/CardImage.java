package klondikepasianssi.gui;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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

    public Button createButton(String imageName, int imageNumber) {
        Button button = new Button();
        button.setGraphic(new ImageView(createImage(imageName, imageNumber)));
        button.setStyle("-fx-background-color: transparent;");

        return button;
    }

}
