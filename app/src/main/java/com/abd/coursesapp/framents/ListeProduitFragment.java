package com.abd.coursesapp.framents;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.abd.coursesapp.Model.Dao.Dao;
import com.abd.coursesapp.Model.Produit;
import com.abd.coursesapp.PageAjoutProduit;
import com.abd.coursesapp.R;

import java.util.List;

public class ListeProduitFragment extends Fragment {

    ListView listProduit;
    ImageButton addProduit;
    ProgressDialog progressDialog;
    int listId;
    List<Produit> produitList;
    AdopterListProduit listAdopter;
    Dao dao;

    public ListeProduitFragment() {
    }



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.list_produit_fragment, container, false);
        addProduit = view.findViewById(R.id.addProduit);
        listId = getActivity().getIntent().getIntExtra("listId", 0);

        if (savedInstanceState != null){
            listId = savedInstanceState.getInt("listId");
        }

        addProduit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PageAjoutProduit.class);
                intent.putExtra("listId", listId);
                startActivity(intent);
            }
        });
        dao = new Dao(getContext());
        listProduit = view.findViewById(R.id.listProduit);
        produitList = dao.getAllProduittlist(listId);
        listAdopter = new AdopterListProduit(getContext(), produitList);
        listProduit.setAdapter(listAdopter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        listAdopter.chageData(dao.getAllProduittlist(listId));
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("listId", listId);
    }
}
