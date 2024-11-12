package com.github.achaaab.solitaire.presentation;

import com.github.achaaab.solitaire.control.CardControl;
import com.github.achaaab.solitaire.control.foundation.FoundationControl;
import com.github.achaaab.solitaire.control.pile.PileControl;
import com.github.achaaab.solitaire.control.pile.PileFaceDownStackControl;
import com.github.achaaab.solitaire.control.pile.PileFaceUpStackControl;
import com.github.achaaab.solitaire.control.SolitaireControl;
import com.github.achaaab.solitaire.control.talon.TalonControl;
import com.github.achaaab.solitaire.control.talon.TalonFaceDownStackControl;
import com.github.achaaab.solitaire.control.talon.TalonFaceUpStackControl;
import com.github.achaaab.solitaire.control.TransferableStackControl;
import com.github.achaaab.solitaire.presentation.dragndrop.TransferableStackPresentation;
import com.github.achaaab.solitaire.presentation.foundation.FoundationPresentation;
import com.github.achaaab.solitaire.presentation.pile.PileFaceDownStackPresentation;
import com.github.achaaab.solitaire.presentation.pile.PileFaceUpStackPresentation;
import com.github.achaaab.solitaire.presentation.pile.PilePresentation;
import com.github.achaaab.solitaire.presentation.talon.TalonFaceDownStackPresentation;
import com.github.achaaab.solitaire.presentation.talon.TalonFaceUpStackPresentation;
import com.github.achaaab.solitaire.presentation.talon.TalonPresentation;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class PresentationFactory {

	public static final PresentationFactory INSTANCE = new PresentationFactory();

	/**
	 * le constructeur prive,juste pour empecher l'instanciation
	 */
	private PresentationFactory() {

	}

	/**
	 * @param control
	 * @return
	 */
	public TalonPresentation newTalon(TalonControl control) {

		return new TalonPresentation(
				control.faceDownStack().getPresentation(),
				control.faceUpStack().presentation(),
				control);
	}

	/**
	 * @param control
	 * @return
	 */
	public TalonFaceDownStackPresentation newTalonFaceDownStack(TalonFaceDownStackControl control) {
		return new TalonFaceDownStackPresentation(control);
	}

	/**
	 * @param control
	 * @return
	 */
	public TalonFaceUpStackPresentation newTalonFaceUpStack(TalonFaceUpStackControl control) {
		return new TalonFaceUpStackPresentation(control);
	}

	/**
	 * @param control
	 * @return
	 */
	public PilePresentation newPile(PileControl control) {

		return new PilePresentation(
				control.faceDownStack().getPresentation(),
				control.faceUpStack().presentation(),
				control);
	}

	/**
	 * @param control
	 * @return
	 */
	public PileFaceDownStackPresentation newPileHiddenStack(PileFaceDownStackControl control) {
		return new PileFaceDownStackPresentation(control);
	}

	/**
	 * @param control
	 * @return
	 */
	public PileFaceUpStackPresentation newPileVisibleStack(PileFaceUpStackControl control) {
		return new PileFaceUpStackPresentation(control);
	}

	/**
	 * @param control
	 * @return
	 */
	public CardPresentation newCard(CardControl control) {
		return new CardPresentation(control);
	}

	/**
	 * @param control
	 * @return
	 */
	public TransferableStackPresentation newTransferableStack(TransferableStackControl control) {
		return new TransferableStackPresentation(control);
	}

	/**
	 * @param control
	 * @return
	 */
	public FoundationPresentation newFoundation(FoundationControl control) {
		return new FoundationPresentation(control);
	}

	/**
	 * @param solitaire
	 * @return
	 */
	public SolitaireWindow newWindow(SolitaireControl solitaire) {
		return new SolitaireWindow(solitaire);
	}
}