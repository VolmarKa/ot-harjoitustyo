
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
public class ReverseMoveTest {

    private ReverseMove reverseMove;
    private MiddlePileManager middlePileManager;
    private UpperLeftPileManager upperLeftPileManager;
    private UpperRightPileManager upperRightPileManager;
    private MovementLogics movementLogics;
    private ValidateMove validateMove;

    public ReverseMoveTest() {

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
    public void revereseMoveFromUpperLeftPileDoesNotAffectPileSize() {
        reverseMove.getSourceCards().push(-1);
        reverseMove.getSourceIndexes().push(-1);
        upperLeftPileManager.getPileClicked().push(2);
        Random rdm = new Random();
        int target = rdm.nextInt(10);
        if (target <= 6) {
            int targetCard = middlePileManager.getPiles()[target].getPile().size() - 2;
            movementLogics.moveCardFromClickedPile(target, targetCard, 0);
            reverseMove.getTargetIndexes().push(target);
            reverseMove.getTargetCards().push(targetCard);

        } else {
            int targetCard = 0;
            movementLogics.moveCardFromClickedPile(target, targetCard, 0);
            reverseMove.getTargetIndexes().push(target);
            reverseMove.getTargetCards().push(targetCard);

        }
        reverseMove.reverseMove();
        assertEquals(24, upperLeftPileManager.getPile().size());

    }

    @Test
    public void revereseMoveFromUpperLeftPileKeepsTheOriginalCardInThePile() {
        reverseMove.getSourceCards().push(-1);
        reverseMove.getSourceIndexes().push(-1);
        Card originalCard = upperLeftPileManager.getPile().peek();
        upperLeftPileManager.getPileClicked().push(2);
        Random rdm = new Random();
        int target = rdm.nextInt(10);
        if (target <= 6) {
            int targetCard = middlePileManager.getPiles()[target].getPile().size() - 2;
            movementLogics.moveCardFromClickedPile(target, targetCard, 0);
            reverseMove.getTargetIndexes().push(target);
            reverseMove.getTargetCards().push(targetCard);

        } else {
            int targetCard = 0;
            movementLogics.moveCardFromClickedPile(target, targetCard, 0);
            reverseMove.getTargetIndexes().push(target);
            reverseMove.getTargetCards().push(targetCard);

        }
        reverseMove.reverseMove();
        assertEquals(true, upperLeftPileManager.getPile().contains(originalCard));
    }

    @Test
    public void reversePileClickKeepsTheOriginalPileSize() {
        upperLeftPileManager.pileClicked();
        reverseMove.reverseMove();
        assertEquals(0, upperLeftPileManager.getView().getClickedCards().getChildren().size());
    }

    @Test
    public void reversePileClickDoesntChangeSizeWhenClickedTooManyTimes() {
        upperLeftPileManager.pileClicked();
        upperLeftPileManager.pileClicked();
        upperLeftPileManager.pileClicked();
        reverseMove.reverseMove();
        reverseMove.reverseMove();
        reverseMove.reverseMove();
        reverseMove.reverseMove();

        assertEquals(0, upperLeftPileManager.getView().getClickedCards().getChildren().size());
    }

    @Test
    public void reversePileClickWorksWhenPileIsRecycled() {
        for (int i = 0; i <= 24; i++) {
            upperLeftPileManager.pileClicked();
        }
        reverseMove.reverseMove();

        assertEquals(24, upperLeftPileManager.getView().getClickedCards().getChildren().size());
    }

    @Test
    public void reverseDoubleClickKeepsTheRightSize() {

        for (int i = 23; i >= 0; i--) {
            if (upperLeftPileManager.getPile().get(i).getRank() == 1) {
                reverseMove.getSourceIndexes().push(-1);
                movementLogics.moveOnDoubleClick(validateMove, -1, upperLeftPileManager.getPile().get(i));
                reverseMove.reverseMove();
            }
        }
        assertEquals(24, upperLeftPileManager.getPile().size());
    }

    @Test
    public void reverseDoubleClickKeepsTheRightSize2() {
        for (int i = 0; i <= 6; i++) {
            if (middlePileManager.getPiles()[i].getPile().peek().getRank() == 1) {
                reverseMove.getSourceIndexes().push(i);
                movementLogics.moveOnDoubleClick(validateMove, i, upperLeftPileManager.getPile().get(i));
                reverseMove.reverseMove();
            }
        }
        assertEquals(24, upperLeftPileManager.getPile().size());
    }

    @Test
    public void reverseMoveInMiddlePileSizeStaySame() {
        int source = 4;
        int target = 6;
        int sourceCard = 3;
        int targetCard = 8;
        double y = middlePileManager.getPiles()[6].getPile().peek().getTranslateY();
        reverseMove.getSourceCards().push(sourceCard);
        reverseMove.getTargetCards().push(targetCard);
        reverseMove.getTargetIndexes().push(target);
        reverseMove.getSourceIndexes().push(source);
        upperLeftPileManager.getPileClicked().push(2);
        movementLogics.moveCardInMiddlePile(target, source, targetCard, sourceCard, y, 1);
        reverseMove.reverseMove();

        assertEquals(5, middlePileManager.getPiles()[source].getPile().size());
        assertEquals(5, middlePileManager.getPiles()[source].getChildren().size());
    }

}
