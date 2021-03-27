package com.abd.coursesapp.framents;

import android.app.ProgressDialog;
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

import com.abd.coursesapp.InscriptionPage;
import com.abd.coursesapp.ListPage;
import com.abd.coursesapp.Model.Dao.Dao;
import com.abd.coursesapp.Model.User;
import com.abd.coursesapp.R;

public class LoginFragment  extends Fragment {


    Button btnlogin, btninscrire;
    EditText login, password;
    ProgressDialog progressDialog;
    public LoginFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.login_fragment, container, false);

        btnlogin = view.findViewById(R.id.btnlogin);
        btninscrire = view.findViewById(R.id.btninscrire);

        login = view.findViewById(R.id.login);
        password = view.findViewById(R.id.password);

        Dao dao = new Dao(getContext());

        btninscrire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InscriptionPage.class);
                startActivity(intent);
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(getActivity());
                progressDialog.show();
                progressDialog.setContentView(R.layout.pregress);
                progressDialog.getWindow().setBackgroundDrawableResource(
                        android.R.color.transparent
                );
                String log = login.getText().toString();
                String psw = password.getText().toString();
                if (log.equals("") != true && psw.equals("") != true){
                    User user = (User) dao.connexion(log, psw);
                    if (user != null){
                       Intent intent = new Intent(getActivity(), ListPage.class);
                       intent.putExtra("idUser", user.getId());
                        startActivity(intent);
                        progressDialog.dismiss();
                    }else {
                        Toast.makeText(getContext(), "Veuillez recevoir les informations fournies", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }else {
                    Toast.makeText(getContext(), "Tous les champs sont aubligatoire", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        });

        return view;
    }
}
