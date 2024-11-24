package com.github.achaaab.solitaire.control;

import com.github.achaaab.solitaire.abstraction.Card;
import com.github.achaaab.solitaire.abstraction.Waste;
import com.github.achaaab.solitaire.presentation.PresentationFactory;
import com.github.achaaab.solitaire.presentation.WastePresentation;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static java.awt.event.MouseEvent.BUTTON1;

/**
 * Control part of a Waste component.
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class WasteControl extends Waste implements StackSourceControl, StackTargetControl {

	private final WastePresentation presentation;
	private SolitaireControl solitaire;
	private DraggedStack draggedOutStack;

	/**
	 * Creates the control part of a Waste component.
	 *
	 * @since 0.0.0
	 */
	public WasteControl() {
		presentation = PresentationFactory.INSTANCE.newWaste(this);
	}

	/**
	 * Sets the Solitaire component for this Waste.
	 *
	 * @param solitaire Solitaire component
	 * @since 0.0.0
	 */
	public void setSolitaire(SolitaireControl solitaire) {

		this.solitaire = solitaire;

		presentation.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent event) {

				var button = event.getButton();
				var clickCount = event.getClickCount();

				if (button == BUTTON1 && clickCount == 2) {
					solitaire.moveToFoundation(WasteControl.this);
				}
			}
		});
	}

	@Override
	public void push(Card card) {

		super.push(card);

		presentation.push(((CardControl) card).presentation());
		solitaire.updateStockState();
	}

	@Override
	public Card pop() {

		var card = (CardControl) super.pop();

		presentation.pop();
		solitaire.updateStockState();

		return card;
	}

	@Override
	public void dropFailed(DraggedStack stack) {
		push(stack);
	}

	@Override
	public void dragOut(CardControl card) {

		if (card == getFirst()) {

			var sourceLocation = card.presentation().getLocation();
			draggedOutStack = ControlFactory.INSTANCE.newDraggedStack(1);
			draggedOutStack.push(pop());
			presentation.initiateDragAndDrop(sourceLocation, draggedOutStack);
		}
	}

	/**
	 * @return presentation of this waste
	 * @since 0.0.0
	 */
	public WastePresentation presentation() {
		return presentation;
	}

	@Override
	public void dragIn(DraggedStack stack) {
		stack.showAccepted(stack == draggedOutStack);
	}

	@Override
	public void drop(DraggedStack stack) {

		if (stack == draggedOutStack) {

			push(stack.pop());
			presentation.acceptDrop();

		} else {

			presentation.rejectDrop();
		}
	}

	/**
	 * Displays an HTML document as a message for the user.
	 *
	 * @param text HTML document to display
	 * @since 0.0.0
	 */
	public void displayMessage(String text) {
		solitaire.message().display(text);
	}
}
