package com.example.examentecnico.views;

import androidx.annotation.NonNull;
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
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.examentecnico.R;
import com.example.examentecnico.contracts.LoginContract;
import com.example.examentecnico.presenters.LoginPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static androidx.core.content.ContextCompat.startActivity;

public class LoginVIew extends AppCompatActivity implements LoginContract.View {
    LoginContract.Presenter presenter;
    ConstraintLayout constraintLayoutLogin;
    EditText edtxtUsuario, edtxtPassword;
    Button btnLogin;
    ProgressBar progressBar;
    public SharedPreferences preferences;
    public SharedPreferences.Editor editor;
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
        preferences = this.getSharedPreferences("sesion", Context.MODE_PRIVATE);
        editor = preferences.edit();
        mAuth = FirebaseAuth.getInstance();

    }

    public void login(View view) {
        String usuario = edtxtUsuario.getText().toString();
        String password = edtxtPassword.getText().toString();
        //RequestQueue queue = Volley.newRequestQueue(this);
        presenter.sesion(usuario, password, mAuth, this);
    }

    public void createUser(View view) {
        String usuario = edtxtUsuario.getText().toString();
        String password = edtxtPassword.getText().toString();
        presenter.createUser(usuario, password, mAuth, this);
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
    public void saveSession() {
        editor.putBoolean("sesion", true);
        editor.apply();
//        startActivity(new Intent(this, CalendarMainView.class));
    }

    @Override
    public void checkSesion() {
        if (this.preferences.getBoolean("sesion", false) == true){
            //esta activa la sesion
        }
    }

    @Override
    public void sessionSuccess() {
        startActivity(new Intent(this, WelcomeView.class));
    }


}