package com.example.eduar.appmultas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ManejoDeListReglamento extends BaseAdapter {

    Context context;
    String [] titulos;
    int[] imagenes;
    LayoutInflater inflater;

    public ManejoDeListReglamento(Context c, String[] t, int[] i) {
        context = c;
        titulos = t;
        imagenes = i;
    }

    @Override
    public int getCount() {
        return titulos.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        TextView txtTitle;
        ImageView imgImg;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.formatofilareglamento, parent, false);
       txtTitle = (TextView) itemView.findViewById(R.id.listRowTitle);
        imgImg = (ImageView) itemView.findViewById(R.id.listRowImage);
        txtTitle.setText(titulos[position]);
       imgImg.setImageResource(imagenes[position]);

        return itemView;
    }
}