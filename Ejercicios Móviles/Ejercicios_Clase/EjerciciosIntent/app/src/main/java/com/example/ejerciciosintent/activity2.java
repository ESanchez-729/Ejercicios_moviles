package com.example.ejerciciosintent;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

    public class activity2 extends AppCompatActivity {
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity2);

            Intent obtener = getIntent();
            String nombre = obtener.getStringExtra("nombre");
            Integer edad = obtener.getIntExtra("edad", -1);
            mascota masc = (mascota) getIntent().getSerializableExtra("mascota");

            Log.d("datos", nombre);
            Log.d("edad", String.valueOf(edad));
            Log.d("masc", masc.toString());

            Button mandarResultados = (Button) findViewById(R.id.datos);

            mandarResultados.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent i = new Intent(view.getContext(), MainActivity.class);
                    int edad = i.getIntExtra("edad", -1);
                    i.putExtra("edad", (int) edad++ );
                    setResult(RESULT_OK, i);
                    finish();
                }
            });

        }

        public void resultado () {



        }
    }
