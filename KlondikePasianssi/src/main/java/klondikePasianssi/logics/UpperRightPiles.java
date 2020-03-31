package klondikePasianssi.logics;

import javafx.scene.image.Image;
import javafx.scene.layout.HBox;

public class UpperRightPiles extends HBox{
    
    private CardImage cardImage;
    
    public UpperRightPiles(){
        Image cPile = cardImage.createImage("cPile", 1);
        Image sPile = cardImage.createImage("sPile", 1);
        Image dPile = cardImage.createImage("dPile", 1);
        Image hPile = cardImage.createImage("hPile", 1);
        
        
    }
}
