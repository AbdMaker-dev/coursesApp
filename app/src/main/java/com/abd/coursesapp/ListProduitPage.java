package com.abd.coursesapp;

import androidx.fragment.app.Fragment;

import com.abd.coursesapp.framents.ListeProduitFragment;

public class ListProduitPage extends MainActivity {
    @Override
    protected Fragment creatFragmaent() {
        return new ListeProduitFragment();
    }
}
