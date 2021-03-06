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
import com.dawnvisions.journeyhome.Home.OnUserDataAdded;

import database.DataSource;
import database.TaskSource;

public class MainActivity extends AppCompatActivity
{
    public DataSource mDataSource;
    Home homeFrag;
    Dashboard dashboard;
    Education educationFrag;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener()
    {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item)
        {
            switch (item.getItemId())
            {
                case R.id.navigation_home:
                    if(homeFrag == null)
                        homeFrag = new Home();
                    ReplaceFragment(homeFrag);
                    return true;
                case R.id.navigation_dashboard:
                    if(dashboard == null)
                        dashboard = new Dashboard();
                    ReplaceFragment(dashboard);
                    return true;
                case R.id.navigation_education:
                    if(educationFrag == null)
                        educationFrag = new Education();
                    ReplaceFragment(educationFrag);
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

        mDataSource = new DataSource(this);
        mDataSource.open();
        mDataSource.getCompletedFromDatabase(TaskSource.tasks);

        homeFrag = new Home();
        ReplaceFragment(homeFrag);
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
            DialogFragment dialog = UserDialog.newInstance(1, mDataSource, new OnUserDataAdded()
            {
                @Override
                public void updateUserData()
                {
                    homeFrag.displayUserInfo(homeFrag.getView());
                }
            });
            dialog.show(getSupportFragmentManager(), "dialog");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void ReplaceFragment(Fragment fragment)
    {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment);
        ft.commit();
    }


    @Override
    protected void onStop()
    {
        mDataSource.setCompletedToDatabase(TaskSource.tasks);
        super.onStop();
    }
}
