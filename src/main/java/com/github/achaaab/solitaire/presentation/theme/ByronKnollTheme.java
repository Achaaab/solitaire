package com.github.achaaab.solitaire.presentation.theme;

import com.github.achaaab.solitaire.presentation.audio.SoundEffect;

import javax.swing.ImageIcon;
import java.util.Optional;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class ByronKnollTheme extends AbstractTheme {

	public static final ByronKnollTheme INSTANCE = new ByronKnollTheme();

	/**
	 *
	 */
	private ByronKnollTheme() {
		super("byron_knoll");
	}

	@Override
	public ImageIcon getCardBackImage() {
		return getImage("back.png", cardWidth(), cardHeight());
	}

	@Override
	public ImageIcon getCardFrontImage(String cardName) {
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
