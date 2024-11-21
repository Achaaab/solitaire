package com.github.achaaab.solitaire.control;

import com.github.achaaab.solitaire.presentation.audio.SoundEffect;
import com.github.achaaab.solitaire.presentation.MessagePresentation;

import static com.github.achaaab.solitaire.presentation.theme.ThemeManager.getTheme;

/**
 * Control part of a Message component.
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class MessageControl {

	private final MessagePresentation presentation;

	private String html;
	private boolean visible;

	/**
	 * @since 0.0.0
	 */
	public MessageControl() {

		presentation = new MessagePresentation(this);

		html = null;
		visible = false;
	}

	/**
	 * Displays an HTML document.
	 *
	 * @param html HTML document to display
	 * @since 0.0.0
	 */
	public void display(String html) {

		this.html = html;

		presentation.display(html);
		visible = true;
	}

	/**
	 * Hides the current HTML document.
	 *
	 * @since 0.0.0
	 */
	public void hide() {

		getTheme().getUnderstoodSound().ifPresent(SoundEffect::play);

		visible = false;
		presentation.setVisible(false);
	}

	/**
	 * @return whether there is a currently displayed HTML document
	 * @since 0.0.0
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * @return current HTML document
	 * @since 0.0.0
	 */
	public String getHtml() {
		return html;
	}

	/**
	 * @return presentation part of this Message component
	 * @since 0.0.0
	 */
	public MessagePresentation presentation() {
		return presentation;
	}
}
