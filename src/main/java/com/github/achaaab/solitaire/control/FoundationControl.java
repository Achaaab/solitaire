package com.github.achaaab.solitaire.control;

import com.github.achaaab.solitaire.abstraction.Card;
import com.github.achaaab.solitaire.abstraction.Foundation;
import com.github.achaaab.solitaire.presentation.FoundationPresentation;
import com.github.achaaab.solitaire.presentation.PresentationFactory;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class FoundationControl extends Foundation implements StackTargetControl, StackSourceControl {

	private final FoundationPresentation presentation;
	private MessageControl message;

	/**
	 * @since 0.0.0
	 */
	public FoundationControl() {
		presentation = PresentationFactory.INSTANCE.newFoundation(this);
	}

	/**
	 * @param message message
	 * @since 0.0.0
	 */
	public void setMessage(MessageControl message) {
		this.message = message;
	}

	/**
	 * Displays an HTML document as a message for the user.
	 *
	 * @param text HTML document to display
	 * @since 0.0.0
	 */
	public void displayMessage(String text) {
		message.display(text);
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
	 * @return presentation of this foundation
	 * @since 0.0.0
	 */
	public FoundationPresentation presentation() {
		return presentation;
	}

	@Override
	public void dropFailed(DraggedStack stack) {
		push(stack.pop());
	}

	@Override
	public void dragIn(DraggedStack stack) {
		stack.showAccepted(canPush(stack));
	}

	@Override
	public void drop(DraggedStack stack) {

		if (canPush(stack)) {

			push(stack);
			presentation.acceptDrop();

		} else {

			presentation.rejectDrop();
		}
	}

	@Override
	public void dragOut(CardControl card) {

		if (card == getFirst()) {

			var sourceLocation = card.presentation().getLocation();
			var transferableStack = ControlFactory.INSTANCE.newDraggedStack(1);
			transferableStack.push(pop());
			presentation.initiateDragAndDrop(sourceLocation, transferableStack);
		}
	}
}
