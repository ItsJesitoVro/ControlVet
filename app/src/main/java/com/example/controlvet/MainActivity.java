package com.example.controlvet;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.controlvet.adaptadores.ListaMascotasAdapter;
import com.example.controlvet.bd.DbHelper;
import com.example.controlvet.bd.DbMascotas;
import com.example.controlvet.entidades.Contactos;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnCrear;
    int REQUEST_CODE = 200;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DbMascotas dbMascotas = new DbMascotas(MainActivity.this);

        verificarPermisos();

        btnCrear = findViewById(R.id.btnCrear);

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
                alerta.setMessage("¿Seguro que desea formatear la Base de datos?")
                        .setCancelable(false)
                        .setPositiveButton("Si, crear", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                DbHelper dbHelper = new DbHelper(MainActivity.this);
                                SQLiteDatabase db = dbHelper.getWritableDatabase();
                                if(db != null){
                                    Toast.makeText(MainActivity.this, "BASE DE DATOS CREADA", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(MainActivity.this, "ERROR AL CARGAR BASE DE DATOS", Toast.LENGTH_LONG).show();
                                }
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.cancel();

                            }
                        });

                AlertDialog titulo = alerta.create();
                titulo.setTitle("Crear base de datos");
                titulo.show();

            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menuNuevo:
                nuevoregistro();
                return true;
            case R.id.menuBuscar:
                registroBuscar();
                return true;
            case R.id.menuVer:
                verRegistro();
                return true;
            case R.id.menuVerVacunas:
                verVacunas();
                return true;
            case R.id.menuAddVacuna:
                addVacunas();
                return true;
            case R.id.menuAddNoti:
                addNoti();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void nuevoregistro(){
        Intent intent = new Intent(this, agregar_datos.class);
        startActivity(intent);
    }

    private void registroBuscar(){
        Intent intent = new Intent(this, ver_datos.class);
        startActivity(intent);
    }

    private void verRegistro(){
        Intent intent = new Intent(this, VerDatos.class);
        startActivity(intent);
    }

    private void verVacunas(){
        Intent intent = new Intent(this, verVacunas2.class);
        startActivity(intent);
    }

    private void addVacunas(){
        Intent intent = new Intent(this, vacunas.class);
        startActivity(intent);
    }

    private void addNoti(){
        Intent intent = new Intent(this, notificaciones.class);
        startActivity(intent);
    }

    //Metodo el botón Siguiente
    public void Siguiente1(View view){
        Intent siguiente1 = new Intent(this, agregar_datos.class);
        startActivity(siguiente1);
    }

    public void SiguienteBuscar(View view){
        Intent siguienteB = new Intent(this, ver_datos.class);
        startActivity(siguienteB);
    }

    public void Siguiente2(View view){
        Intent siguiente2 = new Intent(this, VerDatos.class);
        startActivity(siguiente2);
    }
    public void Siguiente3(View view){
        Intent siguiente3 = new Intent(this, notificaciones.class);
        startActivity(siguiente3);
    }
    public void Siguiente4(View view){
        Intent siguiente4 = new Intent(this, vacunas.class);
        startActivity(siguiente4);
    }

    public void Irweb(View view){
        Intent irweb = new Intent(this, webb.class);
        startActivity(irweb);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void verificarPermisos(){
        int PermisosAlmacenamiento = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (PermisosAlmacenamiento == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "Permiso de Almacenamiento", Toast.LENGTH_SHORT).show();
        } else {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_CODE);
        }
    }
}