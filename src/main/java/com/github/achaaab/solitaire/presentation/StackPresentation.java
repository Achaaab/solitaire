package com.github.achaaab.solitaire.presentation;

import com.github.achaaab.solitaire.abstraction.Stack;

import javax.swing.JComponent;

import static com.github.achaaab.solitaire.presentation.SolitairePresentation.BACKGROUND;
import static com.github.achaaab.solitaire.presentation.theme.ThemeManager.computeWidth;
import static com.github.achaaab.solitaire.presentation.theme.ThemeManager.getTheme;
import static java.lang.Math.round;
import static java.lang.Math.toIntExact;
import static javax.swing.BorderFactory.createLineBorder;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class StackPresentation extends JComponent {

	protected final Stack control;
	private final int gapX;
	private final int gapY;

	/**
	 * @param control
	 * @param capacity
	 * @param gapX
	 * @param gapY
	 * @since 0.0.0
	 */
	public StackPresentation(Stack control, int capacity, int gapX, int gapY) {

		this.control = control;
		this.gapX = gapX;
		this.gapY = gapY;

		var theme = getTheme();
		var width = theme.cardWidth() + (capacity - 1) * gapX;
		var height = theme.cardHeight() + (capacity - 1) * gapY;

		setSize(
				toIntExact(round(width)),
				toIntExact(round(height)));

		setPreferredSize(getSize());
		setMinimumSize(getSize());
		setMaximumSize(getSize());
	}

	/**
	 * @param card
	 */
	public void push(CardPresentation card) {

		add(card, 0);
		var count = getComponentCount();

		card.setLocation(
				(count - 1) * gapX,
				(count - 1) * gapY);

		repaint();
	}

	/**
	 *
	 */
	public void pop() {

		remove(0);
		repaint();
	}

	/**
	 *
	 */
	public void setDefaultBorder() {
		setBorder(createLineBorder(BACKGROUND.darker(), computeWidth(0.03), true));
	}
}
