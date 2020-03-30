
package klondikePasianssi.gui;

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
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class UserInterface extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        
        GridPane mainPane = new GridPane();
        mainPane.setBackground(new Background(new BackgroundFill(Color.GREEN, new CornerRadii(10), new Insets(5, 5, 5, 5))));
        mainPane.setBorder(new Border(new BorderStroke(Color.BURLYWOOD, 
            BorderStrokeStyle.SOLID, new CornerRadii (10), new BorderWidths(7, 7, 7, 7))));
       
        ImageView im = new ImageView(new Image("01c.gif"));
        Button nappi = new Button();
        nappi.setGraphic(im);
        
        mainPane.add(nappi, 1, 1);
        
        
        Scene mainScene = new Scene(mainPane, 1200, 800);      
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }
    
    
    public static void main(String[] args){
        launch(UserInterface.class);
    }
}
