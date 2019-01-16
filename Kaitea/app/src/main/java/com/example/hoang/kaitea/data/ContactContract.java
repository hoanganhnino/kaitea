package com.example.hoang.kaitea.data;

import android.provider.BaseColumns;

/**
 * Created by HOANG on 9/15/2018.
 */

public class ContactContract
{
    private ContactContract()
    {
    }

    public static class ContactTable implements BaseColumns
    {
        public static final String TABLE_NAME = "Contact";
        public static final String COLUMN_NAME = "Name";
        public static final String COLUMN_NUMBER = "Number";

    }
}
