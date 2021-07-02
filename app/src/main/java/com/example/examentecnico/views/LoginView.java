package com.example.examentecnico.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.examentecnico.R;
import com.example.examentecnico.contracts.LoginContract;
import com.example.examentecnico.presenters.LoginPresenter;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginView extends AppCompatActivity implements LoginContract.View {
    LoginContract.Presenter presenter;
    ConstraintLayout constraintLayoutLogin;
    EditText edtxtUsuario, edtxtPassword;
    Button btnLogin;
    ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_view);
        initElements();
    }

    @Override
    public void onStart(){
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null){
            sessionSuccess();
        }
    }

    @Override
    public void initElements() {
        constraintLayoutLogin = findViewById(R.id.LayoutLogin);
        presenter = new LoginPresenter(this);
        edtxtUsuario = findViewById(R.id.edtxtUser);
        edtxtPassword = findViewById(R.id.edtxtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        progressBar = findViewById(R.id.progressBarLogin);
        progressBar.bringToFront();
        hideProgressBar();
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void showMessage(String Message) {
        Snackbar.make(constraintLayoutLogin, Message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showErrorPassword(String message) {
        edtxtPassword.setError(message);
    }

    @Override
    public void showErrorUser(String message) {
        edtxtUsuario.setError(message);
    }

    @Override
    public void showProgressBar() {
        this.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        this.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void sessionSuccess() {
        startActivity(new Intent(this, MainView.class));
    }

    public void login(View view) {
        String usuario = edtxtUsuario.getText().toString();
        String password = edtxtPassword.getText().toString();
        presenter.sesion(usuario, password, mAuth, this);
    }
}