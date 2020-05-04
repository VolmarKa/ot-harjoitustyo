package klondikepasianssi.gui;

import java.util.Stack;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import klondikepasianssi.logics.ClickedPileManager;
import klondikepasianssi.logics.Deck;
import klondikepasianssi.logics.MiddlePileManager;
import klondikepasianssi.logics.UpperLeftPileManager;
import klondikepasianssi.logics.UpperRightPileManager;

public class UserInterface extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane mainPane = new GridPane();
        mainPane.setBackground(new Background(new BackgroundFill(Color.GREEN,
                new CornerRadii(10), new Insets(5, 5, 5, 5))));
        mainPane.setBorder(new Border(new BorderStroke(Color.BURLYWOOD,
                BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(7, 7, 7, 7))));

        Deck deck = new Deck();
        ClickedPileManager clickedPileManager = new ClickedPileManager();
        Stack<Card> upperPileCards = deck.dealUpperPile();
        UpperLeftPileManager upperLeftManager = new UpperLeftPileManager(upperPileCards, clickedPileManager);
        MiddlePileManager middleManager = new MiddlePileManager(deck);
        UpperRightPileManager upperRightManager = new UpperRightPileManager();
        for (Card k : upperPileCards) {
            k.getCardProperties().makeMovable(middleManager, upperLeftManager, upperRightManager);
        }
        for (int i = 0; i <= 6; i++) {
            for (Card k : middleManager.getPiles()[i].getPile()) {
                k.getCardProperties().makeMovable(middleManager, upperLeftManager, upperRightManager);
            }
        }
        for (int i = 0; i <= 3; i++) {
            upperRightManager.getPiles()[i].getPile().get(0).getCardProperties().makeMovable(middleManager, upperLeftManager, upperRightManager);
        }

        HBox UpperRightPiles = new HBox();
        UpperRightPiles.getChildren().addAll(upperRightManager.getPiles());
        mainPane.add(upperLeftManager.getView(), 0, 0, 4, 2);
        mainPane.add(UpperRightPiles, 3, 0, 2, 1);
        mainPane.setHgap(10);
        HBox middlePiles = new HBox();

        middlePiles.getChildren().addAll(middleManager.getPiles());
        mainPane.add(middlePiles, 0, 3, 2, 1);
        //mainPane.setGridLinesVisible(true);
        Scene mainScene = new Scene(mainPane, 800, 500);

        primaryStage.setScene(mainScene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(UserInterface.class
        );
    }

}
