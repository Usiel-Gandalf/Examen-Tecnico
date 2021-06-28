package com.example.examentecnico.models;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.examentecnico.contracts.LoginContract;
import com.example.examentecnico.views.WelcomeView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

import static androidx.core.content.ContextCompat.startActivity;
import static com.example.examentecnico.helpers.strings.urlIndexServiceWeb;

public class LoginModel implements LoginContract.Model {
    private Context context;
    private FirebaseAuth auth;

    @Override
    public void sesionRespons(String usuario, String password, LoginContract.Presenter listener, RequestQueue queue) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlIndexServiceWeb,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        listener.showMessage(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               listener.showMessage("error de request" + error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("user", usuario);
                params.put("password", password);
                params.put("banderaSesion", "true");
                return params;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

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
                });


    }
}
