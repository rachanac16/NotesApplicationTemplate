package com.rachana.notesapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import com.rachana.notesapplication.database.model.Note;


//Task #3a
//TODO extend this class from SQLiteOpenHelper
public class DatabaseHelper{

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "notes_db";

    //TODO constructor here..


    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Note.CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Task #3b
        //TODO Drop older table if existed
        // Create tables again
        onCreate(db);
    }

    //insert notes
    public long insertNote(String note) {

        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(Note.COLUMN_NOTE, note);

        // insert row
        long id = db.insert(Note.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public Note getNote(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Note.TABLE_NAME,
                new String[]{Note.COLUMN_ID, Note.COLUMN_NOTE, Note.COLUMN_TIMESTAMP},
                Note.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        Note note = new Note(
                cursor.getInt(cursor.getColumnIndex(Note.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(Note.COLUMN_NOTE)),
                cursor.getString(cursor.getColumnIndex(Note.COLUMN_TIMESTAMP)));

        // close the db connection
        cursor.close();

        return note;
    }

    public List<Note> getAllNotes() {
        List<Note> notes = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Note.TABLE_NAME + " ORDER BY " +
                Note.COLUMN_TIMESTAMP + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //Task #3c
        //TODO looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {


            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return notes;
    }

    public int getNotesCount() {
        //Task #3d
        //TODO
        //  write a raw query and execute it using db.rawquery
        //  dont forget to close the database
    }

    public long updateNote(Note note) {
        //Task #3e
        //TODO -
        //  initialize db with getWritableDatabase
        //  initialise object of ContentValues and put the note inside
        //  call db.update which returns an id that you should return
    }

    public void deleteNote(Note note) {
        //Task #3f
        //TODO - db.delete beaches.
        //Go to MainActivity for Task #4
    }
}
