package com.github.achaaab.solitaire.utility;

import org.slf4j.Logger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

import static java.awt.Image.SCALE_SMOOTH;
import static java.lang.Math.round;
import static java.lang.Math.toIntExact;
import static java.util.Objects.requireNonNull;
import static java.util.regex.Pattern.compile;
import static java.util.stream.Collectors.joining;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * resource utility methods
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class ResourceUtility {

	private static final Logger LOGGER = getLogger(ResourceUtility.class);

	private static final ClassLoader CLASS_LOADER = ResourceUtility.class.getClassLoader();
	private static final Pattern MESSAGE_PARAMETER_PATTERN = compile("\\{\\{(\\s*\\w+\\s*)}}");

	/**
	 * Loads an icon resource and scale it if necessary.
	 *
	 * @param name name of the icon resource to load
	 * @param targetWidth target image width in pixels, {@code null} to keep original image width
	 * @param targetHeight target image height in pixels, {@code null} to keep original image height
	 * @return loaded and scaled icon
	 * @since 0.0.0
	 */
	public static ImageIcon loadIcon(String name, Integer targetWidth, Integer targetHeight) {
		return loadIcon(name, targetWidth.doubleValue(), targetHeight.doubleValue());
	}

	/**
	 * Loads an icon resource and scale it if necessary.
	 *
	 * @param name name of the icon resource to load
	 * @param targetWidth target image width in pixels, {@code null} to keep original image width
	 * @param targetHeight target image height in pixels, {@code null} to keep original image height
	 * @return loaded and scaled icon
	 * @since 0.0.0
	 */
	public static ImageIcon loadIcon(String name, Double targetWidth, Double targetHeight) {

		var image = loadOptionalImage(name).orElseThrow();
		var scaledImage = scale(image, targetWidth, targetHeight);

		return new ImageIcon(scaledImage);
	}

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

	/**
	 * Loads an image resource.
	 *
	 * @param resourceName name of the image resource to open
	 * @return loaded image resource
	 * @since 0.0.0
	 */
	public static Optional<BufferedImage> loadOptionalImage(String resourceName) {

		Optional<BufferedImage> image;

		var url = CLASS_LOADER.getResource(resourceName);

		if (url == null) {

			image = Optional.empty();
			LOGGER.info("image resource not found: {}", resourceName);

		} else {

			try {

				image = Optional.of(ImageIO.read(url));

			} catch (IOException ioException) {

				image = Optional.empty();
				LOGGER.warn(ioException.getMessage(), ioException);
			}
		}

		return image;
	}

	/**
	 * Gets a URL to a named resource.
	 *
	 * @param resourceName name of the resource
	 * @return URL to the resource
	 */
	public static URL getResourceUrl(String resourceName) {

		var url = CLASS_LOADER.getResource(resourceName);
		return requireNonNull(url);
	}

	/**
	 * @param name
	 * @param parameters
	 * @return
	 * @since 0.0.0
	 */
	public static String getMessage(String name, Map<String, Object> parameters) {

		var defaultLanguage = Locale.getDefault().getLanguage();

		var resourceNames = new ArrayList<String>();

		var extensionIndex = name.lastIndexOf('.');

		if (extensionIndex == -1) {

			resourceNames.add(name + '_' + defaultLanguage);

		} else {

			resourceNames.add(
					name.substring(0, extensionIndex) + '_' + defaultLanguage + name.substring(extensionIndex));
		}

		resourceNames.add(name);

		return getUrl(resourceNames).
				map(url -> getMessage(url, parameters)).
				orElseThrow();
	}

	/**
	 * @param url message URL, not {@code null}
	 * @param parameters message parameters
	 * @return message with variables replaced with given parameters
	 * @since 0.0.0
	 */
	public static String getMessage(URL url, Map<String, Object> parameters) {

		try (
				var inputStream = url.openStream();
				var reader = new BufferedReader(new InputStreamReader(inputStream))) {

			var template = reader.lines().collect(joining());
			var matcher = MESSAGE_PARAMETER_PATTERN.matcher(template);

			return matcher.replaceAll(matchResult -> {

				var parameterName = matcher.group(1).strip();
				return parameters.get(parameterName).toString();
			});

		} catch (IOException cause) {

			throw new UncheckedIOException(cause);
		}
	}

	/**
	 * Finds the resource with given names and returns the first found one.
	 *
	 * @param resourceNames resource potential names
	 * @return first found resource
	 * @since 0.0.0
	 */
	public static Optional<URL> getUrl(List<String> resourceNames) {

		return resourceNames.stream().
				map(CLASS_LOADER::getResource).
				filter(Objects::nonNull).
				findFirst();
	}

	/**
	 * private constructor to prevent instantiation of this utility class
	 *
	 * @since 0.0.0
	 */
	private ResourceUtility() {

	}
}
