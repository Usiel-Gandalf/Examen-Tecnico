package com.example.examentecnico.models;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.examentecnico.contracts.LoginContract;
import com.example.examentecnico.contracts.MainContract;
import com.example.examentecnico.helpers.UserInfo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;

public class MainModel implements MainContract.Model {

    @Override
    public void saveOrUpdateData(String usuario, Map<String, Object> map, FirebaseFirestore db, MainContract.Presenter listener) {
        db.collection("users").document(usuario).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                listener.showMessage("Datos guardados o actualizados correctamente");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                listener.showMessage("Ocurrio un error mientras intentaba guardar o actualizar sus datos");
            }
        });
    }

    @Override
    public void getDataUserInfo(MainContract.Presenter listener, FirebaseFirestore db, Context context, String usuario) {
        db.collection("users").whereEqualTo("usuario", usuario).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    String user = "", country = "", estado = "", gender = "";
                    for (QueryDocumentSnapshot document : task.getResult()) {
                         user =  document.getString("usuario");
                         country = document.getString("pais");
                         estado = document.getString("estado");
                         gender = document.getString("genero");
                    }

                    if (user.isEmpty()){
                        listener.showMessage("No se encontraron resultados de la busqueda");
                    }else{
                        listener.setDataUserOnEditText(country, estado, gender);
                    }
                }
                else {
                    listener.showMessage("Ocurrio un error");
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                listener.showMessage("Ocurrio un error mientras se intentaba buscar los datos");
            }
        });
    }
}
