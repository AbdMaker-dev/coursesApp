package com.abd.coursesapp.framents;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.abd.coursesapp.ListProduitPage;
import com.abd.coursesapp.Model.Dao.Dao;
import com.abd.coursesapp.Model.Liste;
import com.abd.coursesapp.R;

import java.util.Calendar;

public class ListeFrament extends Fragment {

    ListView listuser;
    ImageButton addListe;
    ProgressDialog progressDialog;
    EditText libelle, prix;
    Button btnajouter;
    ListAdopter listAdopter;
    int idUser;
    Dao dao;

    public ListeFrament() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);

        idUser = getActivity().getIntent().getIntExtra("idUser", 0);
        if (savedInstanceState != null){
            idUser = savedInstanceState.getInt("idUser");
        }
        addListe = view.findViewById(R.id.addListe);
        dao = new Dao(getContext());

        addListe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(getActivity());
                progressDialog.show();
                progressDialog.setContentView(R.layout.add_list_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(
                        android.R.color.transparent
                );
                addListeTobd(idUser);
            }
        });
        listuser = view.findViewById(R.id.listuser);
        listAdopter = new ListAdopter(getActivity(), dao.getAllListUser(idUser));
        listuser.setAdapter(listAdopter);

        listuser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ListProduitPage.class);
                intent.putExtra("listId", ((Liste)listAdopter.getItem(position)).getId());
                startActivity(intent);
            }
        });
        return view;
    }

    public  void  addListeTobd(int id_user){
        libelle = progressDialog.findViewById(R.id.libelleList);
        prix = progressDialog.findViewById(R.id.prix);
        btnajouter = progressDialog.findViewById(R.id.btnajouter);
        btnajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!libelle.getText().toString().equals("") && !prix.getText().toString().equals("")){
                    String lb = libelle.getText().toString();
                    double prx = Double.parseDouble(prix.getText().toString());
                    Dao dao = new Dao(getContext());
                    Liste liste = new Liste(lb, Calendar.getInstance().getTime(), prx, Calendar.getInstance().getTime(),id_user);
                    long result = dao.crateList(liste);
                    if (result>0){
                        liste.setId(Integer.parseInt(result+""));
                        libelle.setText("");
                        prix.setText("");
                        listAdopter.changerList(dao.getAllListUser(idUser));
                        Toast.makeText(getContext(), "Inserer avec succes!!!", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getContext(), "Insertion no reussit", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getContext(), "Tous les champs sont aubligatoire", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("idUser", idUser);
    }
}

