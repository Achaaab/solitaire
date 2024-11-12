package com.github.achaaab.solitaire.presentation.talon;

import com.github.achaaab.solitaire.abstraction.Stack;
import com.github.achaaab.solitaire.abstraction.Talon;
import com.github.achaaab.solitaire.presentation.StackPresentation;
import com.github.achaaab.solitaire.presentation.audio.SoundEffect;

import static com.github.achaaab.solitaire.presentation.theme.ThemeManager.computeHeight;
import static com.github.achaaab.solitaire.presentation.theme.ThemeManager.computeWidth;
import static com.github.achaaab.solitaire.presentation.theme.ThemeManager.getTheme;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class TalonFaceDownStackPresentation extends StackPresentation {

	/**
	 * @param control
	 */
	public TalonFaceDownStackPresentation(Stack control) {

		super(control, Talon.CAPACITY, computeWidth(0.01), computeHeight(0.003));

		setDefaultBorder();
	}

	@Override
	public void pop() {

		getTheme().getPopCardSound().ifPresent(SoundEffect::play);
		super.pop();
	}
}
