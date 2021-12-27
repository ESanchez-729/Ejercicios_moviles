package com.example.ejercicio_mediarecorder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    ContextWrapper cw;
    File audioFile;
    MediaRecorder mediaRecorder = new MediaRecorder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, 0);

        }

        cw = new ContextWrapper(this);
        audioFile = new File(cw.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS),"audio.3gp");


        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        mediaRecorder.setOutputFile(audioFile.getAbsolutePath());

        try {
            mediaRecorder.prepare();
        }

        catch (IOException e) {
            e.printStackTrace();
        }

        Button grabar = findViewById(R.id.grabar);
        grabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mediaRecorder.start();
            }
        });

        Button parar = findViewById(R.id.parar);
        parar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mediaRecorder.stop();
                mediaRecorder.release();
                mediaRecorder = null;
            }
        });

        Button reproducir = findViewById(R.id.reproducir);
        reproducir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this, Uri.parse(audioFile.getAbsolutePath()));
                mediaPlayer.start();

            }
        });



    }
}