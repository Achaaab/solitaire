package com.github.achaaab.solitaire.presentation;

import com.github.achaaab.solitaire.control.SolitaireControl;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
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

		var rules = new JMenu("Règles");
		setFontSize(rules, FONT_SIZE);
		rules.setBorder(MENU_BORDER);

		newGame = new JMenuItem("Nouvelle partie");
		setFontSize(newGame, FONT_SIZE);
		newGame.setBorder(MENU_BORDER);
		newGame.addActionListener(this::reset);

		var exit = new JMenuItem("Quitter");
		setFontSize(exit, FONT_SIZE);
		exit.setBorder(MENU_BORDER);
		exit.addActionListener(this::fermerFenetre);

		var turnedCardCount = solitaire.rules().getTurnedCardCount();
		var recycleCount = solitaire.rules().getPassCount();

		var turn1Card = new JRadioButtonMenuItem("Tirer 1 carte");
		setFontSize(turn1Card, FONT_SIZE);
		turn1Card.setBorder(MENU_BORDER);
		turn1Card.setSelected(turnedCardCount == 1);
		turn1Card.addActionListener(event -> solitaire.rules().setTurnedCardCount(1));

		var turn2Cards = new JRadioButtonMenuItem("Tirer 2 cartes");
		setFontSize(turn2Cards, FONT_SIZE);
		turn2Cards.setBorder(MENU_BORDER);
		turn2Cards.setSelected(turnedCardCount == 2);
		turn2Cards.addActionListener(event -> solitaire.rules().setTurnedCardCount(2));

		var turn3Cards = new JRadioButtonMenuItem("Tirer 3 cartes");
		setFontSize(turn3Cards, FONT_SIZE);
		turn3Cards.setBorder(MENU_BORDER);
		turn3Cards.setSelected(turnedCardCount == 3);
		turn3Cards.addActionListener(event -> solitaire.rules().setTurnedCardCount(3));

		var drawNCardsGroup = new ButtonGroup();
		drawNCardsGroup.add(turn1Card);
		drawNCardsGroup.add(turn2Cards);
		drawNCardsGroup.add(turn3Cards);

		var pass1 = new JRadioButtonMenuItem("1 passe");
		setFontSize(pass1, FONT_SIZE);
		pass1.setBorder(MENU_BORDER);
		pass1.setSelected(recycleCount == 1);
		pass1.addActionListener(event -> solitaire.rules().setPassCount(1));

		var pass3 = new JRadioButtonMenuItem("3 passes");
		setFontSize(pass3, FONT_SIZE);
		pass3.setBorder(MENU_BORDER);
		pass3.setSelected(recycleCount == 3);
		pass3.addActionListener(event -> solitaire.rules().setPassCount(3));

		var passUnlimited = new JRadioButtonMenuItem("∞ passes");
		setFontSize(passUnlimited, FONT_SIZE);
		passUnlimited.setBorder(MENU_BORDER);
		passUnlimited.setSelected(recycleCount == -1);
		passUnlimited.addActionListener(event -> solitaire.rules().setPassCount(-1));

		var passGroup = new ButtonGroup();
		passGroup.add(pass1);
		passGroup.add(pass3);
		passGroup.add(passUnlimited);

		var about = new JMenuItem("À propos");
		setFontSize(about, FONT_SIZE);
		about.setBorder(MENU_BORDER);
		about.addActionListener(this::about);

		file.add(newGame);
		file.add(exit);
		rules.add(turn1Card);
		rules.add(turn2Cards);
		rules.add(turn3Cards);
		rules.addSeparator();
		rules.add(pass1);
		rules.add(pass3);
		rules.add(passUnlimited);
		help.add(about);

		add(file);
		add(rules);
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

		solitaire.disable();

		new Thread(() -> {

			try {
				solitaire.reset();
			} finally {
				solitaire.enable();
			}

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
