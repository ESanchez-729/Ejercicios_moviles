package com.example.ejercicios_dialogs;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String [] elementos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        elementos = getResources().getStringArray(R.array.ingredientes);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add("Prueba").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                ArrayList<String> opcionesMarcadas = new ArrayList<String>();
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMultiChoiceItems(R.array.ingredientes, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean isChecked) {

                        if (isChecked) {

                            opcionesMarcadas.add(elementos[i]);
                            Log.d("listaAdd", opcionesMarcadas.toString());

                        }

                        else if (opcionesMarcadas.contains(elementos[i])) {

                            opcionesMarcadas.remove(elementos[i]);
                            Log.d("listaRem", opcionesMarcadas.toString());

                        }
                    }
                });

                builder.setPositiveButton("Hola", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Log.d("lista", opcionesMarcadas.toString());

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

                return true;
            }
        });

        menu.add("PruebaCustom").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                LayoutInflater inflater = getLayoutInflater();

                builder.setView(inflater.inflate(R.layout.dialog, null));

                AlertDialog dialog = builder.create();
                dialog.show();

                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}