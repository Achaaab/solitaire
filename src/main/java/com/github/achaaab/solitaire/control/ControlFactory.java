package com.github.achaaab.solitaire.control;

import com.github.achaaab.solitaire.abstraction.Factory;
import com.github.achaaab.solitaire.abstraction.Rank;
import com.github.achaaab.solitaire.abstraction.Suit;
import com.github.achaaab.solitaire.control.foundation.FoundationControl;
import com.github.achaaab.solitaire.control.message.MessageControl;
import com.github.achaaab.solitaire.control.pile.PileControl;
import com.github.achaaab.solitaire.control.pile.PileFaceDownStackControl;
import com.github.achaaab.solitaire.control.pile.PileFaceUpStackControl;
import com.github.achaaab.solitaire.control.talon.TalonControl;
import com.github.achaaab.solitaire.control.talon.TalonFaceDownStackControl;
import com.github.achaaab.solitaire.control.talon.TalonFaceUpStackControl;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class ControlFactory extends Factory {

	public static final ControlFactory INSTANCE = new ControlFactory();

	/**
	 * le constructeur prive,juste pour empecher l'instanciation
	 */
	private ControlFactory(){

	}

	@Override
	public StackControl newHiddenStack() {
		return new StackControl();
	}

	@Override
	public CardControl newCard(Rank rank, Suit suit){
		return new CardControl(rank, suit);
	}

	@Override
	public FoundationControl newFoundation(){
		return new FoundationControl();
	}

	@Override
	public PileControl newPile() {
		return new PileControl(newPileFaceDownStack(), newPileFaceUpStack());
	}

	/**
	 * @return
	 */
	public PileFaceDownStackControl newPileFaceDownStack() {
		return new PileFaceDownStackControl();
	}

	/**
	 * @return
	 */
	public PileFaceUpStackControl newPileFaceUpStack() {
		return new PileFaceUpStackControl();
	}

	@Override
	public TalonControl newTalon() {
		return new TalonControl(newTalonFaceDownStack(), newTalonFaceUpStack());
	}

	/**
	 * @return
	 */
	public TalonFaceDownStackControl newTalonFaceDownStack() {
		return new TalonFaceDownStackControl();
	}

	/**
	 * @return
	 */
	public TalonFaceUpStackControl newTalonFaceUpStack() {
		return new TalonFaceUpStackControl();
	}

	/**
	 * @return
	 */
	public TransferableStackControl newTransferableStack(int capacity) {
		return new TransferableStackControl(capacity);
	}

	@Override
	public SolitaireControl newSolitaire() {
		return new SolitaireControl();
	}

	/**
	 * @return
	 * @since 0.0.0
	 */
	public MessageControl newMessage() {
		return new MessageControl();
	}
}
