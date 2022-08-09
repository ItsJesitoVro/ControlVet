package com.controlvet.notific;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.notific.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class registrarse extends AppCompatActivity {


    Button btn_register;
    EditText name, email, password;
    FirebaseFirestore mFirestore;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);
        this.setTitle("Registro");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        name = findViewById(R.id.nombre);
        email = findViewById(R.id.correo);
        password = findViewById(R.id.contraseña);
        btn_register = findViewById(R.id.btnregister);


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validar()){
                    String nameUser = name.getText().toString().trim();
                    String emailUser = email.getText().toString().trim();
                    String passUser = password.getText().toString().trim();

                if (nameUser.isEmpty() && emailUser.isEmpty() && passUser.isEmpty()) {
                    Toast.makeText(registrarse.this, "Complete los datos", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(registrarse.this, "Se ha registrado su cuenta", Toast.LENGTH_SHORT).show();
                    registerUser(nameUser, emailUser, passUser);
                    regresar();
                }
            }
        }
        });

    }

    public void regresar(){
        Intent regresar = new Intent(this, MainActivity.class);
        startActivity(regresar);
        finish();
    }

    private void registerUser(String nameUser, String emailUser, String passUser) {
        if (validar()){
            mAuth.createUserWithEmailAndPassword(emailUser, passUser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (validar()){
                        String id = mAuth.getCurrentUser().getUid();
                        Map<String, Object> map = new HashMap<>();
                        map.put("id", id);
                        map.put("name", nameUser);
                        map.put("email", emailUser);
                        map.put("password", passUser);

                    mFirestore.collection("user").document(id).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            if(validar()){
                            finish();
                            startActivity(new Intent(registrarse.this, MainActivity.class));
                            Toast.makeText(registrarse.this, "Usuario registrado con éxito", Toast.LENGTH_SHORT).show();
                        }}
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(registrarse.this, "Error al guardar", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(registrarse.this, "Error al registrar", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

    public static final Pattern EMAIL_ADDRESS_PATTERN =
            Pattern.compile( "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+" );

    private boolean checkEmail(String email) {
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
    }
     public boolean validar(){
         boolean retorno = true;
         String correo = email.getText().toString();
         String nombre = name.getText().toString();
         String contra = password.getText().toString();
         if (checkEmail(correo)==false){
             email.setError("La cuenta debe de tener un @ y debe de estar en algun dominio (ejemplo .com, .edu, .mx)");
             retorno=false;
         } if(nombre.isEmpty()){
             name.setError("Ingrese su nombre");
             retorno=false;
         } if(contra.isEmpty()){
             password.setError("Ingrese su contraseña");
             retorno=false;
         }
         return retorno;
     }
    }


