package com.dawnvisions.journeyhome.Education;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dawnvisions.journeyhome.MainActivity;
import com.dawnvisions.journeyhome.R;

import java.util.ArrayList;
import java.util.List;

import database.DataSource;
import model.EducationItem;


public class Education extends Fragment
{
    DataSource database;
    List<EducationItem> skills = new ArrayList<>();
    List<EducationItem> videos = new ArrayList<>();

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
        getFromDatabase();

        final RecyclerView skillView = view.findViewById(R.id.skills_recycler);
        final EducationAdapter skillAdapter = new EducationAdapter(view.getContext(), skills, database);
        skillView.setAdapter(skillAdapter);

        RecyclerView videoView = view.findViewById(R.id.videos_recycler);
        final EducationAdapter videoAdapter = new EducationAdapter(view.getContext(), videos, database);
        videoView.setAdapter(videoAdapter);

        FloatingActionButton addFAB = view.findViewById(R.id.addEducation_fab);
        addFAB.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                DialogFragment dialog = AddEdDialog.newInstance(1, database, new OnItemAdded()
                {
                    @Override
                    public void onAdd()
                    {
                        getFromDatabase();
                        skillAdapter.notifyDataSetChanged();
                        videoAdapter.notifyDataSetChanged();

                    }
                });
                dialog.show(getActivity().getSupportFragmentManager(), "dialog");
            }
        });

    }

    private void getFromDatabase()
    {
        skills.clear();
        videos.clear();
        List<EducationItem> allEducation = database.getEducationFromDatabase();
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
    }

}
