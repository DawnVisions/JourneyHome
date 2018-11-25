package database;

public class EducationTable
{
    public static final String TABLE_ITEMS = "education";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TYPE= "type";
    public static final String COLUMN_TEXT= "text";
    public static final String COLUMN_DONE= "done";


    public static final String[] ALL_COLUMNS = {COLUMN_ID, COLUMN_TYPE, COLUMN_TEXT, COLUMN_DONE};

    public static final String SQL_CREATE =
            "CREATE TABLE " + TABLE_ITEMS + "(" +
                    COLUMN_ID + " STRING PRIMARY KEY," +
                    COLUMN_TYPE + " STRING," +
                    COLUMN_TEXT + " STRING," +
                    COLUMN_DONE + " INTEGER" + ");";
}
