package model;

import android.content.ContentValues;

import database.CompletedTaskTable;
import database.UserInfoTable;

public class User
{
    String name;
    Integer birth_day;
    Integer birth_month;
    Integer birth_year;

    public User()
    {

    }

    public User(String name, Integer birth_day, Integer birth_month, Integer birth_year)
    {
        this.name = name;
        this.birth_day = birth_day;
        this.birth_month = birth_month;
        this.birth_year = birth_year;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Integer getBirth_day()
    {
        return birth_day;
    }

    public void setBirth_day(Integer birth_day)
    {
        this.birth_day = birth_day;
    }

    public Integer getBirth_month()
    {
        return birth_month;
    }

    public void setBirth_month(Integer birth_month)
    {
        this.birth_month = birth_month;
    }

    public Integer getBirth_year()
    {
        return birth_year;
    }

    public void setBirth_year(Integer birth_year)
    {
        this.birth_year = birth_year;
    }

    public ContentValues toValues()
    {
        ContentValues values = new ContentValues(4);
        values.put(UserInfoTable.COLUMN_NAME, name);
        values.put(UserInfoTable.COLUMN_BIRTH_DAY, birth_day);
        values.put(UserInfoTable.COLUMN_BIRTH_MONTH, birth_month);
        values.put(UserInfoTable.COLUMN_BIRTH_YEAR, birth_year);
        return values;
    }
}
