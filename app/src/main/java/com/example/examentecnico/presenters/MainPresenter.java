package com.example.examentecnico.presenters;

import android.content.Context;

import com.example.examentecnico.contracts.ListInfoContract;
import com.example.examentecnico.contracts.MainContract;
import com.example.examentecnico.models.ListInfoModel;
import com.example.examentecnico.models.MainModel;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainPresenter implements MainContract.Presenter{
    MainContract.View view;
    MainContract.Model model;

    public MainPresenter(MainContract.View view){
        this.view = view;
        model = new MainModel();
    }

    @Override
    public void saveOrUpdateData(String usuario, String pais, String estado, String genero, FirebaseFirestore db) {
        Map<String, Object> map = new HashMap<>();
        map.put("usuario", usuario);
        map.put("pais", pais);
        map.put("estado", estado);
        map.put("genero", genero);

        model.saveOrUpdateData(usuario, map, db, this);
    }

    @Override
    public void saveFakeData(String usuario, String pais, String estado, String genero, FirebaseFirestore db) {
        Map<String, Object> map = new HashMap<>();
        map.put("usuario", usuario);
        map.put("pais", pais);
        map.put("estado", estado);
        map.put("genero", genero);

        model.saveOrUpdateData(usuario, map, db, this);
    }

    @Override
    public void setDataUserOnEditText(String pais, String estado, String genero) {
        view.setDataUserOnEditText(pais, estado, genero);
    }

    @Override
    public void showMessage(String message) {
        view.showMessage(message);
    }

    @Override
    public void getDataUserInfo(FirebaseFirestore db, Context context, String usuario) {
        model.getDataUserInfo(this, db, context, usuario);
    }
}
