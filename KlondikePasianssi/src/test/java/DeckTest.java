
import de.saxsys.javafx.test.JfxRunner;
import klondikePasianssi.logics.Card;
import klondikePasianssi.logics.Deck;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

@RunWith(JfxRunner.class)
public class DeckTest {

    private Deck Deck;

    public DeckTest() {
    }

    @Before
    public void setUp() {
        this.Deck = new Deck();
    }

    @Test
    public void createdDeckHasTheRightSize() {
        assertEquals(52, this.Deck.getDeck().size());

    }
    
    /* Test could possibly fail because shuffle could very well shuffle the same
    card to the same exact spot. It's quite hard to test the method.
    
    @Test
    public void shuffleWorksAsIntended() {
        Card card = Deck.getDeck().elementAt(1);
        Deck.shuffle();
        assertNotEquals(card.toString(), Deck.getDeck().elementAt(1).toString());

    }
    */
}
