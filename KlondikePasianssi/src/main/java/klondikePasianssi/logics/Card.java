package klondikePasianssi.logics;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Card {

    public enum Suit {
        CLUBS, DIAMONDS, SPADES, HEARTS;
    }

     
    private final Suit suit;
    private final int rank;
    private final Button card;
    private CardImage image;

    public Card(Suit suit, String imageName, int imageNumber) {
        this.suit = suit;
        this.rank = imageNumber;
        this.card = new Button();
        this.image = new CardImage();
        this.card.setStyle("-fx-background-color: transparent");
        
        this.card.setGraphic(new ImageView(image.createImage(imageName, imageNumber)));
    }
        
    public void effect(){
        
    }
    

    public Suit getSuit() {
        return suit;
    }
    
    public int getRank(){
        return this.rank;
    }
    
    public Button getCard(){
        return this.card;
    }
    
    @Override
    public String toString(){
        return getSuit() + " " + getRank();
    }
    
    
}
