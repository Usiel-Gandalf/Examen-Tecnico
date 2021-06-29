package com.example.examentecnico.presenters;

import com.example.examentecnico.contracts.WelcomeContract;
import com.example.examentecnico.models.WelcomeModel;

public class WelcomePresenter implements WelcomeContract.Presenter {
    WelcomeContract.View view;
    WelcomeContract.Model model;

    public WelcomePresenter(WelcomeContract.View view){
        this.view = view;
        model = new WelcomeModel();
    }
}
