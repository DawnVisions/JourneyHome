package com.dawnvisions.journeyhome;

        import android.content.SharedPreferences;
        import android.os.Bundle;
        import android.preference.Preference;
        import android.support.annotation.NonNull;
        import android.support.annotation.Nullable;
        import android.support.design.internal.NavigationMenuView;
        import android.support.v4.app.Fragment;
        import android.support.v7.preference.PreferenceManager;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import database.TaskSource;


public class Dashboard extends Fragment
{
    public Dashboard()
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
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        //Check if need to add detour steps
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(super.getContext());
        Boolean detour = preferences.getBoolean("respiratory_switch", false);
        TaskSource.RespiratoryDetour(detour);

        detour = preferences.getBoolean("feeding_switch", false);
        TaskSource.FeedingDetour(detour);

        //Fill recycler view with tasks
        TaskViewAdapter adapter = new TaskViewAdapter(view.getContext(), TaskSource.tasks);
        RecyclerView recyclerView = view.findViewById(R.id.dashboard_recycler);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume()
    {
        onViewCreated(getView(), null);
        super.onResume();
    }

}
