package com.github.achaaab.solitaire.presentation;

import com.github.achaaab.solitaire.control.SolitaireControl;
import com.github.achaaab.solitaire.control.foundation.FoundationControl;
import com.github.achaaab.solitaire.control.pile.PileControl;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import static com.github.achaaab.solitaire.presentation.theme.ThemeManager.computeWidth;
import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.NORTH;
import static javax.swing.BorderFactory.createEmptyBorder;
import static javax.swing.Box.createGlue;
import static javax.swing.Box.createRigidArea;
import static javax.swing.BoxLayout.LINE_AXIS;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class SolitairePresentation extends JPanel {

	public static final Color BACKGROUND = new Color(20, 81, 22);

	/**
	 * @param control
	 */
	public SolitairePresentation(SolitaireControl control) {

		var stackGap = computeWidth(0.10);
		var margin = computeWidth(0.08);

		setLayout(new BorderLayout(margin, margin));
		setBorder(createEmptyBorder(margin, margin, margin, margin));
		setBackground(BACKGROUND);

		var northPanel = new JPanel();
		northPanel.setOpaque(false);
		northPanel.setLayout(new BoxLayout(northPanel, LINE_AXIS));

		var pilesPanel = new JPanel();
		pilesPanel.setOpaque(false);
		pilesPanel.setLayout(new BoxLayout(pilesPanel, LINE_AXIS));

		var foundationsPanel = new JPanel();
		foundationsPanel.setOpaque(false);
		foundationsPanel.setLayout(new BoxLayout(foundationsPanel, LINE_AXIS));

		pilesPanel.add(createRigidArea(new Dimension(stackGap, 0)));

		for (var pile : control.piles()) {

			pilesPanel.add(((PileControl) pile).getPresentation());
			pilesPanel.add(createRigidArea(new Dimension(stackGap, 0)));
		}

		for (var foundation : control.foundations()) {

			foundationsPanel.add(createRigidArea(new Dimension(stackGap, 0)));
			foundationsPanel.add(((FoundationControl) foundation).presentation());
		}

		northPanel.add(control.talon().presentation());
		northPanel.add(createGlue());
		northPanel.add(foundationsPanel);

		add(northPanel, NORTH);
		add(pilesPanel, CENTER);
	}
}
