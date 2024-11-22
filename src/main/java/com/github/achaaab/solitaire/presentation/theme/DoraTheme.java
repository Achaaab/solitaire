package com.github.achaaab.solitaire.presentation.theme;

import com.github.achaaab.solitaire.presentation.audio.SoundEffect;

import javax.swing.ImageIcon;

import java.util.Optional;

import static java.lang.Math.random;

/**
 * Theme inspired by a parody of Dora The Explorer.
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class DoraTheme extends AbstractTheme {

	public static final DoraTheme INSTANCE = new DoraTheme();

	/**
	 * Creates a theme inspired by a parody of Dora The Explorer.
	 *
	 * @since 0.0.0
	 */
	private DoraTheme() {
		super("dora");
	}

	@Override
	public ImageIcon getCardFaceDownImage() {
		return getImage("back.png", cardWidth(), cardHeight());
	}

	@Override
	public ImageIcon getCardFaceUpIMage(String cardName) {
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
	public Optional<SoundEffect> getWinSound() {
		return getSoundEffect("ouais");
	}
}
