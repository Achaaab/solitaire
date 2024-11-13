package com.github.achaaab.solitaire.control.dragndrop;

import com.github.achaaab.solitaire.control.TransferableStackControl;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public interface StackTargetControl {

	/**
	 * @param stack
	 * @since 0.0.0
	 */
	void dragIn(TransferableStackControl stack);

	/**
	 * @param stack
	 * @since 0.0.0
	 */
	void drop(TransferableStackControl stack);
}
