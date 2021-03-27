package com.abd.coursesapp;

import androidx.fragment.app.Fragment;

import com.abd.coursesapp.framents.InscriptionFragment;

public class InscriptionPage extends MainActivity {
    @Override
    protected Fragment creatFragmaent() {
        return new InscriptionFragment();
    }
}
