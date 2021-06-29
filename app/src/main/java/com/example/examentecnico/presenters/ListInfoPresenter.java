package com.example.examentecnico.presenters;

import android.content.Context;

import com.example.examentecnico.contracts.ListInfoContract;
import com.example.examentecnico.contracts.WelcomeContract;
import com.example.examentecnico.helpers.userinfo;
import com.example.examentecnico.models.ListInfoModel;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class ListInfoPresenter implements ListInfoContract.Presenter {
    ListInfoContract.View view;
    ListInfoContract.Model model;

    public ListInfoPresenter(ListInfoContract.View view){
        this.view = view;
        model = new ListInfoModel();
    }

    @Override
    public void setElementsListView(List<userinfo> listinfo) {
        view.initElementsListView(listinfo);
    }

    @Override
    public void getInfoFB(FirebaseFirestore db, Context context, List<userinfo> listinfo) {
        model.getInfoFB(db, context, this, listinfo);
    }

    @Override
    public void getInfoFBForUser(String user, FirebaseFirestore db, Context context) {

    }

    @Override
    public void getInfoFBForCountry(String country, FirebaseFirestore db, Context context) {

    }

    @Override
    public void getInfoFBForState(String state, FirebaseFirestore db, Context context) {

    }

    @Override
    public void getInfoFBForGender(String gender, FirebaseFirestore db, Context context) {

    }

    @Override
    public void showMessage(String message) {
        view.showMessage(message);
    }
}
