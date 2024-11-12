package com.github.achaaab.solitaire.presentation.theme;

import com.github.achaaab.solitaire.presentation.audio.SoundEffect;

import javax.swing.ImageIcon;

import java.util.Optional;

import static java.lang.Math.random;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class DoraTheme extends AbstractTheme {

	public static final DoraTheme INSTANCE = new DoraTheme();

	/**
	 *
	 */
	private DoraTheme() {
		super("dora");
	}

	@Override
	public ImageIcon getCardBackImage() {
		return getImage("dos.png", cardWidth(), cardHeight());
	}

	@Override
	public ImageIcon getCardFrontImage(String cardName) {
		return getImage(cardName + ".png", cardWidth(), cardHeight());
	}

	@Override
	public Optional<SoundEffect> getPushCardSound() {
		return getSoundEffect("ohYeahhh");
	}

	@Override
	public Optional<SoundEffect> getPopCardSound() {
		return random() < 0.5 ? getSoundEffect("oHYesss") : getSoundEffect("deLaBalle");
	}

	@Override
	public Optional<SoundEffect> getUnderstoodSound() {
		return getSoundEffect("siTuEsHandicapeTuNePeuxPasLeFaire");
	}

	@Override
	public Optional<SoundEffect> getStartSound() {
		return getSoundEffect("helloMoiCestDora");
	}

	@Override
	public Optional<SoundEffect> getExitSound() {
		return random() < 0.5 ? getSoundEffect("goodBye") : getSoundEffect("voilaCestLaFinDeNotreAventure");
	}

	@Override
	public Optional<SoundEffect> getTalonRecyclingSound() {
		return getSoundEffect("bruitEtoileTeleMagique");
	}

	@Override
	public Optional<SoundEffect> getWinSound() {
		return getSoundEffect("ouais");
	}
}
