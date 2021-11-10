package com.example.controlvet.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

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
}
