
import de.saxsys.javafx.test.JfxRunner;
import klondikepasianssi.logics.Deck;
import klondikepasianssi.gui.MiddlePileManager;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JfxRunner.class)
public class MiddlePileManagerTest {

    private Deck deck;
    private MiddlePileManager manager;

    public MiddlePileManagerTest() {
    }

    @Before
    public void setUp() {
        this.deck = new Deck();
        deck.dealUpperPile();
        this.manager = new MiddlePileManager(deck);
    }

    @Test
    public void dealCardsWorks() {
        for (int i = 0; i <= 6; i++) {
            assertEquals(i + 2, this.manager.getPiles()[i].getPile().size());

        }
    }

    @Test
    public void dealCardsWorks2() {
        assertEquals(0, this.deck.getDeck().size());

    }

    @Test
    public void dealCardsWorks3() {
        for (int i = 0; i <= 6; i++) {
            assertEquals(i + 2, this.manager.getPiles()[i].getPile().size());
        }

    }

    @Test
    public void changeSideUpdateWorks() {
        this.manager.changeSideUpdate();
        for (int i = 0; i <= 6; i++) {
            assertEquals(true, this.manager.getPiles()[i].getPile().peek().getFaceUp());
        }
    }

}
