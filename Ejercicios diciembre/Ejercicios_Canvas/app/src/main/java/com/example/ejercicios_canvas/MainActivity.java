package com.example.ejercicios_canvas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(new MyView(this));

    }

    public class MyView extends View {

        Paint paint = null;

        public MyView(Context context) {
            super(context);

            paint = new Paint();
        }

        protected void onDraw(Canvas canvas) {

            super.onDraw(canvas);
            int x = getWidth();
            int y = getHeight();
            int radius;
            radius = 300;
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.WHITE);
            canvas.drawPaint(paint);
            RectF oval = new RectF(500, 200, 1000, 800);

            // Use Color.parseColor to define HTML colors
            paint.setColor(Color.parseColor("#CD5C5C"));
            //canvas.drawCircle(x / 2, y / 2, radius, paint);
            //canvas.drawArc(oval, 20F, 90F, true, paint);

        }
    }


}