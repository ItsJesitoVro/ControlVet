package com.example.controlvet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.controlvet.adaptadores.ListaVacunasAdapter;
import com.example.controlvet.bd.DbVacuna;
import com.example.controlvet.entidades.Contactos;

import java.util.ArrayList;

public class verVacunas2 extends AppCompatActivity {

    RecyclerView listaVacunas;
    ArrayList<Contactos> listaArrayVacunas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_vacunas2);

        listaVacunas = findViewById(R.id.listavacunas);
        listaVacunas.setLayoutManager(new LinearLayoutManager(this));

        DbVacuna dbVacuna = new DbVacuna (verVacunas2.this);

        listaArrayVacunas = new ArrayList<>();

        ListaVacunasAdapter adapter = new ListaVacunasAdapter(dbVacuna.mostrarVacunas());
        listaVacunas.setAdapter(adapter);
    }
    //Metodo para el boton anterior
    public void Regresar(View view){
        Intent regresar = new Intent(this, MainActivity.class);
        startActivity(regresar);
    }
}