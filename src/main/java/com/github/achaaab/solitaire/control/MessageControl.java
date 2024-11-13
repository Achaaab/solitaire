package com.github.achaaab.solitaire.control;

import com.github.achaaab.solitaire.presentation.audio.SoundEffect;
import com.github.achaaab.solitaire.presentation.MessagePresentation;

import static com.github.achaaab.solitaire.presentation.theme.ThemeManager.getTheme;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class MessageControl {

	private final MessagePresentation presentation;

	private String text;
	private boolean visible;

	/**
	 * @since 0.0.0
	 */
	public MessageControl() {

		presentation = new MessagePresentation(this);

		text = null;
		visible = false;
	}

	/**
	 * @param text
	 * @since 0.0.0
	 */
	public void display(String text) {

		this.text = text;

		presentation.display(text);
		visible = true;
	}

	/**
	 * @since 0.0.0
	 */
	public void hide() {

		getTheme().getUnderstoodSound().ifPresent(SoundEffect::play);
		visible = false;
		presentation.setVisible(false);
	}

	/**
	 * @return
	 * @since 0.0.0
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * @return
	 * @since 0.0.0
	 */
	public String getText() {
		return text;
	}

	/**
	 * @return
	 * @since 0.0.0
	 */
	public MessagePresentation presentation() {
		return presentation;
	}
}
