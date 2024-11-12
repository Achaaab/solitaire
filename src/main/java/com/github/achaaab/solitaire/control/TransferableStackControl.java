package com.github.achaaab.solitaire.control;

import com.github.achaaab.solitaire.abstraction.Card;
import com.github.achaaab.solitaire.abstraction.Stack;
import com.github.achaaab.solitaire.presentation.PresentationFactory;
import com.github.achaaab.solitaire.presentation.dragndrop.TransferableStackPresentation;

import static javax.swing.SwingUtilities.invokeLater;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class TransferableStackControl extends Stack {

	private final int capacity;
	private final TransferableStackPresentation presentation;

	/**
	 * @param capacity
	 * @since 0.0.0
	 */
	public TransferableStackControl(int capacity) {

		this.capacity = capacity;

		presentation = PresentationFactory.INSTANCE.newTransferableStack(this);
	}

	/**
	 * @return
	 */
	public int capacity() {
		return capacity;
	}

	@Override
	public void push(Card card) {

		super.push(card);

		invokeLater(() -> presentation.push(((CardControl) card).getPresentation()));
	}

	@Override
	public Card pop() {

		var card = (CardControl) super.pop();
		invokeLater(presentation::pop);

		return card;
	}

	/**
	 * @return
	 */
	public TransferableStackPresentation getPresentation() {
		return presentation;
	}

	/**
	 *
	 */
	public void delete() {
		presentation.remove();
	}

	/**
	 * @param accepted
	 */
	public void showAccepted(boolean accepted) {
		presentation.showAccepted(accepted);
	}
}
