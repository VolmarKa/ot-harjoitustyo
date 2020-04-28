package klondikepasianssi.logics;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import klondikepasianssi.gui.CardImage;
import klondikepasianssi.gui.UpperRightPile;

/**
 * Luokka vastaa oikean yläkulman pinojen toiminnallisuuksista.
 */
public class UpperRightPileManager {

    private final UpperRightPile[] piles = new UpperRightPile[4];
    private final CardImage cardImage = new CardImage();

    public UpperRightPileManager() {
        init();
    }

    private void init() {

        for (int i = 0; i <= 3; i++) {
            this.piles[i] = new UpperRightPile();
            this.piles[i].getChildren().add(createBottomCard("b", i + 1));
        }
    }

    private Button createBottomCard(String imageName, int imageNumber) {
        Button button = new Button();
        button.setGraphic(new ImageView(cardImage.createImage(imageName, imageNumber)));
        button.setStyle("-fx-background-color: transparent;");

        return button;
    }

    public UpperRightPile[] getPiles() {
        return this.piles;
    }
}
