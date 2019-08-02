package com.example.eduar.appmultas;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.Toast;
import android.net.Uri;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class emergActivity extends Activity {

    //Anexamos valores al spinner de numeros publicos
    Spinner listaNumPublicos;
    String[] datosPublicos = {"Locatel", "Cruz Roja", "Emergencias", "Angeles Verdes", "Policia Federal Preventiva", "Bomberos",
            "Escuadrón de Rescate", "Policia Federal de Caminos"};

    String numeroPublicoLlamar;
    String guardaNumPublico;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emerg);

        listaNumPublicos = (Spinner) findViewById(R.id.spinner_num_publicos);

        //Para desplegar y almacenar los valores de listas
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datosPublicos);
        listaNumPublicos.setAdapter(adaptador);

        listaNumPublicos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                switch (pos) {
                    case 0:
                        //Locatel
                        guardaNumPublico = datosPublicos[pos];
                        numeroPublicoLlamar = "063";
                        break;
                    case 1:
                        //Cruz Roja
                        guardaNumPublico = datosPublicos[pos];
                        numeroPublicoLlamar = "065";
                        break;
                    case 2:
                        //Emergencias
                        guardaNumPublico = datosPublicos[pos];
                        numeroPublicoLlamar = "066";
                        break;
                    case 3:
                        //Angeles Verdes
                        guardaNumPublico = datosPublicos[pos];
                        numeroPublicoLlamar = "078";
                        break;
                    case 4:
                        //Policia Federal Preventiva
                        guardaNumPublico = datosPublicos[pos];
                        numeroPublicoLlamar = "088";
                        break;
                    case 5:
                        //Bomberos
                        guardaNumPublico = datosPublicos[pos];
                        numeroPublicoLlamar = "068";
                        break;
                    case 6:
                        //Escuadron de Rescate
                        guardaNumPublico = datosPublicos[pos];
                        numeroPublicoLlamar = "080";
                        break;
                    case 7:
                        //Policia Federal de Caminos
                        guardaNumPublico = datosPublicos[pos];
                        numeroPublicoLlamar = "062";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Button llamar = findViewById(R.id.buttonLlamar);
        llamar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                String tel = numeroPublicoLlamar;

                func_llamar(tel);
            }

        });
    }

    public void func_llamar(String tel) {
        try {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + tel)));
        } catch(Exception e){
            e.printStackTrace();
            Toast.makeText(getBaseContext(), "No se ha podido realizar la llamada", Toast.LENGTH_SHORT).show();
        }
    }
}
