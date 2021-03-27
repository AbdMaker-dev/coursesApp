package com.abd.coursesapp;

import android.util.Log;

import androidx.fragment.app.Fragment;

import com.abd.coursesapp.framents.ListeFrament;

public class ListPage extends MainActivity{

    @Override
    protected Fragment creatFragmaent() {

        return new ListeFrament();
    }
}
