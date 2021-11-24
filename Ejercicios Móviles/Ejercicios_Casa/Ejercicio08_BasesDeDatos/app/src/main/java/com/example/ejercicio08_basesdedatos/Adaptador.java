package com.example.ejercicio08_basesdedatos;

/**
 * @author Edgar Sánchez Amores
 * Clase adaptador para asociar los datos con la vista del ViewHolder
 */

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.MyHolder> {

    private List<Encapsulador> entradas;


    public Adaptador(List<Encapsulador> entradas) {

        this.entradas = entradas;
    }

    public static class MyHolder extends RecyclerView.ViewHolder {

        public TextView grupo;
        public TextView disco;

        public MyHolder (View vista) {
            super(vista);

            grupo = (TextView) vista.findViewById(R.id.tituloGrupo);
            disco = (TextView) vista.findViewById(R.id.tituloDisco);
        }
    }

    /**
     * Método que permite crear un ViewHolder nuevo siempre que el RecyclerView lo necesite.
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_card, parent, false);
        MyHolder mvh = new MyHolder(v);
        return mvh;
    }

    /**
     * Método que se encarga de asociar el ViewHolder con los datos
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        holder.grupo.setText(entradas.get(position).getGrupo());
        holder.disco.setText(entradas.get(position).getDisco());

    }

    /**
     * Método con el que obtenemos el tamaño del conjunto de datos.
     * @return
     */
    @Override
    public int getItemCount() {

        return entradas.size();
    }
}
