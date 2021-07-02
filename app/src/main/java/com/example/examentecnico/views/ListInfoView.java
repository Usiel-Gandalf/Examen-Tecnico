package com.example.examentecnico.views;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.examentecnico.R;
import com.example.examentecnico.contracts.ListInfoContract;
import com.example.examentecnico.helpers.UserInfo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class ListInfoView extends AppCompatActivity implements ListInfoContract.View{
    ListInfoContract.Presenter presenter;
    ListView listView;
    LinearLayout linearLayout;
    AlertDialog.Builder alertMessage;
    EditText value;
    int REQUEST_CODE = 200;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_info_view);
    }

    @Override
    public void initElements() {

    }

    @Override
    public void verifyPermissions() {

    }

    @Override
    public void getPermission() {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void initElementsListView(List<UserInfo> listinfo) {

    }

    @Override
    public void getInfoDb() {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.singout:
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(this, "Sesion cerrada", Toast.LENGTH_LONG).show();
                startActivity(new Intent(this, LoginVIew.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }



    @Override
    public void displayOnSelectItemList() {

    }

    @Override
    public void displaySearchFilterList() {

    }

    @Override
    public void displayOrderList() {

    }
}