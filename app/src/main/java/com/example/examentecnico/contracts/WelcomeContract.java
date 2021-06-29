package com.example.examentecnico.contracts;

import android.view.Menu;
import android.view.MenuItem;

public interface WelcomeContract {
    interface View{

        void initElements();

        boolean onCreateOptionsMenu(Menu menu);

        boolean onOptionsItemSelected(MenuItem item);

    }

    interface Presenter{

    }

    interface Model{

    }
}

