package com.github.achaaab.solitaire.presentation.dragndrop;

import com.github.achaaab.solitaire.control.DraggedStack;
import com.github.achaaab.solitaire.control.StackSourceControl;
import com.github.achaaab.solitaire.presentation.CardPresentation;

import javax.swing.JComponent;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceAdapter;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;

import static java.awt.Cursor.HAND_CURSOR;
import static java.awt.dnd.DnDConstants.ACTION_MOVE;
import static javax.swing.SwingUtilities.convertPoint;
import static javax.swing.SwingUtilities.convertPointFromScreen;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class DragSourceManager extends DragSourceAdapter {

	private static final Cursor CURSOR = new Cursor(HAND_CURSOR);

	private final DragSource dragSource;
	private final StackSourceControl sourceControl;
	private final JComponent sourceContainer;

	private DragGestureEvent trigger;
	private Point cursorLocationOnCard;
	private DraggedStack stack;

	/**
	 * @param sourceControl
	 * @param sourceContainer
	 * @since 0.0.0
	 */
	public DragSourceManager(
			StackSourceControl sourceControl,
			JComponent sourceContainer) {

		this.sourceControl = sourceControl;
		this.sourceContainer = sourceContainer;

		dragSource = new DragSource();

		dragSource.addDragSourceMotionListener(this);

		dragSource.createDefaultDragGestureRecognizer(
				sourceContainer,
				ACTION_MOVE,
				this::dragGestureRecognized);
	}

	/**
	 * @param event
	 */
	public void dragGestureRecognized(DragGestureEvent event) {

		var dragOrigin = event.getDragOrigin();
		var originComponent = sourceContainer.getComponentAt(dragOrigin);

		if (originComponent instanceof CardPresentation card) {

			cursorLocationOnCard = convertPoint(sourceContainer, dragOrigin, card);
			trigger = event;
			sourceControl.dragOut(card.getControl());
		}
	}

	/**
	 * @param sourceLocation source location of the dragged card
	 * @param stack stack of cards being dragged
	 * @since 0.0.0
	 */
	public void initiateDragAndDrop(Point sourceLocation, DraggedStack stack) {

		this.stack = stack;

		var stackPresentation = stack.presentation();
		var rootPane = sourceContainer.getRootPane();

		rootPane.add(stackPresentation, 0);

		var stackLocation = convertPoint(sourceContainer, sourceLocation, rootPane);
		stackPresentation.setLocation(stackLocation);

		rootPane.validate();
		rootPane.repaint();

		dragSource.startDrag(trigger, CURSOR, stackPresentation, this);
	}

	@Override
	public void dragMouseMoved(DragSourceDragEvent event) {

		var stackPresentation = stack.presentation();
		var container = stackPresentation.getParent();

		var cursorLocationOnContainer = event.getLocation();
		convertPointFromScreen(cursorLocationOnContainer, container);

		stackPresentation.setLocation(
				cursorLocationOnContainer.x - cursorLocationOnCard.x,
				cursorLocationOnContainer.y - cursorLocationOnCard.y);
	}

	@Override
	public void dragDropEnd(DragSourceDropEvent event) {

		if (event.getDropSuccess()) {
			sourceControl.dropSucceeded(stack);
		} else {
			sourceControl.dropFailed(stack);
		}

		stack.delete();
	}
}
