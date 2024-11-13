package com.github.achaaab.solitaire.abstraction;

import static com.github.achaaab.solitaire.abstraction.Solitaire.PILE_COUNT;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class Pile {

	public static final int HIDDEN_CAPACITY = PILE_COUNT;
	public static final int VISIBLE_CAPACITY = Rank.COUNT;

	protected final FaceDownStack faceDownStack;
	protected final AlternateStack faceUpStack;

	/**
	 * @since 0.0.0
	 */
	public Pile(FaceDownStack faceDownStack, AlternateStack faceUpStack) {

		this.faceDownStack = faceDownStack;
		this.faceUpStack = faceUpStack;
	}

	/**
	 * @return
	 * @since 0.0.0
	 */
	public boolean canFlip() {
		return !faceDownStack.isEmpty() && faceUpStack.isEmpty();
	}

	/**
	 * @since 0.0.0
	 */
	public void flip() {
		faceUpStack.push(faceDownStack.pop());
	}
}
