package com.github.achaaab.solitaire.presentation;

import com.github.achaaab.solitaire.control.StockControl;
import com.github.achaaab.solitaire.presentation.audio.SoundEffect;

import static com.github.achaaab.solitaire.abstraction.Stock.CAPACITY;
import static com.github.achaaab.solitaire.presentation.theme.ThemeManager.computeHeight;
import static com.github.achaaab.solitaire.presentation.theme.ThemeManager.computeWidth;
import static com.github.achaaab.solitaire.presentation.theme.ThemeManager.getTheme;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class StockPresentation extends StackPresentation {

	/**
	 * @param control
	 * @since 0.0.0
	 */
	public StockPresentation(StockControl control) {

		super(control, CAPACITY, computeWidth(0.01), computeHeight(0.003));

		setDefaultBorder();
	}

	@Override
	public void pop() {

		getTheme().getPopCardSound().ifPresent(SoundEffect::play);
		super.pop();
	}
}
