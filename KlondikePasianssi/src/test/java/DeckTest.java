
import de.saxsys.javafx.test.JfxRunner;
import klondikePasianssi.gui.Card;
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

    @Test
    public void dealUpperPileHasTheRightSize() {
        assertEquals(24, this.Deck.dealUpperPile().size());
    }
}
