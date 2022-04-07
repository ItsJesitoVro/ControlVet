package com.controlvet.notific.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_MASCOTAS = "mascotas.bd";
    public static final String TABLE_MASCOTAS = "t_mascotas";
    public static final String TABLE_PROPIETARIO = "t_propietario";
    public static final String TABLE_VACUNAS = "t_vacunas";
    public static final String TABLE_RECORDAR = "t_recordar";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_MASCOTAS, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_MASCOTAS + "(" +
                "identificador INTEGER PRIMARY KEY AUTOINCREMENT," +
                "microchip TEXT," +
                "nombre TEXT ," +
                "raza TEXT ," +
                "nacimiento TEXT," +
                "color TEXT," +
                "tipo_mascota TEXT," +
                "sexo TEXT," +
                "datos_extras TEXT)");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_PROPIETARIO + "(" +
                "identificador INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT," +
                "celular TEXT," +
                "direccion TEXT," +
                "correo TEXT," +
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

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_MASCOTAS);
        onCreate(sqLiteDatabase);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PROPIETARIO);
        onCreate(sqLiteDatabase);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_VACUNAS);
        onCreate(sqLiteDatabase);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_RECORDAR);
        onCreate(sqLiteDatabase);

    }
}
