package com.github.achaaab.solitaire.abstraction;

import static com.github.achaaab.solitaire.abstraction.Solitaire.PILE_COUNT;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class Stock extends Stack {

	public static final int CAPACITY = Card.COUNT - PILE_COUNT * (PILE_COUNT + 1) / 2;

	@Override
	public void push(Card card) {

		card.setFaceUp(false);
		super.push(card);
	}
}
