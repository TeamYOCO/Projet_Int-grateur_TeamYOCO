package gameEngine;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author Tomas
 */
public class Music {
    
    private Clip clip;

    /**
     * constructeur de musique
     * @param filepath Le chemin pour aller chercher la musique
     */
    public Music(String filepath) {
        
        File file = new File(filepath);
        try {
            clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
            clip.open(inputStream);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * méthode pour faire jouer la musique
     */
    public void play() {
        
        if (clip.isActive()) {
            clip.stop();
        }
        clip.setFramePosition(0);
        clip.start();
    }

    /**
     * méthode pour arrêter la musique
     */
    public void stop() {
        clip.stop();
    }

    /**
     * méthode pour boucler la musique
     */
    public void loop() {
        if (!clip.isActive()) {
            clip.setFramePosition(0);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } else {
            System.out.println("ALREADY PLAYING");
        }

    }

    /**
     * getter de l'activité du fichier musical
     * @return isActive booléen qui retourne true si il y a un fichier musical
     */
    public boolean getActive() {
        return clip.isActive();
    }
}
