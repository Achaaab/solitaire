package com.github.achaaab.solitaire.control;

import com.github.achaaab.solitaire.abstraction.Pile;
import com.github.achaaab.solitaire.abstraction.Solitaire;
import com.github.achaaab.solitaire.presentation.SolitairePresentation;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static java.awt.event.MouseEvent.BUTTON1;

/**
 * Control part of a Solitaire component.
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class SolitaireControl extends Solitaire {

	private final SolitairePresentation presentation;
	private final MessageControl message;

	/**
	 * Creates the control part of a Solitaire component.
	 *
	 * @since 0.0.0
	 */
	public SolitaireControl() {

		super(ControlFactory.INSTANCE);

		stock().setSolitaire(this);
		waste().setSolitaire(this);

		message = ControlFactory.INSTANCE.newMessage();
		presentation = new SolitairePresentation(this);

		for (var pile : piles) {

			var pileControl = (PileControl) pile;
			pileControl.setMessage(message);

			pileControl.presentation().addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent event) {

					var button = event.getButton();
					var clickCount = event.getClickCount();

					if (button == BUTTON1 && clickCount == 2) {
						moveToFoundation(pile);
					}
				}
			});
		}

		for (var foundation : foundations()) {
			((FoundationControl) foundation).setMessage(message);
		}
	}

	/**
	 * Updates the stock state, showing the user if he can recycle or not.
	 *
	 * @since 0.0.0
	 */
	public void updateStockState() {
		stock().presentation().showState(canDeal(), canRecycle());
	}

	/**
	 * Tries to move the first card of the given pile to the first foundation accepting it.
	 * If the card is moved and the next card on the pile is turned face down, turn it face up.
	 *
	 * @param pile source pile
	 * @since 0.0.0
	 */
	public void moveToFoundation(Pile pile) {

		super.moveToFoundation(pile);

		if (pile.canFlip()) {
			pile.flip();
		}
	}

	/**
	 * Disables the presentation.
	 *
	 * @since 0.0.0
	 */
	public void disable() {
		presentation.disable();
	}

	/**
	 * Enables the presentation.
	 *
	 * @since 0.0.0
	 */
	public void enable() {
		presentation.enable();
	}

	/**
	 * @return presentation part of this solitaire
	 * @since 0.0.0
	 */
	public SolitairePresentation presentation() {
		return presentation;
	}

	@Override
	public StockControl stock() {
		return (StockControl) stock;
	}

	@Override
	public WasteControl waste() {
		return (WasteControl) waste;
	}

	/**
	 * @return message panel to display HTML documents
	 * @since 0.0.0
	 */
	public MessageControl message() {
		return message;
	}
}
