package klondikepasianssi.gui;

import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import klondikepasianssi.logics.Deck;
import klondikepasianssi.logics.MiddlePileManager;
import klondikepasianssi.logics.MovementLogics;
import klondikepasianssi.logics.ReverseMove;
import klondikepasianssi.logics.UpperLeftPileManager;
import klondikepasianssi.logics.UpperRightPileManager;
import klondikepasianssi.logics.ValidateMove;

public class GameView extends GridPane {
    
    private ReverseMove reverseMove;
    
    public GameView() {
        this.setBackground(new Background(new BackgroundFill(Color.GREEN,
                CornerRadii.EMPTY, new Insets(5, 5, 5, 5))));
        this.setMinSize(800, 500);
        init();

    }

    private void init() {
        ValidateMove validateMove = new ValidateMove();

        Deck deck = new Deck();
        Stack<Card> upperPileCards = deck.dealUpperPile();
        UpperLeftPileManager upperLeftManager = new UpperLeftPileManager(upperPileCards);
        MiddlePileManager middleManager = new MiddlePileManager(deck);
        UpperRightPileManager upperRightManager = new UpperRightPileManager();
        reverseMove = new ReverseMove(middleManager, upperLeftManager, upperRightManager);

        for (Card card : upperPileCards) {
            card.getCardProperties().makeMovable(middleManager, upperLeftManager, upperRightManager, validateMove, reverseMove);
        }
        for (int i = 0; i <= 6; i++) {
            for (Card card : middleManager.getPiles()[i].getPile()) {
                card.getCardProperties().makeMovable(middleManager, upperLeftManager, upperRightManager, validateMove, reverseMove);
            }
        }
        for (int i = 0; i <= 3; i++) {
            upperRightManager.getPiles()[i].getPile().get(0).getCardProperties().makeMovable(middleManager, upperLeftManager, upperRightManager, validateMove, reverseMove);
        }

        HBox UpperRightPiles = new HBox();
        UpperRightPiles.getChildren().addAll(upperRightManager.getPiles());
        this.add(upperLeftManager.getView(), 0, 0, 2, 1);
        this.add(UpperRightPiles, 2, 0, 1, 1);
        HBox middlePiles = new HBox();

        middlePiles.getChildren().addAll(middleManager.getPiles());
        this.add(middlePiles, 0, 1, 2, 1);


    }
    
    public ReverseMove getReverseMove(){
        return this.reverseMove;
    }
   

}
