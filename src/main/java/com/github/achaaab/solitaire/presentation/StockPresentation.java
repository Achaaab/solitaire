package com.github.achaaab.solitaire.presentation;

import com.github.achaaab.solitaire.control.StockControl;
import com.github.achaaab.solitaire.presentation.audio.SoundEffect;

import javax.swing.JLabel;

import static com.github.achaaab.solitaire.abstraction.Stock.CAPACITY;
import static com.github.achaaab.solitaire.presentation.theme.ThemeManager.computeHeight;
import static com.github.achaaab.solitaire.presentation.theme.ThemeManager.computeWidth;
import static com.github.achaaab.solitaire.presentation.theme.ThemeManager.getTheme;
import static com.github.achaaab.solitaire.utility.ResourceUtility.loadIcon;
import static com.github.achaaab.solitaire.utility.SwingUtility.addCentered;

/**
 * Presentation part of a stock component.
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class StockPresentation extends StackPresentation {

	private final JLabel recycleLabel;
	private final JLabel noRecycleLabel;

	/**
	 * Creates the presentation part of a stock component.
	 *
	 * @param control control part of the stock component
	 * @since 0.0.0
	 */
	public StockPresentation(StockControl control) {

		super(control, CAPACITY, computeWidth(0.01), computeHeight(0.003));

		setDefaultBorder();

		var iconSize = computeWidth(0.80);

		recycleLabel = new JLabel(loadIcon("icons/recycle_256.png", iconSize, iconSize));
		noRecycleLabel = new JLabel(loadIcon("icons/no_recycle_256.png", iconSize, iconSize));

		recycleLabel.setSize(iconSize, iconSize);
		noRecycleLabel.setSize(iconSize, iconSize);

		recycleLabel.setVisible(false);
		noRecycleLabel.setVisible(false);

		addCentered(recycleLabel, this);
		addCentered(noRecycleLabel, this);
	}

	@Override
	public void pop() {

		getTheme().getPopCardSound().ifPresent(SoundEffect::play);
		super.pop();
	}

	/**
	 * @return control part of this stock
	 * @since 0.0.0
	 */
	public StockControl control() {
		return (StockControl) control;
	}

	/**
	 * Shows the current state of this stock, indicating to the user which actions are allowed.
	 *
	 * @param dealAllowed whether dealing cards from this stock to the waste is possible
	 * @param recycleAllowed whether recycling the waste into this stock is possible
	 * @since 0.0.0
	 */
	public void showState(boolean dealAllowed, boolean recycleAllowed) {

		recycleLabel.setVisible(!dealAllowed && recycleAllowed);
		noRecycleLabel.setVisible(!dealAllowed && !recycleAllowed);
	}
}
