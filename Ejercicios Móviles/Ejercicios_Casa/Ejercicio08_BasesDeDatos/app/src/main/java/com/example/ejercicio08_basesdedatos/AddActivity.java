package com.example.ejercicio08_basesdedatos;

/**
 * @author Edgar Sánchez Amores
 * Activity que nos permite añadir una entrada a la base de datos.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Button anadir = (Button) findViewById(R.id.anadirEntrada);
        anadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                anadirEntrada();
            }
        });
    }

    /**
     * Cada vez que la aplicación pasa al primer plano, creamos la base de datos. En caso de ya estar creada, la abre.
     */
    @Override
    protected void onResume() {
        super.onResume();

        db = openOrCreateDatabase(getString(R.string.dbName), Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS MiLista(Grupo VARCHAR, Disco VARCHAR);");
    }

    /**
     * Cuando la actividad se cierra o pasa a un segundo plano, cerramos la conexión con la base de datos.
     */
    @Override
    protected void onStop() {
        super.onStop();
        db.close();
    }

    /**
     * Método el cual asignamos al botón de la activity para añadir una nueva entrada a la base de datos.
     */
    public void anadirEntrada() {

        EditText grupo = (EditText) findViewById(R.id.addgroup);
        String gru = grupo.getText().toString().trim();

        EditText disco = (EditText) findViewById(R.id.adddisk);
        String dis = disco.getText().toString().trim();

        /**
         * Si los items para introducir los datos estan vacíos, no nos permitirá añadir
         */
        if (gru.equals("") && dis.equals("")) {

            Toast.makeText(this, R.string.addError, Toast.LENGTH_LONG).show();
        }

        else {

            db.execSQL("INSERT INTO MiLista VALUES ('" + grupo.getText().toString() +"', '" + disco.getText().toString() +"')");
            Toast.makeText(this, R.string.addSuccess, Toast.LENGTH_LONG).show();
            grupo.setText("");
            disco.setText("");
        }
    }
}
