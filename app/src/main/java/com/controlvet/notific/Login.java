package com.controlvet.notific;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notific.R;
import com.google.android.material.button.MaterialButton;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        TextView usuario =(TextView) findViewById(R.id.txtusuario);
        TextView password =(TextView) findViewById(R.id.password);

        MaterialButton loginbtn = (MaterialButton) findViewById(R.id.btnlogin);

        //admin and admin

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(usuario.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
                    //correct
                    Toast.makeText(Login.this,"SESIÓN INICIADA CORRECTAMENTE",Toast.LENGTH_SHORT).show();
                    principal();
                }else
                    //incorrect
                    Toast.makeText(Login.this,"USUARIO O CONTRASEÑA ERRONEAS",Toast.LENGTH_SHORT).show();
            }
        });


    }
    public void principal(){
        Intent principal = new Intent(this, MainActivity.class);
        startActivity(principal);
    }
}