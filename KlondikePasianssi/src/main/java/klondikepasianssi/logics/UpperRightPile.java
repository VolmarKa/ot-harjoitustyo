
package klondikepasianssi.logics;

import java.util.Stack;
import klondikepasianssi.gui.Card;
import klondikepasianssi.gui.UpperRightPileView;


public class UpperRightPile extends Stack<Card> {
    
    public UpperRightPile(UpperRightPileView upperRight){
        
    }
    
    public void print(){
        for(Card k: this){
            System.out.println(k);
        }
    }
    
}
