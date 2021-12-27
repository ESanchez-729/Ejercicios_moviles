package com.example.ejercicio_videoview;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    VideoView videItto;
    Button play;
    Button pause;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play = (Button) findViewById(R.id.play);
        pause = (Button) findViewById(R.id.pause);

        videItto = (VideoView) findViewById(R.id.videItto);

        String ruta = "android.resource://" + getPackageName() + "/" + R.raw.video;

        Uri videoUri = Uri.parse(ruta);

        videItto.setVideoURI(videoUri);

        /*MediaController controlador = new MediaController(this);

        videItto.setMediaController(controlador);
        controlador.setAnchorView(videItto);*/

        /*play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                videItto.start();
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                videItto.pause();
            }
        });*/

        videItto.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {

                videItto.start();
                Log.d("videoView", "ready to play");
            }
        });

        videItto.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Log.d("videoView", "completed");
            }
        });
    }




}