package klondikePasianssi.logics;

import java.util.Stack;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import klondikePasianssi.gui.Card;
import klondikePasianssi.gui.CardImage;
import klondikePasianssi.gui.UpperRightPile;

public class UpperRightPileManager {

    private final CardImage cardImage = new CardImage();
    private Stack<Card> pile = new Stack<>();
    private UpperRightPile upper;

    public UpperRightPileManager(UpperRightPile upper, String imageName, int imageNumber) {
        this.upper = upper;
        this.upper.getChildren().add(createBottomCard(imageName, imageNumber));

    }

    public final Button createBottomCard(String imageName, int imageNumber) {
        Button button = new Button();
        button.setGraphic(new ImageView(cardImage.createImage(imageName, imageNumber)));
        button.setStyle("-fx-background-color: transparent;");

        return button;
    }
}
