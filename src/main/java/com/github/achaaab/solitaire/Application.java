package com.github.achaaab.solitaire;

import com.github.achaaab.solitaire.control.ControlFactory;
import com.github.achaaab.solitaire.presentation.PresentationFactory;
import com.github.achaaab.solitaire.presentation.theme.ByronKnollTheme;
import org.slf4j.Logger;

import static com.github.achaaab.solitaire.presentation.theme.ThemeManager.setTheme;
import static javax.swing.UIManager.getSystemLookAndFeelClassName;
import static javax.swing.UIManager.setLookAndFeel;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class Application {

	private static final Logger LOGGER = getLogger(Application.class);

	/**
	 * @param arguments none
	 * @since 0.0.0
	 */
	public static void main(String... arguments) {

		try {
			setLookAndFeel(getSystemLookAndFeelClassName());
		} catch (Exception exception) {
			LOGGER.error(exception.getMessage(), exception);
		}

		setTheme(ByronKnollTheme.INSTANCE);
		var solitaire = ControlFactory.INSTANCE.newSolitaire();
		PresentationFactory.INSTANCE.newWindow(solitaire);

		solitaire.reset();
	}
}
