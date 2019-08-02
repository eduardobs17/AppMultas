package com.example.eduar.appmultas;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.database.Cursor;

public class recuperaDatos extends AppCompatActivity {

    ListView list;
    long idMulta;
    ManejadorBD db;

    TextView textoId;
    TextView textoMatricula;
    TextView textoName;
    TextView textoCosto;
    TextView textoInfraccion;

    String[] from = new String[] {
            ConexionBD.idMulta,
            ConexionBD.matriculaAuto,
            ConexionBD.nombreInfraccion,
            ConexionBD.nombreConductor,
            ConexionBD.precioMulta,
            ConexionBD.fechaMulta,
            ConexionBD.miComentario };

    int[] to = new int[] {
            R.id.id_menu,
            R.id.recupera_matricula,
            R.id.recupera_infraccion,
            R.id.recupera_nombre,
            R.id.recupera_costo,
            R.id.recupera_fecha,
            R.id.recupera_comentario };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recupera_datos);

        db = new ManejadorBD(this);
        db.abrirBaseDeDatos();

        list = (ListView) findViewById(R.id.listarecpdatos);
        registerForContextMenu(list);
        Cursor cursor = db.leerDatos();

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(recuperaDatos.this, R.layout.formatodatos, cursor, from, to);
        adapter.notifyDataSetChanged();
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                textoId = (TextView) view.findViewById(R.id.id_menu);
                textoName = (TextView) view.findViewById(R.id.recupera_nombre);
                textoMatricula = (TextView) view.findViewById(R.id.recupera_matricula);
                textoCosto = (TextView) view.findViewById(R.id.recupera_costo);
                textoInfraccion = (TextView) view.findViewById(R.id.recupera_infraccion);

                String mensajename = textoName.getText().toString();
                String mensajematricula = textoMatricula.getText().toString();
                String mensajecosto = textoCosto.getText().toString();
                String mensajeinfraccion = textoInfraccion.getText().toString();

                final AlertDialog.Builder alertaconfirmar = new AlertDialog.Builder(recuperaDatos.this);
                alertaconfirmar.setTitle("Verificar");
                alertaconfirmar.setMessage("El conductor: " + mensajename + Html.fromHtml("<br/>") + "con placas: " + mensajematricula + Html.fromHtml("<br/>")
                        + "a cubierto la multa de: $ " + mensajecosto + Html.fromHtml("<br/>")
                        + "por: " + mensajeinfraccion);

                idMulta = Long.parseLong(textoId.getText().toString());

                alertaconfirmar.setPositiveButton("Aceptar", new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        db.borrarDatos(idMulta);
                    }
                });
                Intent borrarmulta = new Intent(recuperaDatos.this, recuperaDatos.class);
                startActivity (borrarmulta);
                alertaconfirmar.setNegativeButton("A un no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alertaconfirmar.create();
                alertaconfirmar.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_recupera_datos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
