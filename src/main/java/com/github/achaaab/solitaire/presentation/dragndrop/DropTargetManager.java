package com.github.achaaab.solitaire.presentation.dragndrop;

import com.github.achaaab.solitaire.control.StackTargetControl;

import javax.swing.JComponent;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.io.IOException;

import static com.github.achaaab.solitaire.presentation.dragndrop.TransferableStackPresentation.DATA_FLAVOR;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class DropTargetManager extends DropTargetAdapter {

	private final StackTargetControl targetControl;

	private TransferableStackPresentation incomingDeck;
	private DropTargetDropEvent incomingEvent;

	/**
	 * @param targetControl
	 * @param targetContainer
	 * @since 0.0.0
	 */
	public DropTargetManager(StackTargetControl targetControl, JComponent targetContainer) {

		this.targetControl = targetControl;

		new DropTarget(targetContainer, this);
	}

	@Override
	public void dragEnter(DropTargetDragEvent event) {

		try {

			var transferable = event.getTransferable();

			if (transferable.isDataFlavorSupported(DATA_FLAVOR)) {

				var transferData = transferable.getTransferData(DATA_FLAVOR);

				if (transferData instanceof TransferableStackPresentation stack) {

					incomingDeck = stack;
					targetControl.dragIn(stack.getControl());
				}
			}

		} catch (IOException | UnsupportedFlavorException cause) {

			throw new RuntimeException(cause);
		}
	}

	@Override
	public void dragExit(DropTargetEvent event) {
		incomingDeck.showAccepted(false);
	}

	@Override
	public void drop(DropTargetDropEvent event) {

		incomingEvent = event;
		targetControl.drop(incomingDeck.getControl());
	}

	/**
	 * @since 0.0.0
	 */
	public void acceptDrop() {
		incomingEvent.getDropTargetContext().dropComplete(true);
	}

	/**
	 * @since 0.0.0
	 */
	public void rejectDrop() {
		incomingEvent.rejectDrop();
	}
}
