package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dawnvisions.journeyhome.Dashboard.Task;

import model.EducationItem;
import model.User;

public class DBHelper extends SQLiteOpenHelper
{

    public static final String DB_FILE_NAME = "data.db";

    public static final int DB_VERSION = 1;

    public DBHelper(Context context)
    {
        super(context, DB_FILE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CompletedTaskTable.SQL_CREATE);
        db.execSQL(UserInfoTable.SQL_CREATE);
        db.execSQL(EducationTable.SQL_CREATE);

        for (Task task: TaskSource.tasks)
        {
            db.insert(CompletedTaskTable.TABLE_ITEMS, null, task.toValues());
        }

        for (EducationItem item: EducationSource.eds)
        {
            db.insert(EducationTable.TABLE_ITEMS, null, item.toValues());
        }

        User u = new User("My Baby", 0, 0, 0);
        db.insert(UserInfoTable.TABLE_ITEMS, null, u.toValues());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        onCreate(db);
    }
}

