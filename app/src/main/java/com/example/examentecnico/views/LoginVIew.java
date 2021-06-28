package com.example.examentecnico.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.examentecnico.R;
import com.example.examentecnico.contracts.LoginContract;

public class LoginVIew extends AppCompatActivity implements LoginContract {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_view);
    }
}