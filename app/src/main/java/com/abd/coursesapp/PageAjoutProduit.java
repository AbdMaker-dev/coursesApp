package com.abd.coursesapp;

import androidx.fragment.app.Fragment;

import com.abd.coursesapp.framents.AjoutProduitFrament;

public class PageAjoutProduit extends MainActivity {
    @Override
    protected Fragment creatFragmaent() {
        return new AjoutProduitFrament();
    }
}
