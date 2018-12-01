package com.dawnvisions.journeyhome.Education;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.dawnvisions.journeyhome.Dashboard.OnCompleteTask;
import com.dawnvisions.journeyhome.R;
import com.dawnvisions.journeyhome.UserDialog;

import database.DataSource;
import model.EducationItem;
import model.User;

public class AddEdDialog extends DialogFragment
{
    DataSource mDataSource;
    OnItemAdded onItemAdded;

    public static com.dawnvisions.journeyhome.Education.AddEdDialog newInstance(int arg, DataSource mDataSource, OnItemAdded delegate)
    {
        com.dawnvisions.journeyhome.Education.AddEdDialog frag = new com.dawnvisions.journeyhome.Education.AddEdDialog();
        Bundle args = new Bundle();
        args.putInt("count", arg);
        frag.setArguments(args);
        frag.setDatabase(mDataSource);
        frag.setComplexVariable(delegate);
        return frag;
    }

    public void setComplexVariable(OnItemAdded delegate) { this.onItemAdded = delegate; }
    public void setDatabase(DataSource database)
    {
        this.mDataSource = database;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View v = inflater.inflate(R.layout.add_ed_dialog, null);
        builder.setView(v);

        final RadioButton skillRadio = v.findViewById(R.id.skill_radioButton);
        final RadioButton videoRadio = v.findViewById(R.id.video_radioButton);
        final EditText itemText = v.findViewById(R.id.edItem_input);

        final EducationItem newItem = new EducationItem();
        // Add action buttons
        builder.setPositiveButton("Save Changes", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int id)
            {
                if(itemText.getText().toString().trim().length() == 0)
                {
                    Toast.makeText(getContext(), "Item not saved. New items must have a title", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String type;
                    if(videoRadio.isChecked())
                        type = "video";
                    else
                        type = "skill";
                    newItem.setType(type);
                    newItem.setText(itemText.getText().toString());

                    mDataSource.addEducationItem(newItem);
                    onItemAdded.onAdd();
                }
            }
        })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        dialog.dismiss();
                    }
                });
        return builder.create();
    }
}

