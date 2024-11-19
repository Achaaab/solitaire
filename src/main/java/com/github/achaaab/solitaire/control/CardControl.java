package com.github.achaaab.solitaire.control;

import com.github.achaaab.solitaire.abstraction.Card;
import com.github.achaaab.solitaire.abstraction.Rank;
import com.github.achaaab.solitaire.abstraction.Suit;
import com.github.achaaab.solitaire.presentation.CardPresentation;
import com.github.achaaab.solitaire.presentation.PresentationFactory;

/**
 * Control part of a card component.
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class CardControl extends Card {

	private final CardPresentation presentation;

	/**
	 * Creates a new card.
	 *
	 * @param rank rank of the card to create
	 * @param suit suit of the card to create
	 * @since 0.0.0
	 */
	public CardControl(Rank rank, Suit suit) {

		super(rank, suit);

		presentation = PresentationFactory.INSTANCE.newCard(this);
	}

	@Override
	public void setFaceUp(boolean faceUp) {

		super.setFaceUp(faceUp);

		presentation.setFaceUp(isFaceUp());
	}

	/**
	 * @return presentation part of this card
	 * @since 0.0.0
	 */
	public CardPresentation presentation() {
		return presentation;
	}
}
