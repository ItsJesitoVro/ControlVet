package com.example.notific;

import android.content.Intent;
import android.os.Bundle;
import android.view.SearchEvent;
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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CrearArticulo extends AppCompatActivity {
    Button btn_add,btn_add_fragment;
    EditText name, descripcion, precio;
    private FirebaseFirestore mfirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_articulo);

        String id = getIntent().getStringExtra("id_articulo");
        mfirestore = FirebaseFirestore.getInstance();

        name = findViewById(R.id.nombre);
        descripcion = findViewById(R.id.Descri);
        precio = findViewById(R.id.Precio);
        btn_add = findViewById(R.id.btn_add);
        btn_add_fragment = findViewById(R.id.btn_add_fragment);

        if (id == null || id == ""){
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
        }else {
            btn_add.setText("Actualizar");
            getArticulo(id);
            btn_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String nombre = name.getText().toString().trim();
                    String descrip = descripcion.getText().toString().trim();
                    String prec = precio.getText().toString().trim();

                    if (nombre.isEmpty() && descrip.isEmpty() && prec.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "ingresa los datos", Toast.LENGTH_SHORT).show();

                    } else {
                        updateArticulo(nombre, descrip, prec, id);
                    }
                }
            });

        }

    }//end onCrate


    private void updateArticulo(String nombre, String descrip, String prec, String id) {
        Map<String, Object> Articulos = new HashMap<>();
        Articulos.put("Nombre", nombre);
        Articulos.put("Descripcion", descrip);
        Articulos.put("Precio", prec);

        mfirestore.collection("Articulos").document(id).update(Articulos).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(getApplicationContext(), "Actualizado exitosamente", Toast.LENGTH_SHORT).show();
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "ERROR AL ACTUALIZAR", Toast.LENGTH_SHORT).show();
            }
        });
    }

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
                startActivity(new Intent(CrearArticulo.this, PrincipalOtros.class));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error al ingresar", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getArticulo(String id){
        mfirestore.collection("Articulos").document(id).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String nombre = documentSnapshot.getString("Nombre");
                String descrip = documentSnapshot.getString("Descripcion");
                String prec = documentSnapshot.getString("Precio");

                name.setText(nombre);
                descripcion.setText(descrip);
                precio.setText(prec);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "ERROR al obtener los datos", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void MAin(View view){
        Intent ir = new Intent(this, PrincipalOtros.class);
        startActivity(ir);
    }
}