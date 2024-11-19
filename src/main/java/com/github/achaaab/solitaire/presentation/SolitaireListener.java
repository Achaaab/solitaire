package com.github.achaaab.solitaire.presentation;

import com.github.achaaab.solitaire.presentation.audio.SoundEffect;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static com.github.achaaab.solitaire.presentation.theme.ThemeManager.getTheme;
import static java.lang.System.exit;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class SolitaireListener extends WindowAdapter {

	@Override
	public void windowClosing(WindowEvent event) {

		getTheme().getExitSound().ifPresent(SoundEffect::playAndWait);
		exit(0);
	}

	@Override
	public void windowOpened(WindowEvent event) {
		getTheme().getStartSound().ifPresent(SoundEffect::play);
	}
}
