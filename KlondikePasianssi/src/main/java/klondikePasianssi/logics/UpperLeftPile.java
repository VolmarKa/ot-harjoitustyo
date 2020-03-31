package klondikePasianssi.logics;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class UpperLeftPile extends HBox {

    private CardImage cardImage = new CardImage();
    private Button card = new Button();

    public UpperLeftPile() {

        this.card.setStyle("-fx-background-color: transparent");
        card.setGraphic(new ImageView(cardImage.backOfTheCard()));
        getChildren().add(card);

    }

}
