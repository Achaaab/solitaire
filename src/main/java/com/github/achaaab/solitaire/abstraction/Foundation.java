package com.github.achaaab.solitaire.abstraction;

import static com.github.achaaab.solitaire.abstraction.Rank.ACE;

/**
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
		var rank = card.getRank();

		if (isEmpty()) {

			accept = rank == ACE;

		} else {

			var suit = card.getSuit();
			var firstCard = getFirst();
			var firstRank = firstCard.getRank();
			var firstSuit = firstCard.getSuit();

			accept = rank.ordinal() == firstRank.ordinal() + 1 && suit == firstSuit;
		}

		return accept;
	}

	@Override
	public boolean canPush(Stack stack) {
		return stack.size() == 1 && canPush(stack.getFirst());
	}
}
