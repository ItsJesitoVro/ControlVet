package com.example.notific;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.controlvet.notific.MainActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CrearArticulo extends AppCompatActivity {
    Button btn_add;
    EditText name, descripcion, precio;
    private FirebaseFirestore mfirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_articulo);

        name = findViewById(R.id.nombre);
        descripcion = findViewById(R.id.Descri);
        precio = findViewById(R.id.Precio);
        btn_add = findViewById(R.id.btn_add);

        mfirestore = FirebaseFirestore.getInstance();

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = name.getText().toString().trim();
                String descrip = descripcion.getText().toString().trim();
                String prec = precio.getText().toString().trim();

                if (nombre.isEmpty() && descrip.isEmpty() && prec.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "ingresa los datos", Toast.LENGTH_SHORT).show();

                } else {
                    postArticulos(nombre, descrip, prec);
                }
            }
        });


    }//end onCrate

    private void postArticulos(String nombre, String descrip, String prec) {

        DocumentReference id = mfirestore.collection("Articulos").document();

        Map<String, Object> Articulos = new HashMap<>();

        Articulos.put("id", id.getId());
        Articulos.put("Nombre", nombre);
        Articulos.put("Descripcion", descrip);
        Articulos.put("Precio", prec);


        mfirestore.collection("Articulos").document(id.getId()).set(Articulos).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(getApplicationContext(), "Creado exitosamente", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(CrearArticulo.this, MainActivity.class));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error al ingresar", Toast.LENGTH_SHORT).show();
            }
        });

    }
}