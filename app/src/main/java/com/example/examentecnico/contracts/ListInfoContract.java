package com.example.examentecnico.contracts;

import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;

import com.example.examentecnico.helpers.userinfo;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public interface ListInfoContract {
    interface View{
        void initElements();

        void showMessage(String message);

        void initElementsListView(List<userinfo> listinfo);

        boolean onCreateOptionsMenu(Menu menu);

        boolean onOptionsItemSelected(MenuItem item);
    }

    interface Presenter{
        void setElementsListView(List<userinfo> listinfo);

        void getInfoFB(FirebaseFirestore db, Context context, List<userinfo> listinfo);

        void getInfoFBForUser(String user, FirebaseFirestore db, Context context);

        void getInfoFBForCountry(String country, FirebaseFirestore db, Context context);

        void getInfoFBForState(String state, FirebaseFirestore db, Context context);

        void getInfoFBForGender(String gender, FirebaseFirestore db, Context context);

        void showMessage(String message);
    }

    interface Model{
        void getInfoFB(FirebaseFirestore db, Context context, ListInfoContract.Presenter listener, List<userinfo> listinfo);

        void getInfoFBForUser(String user, FirebaseFirestore db, Context context, ListInfoContract.Presenter listener);

        void getInfoFBForCountry(String country, FirebaseFirestore db, Context context, ListInfoContract.Presenter listener);

        void getInfoFBForState(String state, FirebaseFirestore db, Context context, ListInfoContract.Presenter listener);

        void getInfoFBForGender(String gender, FirebaseFirestore db, Context context, ListInfoContract.Presenter listener);
    }
}
