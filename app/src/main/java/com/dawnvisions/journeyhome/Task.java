package com.dawnvisions.journeyhome;

public class Task
{
    Integer taskNumber;
    String instruction;
    boolean completed;

    //If item can be clicked to open up a new activity with more information
    private boolean moreContent;

    public Task(Integer taskNumber, String instruction, boolean moreContent)
    {
        this.taskNumber = taskNumber;
        this.instruction = instruction;
        this.completed = false;
        this.moreContent = moreContent;
    }

    private boolean changeCompleted()
    {
        completed = !completed;
        return completed;
    }

}
