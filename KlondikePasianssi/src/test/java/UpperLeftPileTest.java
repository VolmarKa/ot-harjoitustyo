
import de.saxsys.javafx.test.JfxRunner;
import klondikepasianssi.logics.Deck;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(JfxRunner.class)
public class UpperLeftPileTest {

    
    private Deck deck;

    public UpperLeftPileTest() {

    }

    @Before
    public void setUp() {
        this.deck = new Deck();
        
    }

    //Gives error without this test
    @Test
    public void test() {

    }

}
