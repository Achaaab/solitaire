package com.github.achaaab.solitaire.presentation;

import com.github.achaaab.solitaire.control.DraggedStack;
import com.github.achaaab.solitaire.control.PileControl;
import com.github.achaaab.solitaire.control.WasteControl;
import com.github.achaaab.solitaire.presentation.audio.SoundEffect;
import com.github.achaaab.solitaire.presentation.dragndrop.DragSourceManager;
import com.github.achaaab.solitaire.presentation.dragndrop.DropTargetManager;
import com.github.achaaab.solitaire.presentation.dragndrop.StackSourcePresentation;
import com.github.achaaab.solitaire.presentation.dragndrop.StackTargetPresentation;

import java.awt.Point;
import java.util.Map;

import static com.github.achaaab.solitaire.abstraction.Waste.CAPACITY;
import static com.github.achaaab.solitaire.presentation.theme.ThemeManager.computeHeight;
import static com.github.achaaab.solitaire.presentation.theme.ThemeManager.computeWidth;
import static com.github.achaaab.solitaire.presentation.theme.ThemeManager.getTheme;
import static com.github.achaaab.solitaire.utility.ResourceUtility.getMessage;
import static com.github.achaaab.solitaire.utility.SwingUtility.scale;

/**
 * Presentation part of a Waste component.
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class WastePresentation extends StackPresentation
		implements StackSourcePresentation, StackTargetPresentation {

	private static int missedDropCount;
	private static final String MISSED_DROP_MESSAGE;

	static {

		missedDropCount = 0;

		MISSED_DROP_MESSAGE = getMessage("messages/waste.html", Map.of(
				"font_size", scale(16)));
	}

	private final DragSourceManager dragSourceManager;
	private final DropTargetManager dropTargetManager;

	/**
	 * Creates the presentation part of a Waste component.
	 *
	 * @param control control part of a waste component
	 * @since 0.0.0
	 */
	public WastePresentation(WasteControl control) {

		super(control, CAPACITY, computeWidth(0.01), computeHeight(0.003));

		dragSourceManager = new DragSourceManager(control, this);
		dropTargetManager = new DropTargetManager(control, this);
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

	@Override
	public void acceptDrop() {
		dropTargetManager.acceptDrop();
	}

	@Override
	public void rejectDrop() {

		missedDropCount++;

		if (missedDropCount == MISSED_DROP_COUNT_BEFORE_HELP) {

			control().displayMessage(MISSED_DROP_MESSAGE);
			missedDropCount = 0;
		}
	}

	/**
	 * Returns the control part of this presentation.
	 *
	 * @return control part of this presentation
	 * @since 0.0.0
	 */
	public WasteControl control() {
		return (WasteControl) control;
	}
}
