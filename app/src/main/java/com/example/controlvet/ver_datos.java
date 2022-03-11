package com.example.controlvet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class ver_datos extends AppCompatActivity {

    EditText txtNombremascota, txtMicrochip;
    ImageButton btnEliminar1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_datos);

        btnEliminar1 = findViewById(R.id.btnEliminar1);

        btnEliminar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });

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