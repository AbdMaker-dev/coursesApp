package com.abd.coursesapp.framents;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.abd.coursesapp.Model.Dao.Dao;
import com.abd.coursesapp.Model.Liste;
import com.abd.coursesapp.R;

import java.text.DateFormat;
import java.util.List;

public class ListAdopter extends BaseAdapter {

    Context context;
    List<Liste> listeList;

    public  ListAdopter(Context context, List<Liste> listeList){
        this.context = context;
        this.listeList = listeList;
    }

    public  void changerList(List<Liste> listeList){
        this.listeList = listeList;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return listeList.size();
    }

    @Override
    public Object getItem(int position) {
        return listeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(this.context).inflate(R.layout.list_item, parent, false);

        TextView libelleList, createdAt, editedAt,prixList, supList;

        libelleList = convertView.findViewById(R.id.libelleListt);
        createdAt = convertView.findViewById(R.id.createdAt);
        editedAt = convertView.findViewById(R.id.editedAt);
        prixList = convertView.findViewById(R.id.prixList);
        supList = convertView.findViewById(R.id.supList);

        supList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dao dao = new Dao(context);
                dao.deleteSomeData("Liste", listeList.get(position).getId());
                listeList.remove(position);
                notifyDataSetChanged();
            }
        });

        libelleList.setText(listeList.get(position).getLibelle());
        createdAt.setText(DateFormat.getDateInstance(DateFormat.SHORT).format(listeList.get(position).getCreatedAt()));
        editedAt.setText(DateFormat.getDateInstance(DateFormat.SHORT).format(listeList.get(position).getEditedAt()));
        prixList.setText(listeList.get(position).getPrix().toString());
        // convertView =  activity.getLayoutInflater().inflate(R.layout.list_item, parent, false);
        return  convertView;
    }
}
