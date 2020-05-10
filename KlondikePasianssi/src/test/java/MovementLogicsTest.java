
import de.saxsys.javafx.test.JfxRunner;
import java.util.Random;
import java.util.Stack;
import klondikepasianssi.gui.Card;
import klondikepasianssi.logics.Deck;
import klondikepasianssi.gui.MiddlePileManager;
import klondikepasianssi.logics.Movement;
import klondikepasianssi.logics.MovementLogics;
import klondikepasianssi.logics.ReverseMove;
import klondikepasianssi.logics.UpperLeftPileManager;
import klondikepasianssi.gui.UpperRightPileManager;
import klondikepasianssi.logics.ValidateMove;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JfxRunner.class)
public class MovementLogicsTest {
    
    private ReverseMove reverseMove;
    private MiddlePileManager middlePileManager;
    private UpperLeftPileManager upperLeftPileManager;
    private UpperRightPileManager upperRightPileManager;
    private MovementLogics movementLogics;
    private ValidateMove validateMove;
    
    public MovementLogicsTest() {
    }
    
    @Before
    public void setUp() {
        validateMove = new ValidateMove();
        Deck deck = new Deck();
        Stack<Card> upperPileCards = deck.dealUpperPile();
        upperLeftPileManager = new UpperLeftPileManager(upperPileCards);
        middlePileManager = new MiddlePileManager(deck);
        upperRightPileManager = new UpperRightPileManager();
        reverseMove = new ReverseMove(middlePileManager, upperLeftPileManager, upperRightPileManager);
        movementLogics = new MovementLogics(middlePileManager, upperLeftPileManager, upperRightPileManager, reverseMove);
        for (Card card : upperPileCards) {
            card.getCardProperties().makeMovable(middlePileManager, upperLeftPileManager, upperRightPileManager, validateMove, reverseMove, movementLogics);
        }
        for (int i = 0; i <= 6; i++) {
            for (Card card : middlePileManager.getPiles()[i].getPile()) {
                card.getCardProperties().makeMovable(middlePileManager, upperLeftPileManager, upperRightPileManager, validateMove, reverseMove, movementLogics);
            }
        }
        for (int i = 0; i <= 3; i++) {
            upperRightPileManager.getPiles()[i].getPile().get(0).getCardProperties().makeMovable(middlePileManager, upperLeftPileManager, upperRightPileManager, validateMove, reverseMove, movementLogics);
        }
    }
    
    @Test
    public void moveCardToUpperRightPileChangesPileSizes() {
        int source = 5;
        int target = 2 + 7;
        int sourceCard = 6;
        movementLogics.moveCardToUpperRightPile(source, sourceCard, target, 0);
        assertEquals(false, middlePileManager.getPiles()[source].getPile().size() == 7);
        assertEquals(true, upperRightPileManager.getPiles()[target - 7].getPile().size() == 2);
    }
    
    @Test
    public void moveCardToUpperRightPileMovesCards() {
        int source = 5;
        int target = 2 + 7;
        int sourceCard = 6;
        Card originalCard = middlePileManager.getPiles()[source].getPile().peek();
        movementLogics.moveCardToUpperRightPile(source, sourceCard, target, 0);
        assertEquals(true, upperRightPileManager.getPiles()[target - 7].getPile().contains(originalCard));
        assertEquals(false, middlePileManager.getPiles()[source].getPile().contains(originalCard));
        
    }
    
    @Test
    public void moveCardFromUpperRightPileChangesPileSizes() {
        Random rdm = new Random();
        int source2 = 1;
        int source = rdm.nextInt(3) + 7;
        int target = 2;
        int targetCard = 3;
        movementLogics.moveCardToUpperRightPile(source2, 2, source, 0);
        movementLogics.moveCardFromUpperRightPile(source, target, targetCard, 0);
        assertEquals(false, upperRightPileManager.getPiles()[source - 7].getPile().size() == 2);
        assertEquals(true, middlePileManager.getPiles()[target].getPile().size() == 5);
    }
    
    @Test
    public void moveCardFromUpperRightPileMovesCards() {
        Random rdm = new Random();
        int source2 = 1;
        int source = rdm.nextInt(3) + 7;
        int target = 2;
        int targetCard = 3;
        
        movementLogics.moveCardToUpperRightPile(source2, 2, source, 0);
        Card originalCard = upperRightPileManager.getPiles()[source - 7].getPile().peek();
        movementLogics.moveCardFromUpperRightPile(source, target, targetCard, 0);
        assertEquals(false, upperRightPileManager.getPiles()[source - 7].getPile().contains(originalCard));
        assertEquals(true, middlePileManager.getPiles()[target].getPile().contains(originalCard));
    }
    
}
