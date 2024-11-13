package com.github.achaaab.solitaire.presentation;

import com.github.achaaab.solitaire.control.CardControl;
import com.github.achaaab.solitaire.control.SolitaireControl;
import com.github.achaaab.solitaire.control.StockControl;
import com.github.achaaab.solitaire.control.TransferableStackControl;
import com.github.achaaab.solitaire.control.WasteControl;
import com.github.achaaab.solitaire.control.FoundationControl;
import com.github.achaaab.solitaire.control.pile.PileControl;
import com.github.achaaab.solitaire.control.pile.PileFaceDownStackControl;
import com.github.achaaab.solitaire.control.pile.PileFaceUpStackControl;
import com.github.achaaab.solitaire.presentation.dragndrop.TransferableStackPresentation;
import com.github.achaaab.solitaire.presentation.pile.PileFaceDownStackPresentation;
import com.github.achaaab.solitaire.presentation.pile.PileFaceUpStackPresentation;
import com.github.achaaab.solitaire.presentation.pile.PilePresentation;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class PresentationFactory {

	public static final PresentationFactory INSTANCE = new PresentationFactory();

	/**
	 * Private constructor to force singleton usage.
	 *
	 * @see #INSTANCE
	 * @since 0.0.0
	 */
	private PresentationFactory() {

	}

	/**
	 * @param control
	 * @return
	 * @since 0.0.0
	 */
	public StockPresentation newStock(StockControl control) {
		return new StockPresentation(control);
	}

	/**
	 * @param control
	 * @return
	 * @since 0.0.0
	 */
	public WastePresentation newWaste(WasteControl control) {
		return new WastePresentation(control);
	}

	/**
	 * @param control
	 * @return
	 */
	public PilePresentation newPile(PileControl control) {

		return new PilePresentation(
				control.faceDownStack().getPresentation(),
				control.faceUpStack().presentation(),
				control);
	}

	/**
	 * @param control
	 * @return
	 */
	public PileFaceDownStackPresentation newPileFaceDownStack(PileFaceDownStackControl control) {
		return new PileFaceDownStackPresentation(control);
	}

	/**
	 * @param control
	 * @return
	 */
	public PileFaceUpStackPresentation newPileFaceUpStack(PileFaceUpStackControl control) {
		return new PileFaceUpStackPresentation(control);
	}

	/**
	 * @param control
	 * @return
	 */
	public CardPresentation newCard(CardControl control) {
		return new CardPresentation(control);
	}

	/**
	 * @param control
	 * @return
	 */
	public TransferableStackPresentation newTransferableStack(TransferableStackControl control) {
		return new TransferableStackPresentation(control);
	}

	/**
	 * @param control
	 * @return
	 */
	public FoundationPresentation newFoundation(FoundationControl control) {
		return new FoundationPresentation(control);
	}

	/**
	 * @param solitaire
	 * @return
	 */
	public SolitaireWindow newWindow(SolitaireControl solitaire) {
		return new SolitaireWindow(solitaire);
	}
}