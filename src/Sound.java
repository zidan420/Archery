package src;

import java.io.File;

import javafx.scene.media.*;

public class Sound {
	private MediaPlayer mediaPlayer;
	
	public Sound(String filePath) {
		Media media = new Media(new File(filePath).toURI().toString());
		mediaPlayer = new MediaPlayer(media);

		// Stop the sound when the game is closed
		// mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.stop());
		mediaPlayer.setCycleCount(1);
		mediaPlayer.play();
	}
	
	public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }
}
