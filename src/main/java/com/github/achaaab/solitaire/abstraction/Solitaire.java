package com.github.achaaab.solitaire.abstraction;

import java.time.Duration;
import java.util.List;

import static java.lang.Math.min;
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

	protected final Rules rules;
	protected final Stack deck;
	protected final Stock stock;
	protected final Waste waste;
	protected final List<Foundation> foundations;
	protected final List<Pile> piles;

	private int turnedCardCount;
	private int maxPassCount;
	private int passCount;

	/**
	 * Creates a new Solitaire.
	 *
	 * @param factory component factory
	 * @since 0.0.0
	 */
	public Solitaire(Factory factory) {

		rules = factory.newRules();
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
	 * Transfers all the cards from the different stacks to the deck.
	 * Then, shuffles the deck and sets up a new game.
	 *
	 * @since 0.0.0
	 */
	public void reset() {

		turnedCardCount = rules.getTurnedCardCount();
		maxPassCount = rules().getPassCount();
		passCount = 1;

		transfer(waste, deck);
		transfer(stock, deck);

		for (var foundation : foundations) {
			transfer(foundation, deck);
		}

		for (var pile : piles) {
			transfer(pile, deck);
		}

		deck.turnFaceDown();
		shuffle(deck);

		for (var pileIndex = 0; pileIndex < PILE_COUNT; pileIndex++) {

			var pile = piles.get(pileIndex);
			transfer(deck, pile, pileIndex + 1);
		}

		for (var pile : piles) {

			delay();
			pile.flip();
		}

		delay();
		stock.push(deck);
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
	 * @since 0.0.0
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
	 * Tries to move the first card of the given stack to the first foundation accepting it.
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
	 * Dealing is the action of transferring 1 or more cards from the stock to the waste.
	 * Dealt cards are turned face up.
	 * The number of dealt cards depends on the selected rules.
	 *
	 * @return whether the user can deal
	 * @since 0.0.0
	 */
	public boolean canDeal() {
		return !stock.isEmpty();
	}

	/**
	 * Transfers cards from the stock to the waste, one by one, turning them face up.
	 * The number of transferred cards depends on the rules.
	 *
	 * @since 0.0.0
	 */
	public void deal() {

		var cardCount = min(stock.size(), turnedCardCount);

		for (var cardIndex = 0; cardIndex < cardCount; cardIndex++) {
			waste.push(stock.pop());
		}
	}

	/**
	 * @return
	 * @since 0.0.0
	 */
	public boolean canRecycle() {
		return passCount != maxPassCount && !waste.isEmpty() && stock.isEmpty();
	}

	/**
	 * @since 0.0.0
	 */
	public void recycle() {

		stock.push(waste);
		passCount++;
	}

	/**
	 * @return rules
	 * @since 0.0.0
	 */
	public Rules rules() {
		return rules;
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
}
