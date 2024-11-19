package com.github.achaaab.solitaire.presentation;

import com.github.achaaab.solitaire.control.ControlFactory;
import com.github.achaaab.solitaire.presentation.theme.ByronKnollTheme;

import static com.github.achaaab.solitaire.abstraction.Rank.ACE;
import static com.github.achaaab.solitaire.abstraction.Suit.SPADE;
import static com.github.achaaab.solitaire.presentation.PresentationTestUtility.show;
import static com.github.achaaab.solitaire.presentation.theme.ThemeManager.setTheme;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class CardPresentationTest {

	/**
	 * @param arguments none
	 * @since 0.0.0
	 */
	public static void main(String... arguments) {

		setTheme(ByronKnollTheme.INSTANCE);
		var card = ControlFactory.INSTANCE.newCard(ACE, SPADE);
		show(card.presentation(), "card presentation test");
	}
}
