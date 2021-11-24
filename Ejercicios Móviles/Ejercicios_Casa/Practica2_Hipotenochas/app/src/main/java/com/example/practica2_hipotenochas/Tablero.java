package com.example.practica2_hipotenochas;

/**
 * @author Edgar Sánchez Amores
 */

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.math.MathContext;
import java.util.ArrayList;

public class Tablero {

    private Context context;
    private TableLayout tab;
    private static boolean status = false;
    private static ArrayList<Integer> listaID;
    private static ArrayList<ImageButton> listaBelugas;
    private static int belugasAsignadas;
    private static int restantes;


    /**
     * Constructor de tableros
     * @param tabla
     * @param contexto
     */
    public Tablero(TableLayout tabla, Context contexto) {

        context = contexto;
        tab  = tabla;

    }

    /**
     * Método para comprobar el estado del tablero.
     * @return
     */
    public boolean creado() {

        return status;
    }

    /**
     * Método que crea el tablero. Al mismo tiempo, asigna las belugas de forma aleatoria a las
     * casillas y maneja los listeners "onClick" y "onLongClick" de los botones.
     * @param casillas
     * @param numBelugas
     */
    public void crearTablero(int casillas, int numBelugas) {

        tab.setStretchAllColumns(true);
        tab.setShrinkAllColumns(true);
        tab.setWeightSum(casillas);
        status = true;
        listaID = new ArrayList<Integer>();
        listaBelugas = new ArrayList<ImageButton>();
        belugasAsignadas = numBelugas;
        restantes = (casillas*casillas) - belugasAsignadas;


        for (int filas = 0; filas < casillas; filas ++) {

            TableRow trow = new TableRow(context);
            trow.setGravity(Gravity.CENTER);
            trow.setLayoutParams( new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.MATCH_PARENT,
                    1
            ));

            for (int columnas = 0; columnas < casillas; columnas ++) {

                ImageButton casilla = new ImageButton(context);
                casilla.setId(listaBelugas.size());
                casilla.setBackgroundColor(Color.TRANSPARENT);
                casilla.setImageResource(R.drawable.questionmark);
                casilla.setScaleType(ImageView.ScaleType.FIT_XY);
                casilla.setAdjustViewBounds(true);
                casilla.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT, 1));

                casilla.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {


                        if(comprobar(casilla)) {

                            Toast.makeText(context, R.string.beluga, Toast.LENGTH_LONG).show();
                            desactivarTablero();
                        }

                        else {

                            casilla.setImageResource(R.drawable.descubierta);
                            casilla.setEnabled(false);
                            restantes--;

                            if (restantes == 0) {

                                Toast.makeText(context, R.string.victoriaPacifica, Toast.LENGTH_LONG).show();
                                desactivarTablero();
                            }
                        }
                    }
                    });

                casilla.setOnLongClickListener(new View.OnLongClickListener() {

                    @Override
                    public boolean onLongClick(View view) {

                        if (!comprobar(casilla)) {

                            desactivarTablero();
                            Toast.makeText(context, R.string.derrota, Toast.LENGTH_LONG).show();
                        }

                        else if (comprobar(casilla)){

                            belugasAsignadas--;
                            casilla.setImageResource(R.drawable.beluga_muerta);

                            if (belugasAsignadas == 0) {

                                desactivarTablero();
                                Toast.makeText(context, R.string.victoriaEliminatoria, Toast.LENGTH_LONG).show();
                            }

                        }

                        return true;
                    }
                    });

                listaBelugas.add(casilla);
                trow.addView(casilla);
            }

            tab.addView(trow);

        }

        boolean existe = false;
        while (numBelugas > 0) {

            int id = (int) (Math.random()*(casillas*casillas));
            existe = false;



                if(listaID.contains(id)) {

                    existe = true;
                }

            if (!existe) {

                listaID.add(id);
                numBelugas --;
                Log.d("Debug", String.valueOf(id));
            }
        }
    }

    /**
     * Método que asignamos al onClick para comprobar si el boton pulsado es una beluga o no.
     * @param boton
     * @return
     */
    private boolean comprobar(ImageButton boton) {

        int num = boton.getId();

        if (listaID.contains(num)) {

            boton.setImageResource(R.drawable.beluga);
            return true;
        }

        return false;
    }

    /**
     * Método que recorre toda el TableLayout y desactiva botón a botón.
     */
    public void desactivarTablero() {

        for (int filas = 0; filas < tab.getChildCount(); filas++) {

            TableRow tr = (TableRow) tab.getChildAt(filas);

            for (int columnas = 0; columnas < tr.getChildCount(); columnas++) {

                ImageButton cas = (ImageButton) tr.getChildAt(columnas);
                cas.setEnabled(false);
            }
        }
    }

    /**
     * Método que borra todas las vistas del Layout.
     *
     */
    public void eliminarTablero() {

        tab.removeAllViewsInLayout();
    }
}
