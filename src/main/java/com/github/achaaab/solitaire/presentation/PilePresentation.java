package com.github.achaaab.solitaire.presentation;

import com.github.achaaab.solitaire.control.DraggedStack;
import com.github.achaaab.solitaire.control.PileControl;
import com.github.achaaab.solitaire.presentation.audio.SoundEffect;
import com.github.achaaab.solitaire.presentation.dragndrop.DragSourceManager;
import com.github.achaaab.solitaire.presentation.dragndrop.DropTargetManager;
import com.github.achaaab.solitaire.presentation.dragndrop.StackSourcePresentation;
import com.github.achaaab.solitaire.presentation.dragndrop.StackTargetPresentation;

import java.awt.Point;
import java.util.Map;

import static com.github.achaaab.solitaire.abstraction.Pile.CAPACITY;
import static com.github.achaaab.solitaire.utility.SwingUtility.scale;
import static com.github.achaaab.solitaire.presentation.theme.ThemeManager.computeHeight;
import static com.github.achaaab.solitaire.presentation.theme.ThemeManager.getTheme;
import static com.github.achaaab.solitaire.utility.ResourceUtility.getMessage;

/**
 * Presentation part of a pile component.
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class PilePresentation extends StackPresentation
		implements StackTargetPresentation, StackSourcePresentation {

	private static int missedDropCount;
	private static final String MISSED_DROP_MESSAGE;

	static {

		missedDropCount = 0;

		MISSED_DROP_MESSAGE = getMessage("messages/pile.html", Map.of(
				"font_size", scale(16),
				"card_font_size", scale(30),
				"item_margin", scale(10)));
	}

	private final DropTargetManager dropTargetManager;
	private final DragSourceManager dragSourceManager;

	/**
	 * Creates the presentation part of a pile component.
	 *
	 * @param control control part of a pile component
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

		missedDropCount++;

		if (missedDropCount == MISSED_DROP_COUNT_BEFORE_HELP) {

			control().displayMessage(MISSED_DROP_MESSAGE);
			missedDropCount = 0;
		}
	}

	@Override
	public void initiateDragAndDrop(Point sourceLocation, DraggedStack stack) {
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

	/**
	 * Returns the control part of this presentation.
	 *
	 * @return control part of this presentation
	 * @since 0.0.0
	 */
	public PileControl control() {
		return (PileControl) control;
	}
}
