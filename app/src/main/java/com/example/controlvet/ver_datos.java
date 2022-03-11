package com.example.controlvet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ver_datos extends AppCompatActivity {

    EditText txtNombremascota, txtMicrochip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_datos);
    }
    //Metodo para el boton anterior
    public void Regresar(View view){
        Intent regresar = new Intent(this, MainActivity.class);
        startActivity(regresar);
    }

    public void Eliminar(View view){
        Intent eliminar = new Intent(this, MainActivity.class);
        startActivity(eliminar);
    }
}