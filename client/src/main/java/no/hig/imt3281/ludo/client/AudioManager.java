package no.hig.imt3281.ludo.client;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Manages sounds being played in the application.
 */
public class AudioManager {
    private final static Logger LOGGER = Logger.getLogger(AudioManager.class.getSimpleName());

    private AudioManager() {
    }

    /**
     * Plays sound from resource
     * @param url name of the song in the resource folder
     * @param looping is true if you want the sound to be on repeat
     */
    public static synchronized void playSound(final String url, boolean looping) {
        URL path = AudioManager.class.getResource("/sound/" + url);
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(path);
            clip.open(inputStream);
            if (looping) {
                clip.loop(10);
            } else {
                clip.start();
            }
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
    }
}

