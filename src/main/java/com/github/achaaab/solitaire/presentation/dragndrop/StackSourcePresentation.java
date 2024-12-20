package com.github.achaaab.solitaire.presentation.dragndrop;

import com.github.achaaab.solitaire.control.DraggedStack;

import java.awt.Point;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public interface StackSourcePresentation {

	/**
	 * @param sourceLocation source location of the dragged card
	 * @param stack stack of cards being dragged
	 * @since 0.0.0
	 */
	void initiateDragAndDrop(Point sourceLocation, DraggedStack stack);
}
