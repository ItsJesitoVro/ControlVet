package com.example.controlvet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.controlvet.adaptadores.ListaMascotasAdapter;
import com.example.controlvet.adaptadores.ListaPropietarioAdapter;
import com.example.controlvet.bd.DbMascotas;
import com.example.controlvet.bd.DbPropietario;
import com.example.controlvet.entidades.Contactos;

import java.util.ArrayList;

public class VerDatos extends AppCompatActivity {

    RecyclerView listaContactos, listaDueño;
    ArrayList<Contactos> listaArrayContanctos, ListaArrayDueño;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_datos2);

        listaContactos = findViewById(R.id.listapersona);
        listaContactos.setLayoutManager(new LinearLayoutManager(this));

        listaDueño = findViewById(R.id.listadueño);
        listaDueño.setLayoutManager(new LinearLayoutManager(this));

        DbMascotas dbMascotas = new DbMascotas(VerDatos.this);
        DbPropietario dbDueño = new DbPropietario(VerDatos.this);

        listaArrayContanctos = new ArrayList<>();
        ListaArrayDueño = new ArrayList<>();

        ListaMascotasAdapter adapter = new ListaMascotasAdapter(dbMascotas.mostrarContactos());
        ListaPropietarioAdapter adapter2 = new ListaPropietarioAdapter(dbDueño.mostrarDueño());
        listaContactos.setAdapter(adapter);
        listaDueño.setAdapter(adapter2);
    }
}