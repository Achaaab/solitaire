package com.github.achaaab.solitaire.control;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public interface StackSourceControl {

	/**
	 * @param card card being dragged out
	 * @since 0.0.0
	 */
	void dragOut(CardControl card);

	/**
	 * @param stack successfully dropped stack
	 * @since 0.0.0
	 */
	default void dropSucceeded(DraggedStack stack) {

	}

	/**
	 * @param stack stack which could not be dropped
	 * @since 0.0.0
	 */
	void dropFailed(DraggedStack stack);
}
