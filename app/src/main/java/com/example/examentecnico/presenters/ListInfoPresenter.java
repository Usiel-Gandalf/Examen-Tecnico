package com.example.examentecnico.presenters;

import android.content.Context;

import com.example.examentecnico.contracts.ListInfoContract;
import com.example.examentecnico.helpers.UserInfo;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class ListInfoPresenter implements ListInfoContract.Presenter{
    @Override
    public void setElementsListView(List<UserInfo> listinfo) {

    }

    @Override
    public void getInfoFB(FirebaseFirestore db, Context context) {

    }

    @Override
    public void getInfoFBForValue(String value, Integer option, FirebaseFirestore db, Context context) {

    }

    @Override
    public void createDataBase(Context context) {

    }

    @Override
    public void createFolder() {

    }

    @Override
    public void getInfoFBForValueOrdened(Integer option, FirebaseFirestore db, Context context) {

    }

    @Override
    public void displayAlertOnView(Integer option) {

    }

    @Override
    public void veryfiPermission(Context context) {

    }

    @Override
    public void showMessage(String message) {

    }
}
