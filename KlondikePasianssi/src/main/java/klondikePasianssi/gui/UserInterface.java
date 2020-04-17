package klondikePasianssi.gui;

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
import klondikePasianssi.logics.Deck;
import klondikePasianssi.logics.MiddlePileManager;
import klondikePasianssi.logics.UpperLeftPile;
import klondikePasianssi.logics.UpperRightPileManager;

public class UserInterface extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane mainPane = new GridPane();
        mainPane.setBackground(new Background(new BackgroundFill(Color.GREEN,
                new CornerRadii(10), new Insets(5, 5, 5, 5))));
        mainPane.setBorder(new Border(new BorderStroke(Color.BURLYWOOD,
                BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(7, 7, 7, 7))));

        Deck deck = new Deck();
        Stack<Card> uP = deck.dealUpperPile();
        UpperLeftPile upperleft = new UpperLeftPile(uP);
        UpperRightPileManager upperRight = new UpperRightPileManager();

        HBox UpperRightPiles = new HBox();
        UpperRightPiles.getChildren().addAll(upperRight.getPiles());
        mainPane.add(upperleft, 0, 0, 4, 2);
        mainPane.add(UpperRightPiles, 3, 0, 2, 1);
        mainPane.setHgap(10);
        HBox middlePiles = new HBox();
        MiddlePileManager a = new MiddlePileManager(deck);
        middlePiles.getChildren().addAll(a.getPiles());
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
