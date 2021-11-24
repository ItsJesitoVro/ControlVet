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
import android.widget.Toast;

import com.example.controlvet.bd.DbVacuna;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class vacunas extends AppCompatActivity {

    EditText txtMicroChip, txtVacuna, txtDiluente, txtAplicada, txtProxima;
    ImageButton btnGuardarV, btnLimpiar;
    Button btnAplicada, btnProximo;

    Calendar actual = Calendar.getInstance();
    Calendar calendar = Calendar.getInstance();

    private int dia, mes, anio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacunas);

        txtMicroChip = findViewById(R.id.txtMicroChip);
        txtVacuna = findViewById(R.id.txtVacuna);
        txtDiluente = findViewById(R.id.txtDiluente);
        txtAplicada = findViewById(R.id.txtAplicada);
        txtProxima= findViewById(R.id.txtProxima);
        btnGuardarV= findViewById(R.id.btnGuardarV);
        btnAplicada = findViewById(R.id.btnAplicada);
        btnProximo = findViewById(R.id.btnProximo);
        btnLimpiar = findViewById(R.id.btnLimpiar);

        btnGuardarV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbVacuna dbVacuna = new DbVacuna(vacunas.this);
                long id = dbVacuna.insertarVacuna(txtMicroChip.getText().toString(),txtVacuna.getText().toString(),txtDiluente.getText().toString(),txtAplicada.getText().toString(),txtProxima.getText().toString());

                if (id > 0){
                    Toast.makeText(vacunas.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                    limpiar();
                } else {
                    Toast.makeText(vacunas.this, "ERROR AL GUARDAR EL REGISTRO", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnAplicada.setOnClickListener(new View.OnClickListener() {
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
                        txtAplicada.setText(strDate);
                    }
                },anio,mes,dia);
                datePickerDialog.show();
            }
        });

        btnProximo.setOnClickListener(new View.OnClickListener() {
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
                        txtProxima.setText(strDate);
                    }
                },anio,mes,dia);
                datePickerDialog.show();
            }
        });

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiar();
            }
        });
    }

    private void limpiar(){
        txtMicroChip.setText("");
        txtVacuna.setText("");
        txtDiluente.setText("");
        txtAplicada.setText("");
        txtProxima.setText("");
    }

    //Metodo para el boton anterior
    public void Regresar(View view){
        Intent regresar = new Intent(this, MainActivity.class);
        startActivity(regresar);
    }
}