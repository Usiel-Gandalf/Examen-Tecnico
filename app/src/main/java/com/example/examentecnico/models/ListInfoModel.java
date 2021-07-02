package com.example.examentecnico.models;

import android.content.Context;

import com.example.examentecnico.contracts.ListInfoContract;
import com.example.examentecnico.helpers.UserInfo;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class ListInfoModel implements ListInfoContract.Model  {
    @Override
    public void getInfoFB(FirebaseFirestore db, Context context, ListInfoContract.Presenter listener, List<UserInfo> listinfo) {

    }

    @Override
    public void getInfoFBForValue(String value, String option, FirebaseFirestore db, Context context, ListInfoContract.Presenter listener, List<UserInfo> listinfo) {

    }

    @Override
    public void getInfoFBForValueOrdened(String option, FirebaseFirestore db, Context context, ListInfoContract.Presenter listener, List<UserInfo> listinfo) {

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
