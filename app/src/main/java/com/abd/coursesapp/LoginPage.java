package com.abd.coursesapp;

import androidx.fragment.app.Fragment;

import com.abd.coursesapp.framents.LoginFragment;

public class LoginPage extends MainActivity {

    @Override
    protected Fragment creatFragmaent() {
        return new LoginFragment();
    }
}
