package com.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notific.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.model.Articulo;


public class ArticuloAdapter extends  FirestoreRecyclerAdapter<Articulo, ArticuloAdapter.ViewHolder>{
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ArticuloAdapter(@NonNull FirestoreRecyclerOptions<Articulo> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder viewHolder, int i, @NonNull Articulo Articulo) {
        viewHolder.name.setText(Articulo.getNombre());
        viewHolder.desc.setText(Articulo.getDescripcion());
        viewHolder.prec.setText(Articulo.getPrecio());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_articulo_single, parent, false);
        return new ViewHolder(v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, desc, prec;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.nombre);
            desc = itemView.findViewById(R.id.Descri);
            prec = itemView.findViewById(R.id.Precio);
        }
    }
}
