package com.controlvet.notific;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.notific.R;
import com.controlvet.notific.bd.DbMascotas;
import com.controlvet.notific.entidades.Contactos;

public class ver_datos extends AppCompatActivity {

    EditText txtNombremascota, txtMicrochip;
    ImageButton btnEliminar1;
    Button btnBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_datos);

        txtNombremascota=findViewById(R.id.txtNombremascotaB);
        txtMicrochip=findViewById(R.id.txtMicrochipB);

        btnBuscar=(Button)findViewById(R.id.btnBuscar);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contactos contactos = new Contactos();
                DbMascotas dbMascotas = new DbMascotas(ver_datos.this);
                dbMascotas.buscarMascotas(contactos, txtMicrochip.getText().toString());
                txtNombremascota.setText(contactos.getNombreM());

            }
        });
    }


    //Metodo para el boton anterior
    public void Regresar(View view){
        Intent regresar = new Intent(this, MainActivity.class);
        startActivity(regresar);
    }

    public void Eliminar(View view){
        Intent eliminar = new Intent(this, ver_datos.class);
        startActivity(eliminar);
    }

}