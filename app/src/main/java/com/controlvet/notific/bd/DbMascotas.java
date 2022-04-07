package com.controlvet.notific.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.controlvet.notific.entidades.Contactos;

import java.util.ArrayList;

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

    public ArrayList<Contactos> mostrarContactos(){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase bd = dbHelper.getWritableDatabase();

        ArrayList<Contactos> listaContactos = new ArrayList<>();
        Contactos contactos = null;
        Cursor cursorContactos = null;

        cursorContactos = bd.rawQuery("SELECT * FROM " + TABLE_MASCOTAS, null);

        if (cursorContactos.moveToFirst()){
            do {
                contactos = new Contactos();
                contactos.setMicrochip(cursorContactos.getString(1));
                contactos.setNombreM(cursorContactos.getString(2));
                contactos.setRaza(cursorContactos.getString(3));
                contactos.setNacimiento(cursorContactos.getString(4));
                contactos.setColor(cursorContactos.getString(5));
                contactos.setTipo_mascota(cursorContactos.getString(6));
                contactos.setSexo(cursorContactos.getString(7));
                contactos.setDatos_extras(cursorContactos.getString(8));
                listaContactos.add(contactos);
            } while (cursorContactos.moveToNext());
        }
        cursorContactos.close();

        return listaContactos;
    }


    public Contactos verContactos(int identificador){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase bd = dbHelper.getWritableDatabase();

        Contactos contactos = null;
        Cursor cursorContactos;

        cursorContactos = bd.rawQuery("SELECT * FROM " + TABLE_MASCOTAS + " WHERE identificador = " + identificador + " LIMIT 1", null);

        if (cursorContactos.moveToFirst()){
            contactos = new Contactos();
            contactos.setMicrochip(cursorContactos.getString(1));
            contactos.setNombreM(cursorContactos.getString(2));
            contactos.setRaza(cursorContactos.getString(3));
            contactos.setNacimiento(cursorContactos.getString(4));
            contactos.setColor(cursorContactos.getString(5));
            contactos.setTipo_mascota(cursorContactos.getString(6));
            contactos.setSexo(cursorContactos.getString(7));
            contactos.setDatos_extras(cursorContactos.getString(8));
        }
        cursorContactos.close();

        return contactos;
    }
    public boolean eliminar(String microchip){
        boolean correcto = false;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase bd = dbHelper.getWritableDatabase();

        try {
            bd.execSQL("DELETE FROM " + TABLE_MASCOTAS + " WHERE  microchip = '"+microchip+"' LIMIT 1");
            correcto = true;
        }catch (Exception ex){
            ex.toString();
            correcto = false;
        }finally {
            bd.close();
        }
        return correcto;
    }
    public void buscarMascotas(Contactos contactos, String microchip){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase bd = dbHelper.getWritableDatabase();

        Cursor cursorContactos;

        cursorContactos = bd.rawQuery("SELECT * FROM TABLE_MASCOTAS WHERE microchip ='"+microchip+"'" , null);

        if (cursorContactos.moveToFirst()){
            do {
                contactos.setMicrochip(cursorContactos.getString(1));
                contactos.setNombreM(cursorContactos.getString(2));
            } while (cursorContactos.moveToNext());
        }
    }
    public void eliminarr(String microchip){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase bd = dbHelper.getWritableDatabase();
        if (bd!=null){
            bd.execSQL("DELETE FROM TABLE_MASCOTAS WHERE microchip='+microchip+'");
                    bd.close();
        }
    }

}