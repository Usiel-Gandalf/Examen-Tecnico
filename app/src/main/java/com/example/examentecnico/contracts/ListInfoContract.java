package com.example.examentecnico.contracts;

import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;

import com.example.examentecnico.helpers.UserInfo;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public interface ListInfoContract {

    interface View{
        void initElements();

        void verifyPermissions();

        void getPermission();

        void showMessage(String message);

        void initElementsListView(List<UserInfo> listinfo);

        void getInfoDb();

        boolean onCreateOptionsMenu(Menu menu);

        boolean onOptionsItemSelected(MenuItem item);

        void displayOnSelectItemList();

        void displaySearchFilterList();

        void displayOrderList();
    }

    interface Presenter{
        void setElementsListView(List<UserInfo> listinfo);

        void getInfoFB(FirebaseFirestore db, Context context);

        void getInfoFBForValue(String value, Integer option, FirebaseFirestore db, Context context);

        void createDataBase(Context context);

        void createFolder();

        void getInfoFBForValueOrdened(Integer option, FirebaseFirestore db, Context context);

        void displayAlertOnView(Integer option);

        void veryfiPermission(Context context);

        void showMessage(String message);
    }

    interface Model{
        void getInfoFB(FirebaseFirestore db, Context context, ListInfoContract.Presenter listener, List<UserInfo> listinfo);

        void getInfoFBForValue(String value, String option, FirebaseFirestore db, Context context, ListInfoContract.Presenter listener, List<UserInfo> listinfo);

        void getInfoFBForValueOrdened(String option, FirebaseFirestore db, Context context, ListInfoContract.Presenter listener, List<UserInfo> listinfo);

        void getInfoFBForUser(String user, FirebaseFirestore db, Context context, ListInfoContract.Presenter listener);

        void getInfoFBForCountry(String country, FirebaseFirestore db, Context context, ListInfoContract.Presenter listener);

        void getInfoFBForState(String state, FirebaseFirestore db, Context context, ListInfoContract.Presenter listener);

        void getInfoFBForGender(String gender, FirebaseFirestore db, Context context, ListInfoContract.Presenter listener);
    }
}
