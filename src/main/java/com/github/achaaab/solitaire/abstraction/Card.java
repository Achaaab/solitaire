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
	 */
	public Rank getRank() {
		return rank;
	}

	/**
	 * @return
	 */
	public Suit getSuit() {
		return suit;
	}

	/**
	 * @return
	 */
	public boolean isFaceUp() {
		return faceUp;
	}

	/**
	 * @param faceUp
	 */
	public void setFaceUp(boolean faceUp) {
		this.faceUp = faceUp;
	}

	@Override
	public String toString() {
		return faceUp ? rank.toString() + suit.getSymbol() : "?";
	}
}
