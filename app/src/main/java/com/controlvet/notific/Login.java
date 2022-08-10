package com.controlvet.notific;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import com.example.notific.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.Executor;

public class Login extends AppCompatActivity {

    private Button btnregistrar, loginbtn;
    private ImageButton btngmail1;
    private EditText usuario, password;
    private TextView txtsesion;
    private FirebaseAuth autentificacion;
    private GoogleSignInClient mGoogleSignInClient;
    private final static int RC_SIGN_IN = 123;
    boolean aceptacion=false;

    @Override
    protected void onStart(){
        super.onStart();
        FirebaseUser user = autentificacion.getCurrentUser();
        if(user != null){
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }
    }

    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        autentificacion = FirebaseAuth.getInstance();
        createRequest();

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference myRef = db.getReference("message");

        btnregistrar = findViewById(R.id.btnregistrar);
        btngmail1 = findViewById(R.id.btngmail);
        usuario = findViewById(R.id.nombre);
        password = findViewById(R.id.contraseña);
        loginbtn = findViewById(R.id.btniniciar);
        txtsesion = (TextView)findViewById(R.id.txtsesion);

        //handle autenticacion click, start

        executor = ContextCompat.getMainExecutor(this);
        biometricPrompt = new BiometricPrompt(Login.this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                /*ERRORES CUANDO NO ES COMPATIBLE
                ERRORES CUANDO NO ES COMPATIBLE txtsesion.setText("Error en la autenticacion, tipo: " + errorCode);
                Toast.makeText(Login.this, "Error en la autenticacion, tipo: " + errorCode, Toast.LENGTH_SHORT).show();*/
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                //Inicio correcto
                //txtsesion.setText("Autenticacion exitosa");
                Toast.makeText(Login.this, "Autenticacion exitosa", Toast.LENGTH_SHORT).show();
                aceptacion = true;
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                //El dedo no coencide
                //txtsesion.setText("Autenticacion no coencide");
                aceptacion = false;
                Toast.makeText(Login.this, "Vuelve a intentar", Toast.LENGTH_SHORT).show();
            }
        });

        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Autorizacion por Huella")
                .setSubtitle("Coloque su huella para verificar su identidad")
                .setNegativeButtonText("Usa tu contraseña")
                .build();
        //admin and admin

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                biometricPrompt.authenticate(promptInfo);
                Login.super.onPause();
                if(aceptacion==true) {
                    String usuario1 = usuario.getText().toString().trim();
                    String contra = password.getText().toString().trim();
                    if (usuario1.isEmpty() && contra.isEmpty()) {
                        Toast.makeText(Login.this, "Ingrese los datos", Toast.LENGTH_SHORT).show();
                    } else {
                        Login.super.onResume();
                        userLogin(usuario1, contra);
                    }
                }
            }
        });

        btngmail1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                biometricPrompt.authenticate(promptInfo);

                    signIn();

            }
        });

    }

    private void createRequest() {
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();


        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                Toast.makeText(this, e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {


        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        autentificacion.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = autentificacion.getCurrentUser();
                            finish();
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(Login.this, "Sorry auth failed.", Toast.LENGTH_SHORT).show();


                        }

                    }
                });
    }

    public void principal(){
        Intent principal = new Intent(this, MainActivity.class);
        startActivity(principal);
        finish();
    }

    public void registrar(View view){
        Intent registrar = new Intent(this, registrarse.class);
        startActivity(registrar);
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
                    Toast.makeText(Login.this, "Error, verificar sus datos o...", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Login.this, "REGISTRATE!!", Toast.LENGTH_SHORT).show();
            }
        });
    }



}