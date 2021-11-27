package com.example.controlvet;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.controlvet.entidades.Contactos;

import java.util.ArrayList;

public class llamadas extends AppCompatActivity {

    EditText txtCelu;
    ImageButton btnLlamar;
    int REQUEST_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llamadas);

        verificarPermisos();

        txtCelu = findViewById(R.id.txtCelu);
        btnLlamar = findViewById(R.id.btnLlamar);

        String dato = getIntent().getStringExtra("dato");
        txtCelu.setText("" + dato);

        btnLlamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + txtCelu.getText()));
                if (ActivityCompat.checkSelfPermission(llamadas.this, Manifest.permission.CALL_PHONE)!=
                        PackageManager.PERMISSION_GRANTED)
                    return;
                startActivity(i);
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void verificarPermisos(){
        int PermisosLlamar= ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);

        if (PermisosLlamar == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "Permiso de llamada", Toast.LENGTH_SHORT).show();
        } else {
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE},REQUEST_CODE);
        }
    }

    //Metodo para el boton anterior
    public void Regresar(View view){
        Intent regresar = new Intent(this, MainActivity.class);
        startActivity(regresar);
    }
}