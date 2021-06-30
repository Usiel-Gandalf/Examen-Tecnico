package com.example.examentecnico.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.examentecnico.R;
import com.example.examentecnico.contracts.MainContract;
import com.example.examentecnico.helpers.MyAdapter;
import com.example.examentecnico.helpers.userinfo;
import com.example.examentecnico.presenters.MainPresenter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class MainView extends AppCompatActivity implements MainContract.View {
MainContract.Presenter presenter;
    ListView listView;
    List<userinfo> listinfo;
    LinearLayout linearLayout;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);
    }

    @Override
    public void initElements() {
        listView = findViewById(R.id.listViewDates);
        linearLayout= findViewById(R.id.layoutListViewDates);
        presenter = new MainPresenter(this);
        db = FirebaseFirestore.getInstance();
        listinfo = new ArrayList<>();
        presenter.getInfoFB(db, this, listinfo);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void initElementsListView(List<userinfo> listinfo) {
        MyAdapter myAdapter = new MyAdapter(this, R.layout.list_info, listinfo);
        listView.setAdapter(myAdapter);
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
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}