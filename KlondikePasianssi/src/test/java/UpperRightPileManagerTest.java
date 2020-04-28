
import klondikepasianssi.logics.UpperRightPileManager;
import de.saxsys.javafx.test.JfxRunner;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JfxRunner.class)
public class UpperRightPileManagerTest {

    private UpperRightPileManager manager;

    public UpperRightPileManagerTest() {
    }

    @Before
    public void setUp() {
        this.manager = new UpperRightPileManager();
    }

    @Test
    public void pilesAreEmpty() {
        for (int i = 0; i <= 3; i++) {
            assertEquals(1, manager.getPiles()[i].getChildren().size());
        }

    }

}
