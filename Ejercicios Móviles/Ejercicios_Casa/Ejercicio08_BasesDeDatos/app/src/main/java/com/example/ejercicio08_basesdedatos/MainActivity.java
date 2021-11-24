package com.example.ejercicio08_basesdedatos;

/**
 * @author Edgar Sánchez Amores
 * Main activity del proyecto, nos puestra la pantalla principal de la aplicación
 */

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_add = (Button) findViewById(R.id.add);
        Button btn_delete = (Button) findViewById(R.id.delete);
        Button btn_show = (Button) findViewById(R.id.show);
        TextView title1 = (TextView) findViewById(R.id.mainTitle);


        /**
         * Listener del botón para movernos a la activity de añadir
         */
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent add_screen = new Intent(view.getContext(), AddActivity.class);
                view.getContext().startActivity(add_screen);
            }
        });

        /**
         * Listener del botón para movernos a la activity de eliminar
         */
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent delete_screen = new Intent(view.getContext(), DeleteActivity.class);
                view.getContext().startActivity(delete_screen);
            }
        });

        /**
         * Listener del botón para movernos a la activity de listar
         */
        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent list_screen = new Intent(view.getContext(), ListActivity.class);
                startActivity(list_screen);
            }
        });


    }
}