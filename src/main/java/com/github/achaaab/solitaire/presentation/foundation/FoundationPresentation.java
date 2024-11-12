package com.github.achaaab.solitaire.presentation.foundation;

import com.github.achaaab.solitaire.abstraction.Rank;
import com.github.achaaab.solitaire.control.TransferableStackControl;
import com.github.achaaab.solitaire.control.foundation.FoundationControl;
import com.github.achaaab.solitaire.presentation.CardPresentation;
import com.github.achaaab.solitaire.presentation.StackPresentation;
import com.github.achaaab.solitaire.presentation.audio.SoundEffect;
import com.github.achaaab.solitaire.presentation.dragndrop.DragSourceManager;
import com.github.achaaab.solitaire.presentation.dragndrop.DropTargetManager;
import com.github.achaaab.solitaire.presentation.dragndrop.StackSourcePresentation;
import com.github.achaaab.solitaire.presentation.dragndrop.StackTargetPresentation;

import java.awt.Point;

import static com.github.achaaab.solitaire.presentation.theme.ThemeManager.computeHeight;
import static com.github.achaaab.solitaire.presentation.theme.ThemeManager.computeWidth;
import static com.github.achaaab.solitaire.presentation.theme.ThemeManager.getTheme;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class FoundationPresentation extends StackPresentation
		implements StackSourcePresentation, StackTargetPresentation {

	private final DropTargetManager dropTargetManager;
	private final DragSourceManager dragSourceManager;

	/**
	 * @param controle
	 */
	public FoundationPresentation(FoundationControl controle) {

		super(controle, Rank.COUNT, computeWidth(0.01), computeHeight(0.003));

		setDefaultBorder();

		dropTargetManager = new DropTargetManager(controle, this);
		dragSourceManager = new DragSourceManager(controle, this);
	}

	@Override
	public void acceptDrop() {
		dropTargetManager.acceptDrop();
	}

	@Override
	public void rejectDrop() {
		dropTargetManager.rejectDrop();
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
