package com.github.achaaab.solitaire.presentation.theme;

import static java.lang.Math.round;
import static java.lang.Math.toIntExact;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class ThemeManager {

	private static Theme theme;

	/**
	 * @return
	 */
	public static Theme getTheme() {
		return theme;
	}

	/**
	 * @param theme
	 */
	public static void setTheme(Theme theme) {
		ThemeManager.theme = theme;
	}

	/**
	 * @param factor
	 * @return
	 * @since 0.0.0
	 */
	public static int computeWidth(double factor) {

		var cardWidth = theme.cardWidth();
		return toIntExact(round(cardWidth * factor));
	}

	/**
	 * @param factor
	 * @return
	 * @since 0.0.0
	 */
	public static int computeHeight(double factor) {

		var cardHeight = theme.cardHeight();
		return toIntExact(round(cardHeight * factor));
	}
}
