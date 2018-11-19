package database;

public class UserInfoTable
{
    public static final String TABLE_ITEMS = "information";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_BIRTH_DAY= "day";
    public static final String COLUMN_BIRTH_MONTH= "month";
    public static final String COLUMN_BIRTH_YEAR= "year";


    public static final String[] ALL_COLUMNS = {COLUMN_NAME, COLUMN_BIRTH_DAY, COLUMN_BIRTH_MONTH, COLUMN_BIRTH_YEAR};

    public static final String SQL_CREATE =
            "CREATE TABLE " + TABLE_ITEMS + "(" +
                    COLUMN_NAME + " STRING PRIMARY KEY," +
                    COLUMN_BIRTH_DAY + " INTEGER," +
                    COLUMN_BIRTH_MONTH + " INTEGER," +
                    COLUMN_BIRTH_YEAR + " INTEGER" + ");";
}
