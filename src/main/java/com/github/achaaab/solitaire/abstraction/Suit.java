package com.github.achaaab.solitaire.abstraction;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public enum Suit {

	DIAMOND(true, 'D', '♦'),
	HEART(true, 'H', '♥'),
	SPADE(false, 'S', '♠'),
	CLUB(false, 'C', '♣');

	public static final int COUNT = values().length;

	private final boolean red;
	private final char letter;
	private final char symbol;

	/**
	 * @param red
	 * @param letter
	 * @param symbol
	 */
	Suit(boolean red, char letter, char symbol) {

		this.red = red;
		this.letter = letter;
		this.symbol = symbol;
	}

	/**
	 * @return
	 */
	public boolean isRed() {
		return red;
	}

	/**
	 * @return
	 */
	public char getLetter() {
		return letter;
	}

	/**
	 * @return
	 */
	public char getSymbol() {
		return symbol;
	}
}
