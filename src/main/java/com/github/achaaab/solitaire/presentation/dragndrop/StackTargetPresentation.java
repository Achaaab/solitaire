package com.github.achaaab.solitaire.presentation.dragndrop;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public interface StackTargetPresentation {

	/**
	 * @since 0.0.0
	 */
	void acceptDrop();

	/**
	 * @since 0.0.0
	 */
	default void rejectDrop() {

	}
}
