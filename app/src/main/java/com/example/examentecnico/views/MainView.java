package com.example.examentecnico.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.examentecnico.R;
import com.example.examentecnico.contracts.MainContract;
import com.google.firebase.auth.FirebaseAuth;

public class MainView extends AppCompatActivity implements MainContract.View{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);
        setTitle("Bienvenido");
    }

    @Override
    public void initElements() {

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
                this.onDestroy();
                startActivity(new Intent(this, ListInfoView.class));
                return true;

            case R.id.singout:
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(this, "Sesion cerrada", Toast.LENGTH_LONG).show();
                this.onDestroy();
                startActivity(new Intent(this, LoginView.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}