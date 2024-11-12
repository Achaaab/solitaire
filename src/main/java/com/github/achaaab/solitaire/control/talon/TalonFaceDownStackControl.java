package com.github.achaaab.solitaire.control.talon;

import com.github.achaaab.solitaire.abstraction.Card;
import com.github.achaaab.solitaire.abstraction.FaceDownStack;
import com.github.achaaab.solitaire.control.CardControl;
import com.github.achaaab.solitaire.presentation.PresentationFactory;
import com.github.achaaab.solitaire.presentation.talon.TalonFaceDownStackPresentation;

import static javax.swing.SwingUtilities.invokeLater;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class TalonFaceDownStackControl extends FaceDownStack {

	private final TalonFaceDownStackPresentation presentation;

	/**
	 *
	 */
	public TalonFaceDownStackControl() {
		presentation = PresentationFactory.INSTANCE.newTalonFaceDownStack(this);
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
	public TalonFaceDownStackPresentation getPresentation() {
		return presentation;
	}
}
