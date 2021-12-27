package com.example.ejercicio_mediarecorder;

import android.content.ContextWrapper;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.IOException;

public class VisualizarActivity extends AppCompatActivity implements SurfaceHolder.Callback{

    MediaPlayer mediaPlayer;
    SurfaceHolder holder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar);

        mediaPlayer = new MediaPlayer();
        SurfaceView superficie = (SurfaceView)findViewById(R.id.superficieVisualizar);

        // Obteniendo el objeto SurfaceHolder a partir del SurfaceView
        holder = superficie.getHolder();
        holder.addCallback(this);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {

        try {

            ContextWrapper cw = new ContextWrapper(this);
            File videoFile = new File(cw.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "video.mp4");

            mediaPlayer.setDisplay(holder);
            mediaPlayer.setDataSource(getApplicationContext(), Uri.parse(videoFile.getAbsolutePath()));
            mediaPlayer.prepare();
            mediaPlayer.start();
        }

        catch (IllegalArgumentException e) {
            Log.d("MEDIA_PLAYER", e.getMessage());
        }

        catch (IllegalStateException e) {
            Log.d("MEDIA_PLAYER", e.getMessage());
        }

        catch (IOException e) {
            Log.d("MEDIA_PLAYER", e.getMessage());
        }
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

        mediaPlayer.release();

    }
}
