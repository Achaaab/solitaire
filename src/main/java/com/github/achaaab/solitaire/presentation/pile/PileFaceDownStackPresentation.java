package com.github.achaaab.solitaire.presentation.pile;

import com.github.achaaab.solitaire.control.pile.PileFaceDownStackControl;
import com.github.achaaab.solitaire.presentation.CardPresentation;
import com.github.achaaab.solitaire.presentation.StackPresentation;
import com.github.achaaab.solitaire.presentation.audio.SoundEffect;

import static com.github.achaaab.solitaire.abstraction.Pile.HIDDEN_CAPACITY;
import static com.github.achaaab.solitaire.presentation.theme.ThemeManager.computeHeight;
import static com.github.achaaab.solitaire.presentation.theme.ThemeManager.getTheme;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class PileFaceDownStackPresentation extends StackPresentation {

	/**
	 * @param control
	 */
	public PileFaceDownStackPresentation(PileFaceDownStackControl control) {
		super(control, HIDDEN_CAPACITY, 0, computeHeight(0.08));
	}

	@Override
	public void push(CardPresentation card) {

		getTheme().getPushCardSound().ifPresent(SoundEffect::play);
		super.push(card);
	}

	@Override
	public void pop() {

		getTheme().getPopCardSound().ifPresent(SoundEffect::play);
		super.pop();
	}
}
