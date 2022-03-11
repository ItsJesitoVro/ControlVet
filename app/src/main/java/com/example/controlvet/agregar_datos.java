package com.example.controlvet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.controlvet.bd.DbMascotas;
import com.example.controlvet.bd.DbPropietario;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class agregar_datos extends AppCompatActivity {

    EditText txtmicrochip, txtnommascota, txtraza, txtnacimiento, txtcolor, txttipomascota, txtsexo, txtextras, txtnompro, txtcelular, txtdireccion, txtcorreo, txtextras2;
    ImageButton btnGuardar, btnLimpiar;
    Button btnNacimiento;

    Calendar actual = Calendar.getInstance();
    Calendar calendar = Calendar.getInstance();

    private int minutos, hora, dia, mes, anio, prueba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_datos);

        txtmicrochip = findViewById(R.id.txtmicrochip);
        txtnommascota = findViewById(R.id.txtnommascota);
        txtraza = findViewById(R.id.txtraza);
        txtnacimiento = findViewById(R.id.txtnacimiento);
        txtcolor = findViewById(R.id.txtcolor);
        txttipomascota = findViewById(R.id.txttipomascota);
        txtsexo =  findViewById(R.id.txtsexo);
        txtextras = findViewById(R.id.txtextras);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnLimpiar = findViewById(R.id.btnLimpiar);

        txtnompro = findViewById(R.id.txtnompro);
        txtcelular = findViewById(R.id.txtcelular);
        txtdireccion = findViewById(R.id.txtdireccion);
        txtcorreo = findViewById(R.id.txtcorreo);
        txtextras2 = findViewById(R.id.txtextras2);

        btnNacimiento = findViewById(R.id.btnNacimiento);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbMascotas dbMascotas = new DbMascotas(agregar_datos.this);
                long id = dbMascotas.insertarMascotas(txtmicrochip.getText().toString(), txtnommascota.getText().toString(), txtraza.getText().toString(), txtnacimiento.getText().toString(), txtcolor.getText().toString(), txttipomascota.getText().toString(), txtsexo.getText().toString(), txtextras.getText().toString());

                DbPropietario dbPropietario = new DbPropietario(agregar_datos.this);
                long id2 = dbPropietario.insertarPropietario(txtnompro.getText().toString(), txtcelular.getText().toString(), txtdireccion.getText().toString(), txtcorreo.getText().toString(), txtextras2.getText().toString());

                if (id > 0 && id2 > 0){
                    Toast.makeText(agregar_datos.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                    limpiar();
                } else {
                    Toast.makeText(agregar_datos.this, "ERROR AL GUARDAR EL REGISTRO", Toast.LENGTH_LONG).show();
                }

            }
        });
        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiar();
            }
        });

        btnNacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anio = actual.get(Calendar.YEAR);
                mes = actual.get(Calendar.MONTH);
                dia = actual.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int y, int m, int d) {
                        calendar.set(Calendar.DAY_OF_MONTH,d);
                        calendar.set(Calendar.MONTH,m);
                        calendar.set(Calendar.YEAR,y);

                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                        String strDate = format.format(calendar.getTime());
                        txtnacimiento.setText(strDate);
                    }
                },anio,mes,dia);
                datePickerDialog.show();
            }
        });
    }

    private void limpiar(){
        txtmicrochip.setText("");
        txtnommascota.setText("");
        txtraza.setText("");
        txtnacimiento.setText("");
        txtcolor.setText("");
        txttipomascota.setText("");
        txtsexo.setText("");
        txtextras.setText("");

        txtnompro.setText("");
        txtcelular.setText("");
        txtdireccion.setText("");
        txtcorreo.setText("");
        txtextras2.setText("");
    }



    //Metodo para el boton anterior
    public void Regresar(View view){
        Intent regresar = new Intent(this, MainActivity.class);
        startActivity(regresar);
    }
}