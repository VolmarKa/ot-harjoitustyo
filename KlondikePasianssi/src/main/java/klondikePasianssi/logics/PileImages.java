
package klondikePasianssi.logics;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;


public class PileImages{
    
    private CardImage cardImage;
    
    public PileImages(){
        cardImage = new CardImage();
    }
    
    public HBox createPileImage(){
        Button card = new Button();      
        card.setStyle("-fx-background-color: transparent");        
        card.setGraphic(new ImageView(cardImage.backOfTheCard()));
                    
        HBox pileImage = new HBox();        
        pileImage.getChildren().add(card);
           
        return pileImage;
    }
    
    public HBox createCompletePileImage(){
        Button cPile = new Button();
        cPile.setStyle("-fx-background-color: transparent");
        cPile.setGraphic(new ImageView(cardImage.createImage("cPile", 1)));
        
        HBox completePileImage = new HBox();
        completePileImage.getChildren().add(cPile);
        
        return completePileImage;
    }
}
