package com.github.achaaab.solitaire.presentation.theme;

import com.github.achaaab.solitaire.presentation.audio.SoundEffect;

import javax.swing.ImageIcon;
import java.util.Optional;

/**
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
	 * @return L'image du dos de la carte pour ce th�me
	 */
	ImageIcon getCardBackImage();
	
	/**
	 * @param cardName
	 * @return L'image de la face de la carte pour ce thème
	 */
	ImageIcon getCardFrontImage(String cardName);

	/**
	 * @return
	 */
	Optional<SoundEffect> getPushCardSound();

	/**
	 * @return
	 */
	Optional<SoundEffect> getPopCardSound();

	/**
	 * @return un son pour l'evenement : le sabot est retourne
	 */
	Optional<SoundEffect> getTalonRecyclingSound();

	/**
	 * @return un son pour l'evenement : validation d'un message d'explication
	 */
	Optional<SoundEffect> getUnderstoodSound();

	/**
	 * @return un son pour l'evenement : fin du programme
	 */
	Optional<SoundEffect> getExitSound();

	/**
	 * @return un son pour l'evenement : debut du programme
	 */
	Optional<SoundEffect> getStartSound();

	/**
	 * @return un son pour l'evenement : victoire
	 */
	Optional<SoundEffect> getWinSound();
}
