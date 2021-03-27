package com.abd.coursesapp.framents;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.abd.coursesapp.LoginPage;
import com.abd.coursesapp.Model.Dao.Dao;
import com.abd.coursesapp.Model.User;
import com.abd.coursesapp.R;

public class InscriptionFragment  extends Fragment {

    Button btninscrip;
    EditText nom, prenom, tel, login, password;
    public InscriptionFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.inscription_frament, container, false);
        btninscrip = view.findViewById(R.id.btninscrip);
        nom = view.findViewById(R.id.nom);
        prenom = view.findViewById(R.id.prenom);
        tel = view.findViewById(R.id.tel);
        login = view.findViewById(R.id.login);
        password = view.findViewById(R.id.password);
        Dao dao = new Dao(getContext());

        btninscrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nom.getText().toString().equals("") && prenom.getText().toString().equals("") && tel.getText().toString().equals("") && login.getText().toString().equals("") && password.getText().toString().equals("")){
                    Toast.makeText(getContext(), "Tous les champs sont aubligatoire", Toast.LENGTH_SHORT).show();
                    return;
                }
                User user = new User();
                user.setNom(nom.getText().toString());
                user.setPrenom(prenom.getText().toString());
                user.setTel(tel.getText().toString());
                user.setLogin(login.getText().toString());
                user.setPassword(password.getText().toString());
                long result  = dao.inscrire(user);
                if (result>0){
                    Toast.makeText(getContext(), "Inscription reucit!!!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), LoginPage.class);
                    startActivity(intent);
                }
            }
        });
        return view;
    }
}
