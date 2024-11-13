package com.github.achaaab.solitaire.control;

import com.github.achaaab.solitaire.abstraction.Card;
import com.github.achaaab.solitaire.abstraction.Foundation;
import com.github.achaaab.solitaire.presentation.PresentationFactory;
import com.github.achaaab.solitaire.presentation.FoundationPresentation;

import static javax.swing.SwingUtilities.invokeLater;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class FoundationControl extends Foundation implements StackTargetControl, StackSourceControl {

	private static int missedDropCount = 0;

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

				message.display("""
						<html>
							<body>
								<ul>
									<li>Dans ce tas, vous devez posez une carte de la même couleur que la
									précédente.</li>
						
									<li>De plus la valeur de la carte posée doit être juste supérieure
									à la précédente.</li>
						
									<li>Par exemple : <span style="color:#FF0000">🂲</span>
									sur <span style="color:#FF0000">🂱</span> ou <span style="color:#000000">🂫</span>
									sur <span style="color:#000000">🂪</span>.</li>
						
									<li>Dans un tas vide, vous ne pouvez poser qu'un As.</li>
								</ul>
							</body>
						</html>
						""");

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
