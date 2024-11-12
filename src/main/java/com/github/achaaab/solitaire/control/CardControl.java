package com.github.achaaab.solitaire.control;

import com.github.achaaab.solitaire.abstraction.Rank;
import com.github.achaaab.solitaire.abstraction.Suit;
import com.github.achaaab.solitaire.abstraction.Card;
import com.github.achaaab.solitaire.presentation.CardPresentation;
import com.github.achaaab.solitaire.presentation.PresentationFactory;

import static javax.swing.SwingUtilities.invokeLater;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class CardControl extends Card {

	private final CardPresentation presentation;

	/**
	 * @param rank
	 * @param suit
	 */
	public CardControl(Rank rank, Suit suit) {

		super(rank, suit);

		presentation = PresentationFactory.INSTANCE.newCard(this);
	}

	@Override
	public void setFaceUp(boolean faceUp) {

		super.setFaceUp(faceUp);

		invokeLater(() -> presentation.setFaceVisible(isFaceUp()));
	}

	/**
	 * @return
	 */
	public CardPresentation getPresentation() {
		return presentation;
	}
}
