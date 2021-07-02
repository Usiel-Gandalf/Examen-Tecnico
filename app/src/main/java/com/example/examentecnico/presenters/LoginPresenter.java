package com.example.examentecnico.presenters;

import android.content.Context;

import com.example.examentecnico.contracts.LoginContract;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPresenter implements LoginContract.Presenter {

    @Override
    public void showMessage(String message) {

    }

    @Override
    public boolean validatePasswordLenght(String password) {
        return false;
    }

    @Override
    public boolean validateAplhaPassword(String password) {
        return false;
    }

    @Override
    public boolean validateTypeUserEmail(String email) {
        return false;
    }

    @Override
    public boolean isUserEmpty(String email) {
        return false;
    }

    @Override
    public boolean isPasswordEmpty(String password) {
        return false;
    }

    @Override
    public void sesionSuccess() {

    }

    @Override
    public void sesion(String usuario, String password, FirebaseAuth auth, Context context) {

    }

    @Override
    public void createUser(String usuario, String password, FirebaseAuth auth, Context context) {

    }
}
