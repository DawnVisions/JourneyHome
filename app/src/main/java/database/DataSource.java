package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dawnvisions.journeyhome.Dashboard.Task;

import java.util.ArrayList;
import java.util.List;

import model.EducationItem;
import model.User;


public class DataSource
{
    private Context context;
    private SQLiteDatabase database;
    private SQLiteOpenHelper helper;

    public DataSource(Context context)
    {
        this.context = context;
        helper = new DBHelper(context);
        database = helper.getReadableDatabase();
    }

    public void open()
    {
        database = helper.getReadableDatabase();
    }

    public void close()
    {
        helper.close();
    }

    public List<Task> getCompletedFromDatabase(List<Task> tasks)
    {
        Cursor cursor = database.query(CompletedTaskTable.TABLE_ITEMS, CompletedTaskTable.ALL_COLUMNS, null,null, null, null, null);

        while(cursor.moveToNext())
        {
           Task t = tasks.get(cursor.getInt(cursor.getColumnIndex(CompletedTaskTable.COLUMN_NUM)));
           int complete = (cursor.getInt(cursor.getColumnIndex(CompletedTaskTable.COLUMN_COMPLETE)));
           if(complete == 1)
           {
               t.setCompleted(true);
           }
           else
           {
               t.setCompleted(false);
           }

        }
        return tasks;
    }

    public void setCompletedToDatabase(List<Task> tasks)
    {
        for (Task task: tasks)
        {
            ContentValues values = task.toValues();
            String selection = CompletedTaskTable.COLUMN_NUM + " = ?";
            String[] selectionArgs = { Integer.toString(task.getTaskNumber()) };
            database.update(CompletedTaskTable.TABLE_ITEMS, values, selection, selectionArgs);
        }
    }

    public User getUserInfo()
    {
        Cursor cursor = database.query(UserInfoTable.TABLE_ITEMS, UserInfoTable.ALL_COLUMNS, null,null, null, null, null);
        User u = new User();
        while(cursor.moveToNext())
        {
            u.setName(cursor.getString(cursor.getColumnIndex(UserInfoTable.COLUMN_NAME)));
            u.setBirth_day(cursor.getInt(cursor.getColumnIndex(UserInfoTable.COLUMN_BIRTH_DAY)));
            u.setBirth_month(cursor.getInt(cursor.getColumnIndex(UserInfoTable.COLUMN_BIRTH_MONTH)));
            u.setBirth_year(cursor.getInt(cursor.getColumnIndex(UserInfoTable.COLUMN_BIRTH_YEAR)));
        }
        return u;
    }

    public void setUser(User u)
    {
        database.delete(UserInfoTable.TABLE_ITEMS, null, null);
        database.insert(UserInfoTable.TABLE_ITEMS, null, u.toValues());
    }

    public List<EducationItem> getEducationFromDatabase()
    {
        Cursor cursor = database.query(EducationTable.TABLE_ITEMS, EducationTable.ALL_COLUMNS, null,null, null, null, null);

        List<EducationItem> educationItems = new ArrayList<>();
        while(cursor.moveToNext())
        {
            EducationItem item = new EducationItem();
            item.setId(cursor.getString(cursor.getColumnIndex(EducationTable.COLUMN_ID)));
            item.setType(cursor.getString(cursor.getColumnIndex(EducationTable.COLUMN_TYPE)));
            item.setText(cursor.getString(cursor.getColumnIndex(EducationTable.COLUMN_TEXT)));
            int complete = (cursor.getInt(cursor.getColumnIndex(EducationTable.COLUMN_DONE)));
            if(complete == 1)
            {
                item.setDone(true);
            }
            else
            {
                item.setDone(false);
            }
            educationItems.add(item);
        }
        return educationItems;
    }

    public void setEducationDone(String id, boolean done)
    {
        String selection = EducationTable.COLUMN_ID + " = ?";
        String[] selectionArgs = { id };
        ContentValues values = new ContentValues();
        values.put(EducationTable.COLUMN_DONE, done);
        database.update(EducationTable.TABLE_ITEMS, values, selection, selectionArgs);
    }

    public void addEducationItem(EducationItem newItem)
    {
        database.insert(EducationTable.TABLE_ITEMS, null, newItem.toValues());
    }
}

