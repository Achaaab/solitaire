package com.github.achaaab.solitaire.presentation.theme;

import com.github.achaaab.solitaire.presentation.audio.SoundEffect;

import javax.swing.ImageIcon;
import java.util.Optional;

/**
 * Theme built with playing cards images provided by Byron Knoll.
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class ByronKnollTheme extends AbstractTheme {

	public static final ByronKnollTheme INSTANCE = new ByronKnollTheme();

	/**
	 * Creates a new Byron Knoll theme.
	 *
	 * @since 0.0.0
	 */
	private ByronKnollTheme() {
		super("byron_knoll");
	}

	@Override
	public ImageIcon getCardFaceUpIMage(String cardName) {
		return getImage(cardName + ".png", cardWidth(), cardHeight());
	}

	@Override
	public Optional<SoundEffect> getPopCardSound() {
		return getSoundEffect("card-slide");
	}

	@Override
	public Optional<SoundEffect> getPushCardSound() {
		return getSoundEffect("card-place");
	}
}
