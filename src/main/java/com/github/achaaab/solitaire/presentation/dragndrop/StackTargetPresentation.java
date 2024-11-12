package com.github.achaaab.solitaire.presentation.dragndrop;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public interface StackTargetPresentation {

	/**
	 * methode appelee par le controle pour signifier que le drop est accepte sur ce
	 * composant.
	 */
	void acceptDrop();

	/**
	 * methode appelee par le controle pour signifier que le drop est refuse par ce
	 * composant.
	 */
	void rejectDrop();
}
