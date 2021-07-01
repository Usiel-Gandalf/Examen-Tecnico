package com.example.examentecnico.contracts;

import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;

public interface LoginContract {

    interface View{

        void initElements();

        void showMessage(String Message);

        void showErrorPassword(String message);

        void showErrorUser(String message);

        void showProgressBar();

        void hideProgressBar();

        void saveSession();

        void checkSesion();

        void sessionSuccess();
    }

    interface Presenter{
        void showMessage(String message);

        boolean validatePasswordLenght(String password);

        boolean validateAplhaPassword(String password);

        boolean validateTypeUserEmail(String email);

        boolean isUserEmpty(String email);

        boolean isPasswordEmpty(String password);

        void sesionSuccess();

        void sesion(String usuario, String password, FirebaseAuth auth, Context context);
    }

    interface Model{

        void sesionFBase(String usuario, String password, LoginContract.Presenter listener, FirebaseAuth auth, Context context);
    }
}
