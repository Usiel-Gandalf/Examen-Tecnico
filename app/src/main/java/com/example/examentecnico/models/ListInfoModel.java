package com.example.examentecnico.models;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.examentecnico.contracts.ListInfoContract;
import com.example.examentecnico.helpers.UserCrud;
import com.example.examentecnico.helpers.UserInfo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class ListInfoModel implements ListInfoContract.Model  {
    @Override
    public void getInfoFB(FirebaseFirestore db, Context context, ListInfoContract.Presenter listener, List<UserInfo> listinfo) {
        db.collection("users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    UserCrud userCrud = new UserCrud(context);
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String user = document.getString("usuario");
                        String country = document.getString("pais");
                        String ciudad = document.getString("estado");
                        String gender = document.getString("genero");
                        listinfo.add(new UserInfo(user, country, ciudad, gender));
                        userCrud.insertUser(user, country, ciudad, gender);
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
    public void getInfoFBForValue(String value, String option, FirebaseFirestore db, Context context, ListInfoContract.Presenter listener, List<UserInfo> listinfo) {
        db.collection("users").whereEqualTo(option, value).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String user = document.getString("usuario");
                        String country = document.getString("pais");
                        String ciudad = document.getString("estado");
                        String gender = document.getString("genero");
                        listinfo.add(new UserInfo(user, country, ciudad, gender));
                    }

                    if (listinfo.isEmpty()){
                        listener.showMessage("No se encontraron resultados de la busqueda");
                    }else{
                        listener.setElementsListView(listinfo);
                    }
                }
                else {
                    listener.showMessage("Ocurrio un error");
                }
            }
        });
    }

    @Override
    public void getInfoFBForValueOrdened(String option, FirebaseFirestore db, Context context, ListInfoContract.Presenter listener, List<UserInfo> listinfo) {
        db.collection("users").orderBy(option, Query.Direction.ASCENDING).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String user = document.getString("usuario");
                        String country = document.getString("pais");
                        String ciudad = document.getString("estado");
                        String gender = document.getString("genero");
                        listinfo.add(new UserInfo(user, country, ciudad, gender));
                    }

                    if (listinfo.isEmpty()){
                        listener.showMessage("No se encontraron resultados de la busqueda");
                    }else{
                        listener.setElementsListView(listinfo);
                    }
                }
                else {
                    listener.showMessage("Ocurrio un error");
                }
            }
        });
    }

}
