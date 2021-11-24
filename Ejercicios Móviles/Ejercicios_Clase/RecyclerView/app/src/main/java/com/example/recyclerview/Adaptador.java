package com.example.recyclerview;

import android.text.Layout;
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

        public ImageView imagen;
        public TextView titulo;
        public TextView descripcion;

        public MyHolder (View vista) {
            super(vista);

            imagen = (ImageView) vista.findViewById(R.id.cabecera);
            titulo = (TextView) vista.findViewById(R.id.titulo);
            descripcion = (TextView) vista.findViewById(R.id.descripcion);
        }
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.carta, parent, false);
        MyHolder mvh = new MyHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        holder.imagen.setImageResource(entradas.get(position).getImagen());
        holder.titulo.setText(entradas.get(position).getTitulo());
        holder.descripcion.setText(entradas.get(position).getDescripcion());

    }

    @Override
    public int getItemCount() {

        return entradas.size();
    }
}
