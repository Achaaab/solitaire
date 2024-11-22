package com.github.achaaab.solitaire.utility;

import javax.swing.AbstractButton;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;

import static java.awt.Toolkit.getDefaultToolkit;
import static java.lang.Math.pow;
import static java.lang.Math.round;

/**
 * Swing utility methods
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class SwingUtility {

	private static final Toolkit TOOLKIT = getDefaultToolkit();
	private static final float BASE_RESOLUTION = 72.0f;
	private static final int RESOLUTION = TOOLKIT.getScreenResolution();

	private static final int SCROLL_BAR_THICKNESS = scale(18);
	private static final int SCROLL_BAR_BUTTON_SIZE = scale(18);

	/**
	 * Creates a scroll pane and adds the given component in it.
	 * This helper aims to adapt the scroll pane components (track, buttons, thumb...) to scale with the
	 * pixel density of the monitor.
	 *
	 * @param component component to add into a scroll pane
	 * @param opaque whether to set the scroll pane opaque
	 * @return created scroll pane
	 * @since 0.0.0
	 */
	public static JScrollPane scrollPane(Component component, boolean opaque) {

		var scrollPane = new JScrollPane(component);

		scrollPane.setOpaque(opaque);
		scrollPane.getViewport().setOpaque(opaque);

		var verticalScrollBar = scrollPane.getVerticalScrollBar();
		var horizontalScrollBar = scrollPane.getHorizontalScrollBar();

		verticalScrollBar.setPreferredSize(new Dimension(SCROLL_BAR_THICKNESS, 0));
		horizontalScrollBar.setPreferredSize(new Dimension(0, SCROLL_BAR_THICKNESS));

		for (var scrollBarComponent : verticalScrollBar.getComponents()) {

			if (scrollBarComponent instanceof AbstractButton button) {
				button.setPreferredSize(new Dimension(0, SCROLL_BAR_BUTTON_SIZE));
			}
		}

		for (var scrollBarComponent : horizontalScrollBar.getComponents()) {

			if (scrollBarComponent instanceof AbstractButton button) {
				button.setPreferredSize(new Dimension(SCROLL_BAR_BUTTON_SIZE, 0));
			}
		}

		return scrollPane;
	}

	/**
	 * Adds a component in a container and centers it.
	 *
	 * @param component component to add
	 * @param container container in which to add the component
	 * @since 0.0.0
	 */
	public static void addCentered(Component component, Container container) {

		var componentWidth = component.getWidth();
		var componentHeight = component.getHeight();

		var containerWidth = container.getWidth();
		var containerHeight = container.getHeight();

		component.setLocation(
				(containerWidth - componentWidth) / 2,
				(containerHeight - componentHeight) / 2);

		container.add(component);
	}

	/**
	 * @param size normalized size for 72 DPI resolution
	 * @return scaled and rounded size
	 * @since 0.0.0
	 */
	public static int scale(float size) {
		return round(scaleFloat(size));
	}

	/**
	 * @param size normalized size for 72 DPI resolution
	 * @return scaled size
	 * @since 0.0.0
	 */
	public static float scaleFloat(float size) {
		return size * RESOLUTION / BASE_RESOLUTION;
	}

	/**
	 * @param component
	 * @param fontSize
	 * @since 0.0.0
	 */
	public static void setFontSize(Component component, float fontSize) {

		var originalFont = component.getFont();

		if (originalFont.getSize() != fontSize) {

			var resizedFont = originalFont.deriveFont(fontSize);
			component.setFont(resizedFont);
		}
	}

	/**
	 * @param color
	 * @return
	 * @since 0.0.0
	 */
	public static boolean isDark(Color color) {
		return getLuminance(color) < 128;
	}

	/**
	 * @param color
	 * @return
	 * @since 0.0.0
	 */
	public static double getLuminance(Color color) {

		double luminance;

		var red = color.getRed();
		var green = color.getGreen();
		var blue = color.getBlue();

		var linearizedRed = linearizeColorComponent(red);
		var linearizedGreen = linearizeColorComponent(green);
		var linearizedBlue = linearizeColorComponent(blue);

		var linearizedLuminance = 0.2126 * linearizedRed +
				0.7152 * linearizedGreen +
				0.0722 * linearizedBlue;

		if (linearizedLuminance <= 0.0031308) {
			luminance = 12.92 * linearizedLuminance;
		} else {
			luminance = 1.055 * pow(linearizedLuminance, 1 / 2.4) - 0.055;
		}

		return 255.0 * luminance;
	}

	/**
	 * Linearizes a gamma-compressed color component (red, green or blue).
	 *
	 * @param component color component in range {@code [0, 256[}
	 * @return linearized component in range {@code [0.0, 1.0]}
	 * @since 0.0.0
	 */
	public static double linearizeColorComponent(int component) {

		double linearized;

		var normalized = component / 255.0;

		if (normalized <= 0.04045) {
			linearized = normalized / 12.92;
		} else {
			linearized = pow((normalized + 0.055) / 1.055, 2.4);
		}

		return linearized;
	}
}
