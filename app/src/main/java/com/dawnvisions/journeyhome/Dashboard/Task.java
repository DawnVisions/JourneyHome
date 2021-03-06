package com.dawnvisions.journeyhome.Dashboard;

import android.content.ContentValues;

import database.CompletedTaskTable;

public class Task
{
    Integer taskNumber;
    String instruction;
    boolean completed;
    boolean active;

    //If item can be clicked to open up a new activity with more information
    boolean moreContent;

    public Task(Integer taskNumber, String instruction, boolean moreContent, boolean active)
    {
        this.taskNumber = taskNumber;
        this.instruction = instruction;
        this.completed = false;
        this.moreContent = moreContent;
        this.active = active;
    }

    public boolean isCompleted() {return completed;}

    public void setCompleted(boolean completed) { this.completed = completed; }

    public void setActive(Boolean active)
    {
        this.active = active;
    }

    public void setInstruction(String instruction)
    {
        this.instruction = instruction;
    }

    public String getInstruction() {return this.instruction;}

    public int getTaskNumber() {return this.taskNumber;}

    public ContentValues toValues()
    {
        ContentValues values = new ContentValues(2);
        values.put(CompletedTaskTable.COLUMN_NUM, taskNumber);
        values.put(CompletedTaskTable.COLUMN_COMPLETE, completed);
        return values;
    }
}
