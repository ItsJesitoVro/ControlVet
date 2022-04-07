package com.controlvet.notific;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.controlvet.notific.adaptadores.ListaMascotasAdapter;
import com.controlvet.notific.adaptadores.ListaPropietarioAdapter;
import com.controlvet.notific.bd.DbMascotas;
import com.controlvet.notific.bd.DbPropietario;
import com.controlvet.notific.entidades.Contactos;
import com.example.notific.R;

import java.util.ArrayList;

public class  VerDatos extends AppCompatActivity {

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
    //Metodo para el boton anterior
    public void Regresar(View view){
        Intent regresar = new Intent(this, MainActivity.class);
        startActivity(regresar);
    }
}