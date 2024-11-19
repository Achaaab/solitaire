package com.github.achaaab.solitaire.presentation.audio;

/**
 * Audio
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public interface Audio {

	double VOLUME_SCALE = 10.0;

	/**
	 * @return name of this audio
	 * @since 0.0.0
	 */
	String name();

	/**
	 * @param volume in {@code [0, 10]}
	 * @since 0.0.0
	 */
	void setVolume(int volume);

	/**
	 * Starts to play this audio.
	 *
	 * @return whether this audio could be started
	 * @since 0.0.0
	 */
	boolean play();

	/**
	 * Starts to play this audio and waits until it ends.
	 *
	 * @return whether this audio could be played
	 * @since 0.0.0
	 */
	boolean playAndWait();
}
