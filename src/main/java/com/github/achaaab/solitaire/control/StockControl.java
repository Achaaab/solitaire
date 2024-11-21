package com.github.achaaab.solitaire.control;

import com.github.achaaab.solitaire.abstraction.Card;
import com.github.achaaab.solitaire.abstraction.Stock;
import com.github.achaaab.solitaire.presentation.PresentationFactory;
import com.github.achaaab.solitaire.presentation.StockPresentation;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static java.awt.event.MouseEvent.BUTTON1;

/**
 * Control part of a Stock component.
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class StockControl extends Stock {

	private final StockPresentation presentation;

	private SolitaireControl solitaire;

	/**
	 * Creates the control part of a Stock component.
	 *
	 * @since 0.0.0
	 */
	public StockControl() {
		presentation = PresentationFactory.INSTANCE.newStock(this);
	}

	/**
	 * Sets the Solitaire component for this Stock.
	 *
	 * @param solitaire Solitaire component
	 * @since 0.0.0
	 */
	public void setSolitaire(SolitaireControl solitaire) {

		this.solitaire = solitaire;

		presentation.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent event) {

				var button = event.getButton();

				if (button == BUTTON1) {

					if (solitaire.canDeal()) {
						solitaire.deal();
					} else if (solitaire.canRecycle()) {
						solitaire.recycle();
					}
				}
			}
		});
	}

	@Override
	public void push(Card card) {

		super.push(card);

		presentation.push(((CardControl) card).presentation());
		solitaire.updateStockState();
	}

	@Override
	public Card pop() {

		var card = (CardControl) super.pop();

		presentation.pop();
		solitaire.updateStockState();

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
