package klondikePasianssi.logics;

import java.util.Stack;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class UpperRightPiles extends HBox {

    private CardImage cardImage = new CardImage();
    private Stack<Card> CLUBS, HEARTS, DIAMONDS, SPADES;
    private StackPane CLUBSpane, HEARTSpane, DIAMONDSpane, SPADESpane;

    public UpperRightPiles() {
        init();
        createPiles();

    }

    public final void createPiles() {
        
        Button clubs = createButton("cPile", 1);
        Button spades = createButton("sPile", 1);
        Button diamonds = createButton("dPile", 1);
        Button hearts = createButton("hPile", 1);

        CLUBSpane.getChildren().add(clubs);
        SPADESpane.getChildren().add(spades);
        DIAMONDSpane.getChildren().add(diamonds);
        HEARTSpane.getChildren().add(hearts);
    }

    public final void init() {
        SPADESpane = new StackPane();
        CLUBSpane = new StackPane();
        HEARTSpane = new StackPane();
        DIAMONDSpane = new StackPane();

        CLUBS = new Stack<>();
        SPADES = new Stack<>();
        DIAMONDS = new Stack<>();
        HEARTS = new Stack<>();

        this.getChildren().addAll(CLUBSpane, SPADESpane, DIAMONDSpane, HEARTSpane);
    }

    public Button createButton(String imageName, int imageNumber) {
        Button button = new Button();
        button.setGraphic(new ImageView(cardImage.createImage(imageName, imageNumber)));
        button.setStyle("-fx-background-color: transparent;");
        
        return button;
    }

}
