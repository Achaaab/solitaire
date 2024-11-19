package com.github.achaaab.solitaire.control;

import com.github.achaaab.solitaire.abstraction.Card;
import com.github.achaaab.solitaire.abstraction.Stack;
import com.github.achaaab.solitaire.presentation.PresentationFactory;
import com.github.achaaab.solitaire.presentation.dragndrop.DraggedStackPresentation;

/**
 * Control part of a dragged stack component.
 * A dragged stack is a stack created during a drag n drop, holding the dragged cards.
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class DraggedStack extends Stack {

	private final int capacity;
	private final DraggedStackPresentation presentation;

	/**
	 * Creates a new dragged stack component.
	 *
	 * @param capacity number of cards that will be pushed in this stack
	 * @since 0.0.0
	 */
	public DraggedStack(int capacity) {

		this.capacity = capacity;

		presentation = PresentationFactory.INSTANCE.newDraggedStack(this);
	}

	/**
	 * @return number of cards in this stack
	 * @since 0.0.0
	 */
	public int capacity() {
		return capacity;
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
	 * @return presentation of this transferable stack
	 * @since 0.0.0
	 */
	public DraggedStackPresentation presentation() {
		return presentation;
	}

	/**
	 * Deletes this dragged stack component.
	 *
	 * @since 0.0.0
	 */
	public void delete() {
		presentation.remove();
	}

	/**
	 * Shows whether this dragged stack can be dropped at its current position.
	 *
	 * @param accepted whether this dragged stack can be dropped at its current position
	 * @since 0.0.0
	 */
	public void showAccepted(boolean accepted) {
		presentation.showAccepted(accepted);
	}
}
