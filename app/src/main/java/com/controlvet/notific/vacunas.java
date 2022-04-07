package com.controlvet.notific;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Data;

import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.notific.R;
import com.controlvet.notific.bd.DbVacuna;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;
import java.util.regex.Pattern;

public class vacunas extends AppCompatActivity {

    EditText txtMicroChip, txtVacuna, txtDiluente, txtAplicada, txtProxima, txtHora, txtNumero;
    ImageButton btnGuardarV, btnLimpiar;
    Button btnAplicada, btnProximo, btnHora;

    Calendar actual = Calendar.getInstance();
    Calendar calendar = Calendar.getInstance();

    private PendingIntent pendingIntent;

    private int dia, mes, anio, hora, minutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacunas);

        txtMicroChip = findViewById(R.id.txtMicroChip);
        txtVacuna = findViewById(R.id.txtVacuna);
        txtDiluente = findViewById(R.id.txtDiluente);
        txtAplicada = findViewById(R.id.txtAplicada);
        txtProxima= findViewById(R.id.txtProxima);
        txtHora = findViewById(R.id.txtHora);
        txtNumero = findViewById(R.id.txtNumero);
        btnGuardarV= findViewById(R.id.btnGuardarV);
        btnAplicada = findViewById(R.id.btnAplicada);
        btnProximo = findViewById(R.id.btnProximo);
        btnLimpiar = findViewById(R.id.btnLimpiar);
        btnHora = findViewById(R.id.btnHora);

        Intent i = new Intent(this, llamadas.class);

        btnGuardarV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validar()) {
                    DbVacuna dbVacuna = new DbVacuna(vacunas.this);
                    long id = dbVacuna.insertarVacuna(txtMicroChip.getText().toString(), txtVacuna.getText().toString(), txtDiluente.getText().toString(), txtAplicada.getText().toString(), txtProxima.getText().toString());

                    if (id > 0) {
                        String tag = generateKey();
                        long AlertTime = calendar.getTimeInMillis() - System.currentTimeMillis();
                        int random = (int) (Math.random() * 50 + 1);

                        Data data = GuardarData("Recordatorio de la Vacuna: " + txtVacuna.getText(), "Llamar al numero: " + txtNumero.getText(), random);
                        Workmanagernoti2.GuardarNoti2(AlertTime, data, tag);

                        Toast.makeText(vacunas.this, "Registro Guardado", Toast.LENGTH_SHORT).show();

                        i.putExtra("dato", txtNumero.getText().toString());
                        startActivity(i);
                        limpiar();
                    } else {
                        Toast.makeText(vacunas.this, "ERROR AL GUARDAR EL REGISTRO", Toast.LENGTH_LONG).show();
                    }
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

        btnHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hora = actual.get(Calendar.HOUR_OF_DAY);
                minutos = actual.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(v.getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int h, int m) {
                        calendar.set(calendar.HOUR_OF_DAY,h);
                        calendar.set(calendar.MINUTE,m);

                        txtHora.setText(String.format("%02d:%02d", h, m));

                    }
                }, hora,minutos,true);
                timePickerDialog.show();
            }
        });

    }


    private void setPedingIntent(Class<?> clsActivity){
        Intent intent = new Intent(this, clsActivity);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(clsActivity);
        stackBuilder.addNextIntent(intent);
        pendingIntent = stackBuilder.getPendingIntent(1, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private String generateKey(){
        return UUID.randomUUID().toString();
    }

    private Data GuardarData(String titulo, String detalle, int id_noti){
        return new Data.Builder().putString("titulo",titulo).putString("detalle",detalle)
                .putInt("id_noti",id_noti).build();
    }

    public boolean validar()
    {
        boolean retorno = true;
        String Microchip=txtMicroChip.getText().toString();
        String VacunaAplicada=txtVacuna.getText().toString();
        String Diluente=txtDiluente.getText().toString();
        String Numero=txtNumero.getText().toString();
        String Aplicacion=txtAplicada.getText().toString();
        String Proxima=txtProxima.getText().toString();
        if(Microchip.isEmpty())
        {
            txtMicroChip.setError("Ingrese el microchip");
            retorno=false;
        }
        if(VacunaAplicada.isEmpty())
        {
            txtVacuna.setError("Ingrese el nombre de la vacuna");
            retorno=false;
        }
        if(Diluente.isEmpty())
        {
            txtDiluente.setError("Ingrese el Diluente");
            retorno=false;
        }
        if(Numero.isEmpty())
        {
            txtNumero.setError("Ingrese su número de teléfono");
            retorno=false;
        }
        if(Aplicacion.isEmpty())
        {
            txtAplicada.setError("Ingrese la fecha de aplicación");
            retorno=false;
        }
        if(Proxima.isEmpty())
        {
            txtProxima.setError("Ingrese la fecha proxima de aplicación");
            retorno=false;
        }
        if (checkPhone(Numero)==false){
            txtNumero.setError("Ingrese un número válido");
            retorno=false;
        }
        return retorno;
    }

    public static final Pattern PHONE_NUMBER_PATTERN =
            Pattern.compile("^[+]?[0-9]{10,13}$");

    private boolean checkPhone(String telefono) {
        return PHONE_NUMBER_PATTERN.matcher(telefono).matches();
    }

    private void limpiar(){
        txtMicroChip.setText("");
        txtVacuna.setText("");
        txtDiluente.setText("");
        txtAplicada.setText("");
        txtProxima.setText("");
        txtNumero.setText("");
        txtHora.setText("");
    }

    //Metodo para el boton anterior
    public void Regresar(View view){
        Intent regresar = new Intent(this, MainActivity.class);
        startActivity(regresar);
    }
}