package com.github.achaaab.solitaire.presentation.pile;

import com.github.achaaab.solitaire.control.pile.PileControl;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static java.awt.event.MouseEvent.BUTTON1;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class FlipListener extends MouseAdapter {

	private final PileControl pile;

	/**
	 * @param pile
	 */
	public FlipListener(PileControl pile) {
		this.pile = pile;
	}

	@Override
	public void mousePressed(MouseEvent event) {

		var button = event.getButton();

		if (button == BUTTON1 && pile.canFlip()) {
			pile.flip();
		}
	}
}
