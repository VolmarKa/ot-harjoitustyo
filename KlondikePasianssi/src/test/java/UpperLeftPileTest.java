
import de.saxsys.javafx.test.JfxRunner;
import java.awt.event.MouseEvent;
import java.util.Stack;
import klondikepasianssi.gui.Card;
import klondikepasianssi.logics.Deck;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import klondikepasianssi.logics.UpperLeftPile;

@RunWith(JfxRunner.class)
public class UpperLeftPileTest {

    private UpperLeftPile upperLeft;
    private Deck deck;

    public UpperLeftPileTest() {

    }

    @Before
    public void setUp() {
        this.deck = new Deck();
        this.upperLeft = new UpperLeftPile(deck.dealUpperPile());
    }

    //Gives error without this test
    @Test
    public void test() {

    }

}
