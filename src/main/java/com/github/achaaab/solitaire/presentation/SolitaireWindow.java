package com.github.achaaab.solitaire.presentation;

import com.github.achaaab.solitaire.control.SolitaireControl;
import com.github.achaaab.solitaire.utility.ResourceUtility;

import javax.swing.JFrame;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import static com.github.achaaab.solitaire.utility.ResourceUtility.loadOptionalImage;
import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.NORTH;
import static javax.swing.SwingUtilities.invokeLater;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class SolitaireWindow extends JFrame {

	/**
	 * @param solitaire
	 * @since 0.0.0
	 */
	public SolitaireWindow(SolitaireControl solitaire) {

		super("Solitaire");

		loadOptionalImage("icons/icon_256.png").ifPresent(this::setIconImage);

		addWindowListener(new SolitaireListener());

		add(new SolitaireMenu(solitaire), NORTH);
		add(solitaire.presentation(), CENTER);

		var message = solitaire.message();
		var messagePresentation = message.presentation();

		messagePresentation.setLocation(0, 0);
		rootPane.add(messagePresentation, 0);

		rootPane.addComponentListener(new ComponentAdapter() {

			@Override
			public void componentResized(ComponentEvent event) {
				messagePresentation.resize();
			}
		});

		invokeLater(() -> {

			pack();
			setLocationRelativeTo(null);
			setVisible(true);
		});
	}
}
