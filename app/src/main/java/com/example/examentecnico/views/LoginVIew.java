package com.example.examentecnico.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.examentecnico.R;
import com.example.examentecnico.contracts.LoginContract;

public class LoginVIew extends AppCompatActivity implements LoginContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_view);
    }

    @Override
    public void initElements() {

    }

    @Override
    public void showMessage(String Message) {

    }

    @Override
    public void showErrorPassword(String message) {

    }

    @Override
    public void showErrorUser(String message) {

    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void saveSession() {

    }

    @Override
    public void checkSesion() {

    }

    @Override
    public void sessionSuccess() {

    }

    public void login(View view) {
    }

    public void createUser(View view) {
    }
}