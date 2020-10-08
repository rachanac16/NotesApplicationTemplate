package com.rachana.notesapplication.database.model;

public class Note {
    public static final String TABLE_NAME = "notes";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOTE = "note";
    public static final String COLUMN_TIMESTAMP = "timestamp";

    private int id;
    private String note;
    private String timestamp;

    //Task #2a
    //TODO
    //  Create table SQL query
    //  public static final String CREATE_TABLE

    //Task #2b and #2c
    //TODO
    //  Create constructors and getter and settters
    //Go to DatabaseHelper class for Task #3

}
