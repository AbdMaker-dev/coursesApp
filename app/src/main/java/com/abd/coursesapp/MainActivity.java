package com.abd.coursesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import com.abd.coursesapp.Model.User;

public abstract class MainActivity extends AppCompatActivity {

    protected abstract Fragment creatFragmaent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.sideForFragments);

        if (fragment == null)
        {
            fragment = creatFragmaent();
            fm.beginTransaction().add(R.id.sideForFragments, fragment).commit();
        }
    }
}