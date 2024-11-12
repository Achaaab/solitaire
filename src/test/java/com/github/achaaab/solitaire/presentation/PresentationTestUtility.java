package com.github.achaaab.solitaire.presentation;

import javax.swing.JComponent;
import javax.swing.JFrame;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class PresentationTestUtility {

	/**
	 * @param component
	 * @param title
	 * @since 0.0.0
	 */
	public static void show(JComponent component, String title) {

		var window = new JFrame(title);
		window.setDefaultCloseOperation(EXIT_ON_CLOSE);
		window.setContentPane(component);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}
}
