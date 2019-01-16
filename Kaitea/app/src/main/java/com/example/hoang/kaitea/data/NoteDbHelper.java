package com.example.hoang.kaitea.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by HOANG on 9/27/2018.
 */

public class NoteDbHelper extends SQLiteOpenHelper
{
    String DB_PATH = null;
    private static String DB_NAME = "KaiteaDB.db";
    private SQLiteDatabase myDataBase;
    private final Context myContext;

    public NoteDbHelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.myContext = context;
        DB_PATH = "/data/data/" + context.getPackageName() + "/" + "databases/";
    }


    public void createDataBase() throws IOException
    {
        boolean dbExist = checkDataBase();
        if (dbExist) {
        } else {
            this.getReadableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
        }
        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }

    private void copyDataBase() throws IOException {
        InputStream myInput = myContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDataBase() throws SQLException
    {
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }

    @Override
    public synchronized void close() {
        if (myDataBase != null)
            myDataBase.close();
        super.close();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion)
            try {
                copyDataBase();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public Cursor query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        return myDataBase.query(table, null, null, null, null, null, null);
    }

    public long insertDB(Note note)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NoteContract.NoteTable.COLUMN_NAME, note.getTitle());
        contentValues.put(NoteContract.NoteTable.COLUMN_NUMBER, note.getContent());
        long id = db.insert(NoteContract.NoteTable.TABLE_NAME, null, contentValues);
        db.close();
        return  id;
    }

    /*public void deleteContact(Contact contact,int pos) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ContactContract.ContactTable.TABLE_NAME, ContactContract.ContactTable.COLUMN_NUMBER+" = ?",
                new String[] { contact.getSdt() });

        db.close();
    }*/

    public long deleteNote(String tit)
    {
        //String position = String.valueOf(pos);

        SQLiteDatabase db = this.getWritableDatabase();
        long id = db.delete(NoteContract.NoteTable.TABLE_NAME,NoteContract.NoteTable.COLUMN_NAME+" = ?",
                new String[] { tit});
        //db.execSQL("DELETE FROM "+NoteContract.NoteTable.TABLE_NAME+" WHERE ID = "+pos+"");
        db.close();
        return id;

    }

    public long updateDB(Note note)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NoteContract.NoteTable.COLUMN_NAME, note.getTitle());
        contentValues.put(NoteContract.NoteTable.COLUMN_NUMBER, note.getContent());
        long id = db.update(NoteContract.NoteTable.TABLE_NAME, contentValues, NoteContract.NoteTable.COLUMN_NAME+" = ?",
                new String[] {note.getTitle()});
        db.close();
        return  id;
    }

}

