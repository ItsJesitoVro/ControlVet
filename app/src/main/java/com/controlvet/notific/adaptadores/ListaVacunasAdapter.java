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

public class ListaVacunasAdapter extends RecyclerView.Adapter<ListaVacunasAdapter.VacunasViweHolder> {

    ArrayList<Contactos> listaVacunas;

    public ListaVacunasAdapter (ArrayList<Contactos> listaVacunas){
        this.listaVacunas = listaVacunas;
    }

    @NonNull
    @Override
    public VacunasViweHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_vacunas,null, false);
        return new VacunasViweHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VacunasViweHolder holder, int position) {
        holder.viewMicroChip.setText(listaVacunas.get(position).getMicroChip2());
        holder.viewVacuna.setText(listaVacunas.get(position).getVacuna());
        holder.viewDiluente.setText(listaVacunas.get(position).getDiluente());
        holder.viewAplicada.setText(listaVacunas.get(position).getAplicado());
        holder.viewProxima.setText(listaVacunas.get(position).getProximo());
    }

    @Override
    public int getItemCount() {
        return listaVacunas.size();
    }

    public class VacunasViweHolder extends RecyclerView.ViewHolder {

        TextView viewMicroChip, viewVacuna, viewDiluente, viewAplicada, viewProxima;

        public VacunasViweHolder(@NonNull View itemView) {
            super(itemView);

            viewMicroChip = itemView.findViewById(R.id.viewMicroChip);
            viewVacuna = itemView.findViewById(R.id.viewVacuna);
            viewDiluente = itemView.findViewById(R.id.viewDiluente);
            viewAplicada = itemView.findViewById(R.id.viewAplicacion);
            viewProxima = itemView.findViewById(R.id.viewProxima);
        }
    }
}
