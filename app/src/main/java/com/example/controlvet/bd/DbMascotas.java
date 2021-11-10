package com.example.controlvet.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class DbMascotas extends DbHelper {

    Context context;

    public DbMascotas(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarMascotas(String microchip, String nombre, String raza, String nacimiento, String color, String tipomascota, String sexo, String datos_extras) {

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase bd = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("microchip", microchip);
            values.put("nombre", nombre);
            values.put("raza", raza);
            values.put("nacimiento", nacimiento);
            values.put("color", color);
            values.put("tipo_mascota", tipomascota);
            values.put("sexo", sexo);
            values.put("datos_extras", datos_extras);

            id = bd.insert(TABLE_MASCOTAS, null, values);
        } catch (Exception ex){
            ex.toString();
        }

        return id;
    }
}