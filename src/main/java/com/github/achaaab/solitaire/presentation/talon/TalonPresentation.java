package com.github.achaaab.solitaire.presentation.talon;

import com.github.achaaab.solitaire.control.talon.TalonControl;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static com.github.achaaab.solitaire.presentation.theme.ThemeManager.computeWidth;
import static java.awt.event.MouseEvent.BUTTON1;
import static javax.swing.Box.createRigidArea;
import static javax.swing.BoxLayout.LINE_AXIS;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class TalonPresentation extends JComponent {

	/**
	 * @param hiddenStack
	 * @param visibleStack
	 * @param control
	 */
	public TalonPresentation(
			TalonFaceDownStackPresentation hiddenStack,
			TalonFaceUpStackPresentation visibleStack,
			TalonControl control) {

		setLayout(new BoxLayout(this, LINE_AXIS));

		var gap = computeWidth(0.10);

		add(hiddenStack);
		add(createRigidArea(new Dimension(gap, 0)));
		add(visibleStack);

		hiddenStack.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent event) {

				var button = event.getButton();

				if (button == BUTTON1) {

					if (control.canDeal()) {
						control.deal();
					} else if (control.canRecycle()) {
						control.recycle();
					}
				}
			}
		});
	}
}
