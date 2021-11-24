package com.example.practica2_hipotenochas;

/**
 * @author Edgar Sánchez Amores
 */

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    /**
     * Declaramos variables globales que vamos a usar a lo largo del código
     */
    public static final int easy = 8;
    public static final int normal = 12;
    public static final int hard = 16;
    public static final int belugasFacil = 10;
    public static final int belugasNormal = 30;
    int actual = 8;
    int belugasActuales = 10;
    Tablero juego;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Obtenemos el tablero por medio de la View y creamos un objeto de tipo tablero
         */
        TableLayout tablero = findViewById(R.id.table);
        juego = new Tablero(tablero, this);
    }

    /**
     * Método que crea un menú para mostrar una serie de funcionalidades
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(R.string.option1).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle(R.string.option1);
                alert.setMessage(getString(R.string.instrucciones));

                AlertDialog mensaje = alert.create();
                mensaje.show();

                return true;
            }
        });
        menu.add(R.string.option2).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                if (!juego.creado()) {

                    juego.crearTablero(actual, belugasActuales);
                }
                else {

                    juego.eliminarTablero();
                    juego.crearTablero(actual, belugasActuales);
                }

                return true;
            }
        });
        menu.add(R.string.option3).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                showConfig();
                return true;
            }
        });

        return true;
    }

    /**
     * Método que asignamos a una de las opciones del menu. Esto nos muestra una AlertDialog
     * donde nos dejará elegir la dificultad de la partida.
     */
    private void showConfig() {

        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        CharSequence [] options = {getString(R.string.suboption1), getString(R.string.suboption2), getString(R.string.suboption3)};
        alert.setTitle(R.string.option3);
        int select = 0;
        alert.setSingleChoiceItems(options, select, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int option) {

            /**
             * Dependiendo de la dificultad elegida, comprueba si hay o no
             * tablero creado. Si lo hay, lo borra y crea uno nuevo en base a la dificultad
             * establecida.
             */
               switch(option) {

                    case 0:
                        if (juego.creado() == true) {

                            juego.eliminarTablero();
                            juego.crearTablero(easy, belugasFacil);
                        }

                        else {

                            juego.crearTablero(easy, belugasFacil);
                        }

                        actual = easy;
                        belugasActuales = belugasFacil;
                        dialogInterface.dismiss();
                        break;

                    case 1:

                        if (juego.creado() == true) {

                            juego.eliminarTablero();
                            juego.crearTablero(normal, belugasNormal);
                        }

                        else {

                            juego.crearTablero(normal, belugasNormal);
                        }

                        actual = normal;
                        belugasActuales = belugasNormal;
                        dialogInterface.dismiss();
                        break;

                    case 2:

                        Toast.makeText(MainActivity.this, R.string.placeholder, Toast.LENGTH_LONG).show();
                        dialogInterface.dismiss();
                        break;
                }
            }
        });

        AlertDialog dialog = alert.create();
        dialog.show();
    }
}