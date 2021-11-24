/**
 * @author Edgar Sanchez
 */

package com.example.ejercicio07_menus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.SubMenu;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /**
     * Método que recibe un objeto de tipo Menu por parámetro.
     * @param menu
     * @return
     */
    public boolean onCreateOptionsMenu(Menu menu) {

        /**
         * Especificamos que uno de los dos ítems del menú del tipo SubMenu para que nos permita contener
         * elementos en su interior. Ambos los añadimos al objeto menú que hemos pasado por parámetro.
         */
        SubMenu smenu = menu.addSubMenu(R.string.option1);
        menu.add(R.string.option2);

        /**
         * Añadimos dos nodos al ítem de tipo SubMenu para que esten contenidos dentro de él
         * como subelementos.
         */
        smenu.add(R.string.suboption1);
        smenu.add(R.string.suboption2);

        return true;
    }
}