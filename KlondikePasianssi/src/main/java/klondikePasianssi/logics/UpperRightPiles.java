package klondikePasianssi.logics;

import java.util.Stack;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import klondikePasianssi.gui.Card;
import klondikePasianssi.gui.CardImage;

public class UpperRightPiles extends HBox {

    private final CardImage cardImage = new CardImage();
    private Stack<Card> clubs, hearts, diamonds, spades;
    private StackPane clubsPane, heartsPane, diamondsPane, spadesPane;

    public UpperRightPiles() {
        init();
        createPiles();
    }

    public final void createPiles() {

        Button clubs = createButton("cPile", 1);
        Button spades = createButton("sPile", 1);
        Button diamonds = createButton("dPile", 1);
        Button hearts = createButton("hPile", 1);

        clubsPane.getChildren().add(clubs);
        spadesPane.getChildren().add(spades);
        diamondsPane.getChildren().add(diamonds);
        heartsPane.getChildren().add(hearts);
    }

    public final void init() {

        spadesPane = new StackPane();
        clubsPane = new StackPane();
        heartsPane = new StackPane();
        diamondsPane = new StackPane();

        clubs = new Stack<>();
        spades = new Stack<>();
        diamonds = new Stack<>();
        hearts = new Stack<>();

        this.getChildren().addAll(clubsPane, spadesPane, diamondsPane, heartsPane);
    }

    public Button createButton(String imageName, int imageNumber) {
        Button button = new Button();
        button.setGraphic(new ImageView(cardImage.createImage(imageName, imageNumber)));
        button.setStyle("-fx-background-color: transparent;");

        return button;
    }

}
