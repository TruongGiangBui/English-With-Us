package toeicApp;

import javafx.fxml.FXML;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;

public class playvideoController {
    @FXML
    private MediaView videoplay;

    private MediaPlayer mediaPlayer;
    public void initialize()  {
        try {
            File file = new File("src/Resources/Dancin Meme Love.mp4");

            String url = file.toURI().toURL().toString();
            mediaPlayer = new MediaPlayer(new Media(url));
            mediaPlayer.setAutoPlay(true);
            //  mediaPlayer.play();
            videoplay.setMediaPlayer(mediaPlayer);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
