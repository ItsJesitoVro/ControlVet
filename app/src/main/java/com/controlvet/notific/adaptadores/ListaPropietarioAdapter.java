package com.controlvet.notific.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notific.R;
import com.controlvet.notific.entidades.Contactos;

import java.util.ArrayList;

public class ListaPropietarioAdapter extends RecyclerView.Adapter<ListaPropietarioAdapter.DueñoViewHolder>{

    ArrayList<Contactos> listaDueño;

    public ListaPropietarioAdapter (ArrayList<Contactos> listaDueño){
        this.listaDueño = listaDueño;
    }

    @NonNull
    @Override
    public DueñoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_dueno, null, false);
        return new DueñoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DueñoViewHolder holder, int position) {
        holder.viewNombreP.setText(listaDueño.get(position).getNombre());
        holder.viewCelular.setText(listaDueño.get(position).getCelular());
        holder.viewDireccion.setText(listaDueño.get(position).getDireccion());
        holder.viewCorreo.setText(listaDueño.get(position).getCorreo());
        holder.viewExtras2.setText(listaDueño.get(position).getDatos_extras2());
    }

    @Override
    public int getItemCount() {
       return listaDueño.size();
    }

    public class DueñoViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombreP, viewCelular, viewDireccion, viewCorreo, viewExtras2;

        public DueñoViewHolder(@NonNull View itemView) {
            super(itemView);

            viewNombreP = itemView.findViewById(R.id.viewNombreP);
            viewCelular = itemView.findViewById(R.id.viewCelular);
            viewDireccion = itemView.findViewById(R.id.viewDireccion);
            viewCorreo = itemView.findViewById(R.id.viewCorreo);
            viewExtras2 = itemView.findViewById(R.id.viewExtra2);
        }
    }
}