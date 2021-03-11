package com.example.uas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Customadapter extends BaseAdapter {

    Context Ccontext;
    ArrayList<KHS> khsArrayList;

    public Customadapter(Context context, ArrayList<KHS> khsArrayList){
        Ccontext=context;
        this.khsArrayList=khsArrayList;
    }

    @Override
    public int getCount() {
        return khsArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return khsArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            convertView=LayoutInflater.from(Ccontext).inflate(R.layout.listitem, parent,false);
        }
        KHS tempKHS=(KHS) getItem(position);
        TextView TVNo=(TextView)convertView.findViewById(R.id.idTVLNo);
        TextView TVKode=(TextView)convertView.findViewById(R.id.idTVLKode);
        TextView TVNama=(TextView)convertView.findViewById(R.id.idTVLNamaMatkul);
        TextView TVSks=(TextView)convertView.findViewById(R.id.idTVLSKS);
        TextView TVNangka=(TextView)convertView.findViewById(R.id.idTVLNAngka);
        TextView TVNhuruf=(TextView)convertView.findViewById(R.id.idTVLNHuruf);

        TVNo.setText(tempKHS.getNo().toString());
        TVKode.setText(tempKHS.getKodemk());
        TVNama.setText(tempKHS.getMatakuliah());
        TVSks.setText(tempKHS.getSks());
        //TVNangka.setText(tempKHS.getNilai_angka());
        TVNhuruf.setText(tempKHS.getNilai_huruf());

        return convertView;
    }
}
