package com.github.achaaab.solitaire.abstraction;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class Factory {

	/**
	 * Creates a new deck.
	 *
	 * @return created deck
	 * @since 0.0.0
	 */
	public Stack newDeck() {

		var deck = newHiddenStack();

		for (var rank : Rank.values()) {

			for (var suit : Suit.values()) {
				deck.add(newCard(rank, suit));
			}
		}

		return deck;
	}

	/**
	 * @param rank
	 * @param suit
	 * @return
	 */
	public Card newCard(Rank rank, Suit suit) {
		return new Card(rank, suit);
	}

	/**
	 * @return
	 */
	public Stack newHiddenStack() {
		return newFaceUpStack();
	}

	/**
	 * @return
	 */
	public FaceUpStack newFaceUpStack() {
		return new FaceUpStack();
	}

	/**
	 * @return
	 */
	public FaceDownStack newFaceDownStack() {
		return new FaceDownStack();
	}

	/**
	 * @return
	 */
	public Foundation newFoundation() {
		return new Foundation();
	}

	/**
	 * @return
	 */
	public AlternateStack newAlternateStack() {
		return new AlternateStack();
	}

	/**
	 * @return
	 */
	public Pile newPile() {
		return new Pile(newFaceDownStack(), newAlternateStack());
	}

	/**
	 * @return
	 */
	public Talon newTalon() {
		return new Talon(newFaceDownStack(), newFaceUpStack());
	}

	/**
	 * @return
	 */
	public Solitaire newSolitaire() {
		return new Solitaire(this);
	}
}
