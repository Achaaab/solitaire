package com.github.achaaab.solitaire.presentation.pile;

import com.github.achaaab.solitaire.control.pile.PileControl;

import javax.swing.BoxLayout;
import javax.swing.JComponent;

import static javax.swing.BoxLayout.PAGE_AXIS;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class PilePresentation extends JComponent {

	/**
	 * @param hiddenStack
	 * @param visibleStack
	 * @param control
	 */
	public PilePresentation(
			PileFaceDownStackPresentation hiddenStack,
			PileFaceUpStackPresentation visibleStack,
			PileControl control) {

		setLayout(new BoxLayout(this, PAGE_AXIS));
		add(hiddenStack);
		add(visibleStack);

		addMouseListener(new FlipListener(control));
	}
}
