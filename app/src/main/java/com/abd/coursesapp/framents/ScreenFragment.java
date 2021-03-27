package com.abd.coursesapp.framents;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.abd.coursesapp.LoginPage;
import com.abd.coursesapp.Model.Dao.Dao;
import com.abd.coursesapp.Model.Liste;
import com.abd.coursesapp.Model.Modele;
import com.abd.coursesapp.Model.Produit;
import com.abd.coursesapp.Model.User;
import com.abd.coursesapp.R;
import com.abd.coursesapp.ScreenPage;

import java.util.LinkedList;
import java.util.List;

public class ScreenFragment extends Fragment{

    int progresStep;
    private Handler handler = new Handler();

    public ScreenFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       // return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.screen_fragment, container, false);
        Dao dao = new Dao(getContext());

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progresStep < 100) {
                    progresStep += 1;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                        }
                    });
                    try {
                        Thread.sleep(40);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // reditec
                Intent intent = new Intent(getActivity(), LoginPage.class);
                startActivity(intent);
                getActivity().finish();
            }
        }).start();
        return view;
    }
}
