package com.dawnvisions.journeyhome;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;

public class CompleteTask extends DialogFragment
{
    Task task;
    OnCompleteTask onCompleteTask;

    public static CompleteTask newInstance(int arg, Task task, OnCompleteTask delegate) {
        CompleteTask frag = new CompleteTask();
        Bundle args = new Bundle();
        args.putInt("count", arg);
        frag.setArguments(args);
        frag.setComplexVariable(task);
        frag.setComplexVariable(delegate);
        return frag;
    }

    public void setComplexVariable(Task task) {
        this.task = task;
    }
    public void setComplexVariable(OnCompleteTask delegate) { this.onCompleteTask = delegate; }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if(task.isCompleted())
        {
            builder.setMessage("Do you want to mark as incomplete: " + task.getInstruction() + "?");
            builder.setPositiveButton("Mark Incomplete", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    task.setCompleted(false);
                    onCompleteTask.onComplete();
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    dialog.dismiss();
                }
            });
        }
        else
        {
            if(task.moreContent)
            {
                LayoutInflater inflater = getActivity().getLayoutInflater();
                builder.setView(inflater.inflate(R.layout.complete_dialog, null));
            }
            builder.setMessage("Do you want to complete: " + task.getInstruction() + "?");
            builder.setPositiveButton("Complete", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    task.setCompleted(true);
                    onCompleteTask.onComplete();
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    dialog.dismiss();
                }
            });
        }
        return builder.create();
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        //Retains the dialog task variable if the activity is stopped or paused
    }

}
