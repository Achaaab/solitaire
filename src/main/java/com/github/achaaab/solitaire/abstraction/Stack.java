package com.github.achaaab.solitaire.abstraction;

import java.util.LinkedList;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class Stack extends LinkedList<Card> {

	/**
	 * @param card
	 * @return
	 */
	public boolean canPush(Card card) {
		return true;
	}

	/**
	 * @param stack
	 * @return
	 */
	public boolean canPush(Stack stack) {
		return true;
	}

	/**
	 * @return
	 */
	public boolean isAlternate() {

		var alternate = true;

		var size = size();

		for (var index = 0; index < size - 1; index++) {

			var card0 = get(index);
			var card1 = get(index + 1);

			alternate = card0.getRank().ordinal() == card1.getRank().ordinal() - 1 &&
					card0.getSuit().isRed() != card1.getSuit().isRed();
		}

		return alternate;
	}

	/**
	 * @param stack
	 * @since 0.0.0
	 */
	public void push(Stack stack) {

		while (!stack.isEmpty()) {
			push(stack.pop());
		}
	}

	/**
	 * Turns all the cards of this stack face down.
	 *
	 * @since 0.0.0
	 */
	public void turnFaceDown() {
		forEach(card -> card.setFaceUp(false));
	}
}
