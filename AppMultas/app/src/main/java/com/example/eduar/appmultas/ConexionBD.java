package com.example.eduar.appmultas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexionBD extends SQLiteOpenHelper {
    public static final String idMulta = "_id";
    public static final String nombreInfraccion = "Nombre";
    public static final String precioMulta = "PrecioMulta";
    public static final String nombreConductor = "NombreConductor";
    public static final String matriculaAuto = "MatriculaAuto";
    public static final String fechaMulta = "Fecha";
    public static final String miComentario = "Comentario";

    public static final String BaseDatos = "BDMultas";
    public static final String TbMultas = "Multas";


    public ConexionBD(Context context) { super(context, BaseDatos, null, 1); }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TbMultas + " (" +
                idMulta + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                nombreInfraccion + " TEXT, " + precioMulta + " TEXT, " + nombreConductor + " TEXT, " +
                matriculaAuto + " TEXT, " + fechaMulta + " TEXT, " + miComentario + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + TbMultas);
        onCreate(sqLiteDatabase);
    }
}