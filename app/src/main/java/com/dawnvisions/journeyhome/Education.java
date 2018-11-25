package com.dawnvisions.journeyhome;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import database.DataSource;
import model.EducationItem;
import model.User;


public class Education extends Fragment
{
    DataSource database;

    public Education()
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
        return inflater.inflate(R.layout.fragment_education, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        MainActivity main = (MainActivity)getActivity();
        database = main.mDataSource;

        List<EducationItem> allEducation = database.getEducationFromDatabase();
        List<EducationItem> skills = new ArrayList<>();
        List<EducationItem> videos = new ArrayList<>();
        for (EducationItem item: allEducation)
        {
            if (item.getType().equals("skill"))
            {
                skills.add(item);
            } else if (item.getType().equals("video"))
            {
                videos.add(item);
            }
        }

        RecyclerView skillView = view.findViewById(R.id.skills_recycler);
        final EducationAdapter skillAdapter = new EducationAdapter(view.getContext(), skills, database);
        skillView.setAdapter(skillAdapter);

        RecyclerView videoView = view.findViewById(R.id.videos_recycler);
        final EducationAdapter videoAdapter = new EducationAdapter(view.getContext(), videos, database);
        videoView.setAdapter(videoAdapter);
    }

}
