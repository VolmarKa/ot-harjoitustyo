package klondikepasianssi.logics;

import java.util.Stack;
import klondikepasianssi.gui.Card;
import klondikepasianssi.gui.MiddlePileView;

/**
 * Luokka sisältää kunkin keskimmäisen pinon kortit.
 */
public class MiddlePile extends Stack<Card> {

    private MiddlePileView pile;

    public MiddlePile(MiddlePileView pile) {
        this.pile = pile;

    }

}
