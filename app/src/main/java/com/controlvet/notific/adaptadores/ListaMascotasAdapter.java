package com.controlvet.notific.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notific.R;
import com.controlvet.notific.VerDatos;
import com.controlvet.notific.entidades.Contactos;

import java.util.ArrayList;

public class ListaMascotasAdapter extends RecyclerView.Adapter<ListaMascotasAdapter.ContactoViewHolder> {

    ArrayList<Contactos> listaContactos;

    public ListaMascotasAdapter(ArrayList<Contactos> listaContactos){
        this.listaContactos = listaContactos;
    }

    @NonNull
    @Override
    public ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_personas, null, false);
        return new ContactoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactoViewHolder holder, int position) {
        holder.viewNombreM.setText(listaContactos.get(position).getNombreM());
        holder.viewChip.setText(listaContactos.get(position).getMicrochip());
        holder.viewRaza.setText(listaContactos.get(position).getRaza());
        holder.viewNacimiento.setText(listaContactos.get(position).getNacimiento());
        holder.viewColor.setText(listaContactos.get(position).getColor());
        holder.viewTipo.setText(listaContactos.get(position).getTipo_mascota());
        holder.viewSexo.setText(listaContactos.get(position).getSexo());
        holder.viewExtra.setText(listaContactos.get(position).getDatos_extras());
    }

    @Override
    public int getItemCount() {
        return listaContactos.size();
    }

    public class ContactoViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombreM, viewChip, viewRaza, viewNacimiento, viewColor, viewTipo, viewSexo, viewExtra;

        public ContactoViewHolder(@NonNull View itemView) {
            super(itemView);

            viewNombreM = itemView.findViewById(R.id.viewNombreM);
            viewChip = itemView.findViewById(R.id.viewChip);
            viewRaza = itemView.findViewById(R.id.viewRaza);
            viewNacimiento = itemView.findViewById(R.id.viewNacimiento);
            viewColor = itemView.findViewById(R.id.viewColor);
            viewTipo = itemView.findViewById(R.id.viewTipo);
            viewSexo = itemView.findViewById(R.id.viewSexo);
            viewExtra = itemView.findViewById(R.id.viewExtra);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerDatos.class);
                    intent.putExtra("identificador", listaContactos.get(getAdapterPosition()).getIdentificador());
                    context.startActivity(intent);
                }
            });
        }
    }
}
