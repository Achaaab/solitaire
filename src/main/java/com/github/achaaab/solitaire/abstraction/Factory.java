package com.github.achaaab.solitaire.abstraction;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class Factory {

	/**
	 * @return rules
	 * @since 0.0.0
	 */
	public Rules newRules() {
		return new Rules();
	}

	/**
	 * Creates a new deck.
	 *
	 * @return created deck
	 * @since 0.0.0
	 */
	public Stack newDeck() {

		var deck = newStack();

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
	public Stack newStack() {
		return new Stack();
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
	public Pile newPile() {
		return new Pile();
	}

	/**
	 * @return
	 * @since 0.0.0
	 */
	public Stock newStock() {
		return new Stock();
	}

	/**
	 * @return
	 * @since 0.0.0
	 */
	public Waste newWaste() {
		return new Waste();
	}

	/**
	 * @return
	 */
	public Solitaire newSolitaire() {
		return new Solitaire(this);
	}
}
