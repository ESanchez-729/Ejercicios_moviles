package com.example.ejerciciosintent;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityResultLauncher<Intent> ActLaunch;

        Button btnCambiar = (Button) findViewById(R.id.miboton);

        btnCambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ActLaunch = registerForActivityResult(
                        new ActivityResultContracts.StartActivityForResult(),
                        new ActivityResultCallback<ActivityResult>() {
                            @Override
                            public void onActivityResult(ActivityResult result) {

                                if (result.getResultCode() == Activity.RESULT_OK) {

                                    //Si queremos recoger datos
                                    Intent data = result.getData();

                                    Bundle b = data.getExtras();

                                    Integer e = b.getInt("edad");

                                    Log.d("modificado", String.valueOf(e));
                                }

                            }
                        });

                Intent desplazar = new Intent(view.getContext(), activity2.class);
                desplazar.putExtra("nombre", "Egar");
                desplazar.putExtra("edad", 24);

                mascota m = new mascota("perro", 12, "beagle");

                desplazar.putExtra("mascota", m);

                ActLaunch.launch(desplazar);
            }
        });

        Button btnGenshin = (Button) findViewById(R.id.genshin);

        btnGenshin.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {

                Intent paginaMihoyo = new Intent(Intent.ACTION_VIEW);
                paginaMihoyo.setData(Uri.parse("https://genshin.mihoyo.com/es/"));
                startActivity(paginaMihoyo);
            }
        });
    }
}