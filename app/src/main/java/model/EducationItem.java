package model;

import android.content.ContentValues;

import java.util.UUID;

import database.EducationTable;

public class EducationItem
{
    String id;
    String type;
    String text;
    Boolean done;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public Boolean getDone()
    {
        return done;
    }

    public void setDone(Boolean done)
    {
        this.done = done;
    }

    public EducationItem(String type, String text, Boolean done)
    {

        this.id = UUID.randomUUID().toString();
        this.type = type;
        this.text = text;
        this.done = done;
    }

    public EducationItem()
    {
        this.id = UUID.randomUUID().toString();
        this.done = false;
    }

    public ContentValues toValues()
    {
        ContentValues values = new ContentValues(4);
        values.put(EducationTable.COLUMN_ID, id);
        values.put(EducationTable.COLUMN_TYPE, type);
        values.put(EducationTable.COLUMN_TEXT, text);
        values.put(EducationTable.COLUMN_DONE, done);
        return values;
    }
}
