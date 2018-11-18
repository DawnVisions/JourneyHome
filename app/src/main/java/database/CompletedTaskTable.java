package database;

public class CompletedTaskTable
{
    public static final String TABLE_ITEMS = "tasks";
    public static final String COLUMN_NUM = "id";
    public static final String COLUMN_COMPLETE = "complete";

    public static final String[] ALL_COLUMNS = {COLUMN_NUM, COLUMN_COMPLETE};

    public static final String SQL_CREATE =
            "CREATE TABLE " + TABLE_ITEMS + "(" +
                    COLUMN_NUM + " INTEGER PRIMARY KEY," +
                    COLUMN_COMPLETE + " INTEGER" + ");";
}
