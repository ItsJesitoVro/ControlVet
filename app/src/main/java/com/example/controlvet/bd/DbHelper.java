package com.example.controlvet.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_MASCOTAS = "mascotas.bd";
    private static final String TABLE_MASCOTAS = "t_mascotas";
    private static final String TABLE_PROPIETARIO = "t_propietario";
    private static final String TABLE_VACUNAS = "t_vacunas";
    private static final String TABLE_RECORDAR = "t_recordar";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_MASCOTAS, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_MASCOTAS + "(" +
                "identificador INTEGER PRIMARY KEY AUTOINCREMENT," +
                "microchip TEXT NOT NULL," +
                "nombre TEXT NOT NULL," +
                "raza TEXT NOT NULL," +
                "nacimiento NUMERIC NOT NULL," +
                "color TEXT NOT NULL," +
                "tipo_mascota TEXT NOT NULL," +
                "sexo TEXT NOT NULL," +
                "datos_extras TEXT)");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_PROPIETARIO + "(" +
            "identificador INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nombre TEXT NOT NULL," +
            "celular TEXT NOT NULL," +
            "direccion TEXT NOT NULL," +
            "correo TEXT DEFAULT NULL," +
            "datos_extras TEXT)");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_VACUNAS + "(" +
                "identificador INTEGER PRIMARY KEY AUTOINCREMENT," +
                "microchip TEXT NOT NULL," +
                "vacuna_aplicada TEXT NOT NULL," +
                "diluente TEXT NOT NULL," +
                "aplicacion TEXT NOT NULL," +
                "proxima TEXT NOT NULL)");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_RECORDAR + "(" +
                "microchip TEXT PRIMARY KEY," +
                "recordatorio TEXT NOT NULL," +
                "detalles TEXT NOT NULL," +
                "recordar TEXT NOT NULL)");

}

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_MASCOTAS);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_PROPIETARIO);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_VACUNAS);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_RECORDAR);
        onCreate(sqLiteDatabase);

    }

}
