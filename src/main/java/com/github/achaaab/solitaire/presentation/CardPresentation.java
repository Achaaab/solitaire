package com.github.achaaab.solitaire.presentation;

import com.github.achaaab.solitaire.control.CardControl;

import javax.swing.JComponent;
import javax.swing.JLabel;

import static com.github.achaaab.solitaire.presentation.theme.ThemeManager.computeHeight;
import static com.github.achaaab.solitaire.presentation.theme.ThemeManager.computeWidth;
import static com.github.achaaab.solitaire.presentation.theme.ThemeManager.getTheme;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class CardPresentation extends JComponent {

	private final CardControl control;

	protected JLabel frontImageLabel;
	protected JLabel backImageLabel;

	/**
	 * @param control
	 */
	public CardPresentation(CardControl control) {

		this.control = control;

		var theme = getTheme();

		var name = control.getRank().toString() + control.getSuit().getLetter();

		var frontImage = theme.getCardFrontImage(name);
		var backImage = theme.getCardBackImage();

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

		setFaceVisible(control.isFaceUp());
	}

	/**
	 * changer la visibilité de la carte
	 *
	 * @param faceVisible vrai si la face est visible, faux sinon
	 */
	public void setFaceVisible(boolean faceVisible) {

		frontImageLabel.setVisible(faceVisible);
		backImageLabel.setVisible(!faceVisible);
	}

	/**
	 * @return
	 */
	public CardControl getControl() {
		return control;
	}

	@Override
	public String toString() {
		return control.toString();
	}
}
