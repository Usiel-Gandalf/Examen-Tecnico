package com.example.examentecnico.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.example.examentecnico.R;
import com.example.examentecnico.contracts.WelcomeContract;

public class WelcomeView extends AppCompatActivity implements WelcomeContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_view);
        setTitle("Bienvenido");
    }
}