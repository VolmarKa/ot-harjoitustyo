
import de.saxsys.javafx.test.JfxRunner;
import java.util.Stack;
import klondikepasianssi.gui.Card;
import klondikepasianssi.logics.Deck;
import klondikepasianssi.logics.MiddlePileManager;
import klondikepasianssi.logics.Movement;
import klondikepasianssi.logics.MovementLogics;
import klondikepasianssi.logics.ReverseMove;
import klondikepasianssi.logics.UpperLeftPileManager;
import klondikepasianssi.logics.UpperRightPileManager;
import klondikepasianssi.logics.ValidateMove;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JfxRunner.class)
public class ReverseMoveTest {

    private ReverseMove reverseMove;
    private MiddlePileManager middlePileManager;
    private UpperLeftPileManager upperLeftPileManager;
    private UpperRightPileManager upperRightPileManager;
    private Movement movement;
    private MovementLogics movementLogics;

    public ReverseMoveTest() {

    }

    @Before
    public void setUp() {
        ValidateMove validateMove = new ValidateMove();
        Deck deck = new Deck();
        Stack<Card> upperPileCards = deck.dealUpperPile();
        UpperLeftPileManager upperLeftManager = new UpperLeftPileManager(upperPileCards);
        MiddlePileManager middleManager = new MiddlePileManager(deck);
        upperRightPileManager = new UpperRightPileManager();
        reverseMove = new ReverseMove(middleManager, upperLeftManager, upperRightPileManager);
        movementLogics = new MovementLogics(middleManager, upperLeftManager, upperRightPileManager, reverseMove);
        for (Card card : upperPileCards) {
            card.getCardProperties().makeMovable(middleManager, upperLeftManager, upperRightPileManager, validateMove, reverseMove, movementLogics);
        }
        for (int i = 0; i <= 6; i++) {
            for (Card card : middleManager.getPiles()[i].getPile()) {
                card.getCardProperties().makeMovable(middleManager, upperLeftManager, upperRightPileManager, validateMove, reverseMove, movementLogics);
            }
        }
        for (int i = 0; i <= 3; i++) {
            upperRightPileManager.getPiles()[i].getPile().get(0).getCardProperties().makeMovable(middleManager, upperLeftManager, upperRightPileManager, validateMove, reverseMove, movementLogics);
        }
    }


@Test
    public void revereseMoveFromUpperLeftPileWorks(){
        
    }

}
