package com.github.achaaab.solitaire.abstraction;

import java.time.Duration;
import java.util.List;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;
import static java.time.Duration.ofMillis;
import static java.util.Collections.shuffle;
import static java.util.stream.Stream.generate;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class Solitaire {

	public static final int PILE_COUNT = 7;
	private static final Duration DELAY = ofMillis(30);

	protected final Stack deck;
	protected final List<Foundation> foundations;
	protected final List<Pile> piles;
	protected final Stock stock;
	protected final Waste waste;

	/**
	 * @param factory
	 * @since 0.0.0
	 */
	public Solitaire(Factory factory) {

		deck = factory.newDeck();

		stock = factory.newStock();
		waste = factory.newWaste();

		foundations = generate(factory::newFoundation).
				limit(Suit.values().length).
				toList();

		piles = generate(factory::newPile).
				limit(PILE_COUNT).
				toList();
	}

	/**
	 * @since 0.0.0
	 */
	public void reset() {

		transfer(waste, deck);
		transfer(stock, deck);

		for (var foundation : foundations) {
			transfer(foundation, deck);
		}

		for (var pile : piles) {
			transfer(pile.faceUpStack, deck);
		}

		for (var pile : piles) {
			transfer(pile.faceDownStack, deck);
		}

		shuffle(deck);

		for (var pileIndex = 0; pileIndex < PILE_COUNT; pileIndex++) {

			var pile = piles.get(pileIndex);
			transfer(deck, pile.faceDownStack, pileIndex + 1);
		}

		for (var pile : piles) {

			delay();
			pile.flip();
		}

		delay();
		stock.push(deck);
	}

	/**
	 * @return
	 */
	public List<Foundation> foundations() {
		return foundations;
	}

	/**
	 * @return
	 */
	public List<Pile> piles() {
		return piles;
	}

	/**
	 * @return
	 * @since 0.0.0
	 */
	public Stock stock() {
		return stock;
	}

	/**
	 * @return
	 * @since 0.0.0
	 */
	public Waste waste() {
		return waste;
	}

	/**
	 *
	 */
	private void delay() {

		try {
			sleep(DELAY.toMillis());
		} catch (InterruptedException interruptedException) {
			currentThread().interrupt();
		}
	}

	/**
	 * @param source
	 * @param target
	 */
	private void transfer(Stack source, Stack target) {
		transfer(source, target, source.size());
	}

	/**
	 * @param source
	 * @param target
	 * @param count
	 */
	private void transfer(Stack source, Stack target, int count) {

		for (var index = 0; index < count; index++) {

			delay();
			target.push(source.pop());
		}
	}

	/**
	 * Tries to move the first card if the given pile to the first foundation accepting it.
	 *
	 * @param stack stack from which to move a card
	 * @since 0.0.0
	 */
	public void moveToFoundation(Stack stack) {

		if (!stack.isEmpty()) {

			var card = stack.peek();

			foundations.stream().
					filter(foundation -> foundation.canPush(card)).
					findFirst().
					ifPresent(foundation -> foundation.push(stack.pop()));
		}
	}

	/**
	 * @return
	 * @since 0.0.0
	 */
	public boolean canDeal() {
		return !stock.isEmpty();
	}

	/**
	 * @since 0.0.0
	 */
	public void deal() {
		waste.push(stock.pop());
	}

	/**
	 * @return
	 * @since 0.0.0
	 */
	public boolean canRecycle() {
		return !waste.isEmpty() && stock.isEmpty();
	}

	/**
	 * @since 0.0.0
	 */
	public void recycle() {
		stock.push(waste);
	}
}
