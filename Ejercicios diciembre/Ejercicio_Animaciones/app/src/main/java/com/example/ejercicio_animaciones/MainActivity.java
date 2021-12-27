package com.example.ejercicio_animaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView image = (ImageView) findViewById(R.id.imageView);
        image.setImageResource(R.drawable.belugaaa);
        ObjectAnimator animator = ObjectAnimator.ofFloat(image, "translationY", 500f);
        animator.setDuration(3000);
        animator.setStartDelay(2000);
        animator.start();

    }
}