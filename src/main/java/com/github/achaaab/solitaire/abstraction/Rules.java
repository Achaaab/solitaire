package com.github.achaaab.solitaire.abstraction;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class Rules {

	private int turnedCardCount;
	private int passCount;

	/**
	 * @since 0.0.0
	 */
	public Rules() {

		turnedCardCount = 1;
		passCount = -1;
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
	public int getPassCount() {
		return passCount;
	}

	/**
	 * @param passCount how many times the waste can be recycled, {@code -1} if there is no limit
	 * @since 0.0.0
	 */
	public void setPassCount(int passCount) {
		this.passCount = passCount;
	}
}
