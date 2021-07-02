package com.example.examentecnico.contracts;

import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public interface MainContract {

    interface View{

        void initElements();

        void showMessage(String message);

        void setDataUserOnEditText(String pais, String estado, String genero);

        boolean onCreateOptionsMenu(Menu menu);

        boolean onOptionsItemSelected(MenuItem item);

    }

    interface Presenter{

        void saveOrUpdateData(String usuario, String pais, String estado, String genero, FirebaseFirestore db);

        void setDataUserOnEditText(String pais, String estado, String genero);

        void showMessage(String message);

        void getDataUserInfo(FirebaseFirestore db, Context context, String usuario);
    }

    interface Model{
        void saveOrUpdateData(String usuario, Map<String, Object> map, FirebaseFirestore db, MainContract.Presenter listener);

        void getDataUserInfo(MainContract.Presenter listener, FirebaseFirestore db, Context context, String usuario);
    }

}
