package com.example.ejercicios_menus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView opciones = (TextView) findViewById(R.id.mostrarOpciones);
        registerForContextMenu(opciones);


    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id)
        {
            case R.id.buscar_cliente:
                Toast.makeText(getApplicationContext(), "Se ha pulsado Buscar Cliente", Toast.LENGTH_LONG).show();
                return true;

            case R.id.clientes:
                Toast.makeText(getApplicationContext(), "Se ha pulsado Clientes", Toast.LENGTH_LONG).show();
                return true;
            case R.id.Facturas:
                Toast.makeText(getApplicationContext(), "Se ha pulsado Facturas", Toast.LENGTH_LONG).show();
                return true;
            case R.id.ajustes:
            case R.id.conf_clientes:
            case R.id.conf_facturas:
            case R.id.anadir_cliente:
                Toast.makeText(getApplicationContext(), "Se ha pulsado otro elemento del menú(" + item.getTitle() + ")", Toast.LENGTH_LONG).show();
                return true;


        }
        return super.onOptionsItemSelected(item);
    }


    public void onCreateContextMenu (ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        MenuInflater m = getMenuInflater();
        m.inflate(R.menu.menu_contextual, menu);
        super.onCreateContextMenu(menu, v, menuInfo);


    }



}