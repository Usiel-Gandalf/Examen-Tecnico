package com.example.examentecnico.contracts;

import android.view.Menu;
import android.view.MenuItem;

public interface MainContract {

    interface View{
        boolean onCreateOptionsMenu(Menu menu);

        boolean onOptionsItemSelected(MenuItem item);
    }

    interface Presenter{

    }

    interface Model{

    }
}
