package com.example.soundplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;

    private AudioManager audioManager;

    private MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(this, R.raw.mysong);

        Button playbutton = (Button) findViewById(R.id.play_button);
        playbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this,"Playing the song",Toast.LENGTH_SHORT).show();
                mediaPlayer.start();

                mediaPlayer.setOnCompletionListener(completionListener);

//                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
//                    @Override
//                    public void onCompletion(MediaPlayer mediaPlayer){
//                        Toast.makeText(MainActivity.this,"I am done!",Toast.LENGTH_SHORT).show();
//                    }
//                });
            }
        });


        Button pausebutton = (Button) findViewById(R.id.pause_button);
        pausebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Pausing the song",Toast.LENGTH_SHORT).show();
                mediaPlayer.pause();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }


    //Cleanup the media player
    private void releaseMediaPlayer(){

        //IF MEDIAPLAYER IS NOT NULL---NOT BE PLAYING ONG CURRENTLY
        if(mediaPlayer!=null){
            //RELEASE RESOURCES
            mediaPlayer.release();

            //MEDIA PLAYER NOT CONFIGURED TO PLAY AUDIO
            mediaPlayer=null;
        }

    }
}