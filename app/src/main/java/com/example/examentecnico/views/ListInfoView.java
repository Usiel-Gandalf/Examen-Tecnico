package com.example.examentecnico.views;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.examentecnico.R;
import com.example.examentecnico.contracts.ListInfoContract;
import com.example.examentecnico.helpers.MyAdapter;
import com.example.examentecnico.helpers.UserInfo;
import com.example.examentecnico.presenters.ListInfoPresenter;
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
        setTitle("Listainfo");
        initElements();
    }

    @Override
    public void initElements() {
        listView = findViewById(R.id.listViewDates);
        linearLayout= findViewById(R.id.layoutListViewDates);
        value = findViewById(R.id.username);
        presenter = new ListInfoPresenter(this);
        db = FirebaseFirestore.getInstance();
        getPermission();
        getInfoDb();
    }

    @Override
    public void verifyPermissions() {
        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
    }

    @Override
    public void getPermission() {
        presenter.veryfiPermission(this);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void initElementsListView(List<UserInfo> listinfo) {
        MyAdapter myAdapter = new MyAdapter(this, R.layout.list_info, listinfo);
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    //String item = parent.getItemAtPosition(position).toString();
                    displayOnSelectItemList();
                } catch (Exception e) {
                    //  showError("Ocurrio un error cuando se seleccionaba la fecha agendada");
                }
            }
        });
    }

    @Override
    public void getInfoDb() {
        presenter.getInfoFB(db, this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.principal:
                startActivity(new Intent(this, MainView.class));
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

    @Override
    public void displayOnSelectItemList() {
        String[] listDates = new String[] { "Buscar por", "Ordenar por", "Actualizar lista"};
        alertMessage = new AlertDialog.Builder(this);
        alertMessage.setTitle("¿Que desea hacer?").setItems(listDates, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                presenter.displayAlertOnView(which);
            }
        });

        AlertDialog dialog = alertMessage.create();
        dialog.show();
    }

    @Override
    public void displaySearchFilterList() {
        alertMessage = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();
        View MyView = inflater.inflate(R.layout.dialog_searchform, null);

        final EditText valuess = MyView.findViewById(R.id.username);
        final Spinner spinner = (Spinner) MyView.findViewById(R.id.optionsselect);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(MyView.getContext(), R.array.options_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        alertMessage.setView(MyView).setPositiveButton("Buscar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                String valueSearchable = valuess.getText().toString();
                Integer idSpinner = Integer.parseInt(String.valueOf(spinner.getSelectedItemId()));
                presenter.getInfoFBForValue(valueSearchable, idSpinner, db, getApplicationContext());
            }
        });

        AlertDialog dialog = alertMessage.create();
        dialog.show();
    }

    @Override
    public void displayOrderList() {
        alertMessage = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View MyView = inflater.inflate(R.layout.dialog_orderform, null);
        final Spinner spinner = (Spinner) MyView.findViewById(R.id.optionsselect);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(MyView.getContext(), R.array.options_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        alertMessage.setView(MyView).setPositiveButton("Ordenar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Integer idSpinner = Integer.parseInt(String.valueOf(spinner.getSelectedItemId()));
                // presenter.getInfoFBForValue(valueSearchable, idSpinner, db, getApplicationContext());
            }
        });
        AlertDialog dialog = alertMessage.create();
        dialog.show();
    }
}