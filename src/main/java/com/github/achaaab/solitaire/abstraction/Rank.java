package com.github.achaaab.solitaire.abstraction;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public enum Rank {

	ACE("1"),
	TWO("2"),
	THREE("3"),
	FOUR("4"),
	FIVE("5"),
	SIX("6"),
	SEVEN("7"),
	EIGHT("8"),
	NINE("9"),
	TEN("10"),
	JACK("J"),
	QUEEN("Q"),
	KING("K");

	public static final int COUNT = values().length;

	private final String string;

	/**
	 * @param string
	 * @since 0.0.0
	 */
	Rank(String string) {
		this.string = string;
	}

	@Override
	public String toString() {
		return string;
	}
}
