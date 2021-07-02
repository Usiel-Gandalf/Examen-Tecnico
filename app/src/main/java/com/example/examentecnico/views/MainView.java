package com.example.examentecnico.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.examentecnico.R;
import com.example.examentecnico.contracts.MainContract;
import com.example.examentecnico.helpers.UserInfo;
import com.example.examentecnico.presenters.MainPresenter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

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
        getDatasUser();
        generateDataRandom();
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

    public void getDatasUser(){
        presenter.getDataUserInfo(db, this, email);
    }

    public void generateDataRandom(){
        ArrayList<String> nombres = new ArrayList<String>();
        nombres.add("Rodrigo");
        nombres.add("Maurilio");
        nombres.add("Galin");
        nombres.add("Rayda");
        nombres.add("Pedro");
        nombres.add("Karla");
        nombres.add("Perla");
        nombres.add("Rayda");
        nombres.add("Sergio");
        nombres.add("Jorge");
        nombres.add("Ofelio");

        ArrayList<String> paises = new ArrayList<String>();
        paises.add("Mexico");
        paises.add("Estados Unidos");
        paises.add("Canada");
        paises.add("Alemania");
        paises.add("Suiza");
        paises.add("Rusia");
        paises.add("China");
        paises.add("Nueva Zelanda");
        paises.add("Turquia");
        paises.add("Inglaterra");
        paises.add("Italia");

        ArrayList<String> estados = new ArrayList<String>();
        estados.add("Edo Mexico");
        estados.add("Coahuila");
        estados.add("Oaxaca");
        estados.add("Yucatan");
        estados.add("Quebec");
        estados.add("Wyoming");
        estados.add("Texas");
        estados.add("Munich");
        estados.add("Dresden");
        estados.add("Frankfurt");
        estados.add("Siberia");

        ArrayList<String> genero = new ArrayList<String>();
        genero.add("Femenino");
        genero.add("Masculino");

        ArrayList<String> complements = new ArrayList<String>();
        complements.add("Dark");
        complements.add("Happy");
        complements.add("Yum");
        complements.add("Star");
        complements.add("Mert");
        complements.add("Dragon");
        complements.add("Heart");
        complements.add("Man");
        complements.add("Verde");
        complements.add("Gamer");
        complements.add("Crash");

        String pa = "";
        String est = "";
        String nom = "";
        String comp = "";
        String gen = "";
        String fakeCorreo = "";

        for (int i = 0; i <= 100; i ++){
            Integer numero = (int) (Math.random() * 10 + 0);
          pa = paises.get(numero);
            Integer numero1 = (int) (Math.random() * 10 + 0);
           est = estados.get(numero1);
            Integer numero2 = (int) (Math.random() * 10 + 0);
           nom = nombres.get(numero2);
            Integer numero3 = (int) (Math.random() * 10 + 0);
           comp = complements.get(numero3);
            Integer numero4 = (int) (Math.random() * 2 + 0);
            gen = genero.get(numero4);

            fakeCorreo = nom+comp+"@gmail.com";
            presenter.saveFakeData(fakeCorreo, pa, est, gen, db);
        }
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