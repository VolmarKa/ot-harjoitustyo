package klondikepasianssi.gui;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import klondikepasianssi.logics.UpperLeftPileManager;

public class UpperLeftPileView extends HBox {
    
    private StackPane clickedCards = new StackPane();
    private final Button card = new Button();
    private final CardImage cardImage = new CardImage();
    private final ImageView ima1 = new ImageView(cardImage.backOfTheCard());
    private final ImageView ima2 = new ImageView(cardImage.bottomImage());
    private final UpperLeftPileManager manager;

    public UpperLeftPileView(UpperLeftPileManager manager) {
        this.manager = manager;
        this.getChildren().add(card);
        card.setStyle("-fx-background-color: transparent;");
        card.setGraphic(ima1);
        card.setCursor(Cursor.HAND);
        this.getChildren().add(clickedCards);

        pileClicked();

    }

    private void pileClicked() {
        this.card.setOnMouseClicked(event -> {
            manager.pileClicked();

        });
    }

    public void setBottomImage() {
        card.setGraphic(ima2);
    }

    public void setBackImage() {
        card.setGraphic(ima1);
    }
    
    public StackPane getClickedCards(){
        return this.clickedCards;
    }
}
