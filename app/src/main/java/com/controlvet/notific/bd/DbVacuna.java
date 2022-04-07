package com.controlvet.notific.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.controlvet.notific.entidades.Contactos;

import java.util.ArrayList;

public class DbVacuna extends DbHelper{

    Context context;

    public DbVacuna(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarVacuna(String microchip, String vacuna_aplicada, String diluente, String aplicacion, String proxima) {

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase bd = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("microchip", microchip);
            values.put("vacuna_aplicada", vacuna_aplicada);
            values.put("diluente", diluente);
            values.put("aplicacion", aplicacion);
            values.put("proxima", proxima);

            id = bd.insert(TABLE_VACUNAS, null, values);
        } catch (Exception ex){
            ex.toString();
        }

        return id;
    }

    public ArrayList<Contactos> mostrarVacunas() {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase bd = dbHelper.getWritableDatabase();

        ArrayList<Contactos> listaVacunas = new ArrayList<>();
        Contactos contactos = null;
        Cursor cursorVacuna = null;

        cursorVacuna = bd.rawQuery("SELECT * FROM " + TABLE_VACUNAS, null);

        if (cursorVacuna.moveToFirst()) {
            do {
                contactos = new Contactos();
                contactos.setMicroChip2(cursorVacuna.getString(1));
                contactos.setVacuna(cursorVacuna.getString(2));
                contactos.setDiluente(cursorVacuna.getString(3));
                contactos.setAplicado(cursorVacuna.getString(4));
                contactos.setProximo(cursorVacuna.getString(5));
                listaVacunas.add(contactos);
            } while (cursorVacuna.moveToNext());
        }
        cursorVacuna.close();

        return listaVacunas;
    }
}
