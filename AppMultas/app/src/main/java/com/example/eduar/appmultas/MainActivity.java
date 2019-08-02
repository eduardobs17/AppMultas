package com.example.eduar.appmultas;

import android.content.Intent;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Declarar para hacer un array que se guardará en listView
    String[] menuLista = {"Reglamento de tránsito", "Números de emergencia", "Nueva multa",
            "Multas registradas", "Desarrolladores", "Salir"};

    //Galeria de imagenes
    int[] imagenSlider = {R.drawable.multa2, R.drawable.logo2, R.drawable.policia2};

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager aViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Lista
        ListView lista = (ListView) findViewById(R.id.listViewMenu);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, menuLista);
        lista.setAdapter(adapter);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {
                //Toast.makeText(getApplicationContext(), "posicion"+posicion, Toast.LENGTH_SHORT).show();
                switch (posicion) {
                    case 0:
                        Intent i1 = new Intent(getApplicationContext(), Reglamento.class);
                        startActivity(i1);
                        break;

                    case 1:
                        Intent i2 = new Intent(getApplicationContext(), emergActivity.class);
                        startActivity(i2);
                        break;

                    case 2:
                        Intent i3 = new Intent(getApplicationContext(), hacerMulta.class);
                        startActivity(i3);
                        break;

                    case 3:
                        Intent i4 = new Intent(getApplicationContext(), recuperaDatos.class);
                        startActivity(i4);
                        break;

                    case 4:
                        Intent i5 = new Intent(getApplicationContext(), integrantes.class);
                        startActivity(i5);
                        break;

                    case 5:
                        finish();
                        break;
                }
            }
        });

        //Galeria de imagenes
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        lista.setAdapter(adapter);

        aViewPager = (ViewPager) findViewById(R.id.pager);

        //agrega frags
        mSectionsPagerAdapter.agregarFragmentos(PlaceholderFragment.newInstance(imagenSlider[0]));
        mSectionsPagerAdapter.agregarFragmentos(PlaceholderFragment.newInstance(imagenSlider[1]));
        mSectionsPagerAdapter.agregarFragmentos(PlaceholderFragment.newInstance(imagenSlider[2]));

        aViewPager.setAdapter(mSectionsPagerAdapter);
    }

    //Manejador de galería
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        List<Fragment> fragmentos;

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
            fragmentos = new ArrayList<>();
        }

        public void agregarFragmentos(Fragment pFragmentos) {
            fragmentos.add(pFragmentos);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentos.get(position);
        }

        @Override
        public int getCount() {
            return fragmentos.size();
        }
    }

    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_IMAGE = "imagen";
        private int imagen;

        public PlaceholderFragment() {}

        public static PlaceholderFragment newInstance(int imagen) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_IMAGE, imagen);
            fragment.setArguments(args);
            fragment.setRetainInstance(true);
            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (getArguments() != null) {
                imagen = getArguments().getInt(ARG_IMAGE);
            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            ImageView imagenView = (ImageView) rootView.findViewById(R.id.imageViewCabezera);
            imagenView.setImageResource(imagen);
            return rootView;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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