package com.github.achaaab.solitaire.presentation.theme;

import com.github.achaaab.solitaire.utility.SwingUtility;
import com.github.achaaab.solitaire.presentation.audio.SoundEffect;
import org.slf4j.Logger;

import javax.swing.ImageIcon;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Optional;

import static com.github.achaaab.solitaire.utility.ResourceUtility.loadIcon;
import static java.util.Optional.ofNullable;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public abstract class AbstractTheme implements Theme {

	private static final Logger LOGGER = getLogger(AbstractTheme.class);

	protected static final int DEFAULT_CARD_WIDTH = SwingUtility.scale(90.00f);
	protected static final int DEFAULT_CARD_HEIGHT = SwingUtility.scale(130.68f);

	private final String name;

	private final Map<String, SoundEffect> soundEffects;

	/**
	 * @param name
	 * @since 0.0.0
	 */
	public AbstractTheme(String name) {

		this.name = name;

		soundEffects = new HashMap<>();
	}

	@Override
	public double cardWidth() {
		return DEFAULT_CARD_WIDTH;
	}

	@Override
	public double cardHeight() {
		return DEFAULT_CARD_HEIGHT;
	}

	/**
	 * @param name
	 * @return
	 * @since 0.0.0
	 */
	protected Optional<SoundEffect> getSoundEffect(String name) {

		if (!soundEffects.containsKey(name)) {
			loadSoundEffect(name);
		}

		return ofNullable(soundEffects.get(name));
	}

	/**
	 * Loads an audio clip.
	 *
	 * @param name
	 * @since 0.0.0
	 */
	private void loadSoundEffect(String name) {

		SoundEffect soundEffect;

		try {

			soundEffect = new SoundEffect("themes/" + this.name + "/sons/" + name + ".wav", 10, 10);

		} catch (MissingResourceException missingResourceException) {

			soundEffect = null;
			LOGGER.error(missingResourceException.getMessage(), missingResourceException);
		}

		soundEffects.put(name, soundEffect);
	}

	/**
	 * @param imageName
	 * @param targetWidth target image width in pixels, {@code null} to keep original image width
	 * @param targetWidth target image height in pixels, {@code null} to keep original image height
	 * @return
	 * @since 0.0.0
	 */
	protected ImageIcon getImage(String imageName, Double targetWidth, Double targetHeight) {

		var resourceName = "themes/" + name + "/images/" + imageName;
		return loadIcon(resourceName, targetWidth, targetHeight);
	}

	@Override
	public Optional<SoundEffect> getPushCardSound() {
		return Optional.empty();
	}

	@Override
	public Optional<SoundEffect> getPopCardSound() {
		return Optional.empty();
	}

	@Override
	public Optional<SoundEffect> getUnderstoodSound() {
		return Optional.empty();
	}

	@Override
	public Optional<SoundEffect> getStartSound() {
		return Optional.empty();
	}

	@Override
	public Optional<SoundEffect> getExitSound() {
		return Optional.empty();
	}

	@Override
	public Optional<SoundEffect> getTalonRecyclingSound() {
		return Optional.empty();
	}

	@Override
	public Optional<SoundEffect> getWinSound() {
		return Optional.empty();
	}
}
