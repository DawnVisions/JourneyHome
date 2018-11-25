package com.dawnvisions.journeyhome.Education;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.dawnvisions.journeyhome.R;

import java.util.List;

import database.DataSource;
import model.EducationItem;

public class EducationAdapter extends RecyclerView.Adapter<EducationAdapter.EdViewHolder>
{
    private List<EducationItem> topics;
    private Context mContext;
    private DataSource mdatabase;

    public EducationAdapter(Context context, List<EducationItem> objects, DataSource database)
    {
        topics = objects;
        mContext = context;
        mdatabase = database;
    }

    @NonNull
    @Override
    public EducationAdapter.EdViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.education_list_view, parent, false);
        EducationAdapter.EdViewHolder viewHolder = new EducationAdapter.EdViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final EducationAdapter.EdViewHolder holder, int position)
    {
        final EducationItem item = topics.get(position);
        holder.checkBox.setText(item.getText());
        holder.checkBox.setChecked(item.getDone());

        holder.checkBox.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mdatabase.setEducationDone(item.getId(), holder.checkBox.isChecked());
                item.setDone(holder.checkBox.isChecked());
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return topics.size();
    }

    public static class EdViewHolder extends RecyclerView.ViewHolder
    {
        CheckBox checkBox;
        public EdViewHolder(View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkBox);
        }
    }
}
