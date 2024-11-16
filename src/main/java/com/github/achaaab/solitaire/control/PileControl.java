package com.github.achaaab.solitaire.control;

import com.github.achaaab.solitaire.abstraction.Card;
import com.github.achaaab.solitaire.abstraction.Pile;
import com.github.achaaab.solitaire.presentation.PresentationFactory;
import com.github.achaaab.solitaire.presentation.PilePresentation;

import static javax.swing.SwingUtilities.invokeLater;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class PileControl extends Pile implements StackSourceControl, StackTargetControl {

	private static int missedDropCount = 0;

	private final PilePresentation presentation;
	private MessageControl message;
	private TransferableStackControl draggedOutStack;

	/**
	 * @since 0.0.0
	 */
	public PileControl() {
		presentation = PresentationFactory.INSTANCE.newPile(this);
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

				message.display("""
						<html>
							<body>
								<ul>
									<li>Dans ce tas, les couleurs doivent être alternées
									(<span style="color:#FF0000">Rouge</span> /
									<span style="color:#000000">Noire</span>).</li>
						
									<li>De plus la valeur de la carte posée doit être juste inférieure
									à la précédente.</li>
						
									<li>Par exemple : <span style="color:#000000">🂭</span>
									sur <span style="color:#FF0000">🂾</span> ou <span style="color:#FF0000">🃊</span>
									sur <span style="color:#000000">🂫</span>.</li>
						
									<li>Dans un tas vide, vous ne pouvez poser qu'un Roi.</li>
								</ul>
							</body>
						</html>
						""");

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
