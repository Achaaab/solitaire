package com.github.achaaab.solitaire.presentation;

import com.github.achaaab.solitaire.control.ControlFactory;
import com.github.achaaab.solitaire.presentation.theme.ByronKnollTheme;

import static com.github.achaaab.solitaire.abstraction.Talon.CAPACITY;
import static com.github.achaaab.solitaire.presentation.PresentationTestUtility.show;
import static com.github.achaaab.solitaire.presentation.theme.ThemeManager.setTheme;
import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;
import static java.util.Collections.shuffle;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class TalonPresentationTest {

	/**
	 * @param arguments none
	 */
	public static void main(String... arguments) {

		setTheme(ByronKnollTheme.INSTANCE);

		var deck = ControlFactory.INSTANCE.newDeck();
		shuffle(deck);
		var talon = ControlFactory.INSTANCE.newTalon();

		show(talon.presentation(), "talon presentation test");

		deck.stream().limit(CAPACITY).forEach(card -> {

			try {
				sleep(50);
			} catch (InterruptedException interruptedException) {
				currentThread().interrupt();
			}

			talon.faceDownStack().push(card);
		});
	}
}
