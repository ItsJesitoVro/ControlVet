package com.example.controlvet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Metodo el botón Siguiente
    public void Siguiente1(View view){
        Intent siguiente1 = new Intent(this, agregar_datos.class);
        startActivity(siguiente1);
    }
    public void Siguiente2(View view){
        Intent siguiente2 = new Intent(this, ver_datos.class);
        startActivity(siguiente2);
    }
    public void Siguiente3(View view){
        Intent siguiente3 = new Intent(this, notificaciones.class);
        startActivity(siguiente3);
    }
    public void Siguiente4(View view){
        Intent siguiente4 = new Intent(this, vacunas.class);
        startActivity(siguiente4);
    }
}