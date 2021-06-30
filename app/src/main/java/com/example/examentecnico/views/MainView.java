package com.example.examentecnico.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.examentecnico.R;
import com.example.examentecnico.contracts.MainContract;

public class MainView extends AppCompatActivity implements MainContract.View {
MainContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);
    }
}