package com.example.examentecnico.models;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.examentecnico.contracts.ListInfoContract;
import com.example.examentecnico.contracts.LoginContract;
import com.example.examentecnico.helpers.userinfo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.List;

public class ListInfoModel implements ListInfoContract.Model {

    @Override
    public void getInfoFB(FirebaseFirestore db, Context context, ListInfoContract.Presenter listener, List<userinfo> listinfo) {
        db.collection("users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String user = document.getString("usuario");
                        String country = document.getString("pais");
                        String ciudad = document.getString("estado");
                        String gender = document.getString("genero");
                        listinfo.add(new userinfo(user, country, ciudad, gender));

                        //listener.showMessage(document.getString("usuario"));
                    }
                    listener.setElementsListView(listinfo);

                }
                else {
                       listener.showMessage("Ocurrio un error");
                }
            }
        });
    }

    @Override
    public void getInfoFBForUser(String user, FirebaseFirestore db, Context context, ListInfoContract.Presenter listener) {

    }

    @Override
    public void getInfoFBForCountry(String country, FirebaseFirestore db, Context context, ListInfoContract.Presenter listener) {

    }

    @Override
    public void getInfoFBForState(String state, FirebaseFirestore db, Context context, ListInfoContract.Presenter listener) {

    }

    @Override
    public void getInfoFBForGender(String gender, FirebaseFirestore db, Context context, ListInfoContract.Presenter listener) {

    }
}