package klondikePasianssi.gui;

import klondikePasianssi.logics.Deck;
import klondikePasianssi.logics.Card;
import javafx.application.Application;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import klondikePasianssi.logics.PileImages;

public class UserInterface extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        GridPane mainPane = new GridPane();
        mainPane.setBackground(new Background(new BackgroundFill(Color.GREEN, new CornerRadii(10), new Insets(5, 5, 5, 5))));
        mainPane.setBorder(new Border(new BorderStroke(Color.BURLYWOOD,
                BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(7, 7, 7, 7))));

        PileImages p = new PileImages();
        HBox pileImage = p.createPileImage();
        mainPane.add(pileImage, 1, 0, 1, 1);
        mainPane.setGridLinesVisible(true);
        Card kortti = new Card(Card.Suit.CLUBS,"c",1);
        Card kortti1 = new Card(Card.Suit.CLUBS,"c",2);
        mainPane.add(kortti.getCard(), 2, 2, 1, 1);
        mainPane.add(kortti1.getCard(), 1, 2, 1, 1);
        
        
        
        
        
        
        
        
        
        

        Scene mainScene = new Scene(mainPane, 1000, 600);
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(UserInterface.class);
    }
}
