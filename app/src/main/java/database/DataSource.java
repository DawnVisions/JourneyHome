package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dawnvisions.journeyhome.Task;
import java.util.List;


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
}

