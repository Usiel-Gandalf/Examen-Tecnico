package com.example.examentecnico.models;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.examentecnico.contracts.LoginContract;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class LoginModel implements LoginContract.Model {

    @Override
    public void sesionFBase(String usuario, String password, LoginContract.Presenter listener, FirebaseAuth auth, Context context) {
        auth.signInWithEmailAndPassword(usuario, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //listener.showMessage("Sesion Exitosa");
                            listener.sesionSuccess();
                        } else {
                            String errorCode = ((FirebaseAuthException) task.getException()).getErrorCode();
                            switch (errorCode){
                                case "ERROR_USER_NOT_FOUND":
                                    listener.showMessage("Usuario inexistente");
                                    break;

                                case "ERROR_WRONG_PASSWORD":
                                    listener.showMessage("Contraseña invalida");
                                    break;
                            }
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                listener.showMessage("Algo salio mal, intente nuevamente");
            }
        });


    }

    @Override
    public void createFBaseUser(String usuario, String password, LoginContract.Presenter listener, FirebaseAuth auth, Context context) {
        auth.createUserWithEmailAndPassword(usuario, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            listener.showMessage("Registro de usuario Exitoso");
                            listener.sesionSuccess();
                        } else {
                            String errorCode = ((FirebaseAuthException) task.getException()).getErrorCode();
                            switch (errorCode){
                                case "ERROR_EMAIL_ALREADY_IN_USE":
                                    listener.showMessage("Usuario registrado, inicie sesion");
                                    break;

                                case "ERROR_INVALID_EMAIL":
                                    listener.showMessage("Tipo de Email incorrecto");
                                    break;
                            }
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                listener.showMessage("Algo salio mal, intente nuevamente");
            }
        });
    }
}

