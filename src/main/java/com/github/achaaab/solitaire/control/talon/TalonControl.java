package com.github.achaaab.solitaire.control.talon;

import com.github.achaaab.solitaire.abstraction.Talon;
import com.github.achaaab.solitaire.control.SolitaireControl;
import com.github.achaaab.solitaire.presentation.talon.TalonPresentation;
import com.github.achaaab.solitaire.presentation.PresentationFactory;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static java.awt.event.MouseEvent.BUTTON1;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class TalonControl extends Talon {

	private final TalonPresentation presentation;

	/**
	 * @param hiddenStack
	 * @param visibleStack
	 */
	public TalonControl(TalonFaceDownStackControl hiddenStack, TalonFaceUpStackControl visibleStack) {

		super(hiddenStack, visibleStack);

		presentation = PresentationFactory.INSTANCE.newTalon(this);
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
	 */
	public TalonFaceDownStackControl faceDownStack() {
		return (TalonFaceDownStackControl) faceDownStack;
	}

	/**
	 * @return
	 */
	public TalonFaceUpStackControl faceUpStack() {
		return (TalonFaceUpStackControl) faceUpStack;
	}

	/**
	 * @return
	 */
	public TalonPresentation presentation() {
		return presentation;
	}
}
