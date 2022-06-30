package com.controlvet.notific;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notific.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;

import com.google.firebase.auth.*;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.*;

public class Login extends AppCompatActivity {

    FirebaseAuth autentificacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        autentificacion = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference myRef = db.getReference("message");

        EditText usuario, password;
        Button loginbtn;

        usuario = findViewById(R.id.txtusuario);
        password = findViewById(R.id.password);

        loginbtn = findViewById(R.id.btnlogin);

        //admin and admin

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario1 = usuario.getText().toString().trim();
                String contra = password.getText().toString().trim();
                if(usuario1.isEmpty() && contra.isEmpty()){
                    Toast.makeText(Login.this, "Ingrese los datos", Toast.LENGTH_SHORT).show();
                } else {
                    userLogin(usuario1,contra);
                }
            }
        });
    }

    public void principal(){
        Intent principal = new Intent(this, MainActivity.class);
        startActivity(principal);
        finish();
    }

    protected void onStart(){
        super.onStart();
        FirebaseUser usuario1 = autentificacion.getCurrentUser();
        if(usuario1 != null){
            startActivity(new Intent(Login.this, MainActivity.class));
            finish();
        }
    }

    public void userLogin(String usuario1, String contra){
        autentificacion.signInWithEmailAndPassword(usuario1, contra).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    finish();
                    startActivity(new Intent(Login.this, MainActivity.class));
                    Toast.makeText(Login.this, "Bienvenido!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Login.this, "Error, verificar sus datos", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Login.this, "No hay acceso a Internet", Toast.LENGTH_SHORT).show();
            }
        });
    }
}