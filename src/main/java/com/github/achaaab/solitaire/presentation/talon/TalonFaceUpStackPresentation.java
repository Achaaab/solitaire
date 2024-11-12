package com.github.achaaab.solitaire.presentation.talon;

import com.github.achaaab.solitaire.control.TransferableStackControl;
import com.github.achaaab.solitaire.control.talon.TalonFaceUpStackControl;
import com.github.achaaab.solitaire.presentation.CardPresentation;
import com.github.achaaab.solitaire.presentation.StackPresentation;
import com.github.achaaab.solitaire.presentation.audio.SoundEffect;
import com.github.achaaab.solitaire.presentation.dragndrop.DragSourceManager;
import com.github.achaaab.solitaire.presentation.dragndrop.StackSourcePresentation;

import java.awt.Point;

import static com.github.achaaab.solitaire.abstraction.Talon.CAPACITY;
import static com.github.achaaab.solitaire.presentation.theme.ThemeManager.computeHeight;
import static com.github.achaaab.solitaire.presentation.theme.ThemeManager.computeWidth;
import static com.github.achaaab.solitaire.presentation.theme.ThemeManager.getTheme;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class TalonFaceUpStackPresentation extends StackPresentation implements StackSourcePresentation {

	private final DragSourceManager dragSourceManager;

	/**
	 * @param control
	 */
	public TalonFaceUpStackPresentation(TalonFaceUpStackControl control) {

		super(control, CAPACITY, computeWidth(0.01), computeHeight(0.003));

		dragSourceManager = new DragSourceManager(control, this);
	}

	@Override
	public void initiateDragAndDrop(Point sourceLocation, TransferableStackControl stack) {
		dragSourceManager.initiateDragAndDrop(sourceLocation, stack);
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
