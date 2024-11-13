package com.github.achaaab.solitaire.presentation;

import com.github.achaaab.solitaire.control.TransferableStackControl;
import com.github.achaaab.solitaire.control.PileControl;
import com.github.achaaab.solitaire.presentation.audio.SoundEffect;
import com.github.achaaab.solitaire.presentation.dragndrop.DragSourceManager;
import com.github.achaaab.solitaire.presentation.dragndrop.DropTargetManager;
import com.github.achaaab.solitaire.presentation.dragndrop.StackSourcePresentation;
import com.github.achaaab.solitaire.presentation.dragndrop.StackTargetPresentation;

import java.awt.Point;

import static com.github.achaaab.solitaire.abstraction.Pile.CAPACITY;
import static com.github.achaaab.solitaire.presentation.theme.ThemeManager.computeHeight;
import static com.github.achaaab.solitaire.presentation.theme.ThemeManager.getTheme;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class PilePresentation extends StackPresentation
		implements StackTargetPresentation, StackSourcePresentation {

	private final DropTargetManager dropTargetManager;
	private final DragSourceManager dragSourceManager;

	/**
	 * @param control
	 * @since 0.0.0
	 */
	public PilePresentation(PileControl control) {

		super(control, CAPACITY, 0, computeHeight(0.13));

		setDefaultBorder();

		dropTargetManager = new DropTargetManager(control, this);
		dragSourceManager = new DragSourceManager(control, this);
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
