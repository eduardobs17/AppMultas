package com.example.eduar.appmultas;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class hacerMulta extends Activity {

    //Anexamos valores al spinner de numeros publico
    Spinner listaInfraccion;
    String[] datosInfraccion = {"Selecciona una categoría de infracción," +
            "Exceso de velocidad", "Hablar conduciendo", "Manejar en estado de ebriedad", "Estacionarse en línea amarilla",
            "Estacionarse en doble fila", "No obedecer señales de vialidad"};

    EditText editaPrecio;
    EditText editaNombre;
    EditText editaMatricula;
    EditText editaFecha;
    EditText editaComentario;

    String guardaInfraccion, guardaInfraccionTotal, guardaPrecio, guardaNombre, guardaMatricula, guardaFecha, guardaComentario;

    //Hacemos la conexion con nuestra bd
    ManejadorBD db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hacer_multa);

        listaInfraccion = (Spinner) findViewById(R.id.spinner_infraccion);
        editaPrecio = (EditText) findViewById(R.id.editTextPrecio);
        editaNombre = (EditText) findViewById(R.id.editTextNombrePersona);
        editaMatricula = (EditText) findViewById(R.id.editTextMatricula);
        editaFecha = (EditText) findViewById(R.id.editTextFechas);
        editaComentario = (EditText) findViewById(R.id.editTextComentario);

        db = new ManejadorBD(this);
        db.abrirBaseDeDatos();

        //Adaptador para guardar el valor que se despliega en nuestro fidget spinner
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datosInfraccion);
        listaInfraccion.setAdapter(adaptador);

        listaInfraccion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                switch (pos) {
                    case 0:
                        guardaInfraccion = "";
                        break;
                    case 1:
                        guardaInfraccion = datosInfraccion[pos];
                        break;
                    case 2:
                        guardaInfraccion = datosInfraccion[pos];
                        break;
                    case 3:
                        guardaInfraccion = datosInfraccion[pos];
                        break;
                    case 4:
                        guardaInfraccion = datosInfraccion[pos];
                        break;
                    case 5:
                        guardaInfraccion = datosInfraccion[pos];
                        break;
                    case 6:
                        guardaInfraccion = datosInfraccion[pos];
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    public void agregarMulta(View view) {
        guardaInfraccionTotal = guardaInfraccion;
        guardaPrecio = editaPrecio.getText().toString();
        guardaNombre = editaNombre.getText().toString();
        guardaMatricula = editaMatricula.getText().toString();
        guardaFecha = editaFecha.getText().toString();
        guardaComentario = editaComentario.getText().toString();

        if (guardaInfraccionTotal == null || guardaPrecio == null || guardaNombre == null || guardaMatricula == null || guardaFecha == null || guardaComentario == null) {
            Toast.makeText(this, "Favor de llenar todos los campos de información", Toast.LENGTH_LONG).show();
        } else {
            if (editaComentario == null) {
                guardaComentario = "Sin comentarios";
            }
            db.insertarValores(guardaInfraccionTotal, guardaPrecio, guardaNombre, guardaMatricula, guardaFecha, guardaComentario);

            Toast.makeText(this, "Su base de multas ha sido actualizada", Toast.LENGTH_LONG).show();

            editaNombre.setText("");
            editaPrecio.setText("");
            editaMatricula.setText("");
            editaFecha.setText("");
            editaComentario.setText("");
        }
    }
}
