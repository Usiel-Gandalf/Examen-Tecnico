package com.example.examentecnico.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.examentecnico.R;
import com.example.examentecnico.contracts.ListInfoContract;
import com.example.examentecnico.contracts.MainContract;
import com.example.examentecnico.helpers.User;
import com.example.examentecnico.helpers.UserCrud;
import com.example.examentecnico.presenters.ListInfoPresenter;
import com.example.examentecnico.presenters.MainPresenter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainView extends AppCompatActivity implements MainContract.View{
    MainContract.Presenter presenter;
    EditText edtxUser, edtxCountry, edtxState, edtxGender;
    String email = "", pais = "", estado = "", genero = "";
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);
        setTitle("Bienvenido");
        initElements();
        getD();
    }

    @Override
    public void initElements() {
        edtxUser = findViewById(R.id.edtxtUser);
        edtxCountry = findViewById(R.id.edtxtCountry);
        edtxState = findViewById(R.id.edtxtState);
        edtxGender = findViewById(R.id.edtxtGender);
        presenter = new MainPresenter(this);
        db = FirebaseFirestore.getInstance();
        edtxUser.setEnabled(false);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
             email = user.getEmail();
             edtxUser.setText(email);
        }
    }

    public void getD(){
        presenter.getDataUserInfo(db, this, email);

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setDataUserOnEditText(String pais, String estado, String genero) {
        edtxCountry.setText(pais);
        edtxState.setText(estado);
        edtxGender.setText(genero);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.lista:
                startActivity(new Intent(this, ListInfoView.class));
                return true;

            case R.id.singout:
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(this, "Sesion cerrada", Toast.LENGTH_LONG).show();
                startActivity(new Intent(this, LoginView.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void saveData(View view) {
        pais = edtxCountry.getText().toString();
        estado = edtxState.getText().toString();
        genero = edtxGender.getText().toString();
        presenter.saveOrUpdateData(email, pais, estado, genero, db);
    }
}