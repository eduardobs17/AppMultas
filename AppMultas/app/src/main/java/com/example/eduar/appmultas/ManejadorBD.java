package com.example.eduar.appmultas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ManejadorBD {

    private ConexionBD conexionBD;
    private Context ourContext;
    private SQLiteDatabase database;

    public ManejadorBD(Context c) { ourContext = c; }

    public ManejadorBD abrirBaseDeDatos() throws SQLException {
        conexionBD = new ConexionBD(ourContext);
        database = conexionBD.getWritableDatabase();
        return this;
    }

    public void cerrar() { conexionBD.close(); }

    public void insertarValores(String nombre, String precio, String nombreC, String matriculaA, String fecha, String comentario) {
        ContentValues valores = new ContentValues();
        valores.put(ConexionBD.nombreInfraccion, nombre);
        valores.put(ConexionBD.precioMulta, precio);
        valores.put(ConexionBD.nombreConductor, nombreC);
        valores.put(ConexionBD.matriculaAuto, matriculaA);
        valores.put(ConexionBD.fechaMulta, fecha);
        valores.put(ConexionBD.miComentario, comentario);
        database.insert(ConexionBD.TbMultas, null, valores);
    }

    public Cursor leerDatos() {
        String[] todasLasColumnas = new String[] {
                ConexionBD.idMulta, ConexionBD.nombreInfraccion, ConexionBD.precioMulta, ConexionBD.nombreConductor,
                ConexionBD.fechaMulta, ConexionBD.miComentario,
        };

        Cursor c = database.query(ConexionBD.TbMultas, todasLasColumnas, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public int actualizarDatos(long pIdMulta, String pNombre, String pPrecio, String pNombreC, String pMatricula, String pFecha, String pComentario) {
        ContentValues cvActualizar = new ContentValues();
        cvActualizar.put(ConexionBD.nombreInfraccion, pNombre);
        cvActualizar.put(ConexionBD.precioMulta, pPrecio);
        cvActualizar.put(ConexionBD.nombreConductor, pNombreC);
        cvActualizar.put(ConexionBD.matriculaAuto, pMatricula);
        cvActualizar.put(ConexionBD.fechaMulta, pFecha);
        cvActualizar.put(ConexionBD.miComentario, pComentario);

        int i = database.update(ConexionBD.TbMultas, cvActualizar, ConexionBD.idMulta +  " = " + pIdMulta, null);
        return i;
    }

    public void borrarDatos(long pId) {
        database.delete(ConexionBD.TbMultas, ConexionBD.idMulta + " = " + pId, null);
    }
}