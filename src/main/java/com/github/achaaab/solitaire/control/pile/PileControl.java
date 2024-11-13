package com.github.achaaab.solitaire.control.pile;

import com.github.achaaab.solitaire.abstraction.Pile;
import com.github.achaaab.solitaire.control.SolitaireControl;
import com.github.achaaab.solitaire.presentation.PresentationFactory;
import com.github.achaaab.solitaire.presentation.pile.PilePresentation;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static java.awt.event.MouseEvent.BUTTON1;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class PileControl extends Pile {

	private final PilePresentation presentation;

	/**
	 * @param faceDownStack
	 * @param faceUpStack
	 * @since 0.0.0
	 */
	public PileControl(PileFaceDownStackControl faceDownStack, PileFaceUpStackControl faceUpStack) {

		super(faceDownStack, faceUpStack);

		faceUpStack.setPile(this);
		presentation = PresentationFactory.INSTANCE.newPile(this);
	}

	/**
	 * @param solitaire
	 * @since 0.0.0
	 */
	public void setSolitaire(SolitaireControl solitaire) {

		faceUpStack().presentation().addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent event) {

				var button = event.getButton();
				var clickCount = event.getClickCount();

				if (button == BUTTON1 && clickCount == 2) {
					solitaire.moveToFoundation(faceUpStack());
				}
			}
		});
	}

	/**
	 * @return
	 * @since 0.0.0
	 */
	public PileFaceDownStackControl faceDownStack() {
		return (PileFaceDownStackControl) faceDownStack;
	}

	/**
	 * @return
	 * @since 0.0.0
	 */
	public PileFaceUpStackControl faceUpStack() {
		return (PileFaceUpStackControl) faceUpStack;
	}

	/**
	 * @return
	 * @since 0.0.0
	 */
	public PilePresentation getPresentation() {
		return presentation;
	}
}
