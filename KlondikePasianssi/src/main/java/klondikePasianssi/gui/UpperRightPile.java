
package klondikePasianssi.gui;

import javafx.scene.layout.StackPane;
import klondikePasianssi.logics.UpperRightPileManager;

public class UpperRightPile extends StackPane{
    
    private UpperRightPileManager manager;
    
    public UpperRightPile(String image, int number){
        this.manager = new UpperRightPileManager(this, image, number);
        
    }
}
                