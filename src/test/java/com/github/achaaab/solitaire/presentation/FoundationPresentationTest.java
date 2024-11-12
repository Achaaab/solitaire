package com.github.achaaab.solitaire.presentation;

import com.github.achaaab.solitaire.abstraction.Card;
import com.github.achaaab.solitaire.control.ControlFactory;
import com.github.achaaab.solitaire.presentation.theme.ByronKnollTheme;

import static com.github.achaaab.solitaire.abstraction.Suit.SPADE;
import static com.github.achaaab.solitaire.presentation.PresentationTestUtility.show;
import static com.github.achaaab.solitaire.presentation.theme.ThemeManager.setTheme;
import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;
import static java.util.Comparator.comparing;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class FoundationPresentationTest {

	/**
	 * @param arguments none
	 * @since 0.0.0
	 */
	public static void main(String... arguments) {

		setTheme(ByronKnollTheme.INSTANCE);

		var deck = ControlFactory.INSTANCE.newDeck();

		var spades = deck.stream().
				filter(card -> card.getSuit() == SPADE).
				sorted(comparing(Card::getRank));

		var foundation = ControlFactory.INSTANCE.newFoundation();

		show(foundation.presentation(), "foundation presentation test");

		spades.forEach(spade -> {

			try {
				sleep(500);
			} catch (InterruptedException interruptedException) {
				currentThread().interrupt();
			}

			foundation.push(spade);
		});
	}
}
