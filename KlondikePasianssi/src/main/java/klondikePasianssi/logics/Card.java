package klondikePasianssi.logics;

import javafx.scene.image.Image;

public class Card {

    public enum Suit {
        CLUBS, DIAMONDS, SPADES, HEARTS;
    }

    public enum Rank {
        
        ACE(1), DEUCE(2), THREE(3), FOUR(4), 
        FIVE(5), SIX(6), SEVEN(7), EIGHT(8), 
        NINE(9), TEN(10), JACK(11), QUEEN(12), 
        KING(13);
        
        private final int rankValue;
            
        private Rank(int rankValue){
            this.rankValue=rankValue;
        }
        
        public int getRankValue(){
            return this.rankValue;
        }
    }
     
    private final Suit suit;
    private final Rank rank;
    private Image cardImage;

    public Card(Suit suit, Rank rank, Image image) {
        this.suit = suit;
        this.rank = rank;
        this.cardImage = image;
    }

    public Suit getSuit() {
        return suit;
    }
    
    public Rank getRank(){
        return this.rank;
    }
    
    
}
