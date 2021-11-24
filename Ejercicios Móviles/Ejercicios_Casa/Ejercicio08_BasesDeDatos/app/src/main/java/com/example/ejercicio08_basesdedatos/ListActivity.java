package com.example.ejercicio08_basesdedatos;

/**
 * @author Edgar Sánchez Amores
 * Activity para listar los registros de la base de datos en el recycler view
 */

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.gesture.Gesture;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private RecyclerView reciclador;
    private RecyclerView.Adapter adaptador;
    private RecyclerView.LayoutManager manager;
    SQLiteDatabase db;
    List<Encapsulador> entradas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

    }

    /**
     * Cada vez que la activity pasa al plano principal volvemos a abrir la base de datos
     * y a usar el método de listar
     */
    @Override
    protected void onResume() {
        super.onResume();
        db = openOrCreateDatabase(getString(R.string.dbName), Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS MiLista(Grupo VARCHAR, Disco VARCHAR);");
        listar();
    }

    @Override
    protected void onStop() {
        super.onStop();
        db.close();
    }

    /**
     * Método con el cual realizamos una cosulta y recogemos todos los datos para pasarlos al
     * recyclew view mediante las clases del adaptador, el encapsulador y elmanager
     */
    public void listar() {

        entradas = new ArrayList<>();
        reciclador = (RecyclerView) findViewById(R.id.lista);

        Cursor cursor = db.rawQuery("SELECT * FROM MiLista", null);

        if (cursor.getCount() != 0) {

            while (cursor.moveToNext()) {

                entradas.add(new Encapsulador(cursor.getString(0), cursor.getString(1)));
            }
        }

        cursor.close();

        reciclador.setHasFixedSize(true);
        manager = new LinearLayoutManager(this);
        adaptador = new Adaptador(entradas);
        reciclador.setLayoutManager(manager);
        reciclador.setAdapter(adaptador);

        reciclador.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            GestureDetector gestureDetector = new GestureDetector(getApplicationContext(),
                    new GestureDetector.SimpleOnGestureListener() {

                        public boolean onSingleTapUp(MotionEvent event) {

                            return true;
                        }
                    });

            /**
             * Método con el cual, cuando pinchamos en un elemento de recycler, mandamos los datos
             * a la activity de actualizar la entrada.
             * @param rv
             * @param e
             * @return
             */
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

                View hijo = rv.findChildViewUnder(e.getX(), e.getY());
                if (hijo != null && gestureDetector.onTouchEvent(e)) {

                    int position = rv.getChildAdapterPosition(hijo);

                    Bundle paquete = new Bundle();

                    Intent update_screen = new Intent(rv.getContext(), UpdateActivity.class);
                    //update_screen.putExtra("Grupo", entradas.get(position).getGrupo());
                    //update_screen.putExtra("Disco", entradas.get(position).getDisco());

                    /**
                     * Pasamos al intent un bundle el cual contiene el objeto encapsulador.
                     */
                    paquete.putSerializable("Encapsulador1", entradas.get(position));
                    update_screen.putExtras(paquete);
                    startActivity(update_screen);
                }

                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

    }
}
