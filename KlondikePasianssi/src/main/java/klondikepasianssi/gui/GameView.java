package klondikepasianssi.gui;

import java.util.Stack;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import klondikepasianssi.logics.Deck;
import klondikepasianssi.logics.MovementLogics;
import klondikepasianssi.logics.ReverseMove;
import klondikepasianssi.logics.UpperLeftPileManager;
import klondikepasianssi.logics.ValidateMove;

public class GameView extends GridPane {

    private ReverseMove reverseMove;
    private MovementLogics movementLogics;
    private UpperRightPileManager upperRightManager;

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
        upperRightManager = new UpperRightPileManager();
        reverseMove = new ReverseMove(middleManager, upperLeftManager, upperRightManager);
        movementLogics = new MovementLogics(middleManager, upperLeftManager, upperRightManager, reverseMove);
        for (Card card : upperPileCards) {
            card.getCardProperties().makeMovable(middleManager, upperLeftManager, upperRightManager, validateMove, reverseMove, movementLogics);
        }
        for (int i = 0; i <= 6; i++) {
            for (Card card : middleManager.getPiles()[i].getPile()) {
                card.getCardProperties().makeMovable(middleManager, upperLeftManager, upperRightManager, validateMove, reverseMove, movementLogics);
            }
        }
        for (int i = 0; i <= 3; i++) {
            upperRightManager.getPiles()[i].getPile().get(0).getCardProperties().makeMovable(middleManager, upperLeftManager, upperRightManager, validateMove, reverseMove, movementLogics);
        }

        HBox UpperRightPiles = new HBox();
        UpperRightPiles.getChildren().addAll(upperRightManager.getPiles());
        this.add(upperLeftManager.getView(), 0, 0, 2, 1);
        this.add(UpperRightPiles, 2, 0, 1, 1);
        HBox middlePiles = new HBox();

        middlePiles.getChildren().addAll(middleManager.getPiles());
        this.add(middlePiles, 0, 1, 2, 1);

    }

    public ReverseMove getReverseMove() {
        return this.reverseMove;
    }
    
    public MovementLogics getMovementLogics(){
        return this.movementLogics;
    }
    
    public UpperRightPileManager getUpperRightManager(){
        return this.upperRightManager;
    }

}
