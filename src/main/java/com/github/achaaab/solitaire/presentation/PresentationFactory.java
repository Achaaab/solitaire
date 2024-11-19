package com.github.achaaab.solitaire.presentation;

import com.github.achaaab.solitaire.control.CardControl;
import com.github.achaaab.solitaire.control.FoundationControl;
import com.github.achaaab.solitaire.control.PileControl;
import com.github.achaaab.solitaire.control.SolitaireControl;
import com.github.achaaab.solitaire.control.StockControl;
import com.github.achaaab.solitaire.control.DraggedStack;
import com.github.achaaab.solitaire.control.WasteControl;
import com.github.achaaab.solitaire.presentation.dragndrop.DraggedStackPresentation;

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
	 * @since 0.0.0
	 */
	public PilePresentation newPile(PileControl control) {
		return new PilePresentation(control);
	}

	/**
	 * @param control
	 * @return
	 * @since 0.0.0
	 */
	public CardPresentation newCard(CardControl control) {
		return new CardPresentation(control);
	}

	/**
	 * @param control
	 * @return
	 * @since 0.0.0
	 */
	public DraggedStackPresentation newDraggedStack(DraggedStack control) {
		return new DraggedStackPresentation(control);
	}

	/**
	 * @param control
	 * @return
	 * @since 0.0.0
	 */
	public FoundationPresentation newFoundation(FoundationControl control) {
		return new FoundationPresentation(control);
	}

	/**
	 * Creates a new window including a menu for the given Solitaire.
	 *
	 * @param control control part of a Solitaire
	 * @return created window
	 * @since 0.0.0
	 */
	public SolitaireWindow newWindow(SolitaireControl control) {
		return new SolitaireWindow(control);
	}
}
