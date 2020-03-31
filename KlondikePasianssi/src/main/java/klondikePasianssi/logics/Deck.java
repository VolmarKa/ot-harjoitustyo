package klondikePasianssi.logics;

import java.util.Arrays;
import java.util.Collections;
import javafx.scene.image.Image;

public class Deck {

    private int b;
    private int index;
    private Card[] deck;

    public Deck() {
        this.deck = new Card[52];

    }

    public void shuffle() {
        Collections.shuffle(Arrays.asList(this.deck));
    }

}
