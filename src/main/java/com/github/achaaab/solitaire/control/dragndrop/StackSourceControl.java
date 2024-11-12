package com.github.achaaab.solitaire.control.dragndrop;

import com.github.achaaab.solitaire.control.CardControl;
import com.github.achaaab.solitaire.control.TransferableStackControl;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public interface StackSourceControl {

	/**
	 * methode appelee lors de la detection d'un drag n drop
	 * @param card carte selectionne pour le drag and drop
	 */
	void dragOut(CardControl card);

	/**
	 * @param stack successfully dropped stack
	 * @since 0.0.0
	 */
	default void dropSucceeded(TransferableStackControl stack) {

	}

	/**
	 * methode appelee a la fin d'un drag n drop
	 * @param stack tas de carte du drag and drop
	 */
	void dropFailed(TransferableStackControl stack);
}
