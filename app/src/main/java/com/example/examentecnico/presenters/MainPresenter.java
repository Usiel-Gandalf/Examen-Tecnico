package com.example.examentecnico.presenters;

import com.example.examentecnico.contracts.MainContract;
import com.example.examentecnico.models.MainModel;

public class MainPresenter implements MainContract.Presenter {

    MainContract.View view;
    MainContract.Model model;

    public MainPresenter(MainContract.View view){
        this.view = view;
        model = new MainModel();
    }

}
