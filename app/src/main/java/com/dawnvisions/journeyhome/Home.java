package com.dawnvisions.journeyhome;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import org.joda.time.LocalDate;
import org.joda.time.Period;

import database.DataSource;
import model.User;

public class Home extends Fragment
{
    DataSource database;

    public Home()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        MainActivity main = (MainActivity)getActivity();
        database = main.mDataSource;
        User user = database.getUserInfo();

        //Get Baby name from database
        String name = user.getName();
        TextView nameTv = view.findViewById(R.id.baby_name_text);
        nameTv.setText(name);

        if(user.getBirth_day() > 0)
        {
            //Display number of days in the NICU
            LocalDate now = LocalDate.now();
            LocalDate birthDate = new LocalDate(2000 + user.getBirth_year(), user.getBirth_month(), user.getBirth_day());
            Period period = new Period(birthDate, now);
            TextView dateTV = view.findViewById(R.id.date_text);
            if(period.getMonths()>0)
            {
                dateTV.setText("My NICU Stay: " + period.getMonths() + " months");
            }
            else if(period.getWeeks()>0)
            {
                dateTV.setText("My NICU Stay: " + period.getWeeks() + " weeks and " + period.getDays() + " days");
            }
            else
            {
                dateTV.setText("My NICU Stay: " + period.getDays() + " days");
            }

        }
    }
}
