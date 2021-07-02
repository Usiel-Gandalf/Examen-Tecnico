package com.example.examentecnico.models;

import android.content.Context;

import com.example.examentecnico.contracts.LoginContract;
import com.google.firebase.auth.FirebaseAuth;

public class LoginModel implements LoginContract.Model {

    @Override
    public void sesionFBase(String usuario, String password, LoginContract.Presenter listener, FirebaseAuth auth, Context context) {

    }
}
