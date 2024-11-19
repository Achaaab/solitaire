package com.github.achaaab.solitaire.presentation.audio;

import com.adonax.audiocue.AudioCue;
import org.slf4j.Logger;

import java.time.Duration;

import static com.adonax.audiocue.AudioCue.makeStereoCue;
import static com.github.achaaab.solitaire.utility.ResourceUtility.getResourceUrl;
import static java.lang.System.nanoTime;
import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;
import static java.time.Duration.ofNanos;
import static java.util.stream.IntStream.range;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * A sound effect is an audio fully loaded in memory.
 * Playing a sound effect is non-blocking.
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class SoundEffect extends NamedAudio {

	private static final Logger LOGGER = getLogger(SoundEffect.class);

	private final int polyphony;

	private int volume;
	private AudioCue cue;
	private Duration duration;
	private Long lastPlayTime;
	private long minimumDelay;

	/**
	 * Creates a new sound effect.
	 *
	 * @param name sound effect resource name
	 * @param polyphony maximum number of concurrent instances
	 * @param volume volume in {@code [0, 10]}
	 * @since 0.0.0
	 */
	public SoundEffect(String name, int polyphony, int volume) {

		super(name);

		this.polyphony = polyphony;
		this.volume = volume;

		try {

			var url = getResourceUrl(name);
			cue = makeStereoCue(url, polyphony);
			cue.open();
			lastPlayTime = null;

			duration = ofNanos(1_000 * cue.getMicrosecondLength());
			minimumDelay = duration.dividedBy(polyphony).toNanos();

		} catch (Exception exception) {

			LOGGER.error("error while loading sound effect {}", name, exception);
			cue = null;
		}
	}

	@Override
	public void setVolume(int volume) {

		if (cue != null) {

			this.volume = volume;

			range(0, polyphony).
					filter(cue::getIsActive).
					forEach(instanceId -> cue.setVolume(instanceId, volume / VOLUME_SCALE));
		}
	}

	@Override
	public boolean play() {

		var started = false;

		if (cue != null) {

			var nanoTime = nanoTime();

			if (lastPlayTime == null || nanoTime - lastPlayTime > minimumDelay) {

				lastPlayTime = nanoTime;
				cue.play(volume / VOLUME_SCALE);
				started = true;
			}
		}

		return started;
	}

	/**
	 * Plays this audio and waits until it is completed plus a small amount of time.
	 *
	 * @since 0.0.0
	 */
	public boolean playAndWait() {

		var played = false;

		if (cue != null) {

			if (play()) {

				try {
					sleep(duration.plusMillis(100).toMillis());
				} catch (InterruptedException interruptedException) {
					currentThread().interrupt();
				}

				played = true;
			}
		}

		return played;
	}
}
