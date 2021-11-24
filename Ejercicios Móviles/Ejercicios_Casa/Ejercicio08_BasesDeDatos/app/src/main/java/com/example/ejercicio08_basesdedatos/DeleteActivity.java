package com.example.ejercicio08_basesdedatos;

/**
 * @author Edgar Sánchez Amores
 * Activity que nos permite eliminar una entrada de la base de datos
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DeleteActivity extends AppCompatActivity {

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        Button eliminar = (Button) findViewById(R.id.eliminarEntrada);
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                eliminarEntrada();
            }
        });

    }

    /**
     * Mismo procedimiento que en la activity de añadir
     */
    @Override
    protected void onResume() {
        super.onResume();
        db = openOrCreateDatabase(getString(R.string.dbName), Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS MiLista(Grupo VARCHAR, Disco VARCHAR);");
    }

    /**
     * Mismo procedimiento que en la activity de añadir
     */
    @Override
    protected void onStop() {
        super.onStop();
        db.close();
    }

    /**
     * Método que asignamos al botón de esta activity para eliminar una entrada de la base de datos
     */
    public void eliminarEntrada() {

        EditText grupo = (EditText) findViewById(R.id.deletegroup);
        String gru = grupo.getText().toString().trim();

        EditText disco = (EditText) findViewById(R.id.deletedisk);
        String dis = disco.getText().toString().trim();

        if (gru.equals("") && dis.equals("")) {

            Toast.makeText(this, R.string.deleteError, Toast.LENGTH_LONG).show();
        }

        else {

            db.execSQL("DELETE FROM MiLista WHERE Grupo = '" + grupo.getText().toString() + "'AND Disco ='" + disco.getText().toString() +"'");
            Toast.makeText(this, R.string.deleteSuccess, Toast.LENGTH_LONG).show();
            grupo.setText("");
            disco.setText("");
        }
    }
}
