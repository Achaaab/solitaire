package com.github.achaaab.solitaire.control;

import com.github.achaaab.solitaire.abstraction.Card;
import com.github.achaaab.solitaire.abstraction.Stock;
import com.github.achaaab.solitaire.presentation.PresentationFactory;
import com.github.achaaab.solitaire.presentation.StockPresentation;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class StockControl extends Stock {

	private final StockPresentation presentation;

	/**
	 * @since 0.0.0
	 */
	public StockControl() {
		presentation = PresentationFactory.INSTANCE.newStock(this);
	}

	@Override
	public void push(Card card) {

		super.push(card);

		presentation.push(((CardControl) card).presentation());
	}

	@Override
	public Card pop() {

		var card = (CardControl) super.pop();

		presentation.pop();

		return card;
	}

	/**
	 * @return presentation of this stock
	 * @since 0.0.0
	 */
	public StockPresentation presentation() {
		return presentation;
	}
}
