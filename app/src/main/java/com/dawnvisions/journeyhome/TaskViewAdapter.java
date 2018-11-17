package com.dawnvisions.journeyhome;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;


public class TaskViewAdapter extends RecyclerView.Adapter<TaskViewAdapter.ViewHolder>
{

    private List<Task> tasks;
    private Context mContext;

    public TaskViewAdapter(Context context, List<Task> objects)
    {
        LinkedList<Task> activeTasks = new LinkedList<Task>();
        for (Task task : objects)
        {
            if (task.active)
            {
                activeTasks.add(task);
            }
        }
        tasks = activeTasks;

        mContext = context;
    }

    @NonNull
    @Override
    public TaskViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.task_list_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TaskViewAdapter.ViewHolder holder, int position)
    {
        final Task item = tasks.get(position);

            holder.descriptionTv.setText(item.instruction);

            if (item.completed)
            {
                holder.imageView.setImageResource(R.drawable.ic_completed_task);
            }
            else
            {
                holder.imageView.setImageResource(R.drawable.ic_task);
            }

            if(!item.moreContent)
            {
                holder.infoButton.setVisibility(View.INVISIBLE);
            }
            else
            {
                holder.infoButton.setVisibility(View.VISIBLE);
            }

        holder.descriptionTv.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //TODO: Dialog to complete task
                Toast.makeText(mContext, "Complete task " + item.instruction, Toast.LENGTH_SHORT).show();
            }
        });

        holder.infoButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //TODO: Open up more info activity
                Toast.makeText(mContext, "More information " + item.instruction, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount()
    {
        return tasks.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView imageView;
        public TextView descriptionTv;
        public ImageButton infoButton;
        public ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.task_list_image);
            descriptionTv = itemView.findViewById(R.id.task_description);
            infoButton = itemView.findViewById(R.id.more_info_button);
        }
    }

}
