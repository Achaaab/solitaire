package com.github.achaaab.solitaire.abstraction;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class FaceDownStack extends Stack {

	@Override
	public void push(Card card) {

		card.setFaceUp(false);
		super.push(card);
	}
}
