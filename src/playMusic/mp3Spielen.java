package playMusic;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class mp3Spielen {

	public static void main(String[] args) {

	}

	public mp3Spielen(String pfad) {

		JFXPanel fxPanel = new JFXPanel();

		String bip = pfad;
		Media hit = new Media(bip);
		MediaPlayer mediaPlayer = new MediaPlayer(hit);
		mediaPlayer.play();
	}
}
