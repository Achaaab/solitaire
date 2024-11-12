package com.github.achaaab.solitaire.abstraction;

import static com.github.achaaab.solitaire.abstraction.Rank.KING;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class AlternateStack extends FaceUpStack {

	@Override
	public boolean canPush(Card card) {

		boolean canPush;

		if (isEmpty()) {

			canPush = card.getRank() == KING;

		} else {

			var firstCard = getFirst();

			canPush = firstCard.getRank().ordinal() == card.getRank().ordinal() + 1 &&
					firstCard.getSuit().isRed() != card.getSuit().isRed();
		}

		return canPush;
	}

	@Override
	public boolean canPush(Stack stack) {
		return stack.isEmpty() || stack.isAlternate() && canPush(stack.getLast());
	}
}
