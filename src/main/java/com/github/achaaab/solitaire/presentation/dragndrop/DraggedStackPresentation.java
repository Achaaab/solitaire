package com.github.achaaab.solitaire.presentation.dragndrop;

import com.github.achaaab.solitaire.control.DraggedStack;
import com.github.achaaab.solitaire.presentation.StackPresentation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

import static com.github.achaaab.solitaire.presentation.theme.ThemeManager.computeHeight;
import static com.github.achaaab.solitaire.presentation.theme.ThemeManager.getTheme;
import static java.awt.datatransfer.DataFlavor.javaJVMLocalObjectMimeType;
import static java.lang.Math.round;
import static java.lang.Math.toIntExact;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class DraggedStackPresentation extends StackPresentation implements Transferable {

	private static final Color ACCEPTED_COLOR = new Color(32, 255, 32, 128);
	private static final Color REJECTED_COLOR = new Color(255, 32, 32, 128);

	public static final DataFlavor DATA_FLAVOR;

	private static final DataFlavor[] DATA_FLAVORS;

	static {

		try {

			DATA_FLAVOR = new DataFlavor(javaJVMLocalObjectMimeType);
			DATA_FLAVORS = new DataFlavor[] { DATA_FLAVOR };

		} catch (ClassNotFoundException cause) {

			throw new RuntimeException(cause);
		}
	}

	private boolean accepted;

	/**
	 * Creates the presentation part for a dragged stack component.
	 *
	 * @param control control part of a dragged stack component
	 * @since 0.0.0
	 */
	public DraggedStackPresentation(DraggedStack control) {

		super(control, control.capacity(), 0, computeHeight(0.13));

		accepted = true;
	}

	@Override
	public Object getTransferData(DataFlavor dataFlavor) {
		return this;
	}

	@Override
	public DataFlavor[] getTransferDataFlavors() {
		return DATA_FLAVORS;
	}

	@Override
	public boolean isDataFlavorSupported(DataFlavor dataFlavor) {
		return dataFlavor.isMimeTypeEqual(DATA_FLAVOR);
	}

	/**
	 * @return control part of this
	 * @since 0.0.0
	 */
	public DraggedStack control() {
		return (DraggedStack) control;
	}

	/**
	 * Removes this presentation part from its parent.
	 *
	 * @since 0.0.0
	 */
	public void remove() {

		var parent = getParent();

		parent.remove(this);
		parent.validate();
		parent.repaint();
	}

	/**
	 * Shows whether this dragged stack is accepted at its current position.
	 *
	 * @param accepted whether this dragged stack is accepted at its current position
	 * @since 0.0.0
	 */
	public void showAccepted(boolean accepted) {

		this.accepted = accepted;

		repaint();
	}

	@Override
	public void paint(Graphics graphics) {

		super.paint(graphics);

		var theme = getTheme();
		var cardWidth = theme.cardWidth();
		var cardHeight = theme.cardHeight();
		var arcWidth = toIntExact(round(cardWidth * 0.09));
		var arcHeight = toIntExact(round(cardHeight * 0.06));

		graphics.setColor(accepted ? ACCEPTED_COLOR : REJECTED_COLOR);
		graphics.fillRoundRect(0, 0, getWidth(), getHeight(), arcWidth, arcHeight);
	}
}
