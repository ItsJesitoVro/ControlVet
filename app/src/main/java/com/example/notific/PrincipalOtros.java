package com.example.notific;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adapter.ArticuloAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.model.Articulo;

public class PrincipalOtros extends AppCompatActivity {
    RecyclerView mRecycler;
    ArticuloAdapter mAdapter;
    FirebaseFirestore mFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_otros);
        mFirestore= FirebaseFirestore.getInstance();
        mRecycler = findViewById(R.id.recyclerViewSingle);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        Query query = mFirestore.collection("Articulos");

        FirestoreRecyclerOptions<Articulo> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<Articulo>().setQuery(query, Articulo.class).build();
        mAdapter = new ArticuloAdapter(firestoreRecyclerOptions);
        mRecycler.setAdapter(mAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAdapter.stopListening();
    }
}