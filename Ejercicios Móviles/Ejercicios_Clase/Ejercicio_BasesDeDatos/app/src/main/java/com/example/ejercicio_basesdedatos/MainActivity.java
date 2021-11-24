package com.example.ejercicio_basesdedatos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText txtGrupo, txtDisco;
    ListView lista;
    SQLiteDatabase db;
    Button anadirB;
    Button eliminarB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtGrupo = (EditText) findViewById(R.id.txtGrupo);
        txtDisco = (EditText) findViewById(R.id.txtDisco);
        lista = (ListView) findViewById(R.id.lista);
        anadirB = (Button) findViewById(R.id.anadir);
        eliminarB = (Button) findViewById(R.id.eliminar);

        anadirB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                anadir(view);
            }
        });

        eliminarB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                borrar(view);
            }
        });

        db = openOrCreateDatabase("Mis Discos", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS MisDiscos(Grupo VARCHAR, Disco VARCHAR);");

        listar();
    }

    public void anadir(View w) {

        db.execSQL("INSERT INTO MisDiscos VALUES ('" + txtGrupo.getText().toString() +"', '" + txtDisco.getText().toString() +"')");

        Toast.makeText(this, "Se añadió el disco " + txtDisco.getText().toString(), Toast.LENGTH_LONG).show();

        listar();
    }

    public void borrar(View w) {

        db.execSQL("DELETE FROM MisDiscos WHERE Grupo = '" + txtGrupo.getText().toString() + "'AND Disco ='" + txtDisco.getText().toString() +"'");

        Toast.makeText(this, "Se borró el disco " + txtDisco.getText().toString(), Toast.LENGTH_LONG).show();

        listar();
    }

    public void listar() {

        ArrayAdapter<String> adaptador;

        List<String> list = new ArrayList<String>();
        Cursor c = db.rawQuery("SELECT * FROM MisDiscos",null);

        if (c.getCount() == 0) {

            list.add("No hay registros");
        }

        else {

            while (c.moveToNext()) {

                list.add(c.getString(0) + " - " + c.getString(1));
            }
        }

        adaptador = new ArrayAdapter<String> (getApplicationContext(), R.layout.fila, list);
        lista.setAdapter(adaptador);

        c.close();
    }


}