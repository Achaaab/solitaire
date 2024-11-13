package com.github.achaaab.solitaire.control;

import com.github.achaaab.solitaire.abstraction.Solitaire;
import com.github.achaaab.solitaire.control.pile.PileControl;
import com.github.achaaab.solitaire.control.pile.PileFaceUpStackControl;
import com.github.achaaab.solitaire.presentation.SolitairePresentation;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static java.awt.event.MouseEvent.BUTTON1;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class SolitaireControl extends Solitaire {

	private final SolitairePresentation presentation;
	private final MessageControl message;

	/**
	 * @since 0.0.0
	 */
	public SolitaireControl() {

		super(ControlFactory.INSTANCE);

		message = ControlFactory.INSTANCE.newMessage();
		presentation = new SolitairePresentation(this);

		for (var pile : piles) {

			((PileControl) pile).faceUpStack().setMessage(message);
			((PileControl) pile).setSolitaire(this);
		}

		for (var foundation : foundations()) {
			((FoundationControl) foundation).setMessage(message);
		}

		stock().presentation().addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent event) {

				var button = event.getButton();

				if (button == BUTTON1) {

					if (canDeal()) {
						deal();
					} else if (canRecycle()) {
						recycle();
					}
				}
			}
		});

		waste().presentation().addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent event) {

				var button = event.getButton();
				var clickCount = event.getClickCount();

				if (button == BUTTON1 && clickCount == 2) {
					moveToFoundation(waste);
				}
			}
		});
	}

	/**
	 * @param pile
	 * @since 0.0.0
	 */
	public void moveToFoundation(PileFaceUpStackControl pile) {

		super.moveToFoundation(pile);

		if (pile.getPile().canFlip()) {
			pile.getPile().flip();
		}
	}

	/**
	 * @return
	 * @since 0.0.0
	 */
	public SolitairePresentation presentation() {
		return presentation;
	}

	@Override
	public StockControl stock() {
		return (StockControl) stock;
	}

	@Override
	public WasteControl waste() {
		return (WasteControl) waste;
	}

	/**
	 * @return
	 * @since 0.0.0
	 */
	public MessageControl message() {
		return message;
	}
}
