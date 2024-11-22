package com.github.achaaab.solitaire.abstraction;

/**
 * A playing card, defined by its rank and its suit.
 * A card is not immutable, it can be turned face up or face down.
 *
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
	 * @param rank rank of the card to create
	 * @param suit suit of the card to create
	 * @since 0.0.0
	 */
	public Card(Rank rank, Suit suit) {

		this.rank = rank;
		this.suit = suit;

		faceUp = false;
	}

	/**
	 * @return rank of this card
	 * @since 0.0.0
	 */
	public Rank rank() {
		return rank;
	}

	/**
	 * @return suit of this card
	 * @since 0.0.0
	 */
	public Suit suit() {
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
