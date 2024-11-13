package com.github.achaaab.solitaire.control;

import com.github.achaaab.solitaire.abstraction.Factory;
import com.github.achaaab.solitaire.abstraction.Rank;
import com.github.achaaab.solitaire.abstraction.Suit;
import com.github.achaaab.solitaire.control.pile.PileControl;
import com.github.achaaab.solitaire.control.pile.PileFaceDownStackControl;
import com.github.achaaab.solitaire.control.pile.PileFaceUpStackControl;

/**
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
	public StackControl newHiddenStack() {
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
		return new PileControl(newPileFaceDownStack(), newPileFaceUpStack());
	}

	/**
	 * @return
	 */
	public PileFaceDownStackControl newPileFaceDownStack() {
		return new PileFaceDownStackControl();
	}

	/**
	 * @return
	 */
	public PileFaceUpStackControl newPileFaceUpStack() {
		return new PileFaceUpStackControl();
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
	 * @return
	 */
	public TransferableStackControl newTransferableStack(int capacity) {
		return new TransferableStackControl(capacity);
	}

	@Override
	public SolitaireControl newSolitaire() {
		return new SolitaireControl();
	}

	/**
	 * @return
	 * @since 0.0.0
	 */
	public MessageControl newMessage() {
		return new MessageControl();
	}
}
