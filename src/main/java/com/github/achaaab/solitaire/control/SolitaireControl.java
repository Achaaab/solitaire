package com.github.achaaab.solitaire.control;

import com.github.achaaab.solitaire.abstraction.Solitaire;
import com.github.achaaab.solitaire.abstraction.Stack;
import com.github.achaaab.solitaire.control.foundation.FoundationControl;
import com.github.achaaab.solitaire.control.message.MessageControl;
import com.github.achaaab.solitaire.control.pile.PileControl;
import com.github.achaaab.solitaire.control.pile.PileFaceUpStackControl;
import com.github.achaaab.solitaire.control.talon.TalonControl;
import com.github.achaaab.solitaire.presentation.SolitairePresentation;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class SolitaireControl extends Solitaire {

	private final SolitairePresentation presentation;
	private final MessageControl message;

	/**
	 * @since 0.0.0
	 */
	public SolitaireControl() {

		super(ControlFactory.INSTANCE);

		message = ControlFactory.INSTANCE.newMessage();
		presentation = new SolitairePresentation(this);

		talon().setSolitaire(this);

		for (var pile : piles) {

			((PileControl) pile).faceUpStack().setMessage(message);
			((PileControl) pile).setSolitaire(this);
		}

		for (var foundation : foundations()) {
			((FoundationControl) foundation).setMessage(message);
		}
	}

	@Override
	public void moveToFoundation(Stack stack) {

		super.moveToFoundation(stack);

		if (stack instanceof PileFaceUpStackControl pileFaceUpStackControl) {

			var pile = pileFaceUpStackControl.getPile();

			if (pile.canFlip()) {
				pile.flip();
			}
		}
	}

	/**
	 * @return
	 * @since 0.0.0
	 */
	public SolitairePresentation presentation() {
		return presentation;
	}

	@Override
	public TalonControl talon() {
		return (TalonControl) talon;
	}

	/**
	 * @return
	 * @since 0.0.0
	 */
	public MessageControl message() {
		return message;
	}
}
