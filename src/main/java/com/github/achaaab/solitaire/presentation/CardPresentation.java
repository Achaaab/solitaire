package com.github.achaaab.solitaire.presentation;

import com.github.achaaab.solitaire.control.CardControl;

import javax.swing.JComponent;
import javax.swing.JLabel;

import static com.github.achaaab.solitaire.presentation.theme.ThemeManager.computeHeight;
import static com.github.achaaab.solitaire.presentation.theme.ThemeManager.computeWidth;
import static com.github.achaaab.solitaire.presentation.theme.ThemeManager.getTheme;

/**
 * Presentation part of the Card component.
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class CardPresentation extends JComponent {

	private final CardControl control;

	protected JLabel frontImageLabel;
	protected JLabel backImageLabel;

	/**
	 * @param control control part of this component
	 * @since 0.0.0
	 */
	public CardPresentation(CardControl control) {

		this.control = control;

		var theme = getTheme();

		var name = control.rank().toString() + control.suit().getLetter();

		var frontImage = theme.getCardFaceUpIMage(name);
		var backImage = theme.getCardFaceDownImage();

		var width = computeWidth(1.0);
		var height = computeHeight(1.0);

		setSize(width, height);
		setPreferredSize(getSize());

		frontImageLabel = new JLabel(frontImage);
		add(frontImageLabel);
		frontImageLabel.setLocation(0, 0);
		frontImageLabel.setSize(width, height);

		backImageLabel = new JLabel(backImage);
		add(backImageLabel);
		backImageLabel.setLocation(0, 0);
		backImageLabel.setSize(width, height);

		setFaceUp(control.isFaceUp());
	}

	/**
	 * @param faceUp whether the card is turned face up
	 * @since 0.0.0
	 */
	public void setFaceUp(boolean faceUp) {

		frontImageLabel.setVisible(faceUp);
		backImageLabel.setVisible(!faceUp);
	}

	/**
	 * @return
	 * @since 0.0.0
	 */
	public CardControl getControl() {
		return control;
	}

	@Override
	public String toString() {
		return control.toString();
	}
}
