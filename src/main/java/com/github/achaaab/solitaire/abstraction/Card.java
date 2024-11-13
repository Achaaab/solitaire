package com.github.achaaab.solitaire.abstraction;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class Card {

	public static final int COUNT = Rank.COUNT * Suit.COUNT;

	private final Rank rank;
	private final Suit suit;

	private boolean faceUp;

	/**
	 * Creates a new card, initially turned face down.
	 *
	 * @param rank
	 * @param suit
	 */
	public Card(Rank rank, Suit suit) {

		this.rank = rank;
		this.suit = suit;

		faceUp = false;
	}

	/**
	 * @return
	 * @since 0.0.0
	 */
	public Rank getRank() {
		return rank;
	}

	/**
	 * @return
	 * @since 0.0.0
	 */
	public Suit getSuit() {
		return suit;
	}

	/**
	 * @return whether this card is turned face up
	 * @since 0.0.0
	 */
	public boolean isFaceUp() {
		return faceUp;
	}

	/**
	 * @param faceUp whether to turn this card face up or face down
	 * @since 0.0.0
	 */
	public void setFaceUp(boolean faceUp) {
		this.faceUp = faceUp;
	}

	@Override
	public String toString() {
		return faceUp ? rank.toString() + suit.getSymbol() : "?";
	}
}
