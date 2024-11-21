package com.github.achaaab.solitaire.control;

import com.github.achaaab.solitaire.abstraction.Factory;
import com.github.achaaab.solitaire.abstraction.Rank;
import com.github.achaaab.solitaire.abstraction.Suit;

/**
 * Factory creating control parts of all Solitaire components.
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class ControlFactory extends Factory {

	public static final ControlFactory INSTANCE = new ControlFactory();

	/**
	 * Private constructor to force singleton usage.
	 *
	 * @see #INSTANCE
	 * @since 0.0.0
	 */
	private ControlFactory() {

	}

	@Override
	public StackControl newStack() {
		return new StackControl();
	}

	@Override
	public CardControl newCard(Rank rank, Suit suit) {
		return new CardControl(rank, suit);
	}

	@Override
	public FoundationControl newFoundation() {
		return new FoundationControl();
	}

	@Override
	public PileControl newPile() {
		return new PileControl();
	}

	@Override
	public StockControl newStock() {
		return new StockControl();
	}

	@Override
	public WasteControl newWaste() {
		return new WasteControl();
	}

	/**
	 * Creates a new dragged stack component.
	 *
	 * @param capacity number of cards that will be stacked
	 * @return created dragged stack
	 * @since 0.0.0
	 */
	public DraggedStack newDraggedStack(int capacity) {
		return new DraggedStack(capacity);
	}

	@Override
	public SolitaireControl newSolitaire() {
		return new SolitaireControl();
	}

	/**
	 * Creates a new message component.
	 *
	 * @return created message component
	 * @since 0.0.0
	 */
	public MessageControl newMessage() {
		return new MessageControl();
	}
}
