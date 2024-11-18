package com.github.achaaab.solitaire.utility;

import org.slf4j.Logger;

import javax.imageio.ImageIO;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

import static java.awt.Font.DIALOG;
import static java.awt.Font.PLAIN;
import static java.awt.Font.TRUETYPE_FONT;
import static java.awt.Font.createFont;
import static java.nio.file.FileSystems.newFileSystem;
import static java.nio.file.Files.readAllLines;
import static java.util.Collections.emptyMap;
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
	private static final Font DEFAULT_FONT = new Font(DIALOG, PLAIN, 12);

	private static final Pattern MESSAGE_PARAMETER_PATTERN = compile("\\{\\{(.+)}}");

	/**
	 * Loads an image resource.
	 *
	 * @param resourceName name of the image resource to open
	 * @return loaded image resource
	 * @since 0.0.0
	 */
	public static Optional<Image> loadOptionalImage(String resourceName) {

		Optional<Image> image;

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
	 * Opens an input stream on a named resource.
	 *
	 * @param resourceName name of the resource to open
	 * @return open input stream, {@code null} if the resource is not found
	 * @since 0.0.0
	 */
	public static InputStream openInputStream(String resourceName) {
		return CLASS_LOADER.getResourceAsStream(resourceName);
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
	 * Loads a font resource.
	 *
	 * @param resourceName name of the font resource
	 * @return loaded font
	 * @since 0.0.0
	 */
	public static Font loadFont(String resourceName) {

		var font = DEFAULT_FONT;

		try (var inputStream = openInputStream(resourceName)) {
			font = createFont(TRUETYPE_FONT, requireNonNull(inputStream));
		} catch (IOException | FontFormatException | NullPointerException exception) {
			LOGGER.error("error while loading font: {}", resourceName, exception);
		}

		return font;
	}

	/**
	 * Reads all lines from a resource.
	 *
	 * @param resourceName name of the resource to read
	 * @param charset charset to use for character decoding
	 * @return read lines
	 * @since 0.0.0
	 */
	public static List<String> readLines(String resourceName, Charset charset) {

		List<String> lines = null;

		try {
			var url = CLASS_LOADER.getResource(resourceName);

			if (url != null) {

				var uri = url.toURI();
				var scheme = uri.getScheme();

				if (!scheme.equals("file")) {
					newFileSystem(uri, emptyMap());
				}

				var path = Path.of(uri);
				lines = readAllLines(path, charset);
			}

		} catch (IOException | URISyntaxException exception) {

			LOGGER.error("error while reading lines of resource {}", resourceName, exception);
		}

		return lines;
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
		resourceNames.add(name);

		var extensionIndex = name.lastIndexOf('.');

		if (extensionIndex == -1) {

			resourceNames.add(name + '_' + defaultLanguage);

		} else {

			resourceNames.add(
					name.substring(0, extensionIndex) + '_' + defaultLanguage + name.substring(extensionIndex));
		}

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
	 * private constructor to prevent instanciation of this utility class
	 *
	 * @since 0.0.0
	 */
	private ResourceUtility() {

	}
}
