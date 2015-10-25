package wmp.uksw.pl.pracalicencjacka.templates;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import wmp.uksw.pl.pracalicencjacka.helpers.database.DbHelper;
import wmp.uksw.pl.pracalicencjacka.helpers.sessions.SessionManager;

/**
 * Created by MSI on 2015-09-12.
 */
public abstract class MyActivity extends AppCompatActivity {

    protected SessionManager sessionManager;
    protected DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Disable Title
        //getSupportActionBar().setDisplayShowTitleEnabled(false);

        //creates SessionManager object that helps with managing
        this.sessionManager = new SessionManager(getContext());

        //creates DbHelper to access database
        this.dbHelper = new DbHelper(getContext());

        setContentView(getLayoutResourceId());
    }

    protected abstract int getLayoutResourceId();

    protected abstract Context getContext();

}
