package com.github.achaaab.solitaire.abstraction;

import static com.github.achaaab.solitaire.abstraction.Rank.ACE;

/**
 * A foundation is a stack in which the cards are placed face up.
 * Cards in a foundation must have the same {@link Suit} and must be ranked from {@link Rank#ACE} to {@link Rank#KING}.
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class Foundation extends Stack {

	@Override
	public void push(Card card) {

		card.setFaceUp(true);
		super.push(card);
	}

	@Override
	public boolean canPush(Card card) {

		boolean accept;
		var rank = card.rank();

		if (isEmpty()) {

			accept = rank == ACE;

		} else {

			var suit = card.suit();
			var firstCard = getFirst();
			var firstRank = firstCard.rank();
			var firstSuit = firstCard.suit();

			accept = rank.ordinal() == firstRank.ordinal() + 1 && suit == firstSuit;
		}

		return accept;
	}

	@Override
	public boolean canPush(Stack stack) {
		return stack.size() == 1 && canPush(stack.getFirst());
	}
}
