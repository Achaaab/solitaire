package com.github.achaaab.solitaire.control;

import com.github.achaaab.solitaire.abstraction.Card;
import com.github.achaaab.solitaire.abstraction.Pile;
import com.github.achaaab.solitaire.presentation.PilePresentation;
import com.github.achaaab.solitaire.presentation.PresentationFactory;

import java.util.Map;

import static com.github.achaaab.solitaire.presentation.SwingUtility.scale;
import static com.github.achaaab.solitaire.utility.ResourceUtility.getMessage;
import static javax.swing.SwingUtilities.invokeLater;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class PileControl extends Pile implements StackSourceControl, StackTargetControl {

	private static int missedDropCount = 0;

	private final PilePresentation presentation;
	private final String missedDropMessage;
	private MessageControl message;
	private TransferableStackControl draggedOutStack;

	/**
	 * @since 0.0.0
	 */
	public PileControl() {

		presentation = PresentationFactory.INSTANCE.newPile(this);
		missedDropMessage = getMessage("messages/pile.html", Map.of("font-size", scale(16)));
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

	@Override
	public void dragOut(CardControl card) {

		if (card.isFaceUp()) {

			var cardIndex = indexOf(card);
			var sourceLocation = card.getPresentation().getLocation();

			draggedOutStack = ControlFactory.INSTANCE.newTransferableStack(cardIndex + 1);
			var temporaryStack = ControlFactory.INSTANCE.newStack();

			while (temporaryStack.size() <= cardIndex) {
				temporaryStack.push(pop());
			}

			draggedOutStack.push(temporaryStack);
			presentation.initiateDragAndDrop(sourceLocation, draggedOutStack);
		}
	}

	@Override
	public void dragIn(TransferableStackControl stack) {

		var accepted = stack == draggedOutStack || canPush(stack);
		stack.showAccepted(accepted);
	}

	@Override
	public void drop(TransferableStackControl stack) {

		var accepted = stack == draggedOutStack || canPush(stack);

		if (accepted) {

			var temporaryStack = ControlFactory.INSTANCE.newStack();
			temporaryStack.push(stack);
			push(temporaryStack);

			presentation.acceptDrop();

		} else {

			presentation.rejectDrop();

			if (++missedDropCount == 3) {

				message.display(missedDropMessage);
				missedDropCount = 0;
			}
		}
	}

	@Override
	public void dropSucceeded(TransferableStackControl stack) {

		if (canFlip()) {
			flip();
		}
	}

	@Override
	public void dropFailed(TransferableStackControl stack) {

		var temporaryStack = ControlFactory.INSTANCE.newStack();
		temporaryStack.push(stack);
		push(temporaryStack);
	}

	/**
	 * @param message
	 * @since 0.0.0
	 */
	public void setMessage(MessageControl message) {
		this.message = message;
	}

	/**
	 * @return
	 * @since 0.0.0
	 */
	public PilePresentation presentation() {
		return presentation;
	}
}
