package com.github.achaaab.solitaire.abstraction;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class Rules {

	private int turnedCardCount;
	private int recycleCount;

	/**
	 * @since 0.0.0
	 */
	public Rules() {

		turnedCardCount = 1;
		recycleCount = -1;
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

	/**
	 * @return how many times the waste can be recycled, {@code -1} if there is no limit
	 * @since 0.0.0
	 */
	public int getRecycleCount() {
		return recycleCount;
	}

	/**
	 * @param recycleCount how many times the waste can be recycled, {@code -1} if there is no limit
	 * @since 0.0.0
	 */
	public void setRecycleCount(int recycleCount) {
		this.recycleCount = recycleCount;
	}
}
