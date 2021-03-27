package com.abd.coursesapp.framents;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.abd.coursesapp.Model.Dao.Dao;
import com.abd.coursesapp.Model.Liste;
import com.abd.coursesapp.Model.Produit;
import com.abd.coursesapp.R;

import java.util.List;

public class AdopterListProduit extends BaseAdapter {

    Context context;
    List<Produit> listeList;

    public  AdopterListProduit(Context context, List<Produit> listeList){
        this.context = context;
        this.listeList = listeList;
    }


    public  void chageData(List<Produit> listeList){
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
        convertView = LayoutInflater.from(this.context).inflate(R.layout.item_list_produit, parent, false);
        // convertView =  activity.getLayoutInflater().inflate(R.layout.list_item, parent, false);

        TextView libelleProd, quantiter, montant,prixList, supProd;
        CheckBox accomplis;
        ImageView imageView;

        libelleProd = convertView.findViewById(R.id.libelleProd);
        quantiter = convertView.findViewById(R.id.quantiter);
        montant = convertView.findViewById(R.id.montant);
        supProd = convertView.findViewById(R.id.supProd);
        accomplis = convertView.findViewById(R.id.accomplis);
        imageView = convertView.findViewById(R.id.imageView);

        libelleProd.setText(listeList.get(position).getLibelle());
        montant.setText("Montant: " + listeList.get(position).getMontant());
        quantiter.setText("Nbr: " + listeList.get(position).getQuantiter());
        accomplis.setChecked(listeList.get(position).isAccomplis());
        imageView.setImageBitmap(toBipmapImage(listeList.get(position).getPhoto()));

        accomplis.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                listeList.get(position).setAccomplis(isChecked);
                Dao dao = new Dao(context);
                dao.updateSomeData("Produit", listeList.get(position));
            }
        });

        supProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dao dao = new Dao(context);
                dao.deleteSomeData("Produit", listeList.get(position).getId());
                listeList.remove(position);
                notifyDataSetChanged();
            }
        });

        return  convertView;
    }


    public static Bitmap toBipmapImage(String base64Str) throws IllegalArgumentException
    {
        byte[] decodedBytes = Base64.decode(
                base64Str.substring(base64Str.indexOf(",")  + 1),
                Base64.DEFAULT
        );
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }
}
