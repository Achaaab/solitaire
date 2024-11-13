package com.github.achaaab.solitaire.control;

import com.github.achaaab.solitaire.abstraction.Card;
import com.github.achaaab.solitaire.abstraction.Waste;
import com.github.achaaab.solitaire.control.dragndrop.StackSourceControl;
import com.github.achaaab.solitaire.presentation.PresentationFactory;
import com.github.achaaab.solitaire.presentation.WastePresentation;

import static javax.swing.SwingUtilities.invokeLater;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class WasteControl extends Waste implements StackSourceControl {

	private final WastePresentation presentation;

	/**
	 * @since 0.0.0
	 */
	public WasteControl() {
		presentation = PresentationFactory.INSTANCE.newWaste(this);
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
	 * @return presentation of this waste
	 * @since 0.0.0
	 */
	public WastePresentation presentation() {
		return presentation;
	}
}
