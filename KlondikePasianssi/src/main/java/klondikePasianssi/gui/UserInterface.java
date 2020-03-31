package klondikePasianssi.gui;

import klondikePasianssi.logics.Card;
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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import klondikePasianssi.logics.Deck;
import klondikePasianssi.logics.UpperLeftPile;

public class UserInterface extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        GridPane mainPane = new GridPane();
        mainPane.setBackground(new Background(new BackgroundFill(Color.GREEN,
                new CornerRadii(10), new Insets(5, 5, 5, 5))));
        mainPane.setBorder(new Border(new BorderStroke(Color.BURLYWOOD,
                BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(7, 7, 7, 7))));

        mainPane.add(new UpperLeftPile(), 1, 0, 1, 1);
        mainPane.setGridLinesVisible(true);

        Deck deck = new Deck();
        for (Card k : deck.getDeck()) {
            System.out.println(k.toString());
        }

        deck.shuffle();

        Scene mainScene = new Scene(mainPane, 1000, 600);
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(UserInterface.class);
    }
}
