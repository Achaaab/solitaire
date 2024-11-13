package com.github.achaaab.solitaire.control.dragndrop;

import com.github.achaaab.solitaire.control.CardControl;
import com.github.achaaab.solitaire.control.TransferableStackControl;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public interface StackSourceControl {

	/**
	 * @param card
	 * @since 0.0.0
	 */
	void dragOut(CardControl card);

	/**
	 * @param stack successfully dropped stack
	 * @since 0.0.0
	 */
	default void dropSucceeded(TransferableStackControl stack) {

	}

	/**
	 * @param stack
	 * @since 0.0.0
	 */
	void dropFailed(TransferableStackControl stack);
}
