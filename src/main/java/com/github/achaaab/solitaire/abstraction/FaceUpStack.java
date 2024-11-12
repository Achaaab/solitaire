package com.github.achaaab.solitaire.abstraction;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class FaceUpStack extends Stack {

	@Override
	public void push(Card card) {

		card.setFaceUp(true);
		super.push(card);
	}
}
