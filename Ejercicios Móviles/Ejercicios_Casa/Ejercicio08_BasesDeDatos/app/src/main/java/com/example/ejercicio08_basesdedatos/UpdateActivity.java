package com.example.ejercicio08_basesdedatos;

/**
 * @author Edgar Sánchez Amores
 * Activity para actualizar los datos de una entrada del Recycler view y de la base de datos
 */

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {

    SQLiteDatabase db;
    Encapsulador datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        Button btn_update = (Button) findViewById(R.id.actualizarEntrada);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                actualizarEntrada();
            }
        });

        Button btn_back = (Button) findViewById(R.id.atras);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               finish();
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();

        db = openOrCreateDatabase(getString(R.string.dbName), Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS MiLista(Grupo VARCHAR, Disco VARCHAR);");

        //Bundle entradas = getIntent().getExtras();
        //Grupo = entradas.getString("Grupo");
        //Disco = entradas.getString("Disco");
        datos = (Encapsulador) getIntent().getSerializableExtra("Encapsulador1");

        EditText grupo = (EditText) findViewById(R.id.updategroup);
        grupo.setText(datos.getGrupo());

    }

    @Override
    protected void onStop() {
        super.onStop();
        db.close();
    }

    /**
     * Método para cerrar la activity en el momento en el que su función o propósito se ha completado
     */
    @Override
    public void finish() {
        super.finish();
    }

    /**
     * Método con el que realizamos la actualización de los datos de la entrada que hayamos elegido en la lista
     */
    public void actualizarEntrada() {

        EditText disco = (EditText) findViewById(R.id.updatedisk);
        String dis = disco.getText().toString().trim();

        if (dis.equals("") || dis.equals(".")) {

            Toast.makeText(this, R.string.updateError, Toast.LENGTH_LONG).show();
        }

        else {

            db.execSQL("UPDATE MiLista SET Disco = '" + disco.getText() + "' WHERE Grupo = '" + datos.getGrupo() + "' AND Disco = '" + datos.getDisco() + "'");
            Toast.makeText(this, R.string.updateSuccess, Toast.LENGTH_LONG).show();
            disco.setText("");
        }
    }
}
