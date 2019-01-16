package com.example.hoang.kaitea.data;

import android.provider.BaseColumns;

/**
 * Created by HOANG on 9/27/2018.
 */

public class NoteContract
{
    private NoteContract()
    {
    }

    public static class NoteTable implements BaseColumns
    {
        public static final String TABLE_NAME = "Note";
        public static final String COLUMN_ID = "ID";
        public static final String COLUMN_NAME = "Title";
        public static final String COLUMN_NUMBER = "Content";

    }
}
