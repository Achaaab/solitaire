package com.github.achaaab.solitaire.presentation;

import com.github.achaaab.solitaire.control.SolitaireControl;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

import static com.github.achaaab.solitaire.presentation.SwingUtility.scale;
import static com.github.achaaab.solitaire.presentation.SwingUtility.scaleFloat;
import static com.github.achaaab.solitaire.presentation.SwingUtility.setFontSize;
import static java.awt.event.WindowEvent.WINDOW_CLOSING;
import static javax.swing.BorderFactory.createEmptyBorder;
import static javax.swing.SwingUtilities.getWindowAncestor;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class SolitaireMenu extends JMenuBar {

	private static final float FONT_SIZE = scaleFloat(13);

	/**
	 * Depending on the Look & Feel, this border can be necessary to make the menu bar more ergonomic.
	 * It should be adjustable in a configuration file.
	 *
	 * @since 0.0.0
	 */
	private static final Border MENU_BORDER = createEmptyBorder(scale(5), scale(10), scale(5), scale(10));

	private final SolitaireControl solitaire;
	private final JMenuItem newGame;

	/**
	 * @param solitaire
	 * @since 0.0.0
	 */
	public SolitaireMenu(SolitaireControl solitaire) {

		this.solitaire = solitaire;

		var file = new JMenu("Fichier");
		setFontSize(file, FONT_SIZE);
		file.setBorder(MENU_BORDER);

		var help = new JMenu("Aide");
		setFontSize(help, FONT_SIZE);
		help.setBorder(MENU_BORDER);

		newGame = new JMenuItem("Nouvelle partie");
		setFontSize(newGame, FONT_SIZE);
		newGame.setBorder(MENU_BORDER);
		newGame.addActionListener(this::reset);

		var exit = new JMenuItem("Quitter");
		setFontSize(exit, FONT_SIZE);
		exit.setBorder(MENU_BORDER);
		exit.addActionListener(this::fermerFenetre);

		var about = new JMenuItem("À propos");
		setFontSize(about, FONT_SIZE);
		about.setBorder(MENU_BORDER);
		about.addActionListener(this::about);

		file.add(newGame);
		file.add(exit);
		help.add(about);

		add(file);
		add(help);
	}

	/**
	 * @param event
	 * @since 0.0.0
	 */
	private void fermerFenetre(ActionEvent event) {

		var window = getWindowAncestor(solitaire.presentation());
		window.dispatchEvent(new WindowEvent(window, WINDOW_CLOSING));
	}

	/**
	 * @param event
	 * @since 0.0.0
	 */
	private void reset(ActionEvent event) {

		newGame.setEnabled(false);

		new Thread(() -> {

			solitaire.reset();
			newGame.setEnabled(true);

		}).start();
	}

	/**
	 * @param event
	 * @since 0.0.0
	 */
	private void about(ActionEvent event) {

		solitaire.message().display("""
				<html>
					<body>
						<h1 style="text-align:center">Solitaire</h1>
						<p style="text-align:center">version 0.0.0</p>
						<br>
						<p style="text-align:center">Jonathan Guéhenneux</p>
						<p style="text-align:center">
							<a href="https://github.com/Achaaab">https://github.com/Achaaab</a>
						</p>
					</body>
				</html>
				""");
	}
}
