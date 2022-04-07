package com.controlvet.notific;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Data;
import androidx.work.WorkManager;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.notific.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

public class notificaciones extends AppCompatActivity {

    ImageButton btnGuardarN, btnLimpiar;
    Button btnFecha, btnHora, btnEliminar;
    EditText txtFecha, txtHora, txtTitulo, txtDetalle;

    Calendar actual = Calendar.getInstance();
    Calendar calendar = Calendar.getInstance();

    private int minutos, hora, dia, mes, anio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificaciones);

        btnFecha = findViewById(R.id.btnFecha);
        btnHora = findViewById(R.id.btnHora);
        btnEliminar = findViewById(R.id.btnEliminar1);
        btnGuardarN = findViewById(R.id.btnGuardarN);
        txtFecha = findViewById(R.id.txtFecha);
        txtHora = findViewById(R.id.txtHora);
        txtTitulo = findViewById(R.id.txtTitulo);
        txtDetalle = findViewById(R.id.txtDetalle);
        btnLimpiar = findViewById(R.id.btnLimpiar);

        btnFecha.setOnClickListener(new View.OnClickListener() {
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
                        txtFecha.setText(strDate);
                    }
                },anio,mes,dia);
                datePickerDialog.show();
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

        btnGuardarN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validar()) {
                    String txtTitul = txtTitulo.getText().toString();
                    String txtDetall = txtDetalle.getText().toString();
                    String txtFech = txtFecha.getText().toString();
                    String txtHor = txtHora.getText().toString();

                    if(txtTitul.length() == 0 || txtDetall.length() == 0 || txtFech.length() == 0 || txtHor.length() == 0){
                        Toast.makeText(notificaciones.this,"Debes de acompletar todos los campos", Toast.LENGTH_LONG).show();
                 } if (txtTitul.length() != 0 && txtDetall.length() != 0 && txtFech.length() != 0 && txtHor.length() != 0){
                    String tag = generateKey();
                    long AlertTime = calendar.getTimeInMillis() - System.currentTimeMillis();
                    int random = (int)(Math.random() * 50 + 1);

                    Data data = GuardarData(" " + txtTitulo.getText(), "" + txtDetalle.getText(),random);
                    Workmanagernoti.GuardarNoti(AlertTime,data,tag);

                    Toast.makeText(notificaciones.this,"Recordatorio Guardado", Toast.LENGTH_SHORT).show();
                    limpiar();
                    }
                }
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EliminarNoti("tag1");
            }
        });

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiar();
            }
        });
    }

    private void EliminarNoti(String tag){
        WorkManager.getInstance(this).cancelAllWorkByTag(tag);
        Toast.makeText(notificaciones.this,"Recordatorio Dado de Baja", Toast.LENGTH_SHORT).show();
    }

    private String generateKey(){
        return UUID.randomUUID().toString();
    }

    private Data GuardarData(String titulo, String detalle, int id_noti){
        return new Data.Builder().putString("titulo",titulo).putString("detalle",detalle)
                .putInt("id_noti",id_noti).build();
    }

    public void limpiar(){
        txtTitulo.setText("");
        txtDetalle.setText("");
        txtHora.setText("");
        txtFecha.setText("");
    }

    public boolean validar()
    {
        boolean retorno = true;
        String Titulo = txtTitulo.getText().toString();
        String Detalle = txtDetalle.getText().toString();
        if(Titulo.isEmpty()){
            txtTitulo.setError("Ingrese Titulo");
            retorno=false;
        }
        if(Detalle.isEmpty()){
            txtDetalle.setError("Ingrese los detalles");
            retorno=false;
        }
        return retorno;
    }

    private void performNotify(View view){
        Uri uriRing = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Ringtone ringtone = RingtoneManager.getRingtone(notificaciones.this, uriRing);
        ringtone.play();
    }

    //Metodo para el boton anterior
    public void Regresar(View view){
        Intent regresar = new Intent(this, MainActivity.class);
        startActivity(regresar);
    }
}