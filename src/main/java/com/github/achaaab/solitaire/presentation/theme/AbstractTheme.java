package com.github.achaaab.solitaire.presentation.theme;

import com.github.achaaab.solitaire.presentation.SwingUtility;
import com.github.achaaab.solitaire.presentation.audio.SoundEffect;
import org.slf4j.Logger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Optional;

import static java.awt.Image.SCALE_SMOOTH;
import static java.lang.ClassLoader.getSystemResource;
import static java.lang.Math.round;
import static java.lang.Math.toIntExact;
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

	/**
	 * @param image image to scale
	 * @param targetWidth target width in pixels
	 * @param targetHeight target height in pixels
	 * @return scaled image
	 */
	private static Image scale(BufferedImage image, Double targetWidth, Double targetHeight) {

		Image scaledImage = image;
		var width = image.getWidth();
		var height = image.getHeight();

		targetWidth = targetWidth == null ? width : targetWidth;
		targetHeight = targetHeight == null ? height : targetHeight;

		if (width != targetWidth || height != targetHeight) {

			scaledImage = image.getScaledInstance(
					toIntExact(round(targetWidth)),
					toIntExact(round(targetHeight)),
					SCALE_SMOOTH);
		}

		return scaledImage;
	}

	private final String name;

	private final Map<String, SoundEffect> soundEffects;

	/**
	 * @param name
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
	 * @return
	 */
	protected ImageIcon getImage(String imageName) {
		return getImage(imageName, null, null);
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

		try {

			var imageUrl = getSystemResource(resourceName);
			var bufferedImage = ImageIO.read(imageUrl);
			var scaledImage = scale(bufferedImage, targetWidth, targetHeight);

			return new ImageIcon(scaledImage);

		} catch (IOException | IllegalArgumentException cause) {

			throw new MissingResourceException("cannot find " + resourceName, resourceName, null);
		}
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
