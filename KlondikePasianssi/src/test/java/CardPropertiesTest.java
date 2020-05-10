
import de.saxsys.javafx.test.JfxRunner;
import javafx.scene.Node;
import javafx.scene.image.Image;
import klondikepasianssi.gui.Card;
import klondikepasianssi.gui.Card.Suit;
import klondikepasianssi.logics.CardProperties;
import klondikepasianssi.logics.Deck;
import klondikepasianssi.logics.MiddlePileManager;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JfxRunner.class)
public class CardPropertiesTest {

    private Deck deck;

    public CardPropertiesTest() {

    }

    @Before
    public void setUp() {
        this.deck = new Deck();

    }

   

}
