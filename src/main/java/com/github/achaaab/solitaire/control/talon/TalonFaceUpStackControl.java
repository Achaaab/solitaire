package com.github.achaaab.solitaire.control.talon;

import com.github.achaaab.solitaire.abstraction.Card;
import com.github.achaaab.solitaire.abstraction.FaceUpStack;
import com.github.achaaab.solitaire.control.CardControl;
import com.github.achaaab.solitaire.control.ControlFactory;
import com.github.achaaab.solitaire.control.TransferableStackControl;
import com.github.achaaab.solitaire.control.dragndrop.StackSourceControl;
import com.github.achaaab.solitaire.presentation.PresentationFactory;
import com.github.achaaab.solitaire.presentation.talon.TalonFaceUpStackPresentation;

import static javax.swing.SwingUtilities.invokeLater;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class TalonFaceUpStackControl extends FaceUpStack implements StackSourceControl {

	private final TalonFaceUpStackPresentation presentation;

	/**
	 * @since 0.0.0
	 */
	public TalonFaceUpStackControl() {
		presentation = PresentationFactory.INSTANCE.newTalonFaceUpStack(this);
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
	public void dropFailed(TransferableStackControl stack) {
		push(stack);
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

	/**
	 * @return
	 * @since 0.0.0
	 */
	public TalonFaceUpStackPresentation presentation() {
		return presentation;
	}
}
