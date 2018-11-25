package com.dawnvisions.journeyhome;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.dawnvisions.journeyhome.Dashboard.Dashboard;
import com.dawnvisions.journeyhome.Education.Education;
import com.dawnvisions.journeyhome.Home.Home;

import database.DataSource;
import database.TaskSource;

public class MainActivity extends AppCompatActivity
{
    public DataSource mDataSource;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener()
    {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item)
        {
            Fragment fragment;
            switch (item.getItemId())
            {
                case R.id.navigation_home:
                    fragment = new Home();
                    ReplaceFragment(fragment);
                    return true;
                case R.id.navigation_dashboard:
                    fragment = new Dashboard();
                    ReplaceFragment(fragment);
                    return true;
                case R.id.navigation_education:
                    fragment = new Education();
                    ReplaceFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar= findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        ReplaceFragment(new Home());

        mDataSource = new DataSource(this);
        mDataSource.open();
        mDataSource.getCompletedFromDatabase(TaskSource.tasks);
    }

    @Override
    public void onBackPressed()
    {
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        if (getSupportFragmentManager().getBackStackEntryCount() > 0)
        {
            Boolean result = fm.popBackStackImmediate();
        }
        else
        {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if (id == R.id.toolbar_settings)
        {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
        if (id == R.id.toolbar_user)
        {
            DialogFragment dialog = UserDialog.newInstance(1, mDataSource);
            dialog.show(getSupportFragmentManager(), "dialog");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void ReplaceFragment(Fragment fragment)
    {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment).addToBackStack(null);
        ft.commit();
    }


    @Override
    protected void onStop()
    {
        mDataSource.setCompletedToDatabase(TaskSource.tasks);
        super.onStop();
    }
}
