package com.controlvet.notific.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.controlvet.notific.entidades.Contactos;

import java.util.ArrayList;

public class DbPropietario extends DbHelper {
    Context context;

    public DbPropietario(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarPropietario(String nombre, String celular, String direccion, String correo, String datos_extras) {

        long id2 = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase bd = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("celular", celular);
            values.put("direccion", direccion);
            values.put("correo", correo);
            values.put("datos_extras", datos_extras);

            id2 = bd.insert(TABLE_PROPIETARIO, null, values);
        } catch (Exception ex){
            ex.toString();
        }

        return id2;
    }

    public ArrayList<Contactos> mostrarDueño(){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase bd = dbHelper.getWritableDatabase();

        ArrayList<Contactos> listaDueño = new ArrayList<>();
        Contactos contactos = null;
        Cursor cursorDueño = null;

        cursorDueño = bd.rawQuery("SELECT * FROM " + TABLE_PROPIETARIO, null);

        if (cursorDueño.moveToFirst()){
            do {
                contactos = new Contactos();
                contactos.setNombre(cursorDueño.getString(1));
                contactos.setCelular(cursorDueño.getString(2));
                contactos.setDireccion(cursorDueño.getString(3));
                contactos.setCorreo(cursorDueño.getString(4));
                contactos.setDatos_extras2(cursorDueño.getString(5));

                listaDueño.add(contactos);
            } while (cursorDueño.moveToNext());
        }
        cursorDueño.close();

        return listaDueño;
    }
}