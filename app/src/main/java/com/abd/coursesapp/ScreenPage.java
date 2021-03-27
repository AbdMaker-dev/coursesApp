package com.abd.coursesapp;

import android.util.Log;

import androidx.fragment.app.Fragment;

import com.abd.coursesapp.MainActivity;
import com.abd.coursesapp.framents.ScreenFragment;

public class ScreenPage extends MainActivity {

    @Override
    protected Fragment creatFragmaent() {
        Fragment fr = new ScreenFragment();
        return fr;
    }
}
