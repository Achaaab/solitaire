package com.github.achaaab.solitaire.abstraction;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class Rules {

	private int turnedCardCount;

	/**
	 * @since 0.0.0
	 */
	public Rules() {
		turnedCardCount = 1;
	}

	/**
	 * @return number of cards to turn
	 * @since 0.0.0
	 */
	public int getTurnedCardCount() {
		return turnedCardCount;
	}

	/**
	 * @param turnedCardCount number of cards to turn
	 * @since 0.0.0
	 */
	public void setTurnedCardCount(int turnedCardCount) {
		this.turnedCardCount = turnedCardCount;
	}
}
