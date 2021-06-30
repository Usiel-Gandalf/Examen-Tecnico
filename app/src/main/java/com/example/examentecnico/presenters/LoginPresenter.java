package com.example.examentecnico.presenters;

import android.content.Context;

import com.example.examentecnico.contracts.LoginContract;
import com.example.examentecnico.models.LoginModel;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginPresenter implements LoginContract.Presenter {
    LoginContract.View view;
    LoginContract.Model model;

    public LoginPresenter(LoginContract.View view){
        this.view = view;
        model = new LoginModel();
    }

    @Override
    public void showMessage(String message) {
        view.showMessage(message);
    }

    @Override
    public boolean validatePasswordLenght(String password) {
        Boolean validation = false;
        if (password.length() >= 7){
            validation = true;
        }
        return validation;
    }

    @Override
    public boolean validateAplhaPassword(String password) {
        Boolean validation = false;
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]*$");
        Matcher matcher = pattern.matcher(password);
        if (matcher.matches()){
            validation = true;
        }
        return validation;
    }

    @Override
    public boolean validateTypeUserEmail(String email) {
        Boolean validation = false;
        if (email.contains("@") || email.contains(".com")) {
            validation = true;
        }
        return validation;
    }

    @Override
    public boolean isUserEmpty(String email) {
        boolean validation = false;
        if (email.isEmpty()){
            validation = true;
        }
        return validation;
    }

    @Override
    public boolean isPasswordEmpty(String password) {
        boolean validation = false;
        if (password.isEmpty()){
            validation = true;
        }
        return validation;
    }

    @Override
    public void sesionSuccess() {
        view.sessionSuccess();
    }

    @Override
    public void sesion(String usuario, String password, FirebaseAuth auth, Context context) {
        view.showProgressBar();

        if (validateTypeUserEmail(usuario)){
            if (validatePasswordLenght(password) || validateAplhaPassword(password)){
                if (isUserEmpty(usuario)){
                    view.hideProgressBar();
                    view.showErrorUser("El campo usuario no puede estar vacio");
                }else{
                    if (isPasswordEmpty(password)){
                        view.hideProgressBar();
                        view.showErrorPassword("El campo password no puede estar vacio");
                    }else{
                        view.hideProgressBar();
                        model.sesionFBase(usuario, password, this, auth, context);
                    }
                }
            }else{
                view.hideProgressBar();
                view.showErrorPassword("Longitud minima de 8, unicamente alfanumericos");
            }
        }else {
            view.hideProgressBar();
            view.showErrorUser("El usuario debe de ser de tipo Mail");
        }
    }

    @Override
    public void createUser(String usuario, String password, FirebaseAuth auth, Context context) {
        view.showProgressBar();

        if (validateTypeUserEmail(usuario)){
            if (validatePasswordLenght(password) || validateAplhaPassword(password)){
                if (isUserEmpty(usuario)){
                    view.hideProgressBar();
                    view.showErrorUser("El campo usuario no puede estar vacio");
                }else{
                    if (isPasswordEmpty(password)){
                        view.hideProgressBar();
                        view.showErrorPassword("El campo password no puede estar vacio");
                    }else{
                        view.hideProgressBar();
                        model.createFBaseUser(usuario, password, this, auth, context);
                    }
                }
            }else{
                view.hideProgressBar();
                view.showErrorPassword("Longitud minima de 8, unicamente alfanumericos");
            }
        }else {
            view.hideProgressBar();
            view.showErrorUser("El usuario debe de ser de tipo Mail");
        }
    }
}
