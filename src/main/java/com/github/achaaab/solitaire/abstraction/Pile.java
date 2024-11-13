package com.github.achaaab.solitaire.abstraction;

import static com.github.achaaab.solitaire.abstraction.Rank.KING;
import static com.github.achaaab.solitaire.abstraction.Solitaire.PILE_COUNT;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class Pile extends Stack {

	public static final int CAPACITY = PILE_COUNT + Rank.COUNT;

	/**
	 * @return
	 * @since 0.0.0
	 */
	public boolean canFlip() {
		return !isEmpty() && !getFirst().isFaceUp();
	}

	/**
	 * @since 0.0.0
	 */
	public void flip() {
		getFirst().setFaceUp(true);
	}

	@Override
	public boolean canPush(Card card) {

		boolean canPush;

		if (isEmpty()) {

			canPush = card.getRank() == KING;

		} else {

			var firstCard = getFirst();

			canPush = firstCard.isFaceUp() &&
					firstCard.getRank().ordinal() == card.getRank().ordinal() + 1 &&
					firstCard.getSuit().isRed() != card.getSuit().isRed();
		}

		return canPush;
	}

	@Override
	public boolean canPush(Stack stack) {
		return stack.isEmpty() || stack.isAlternate() && canPush(stack.getLast());
	}
}
