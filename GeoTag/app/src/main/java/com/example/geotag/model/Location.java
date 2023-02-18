package com.example.geotag.model;

import java.util.Date;

public class Location {
}

@Entity(tableName = "Task_Table")
public class Task {

    @ColumnInfo(name = "task_id")
    @PrimaryKey(autoGenerate = true)
    public long taskId;

    public String task;
    public Priority priority;

    @ColumnInfo(name = "due_date")
    public Date dueDate;
    public int chipNo;
    @ColumnInfo(name = "created_date")
    public Date dateCreated;
    @ColumnInfo(name = "is_done")
    public boolean isdone;

