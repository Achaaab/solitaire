package com.github.achaaab.solitaire.abstraction;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class Waste extends Stack {

	public static final int CAPACITY = Stock.CAPACITY;

	@Override
	public void push(Card card) {

		card.setFaceUp(true);
		super.push(card);
	}
}
