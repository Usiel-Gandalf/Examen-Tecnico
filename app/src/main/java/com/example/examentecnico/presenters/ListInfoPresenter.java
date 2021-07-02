package com.example.examentecnico.presenters;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import androidx.core.content.ContextCompat;

import com.example.examentecnico.contracts.ListInfoContract;
import com.example.examentecnico.db.DbHelper;
import com.example.examentecnico.helpers.UserInfo;
import com.example.examentecnico.models.ListInfoModel;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ListInfoPresenter implements ListInfoContract.Presenter{
    ListInfoContract.View view;
    ListInfoContract.Model model;

    public ListInfoPresenter(ListInfoContract.View view){
        this.view = view;
        model = new ListInfoModel();
    }

    @Override
    public void setElementsListView(List<UserInfo> listinfo) {
        view.initElementsListView(listinfo);
    }

    @Override
    public void getInfoFB(FirebaseFirestore db, Context context) {
        List<UserInfo> listinfo = new ArrayList<>();;
        model.getInfoFB(db, context, this, listinfo);
    }

    @Override
    public void getInfoFBForValue(String value, Integer option, FirebaseFirestore db, Context context) {
        String optionDb = "";
        List<UserInfo> listinfo = new ArrayList<>();

        if (value.isEmpty()){
            view.showMessage("El valor a buscar no debe de estar vacio");
        }else{
            if (option == 0){
                optionDb = "usuario";
            }else if(option == 1){
                optionDb = "pais";
            }else if(option == 2){
                optionDb = "estado";
            }else if(option == 3){
                optionDb = "genero";
            }

            model.getInfoFBForValue(value, optionDb, db, context, this, listinfo);
        }
    }

    @Override
    public void getInfoFBForValueOrdened(Integer option, FirebaseFirestore db, Context context) {
        String optionDb = "";
        List<UserInfo> listinfo = new ArrayList<>();

        if (option == 0){
            optionDb = "usuario";
        }else if(option == 1){
            optionDb = "pais";
        }else if(option == 2){
            optionDb = "estado";
        }else if(option == 3){
            optionDb = "genero";
        }

        model.getInfoFBForValueOrdened(optionDb, db, context, this, listinfo);
    }

    @Override
    public void displayAlertOnView(Integer option) {
        if(option == 0){
            view.displaySearchFilterList();
        }else if(option == 1){
            view.displayOrderList();
        }else if(option == 2){
            view.getInfoDb();
        }
    }

    @Override
    public void showMessage(String message) {
        view.showMessage(message);
    }
}
