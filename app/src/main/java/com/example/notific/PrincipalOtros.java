package com.example.notific;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adapter.ArticuloAdapter;
import com.controlvet.notific.MainActivity;
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

        setUpRecyclerView();

    }
    private void setUpRecyclerView(){
        mRecycler = findViewById(R.id.recyclerViewSingle);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        Query query = mFirestore.collection("Articulos");

        FirestoreRecyclerOptions<Articulo> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<Articulo>().setQuery(query, Articulo.class).build();
        mAdapter = new ArticuloAdapter(firestoreRecyclerOptions,this);
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

    public void AgregarArt(View view){
        Intent ir = new Intent(this, CrearArticulo.class);
        startActivity(ir);
    }

    public void MAin(View view){
        Intent ir = new Intent(this, MainActivity.class);
        startActivity(ir);
    }
}