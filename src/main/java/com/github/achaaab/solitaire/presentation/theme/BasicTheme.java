package com.github.achaaab.solitaire.presentation.theme;

import javax.swing.ImageIcon;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class BasicTheme extends AbstractTheme {

	public static final BasicTheme INSTANCE = new BasicTheme();

	/**
	 * @since 0.0.0
	 */
	private BasicTheme() {
		super("basic");
	}

	@Override
	public ImageIcon getCardBackImage() {
		return getImage("dos.jpg", cardWidth(), cardHeight());
	}

	@Override
	public ImageIcon getCardFrontImage(String cardName) {
		return getImage(cardName + ".gif", cardWidth(), cardHeight());
	}
}
