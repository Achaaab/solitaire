package com.github.achaaab.solitaire.control;

import com.github.achaaab.solitaire.abstraction.Card;
import com.github.achaaab.solitaire.abstraction.Pile;
import com.github.achaaab.solitaire.presentation.PilePresentation;
import com.github.achaaab.solitaire.presentation.PresentationFactory;

/**
 * Control part of a Pile component.
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class PileControl extends Pile implements StackSourceControl, StackTargetControl {

	private final PilePresentation presentation;
	private MessageControl message;
	private DraggedStack draggedOutStack;

	/**
	 * Creates the control part of a Pile component.
	 *
	 * @since 0.0.0
	 */
	public PileControl() {
		presentation = PresentationFactory.INSTANCE.newPile(this);
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

	@Override
	public void dragOut(CardControl card) {

		if (card.isFaceUp()) {

			var cardIndex = indexOf(card);
			var sourceLocation = card.presentation().getLocation();

			draggedOutStack = ControlFactory.INSTANCE.newDraggedStack(cardIndex + 1);
			var temporaryStack = ControlFactory.INSTANCE.newStack();

			while (temporaryStack.size() <= cardIndex) {
				temporaryStack.push(pop());
			}

			draggedOutStack.push(temporaryStack);
			presentation.initiateDragAndDrop(sourceLocation, draggedOutStack);
		}
	}

	@Override
	public void dragIn(DraggedStack stack) {

		var accepted = stack == draggedOutStack || canPush(stack);
		stack.showAccepted(accepted);
	}

	@Override
	public void drop(DraggedStack stack) {

		if (stack == draggedOutStack || canPush(stack)) {

			var temporaryStack = ControlFactory.INSTANCE.newStack();
			temporaryStack.push(stack);
			push(temporaryStack);

			presentation.acceptDrop();

		} else {

			presentation.rejectDrop();
		}
	}

	@Override
	public void dropSucceeded(DraggedStack stack) {

		if (canFlip()) {
			flip();
		}
	}

	@Override
	public void dropFailed(DraggedStack stack) {

		var temporaryStack = ControlFactory.INSTANCE.newStack();
		temporaryStack.push(stack);
		push(temporaryStack);
	}

	/**
	 * @param message message component
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

	/**
	 * @return presentation of this pile
	 * @since 0.0.0
	 */
	public PilePresentation presentation() {
		return presentation;
	}
}
