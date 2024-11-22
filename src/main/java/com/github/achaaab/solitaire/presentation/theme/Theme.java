package com.github.achaaab.solitaire.presentation.theme;

import com.github.achaaab.solitaire.presentation.audio.SoundEffect;

import javax.swing.ImageIcon;
import java.util.Optional;

/**
 * Theme for a Solitaire presentation.
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public interface Theme {

	/**
	 * @return card display width in pixels
	 * @since 0.0.0
	 */
	double cardWidth();

	/**
	 * @return card display height in pixels
	 * @since 0.0.0
	 */
	double cardHeight();

	/**
	 * @return image representing a playing card turned face down
	 * @since 0.0.0
	 */
	ImageIcon getCardFaceDownImage();
	
	/**
	 * @param cardName name of the card
	 * @return image representing the playing card turned face up
	 * @since 0.0.0
	 */
	ImageIcon getCardFaceUpIMage(String cardName);

	/**
	 * @return sound to play when a card is placed on a stack
	 * @since 0.0.0
	 */
	Optional<SoundEffect> getPushCardSound();

	/**
	 * @return sound to play when a card is removed from a stack
	 * @since 0.0.0
	 */
	Optional<SoundEffect> getPopCardSound();

	/**
	 * @return sound to play when the user validate a help message
	 * @since 0.0.0
	 */
	Optional<SoundEffect> getUnderstoodSound();

	/**
	 * @return sound to play when the user closes the main window
	 * @since 0.0.0
	 */
	Optional<SoundEffect> getExitSound();

	/**
	 * @return sound to play when the main window is opened
	 * @since 0.0.0
	 */
	Optional<SoundEffect> getStartSound();

	/**
	 * @return sound to play when the user wins a game
	 * @since 0.0.0
	 */
	Optional<SoundEffect> getWinSound();
}
