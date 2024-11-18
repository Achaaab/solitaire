package com.github.achaaab.solitaire.control;

import com.github.achaaab.solitaire.abstraction.Card;
import com.github.achaaab.solitaire.abstraction.Foundation;
import com.github.achaaab.solitaire.presentation.FoundationPresentation;
import com.github.achaaab.solitaire.presentation.PresentationFactory;

import java.util.Map;

import static com.github.achaaab.solitaire.presentation.SwingUtility.scale;
import static com.github.achaaab.solitaire.utility.ResourceUtility.getMessage;
import static javax.swing.SwingUtilities.invokeLater;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class FoundationControl extends Foundation implements StackTargetControl, StackSourceControl {

	private static int missedDropCount = 0;

	private final FoundationPresentation presentation;
	private final String missedDropMessage;
	private MessageControl message;

	/**
	 * @since 0.0.0
	 */
	public FoundationControl() {

		presentation = PresentationFactory.INSTANCE.newFoundation(this);
		missedDropMessage = getMessage("messages/foundation.html", Map.of("font-size", scale(16)));
	}

	/**
	 * @param message message
	 * @since 0.0.0
	 */
	public void setMessage(MessageControl message) {
		this.message = message;
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
	public FoundationPresentation presentation() {
		return presentation;
	}

	@Override
	public void dropFailed(TransferableStackControl stack) {
		push(stack.pop());
	}

	@Override
	public void dragIn(TransferableStackControl stack) {
		stack.showAccepted(canPush(stack));
	}

	@Override
	public void drop(TransferableStackControl stack) {

		if (canPush(stack)) {

			push(stack);
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
	public void dragOut(CardControl card) {

		if (card == getFirst()) {

			var sourceLocation = card.getPresentation().getLocation();
			var transferableStack = ControlFactory.INSTANCE.newTransferableStack(1);
			transferableStack.push(pop());
			presentation.initiateDragAndDrop(sourceLocation, transferableStack);
		}
	}
}
