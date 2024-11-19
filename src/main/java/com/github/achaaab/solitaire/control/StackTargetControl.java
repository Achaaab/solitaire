package com.github.achaaab.solitaire.control;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public interface StackTargetControl {

	/**
	 * @param stack stack being dragged in
	 * @since 0.0.0
	 */
	void dragIn(DraggedStack stack);

	/**
	 * @param stack stack being dropped
	 * @since 0.0.0
	 */
	void drop(DraggedStack stack);
}
