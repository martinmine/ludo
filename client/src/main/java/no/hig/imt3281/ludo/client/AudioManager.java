package no.hig.imt3281.ludo.client;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Joakim on 24.11.2014.
 */
public class AudioManager {
    final static Logger LOGGER = Logger.getLogger(AudioManager.class.getSimpleName());
    private static AudioInputStream inputStream;
    public static synchronized void playSound(final String url, boolean looping) {

        java.net.URL path = AudioManager.class.getResource("/sound/" + url);
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(path);
            clip.open(inputStream);
            if (looping)
                clip.loop(20);
            else
                clip.start();

        } catch (Exception e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        }

    }
    public static void stopSound() {
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

