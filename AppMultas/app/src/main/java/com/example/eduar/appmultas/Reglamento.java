package com.example.eduar.appmultas;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class Reglamento extends ActionBarActivity{

    ManejoDeListReglamento adapter;

    String[] titulo = new String[] {
            "ARTÍCULO 85.- Los vehículos deben conducirse por el carril derecho de la vía.",
            "ARTÍCULO 86.- El conductor de un vehículo que circule por la vía pública debe mantener una distancia razonable.",
            "ARTÚCULO 87.- Toda modificación en la velocidad y dirección debe ser anticipada.",
            "ARTÍCULO 88.- Todo conductor que reduzca la velocidad debe cersiorarse del ambiente.",
            "ARTÍCULO 89.- El vehículo debe contar con luces.",
            "ARTÍCULO 90.- Al aproximarse a una intersección, el conductor debe indicar con señales hacia dónde se dirige.",
            "ARTÍCULO 91.- Tienen prioridad de paso con respecto a los demás vehículos."};

    int[] imagenes = { R.drawable.iconopolicia,
            R.drawable.iconopolicia,
            R.drawable.iconopolicia,
            R.drawable.iconopolicia,
            R.drawable.iconopolicia,
            R.drawable.iconopolicia,
            R.drawable.iconopolicia };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reglamento);

        final ListView lista = (ListView) findViewById(R.id.listViewReglas);
        adapter = new ManejoDeListReglamento(this, titulo, imagenes);
        lista.setAdapter(adapter);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "Artículo " + (85+i), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }
}