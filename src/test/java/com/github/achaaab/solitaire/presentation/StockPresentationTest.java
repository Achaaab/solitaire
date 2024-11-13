package com.github.achaaab.solitaire.presentation;

import com.github.achaaab.solitaire.control.ControlFactory;
import com.github.achaaab.solitaire.presentation.theme.ByronKnollTheme;

import static com.github.achaaab.solitaire.abstraction.Stock.CAPACITY;
import static com.github.achaaab.solitaire.presentation.PresentationTestUtility.show;
import static com.github.achaaab.solitaire.presentation.theme.ThemeManager.setTheme;
import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;
import static java.util.Collections.shuffle;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class StockPresentationTest {

	/**
	 * @param arguments none
	 * @since 0.0.0
	 */
	public static void main(String... arguments) {

		setTheme(ByronKnollTheme.INSTANCE);

		var deck = ControlFactory.INSTANCE.newDeck();
		shuffle(deck);
		var stock = ControlFactory.INSTANCE.newStock();

		show(stock.presentation(), "Stock presentation test");

		deck.stream().limit(CAPACITY).forEach(card -> {

			try {
				sleep(50);
			} catch (InterruptedException interruptedException) {
				currentThread().interrupt();
			}

			stock.push(card);
		});
	}
}
