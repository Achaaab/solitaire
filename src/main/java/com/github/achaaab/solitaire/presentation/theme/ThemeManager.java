package com.github.achaaab.solitaire.presentation.theme;

import static java.lang.Math.round;
import static java.lang.Math.toIntExact;

/**
 * Theme manager.
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class ThemeManager {

	private static Theme theme;

	/**
	 * @return current theme
	 * @since 0.0.0
	 */
	public static Theme getTheme() {
		return theme;
	}

	/**
	 * @param theme theme to set
	 * @since 0.0.0
	 */
	public static void setTheme(Theme theme) {
		ThemeManager.theme = theme;
	}

	/**
	 * Computes a width, relatively to card's width.
	 *
	 * @param factor factor to apply to card's width
	 * @return computed relative width
	 * @since 0.0.0
	 */
	public static int computeWidth(double factor) {

		var cardWidth = theme.cardWidth();
		return toIntExact(round(cardWidth * factor));
	}

	/**
	 * Computes a height, relatively to card's height.
	 *
	 * @param factor factor to apply to card's height
	 * @return computed relative height
	 * @since 0.0.0
	 */
	public static int computeHeight(double factor) {

		var cardHeight = theme.cardHeight();
		return toIntExact(round(cardHeight * factor));
	}
}
