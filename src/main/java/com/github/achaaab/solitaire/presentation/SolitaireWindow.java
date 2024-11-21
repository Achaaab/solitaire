package com.github.achaaab.solitaire.presentation;

import com.github.achaaab.solitaire.control.SolitaireControl;

import javax.swing.JFrame;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import static com.github.achaaab.solitaire.utility.ResourceUtility.loadOptionalImage;
import static com.github.achaaab.solitaire.utility.SwingUtility.isDark;
import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.NORTH;
import static javax.swing.SwingUtilities.invokeLater;

/**
 * Window for a Solitaire component.
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class SolitaireWindow extends JFrame {

	/**
	 * Creates a window for a given Solitaire.
	 *
	 * @param control control part of a Solitaire component
	 * @since 0.0.0
	 */
	public SolitaireWindow(SolitaireControl control) {

		super("Solitaire");

		var dark = isDark(getBackground());

		loadOptionalImage(dark ?
				"icons/dark/icon_256.png" :
				"icons/light/icon_256.png").
				ifPresent(this::setIconImage);

		addWindowListener(new SolitaireListener());

		add(new SolitaireMenu(control), NORTH);
		add(control.presentation(), CENTER);
		addMessage(control.message().presentation());

		invokeLater(() -> {

			pack();
			setLocationRelativeTo(null);
			setVisible(true);
		});
	}

	/**
	 * Adds a message component in this window.
	 * A message is kind of a replacement for a modal dialog, but integrated in the root pane of the main window.
	 *
	 * @param message message to add to this window
	 * @since 0.0.0
	 */
	private void addMessage(MessagePresentation message) {

		message.setLocation(0, 0);
		rootPane.add(message, 0);

		rootPane.addComponentListener(new ComponentAdapter() {

			@Override
			public void componentResized(ComponentEvent event) {
				message.resize();
			}
		});
	}
}
