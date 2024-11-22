package com.github.achaaab.solitaire.abstraction;

import org.junit.jupiter.api.Test;

import static com.github.achaaab.solitaire.abstraction.Rank.ACE;
import static com.github.achaaab.solitaire.abstraction.Suit.SPADE;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests of {@link Card}.
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
class CardTest {

	@Test
	void card() {

		var card = new Card(ACE, SPADE);

		assertFalse(card.isFaceUp());
		assertSame(ACE, card.rank());
		assertSame(SPADE, card.suit());
	}

	@Test
	void faceUp() {

		var card = new Card(ACE, SPADE);

		card.setFaceUp(true);
		assertTrue(card.isFaceUp());
		card.setFaceUp(false);
		assertFalse(card.isFaceUp());
	}
}
