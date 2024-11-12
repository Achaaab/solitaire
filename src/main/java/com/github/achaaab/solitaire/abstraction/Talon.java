package com.github.achaaab.solitaire.abstraction;

import static com.github.achaaab.solitaire.abstraction.Solitaire.PILE_COUNT;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class Talon {

	public static final int CAPACITY = Card.COUNT - PILE_COUNT * (PILE_COUNT + 1) / 2;

	protected final Stack faceDownStack;
	protected final Stack faceUpStack;

	/**
	 * @param faceDownStack
	 * @param faceUpStack
	 */
	public Talon(FaceDownStack faceDownStack, FaceUpStack faceUpStack) {

		this.faceDownStack = faceDownStack;
		this.faceUpStack = faceUpStack;
	}

	/**
	 * @return
	 */
	public boolean canDeal() {
		return !faceDownStack.isEmpty();
	}

	/**
	 *
	 */
	public void deal() {
		faceUpStack.push(faceDownStack.pop());
	}

	/**
	 * @return
	 */
	public boolean canRecycle() {
		return faceDownStack.isEmpty() && !faceUpStack.isEmpty();
	}

	/**
	 *
	 */
	public void recycle() {
		faceDownStack.push(faceUpStack);
	}
}
