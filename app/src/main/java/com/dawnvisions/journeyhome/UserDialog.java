package com.dawnvisions.journeyhome;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.dawnvisions.journeyhome.Home.OnUserDataAdded;

import java.util.Arrays;

import database.DataSource;
import model.User;

public class UserDialog extends DialogFragment
{
    DataSource mDataSource;
    OnUserDataAdded onUserDataAdded;

    public static UserDialog newInstance(int arg, DataSource mDataSource, OnUserDataAdded delegate)
    {
        UserDialog frag = new UserDialog();
        Bundle args = new Bundle();
        args.putInt("count", arg);
        frag.setArguments(args);
        frag.setDatabase(mDataSource);
        frag.setDelegate(delegate);
        return frag;
    }

    public void setDatabase(DataSource database) { this.mDataSource = database; }
    public void setDelegate(OnUserDataAdded delegate) { this.onUserDataAdded = delegate;}

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View v = inflater.inflate(R.layout.user_dialog, null);
        builder.setView(v);

        final EditText nameView = v.findViewById(R.id.name_input);
        final EditText dateView = v.findViewById(R.id.birthdate_input);

        //Get current user data
        final User user = mDataSource.getUserInfo();
        nameView.setText(user.getName());
        if(user.getBirth_day() != 0)
            dateView.setText(user.getBirth_month() + "." + user.getBirth_day() + "." + user.getBirth_year());

                // Add action buttons
                builder.setPositiveButton("Save Changes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        user.setName(nameView.getText().toString());
                        String date = dateView.getText().toString();

                        //Parse date mm.dd.yy
                        String[] parts = date.split("\\.");
                        user.setBirth_month(Integer.parseInt(parts[0]));
                        user.setBirth_day(Integer.parseInt(parts[1]));
                        user.setBirth_year(Integer.parseInt(parts[2]));

                        mDataSource.setUser(user);
                        onUserDataAdded.updateUserData();

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        return builder.create();
    }
}
