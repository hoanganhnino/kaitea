package com.example.hoang.kaitea.data;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hoang.kaitea.NoteActivity;
import com.example.hoang.kaitea.R;

import java.io.IOException;
import java.util.ArrayList;

public class EditNoteActivity extends AppCompatActivity
{
    Integer pos;
    EditText title;
    EditText content;
    ArrayList<Note> listNote;
    Cursor c;
    NoteDbHelper myDbHelper;
    //NoteActivity noteActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //setTheme(R.style.Theme_AppCompat_Dialog);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_note_layout);
        Intent intent = getIntent();
        setTitle("Ghi chú");

        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            pos = -1;
        } else {
            pos = extras.getInt("position");

        }

        title = (EditText)findViewById(R.id.title);
        content = (EditText)findViewById(R.id.content);
        //myDbHelper = new NoteDbHelper(this);
        listNote = new ArrayList<>();
        getNoteDB();
        if (pos != -1)
        {
            title.setText(listNote.get(pos).getTitle());
            content.setText(listNote.get(pos).getContent());
        }

        Toast.makeText(this,"ID: "+pos,Toast.LENGTH_SHORT).show();


    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.note_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                // Save pet to database

                saveNote();
                // Exit activity
                finish();
                //justFinish();
                return true;
            // Respond to a click on the "Delete" menu option
            case R.id.action_delete:
                // Do nothing for now
                confirmDelete();
                //finish();
                //justFinish();
                return true;
            // Respond to a click on the "Up" arrow button in the app bar

        }
        return super.onOptionsItemSelected(item);
    }

    public void getNoteDB()
    {
        myDbHelper = new NoteDbHelper(this);
        try
        {
            myDbHelper.createDataBase();
        } catch (IOException ioe)
        {
            throw new Error("Unable to create database");
        }
        try
        {
            myDbHelper.openDataBase();
        } catch (SQLException sqle)
        {
            throw sqle;
        }
        c = myDbHelper.query("Note", null, null, null, null, null, null);

        c.moveToFirst();
        while (!c.isAfterLast())
        {
            listNote.add(new Note(c.getString(1), c.getString(2)));
            //justGetQuestion.add(c.getString(1));
            //Toast.makeText(this, "justGetQuestion: "+i,Toast.LENGTH_SHORT).show();
            c.moveToNext();

        }


    }

    public void saveNote()
    {
        String titleText = title.getText().toString();
        String contentText = content.getText().toString();
        if (pos == -1)
            myDbHelper.insertDB(new Note(titleText,contentText));
        else
            myDbHelper.updateDB(new Note(titleText,contentText));
        //listNote.add(new Note(titleText,contentText));
        //adapter.updateData(listNote);
        //adapter.notifyDataSetChanged();
       // NoteActivity noteActivity = (NoteActivity)getApplicationContext();
        //noteActivity.getAdapter().notifyDataSetChanged();
        //Toast.makeText(this, "ID: "+myDbHelper.insertDB(new Note(titleText,contentText)),Toast.LENGTH_LONG).show();
    }

    public void justFinish()
    {
        Intent intent = new Intent(this, NoteActivity.class);
        startActivity(intent);
    }

    public void deleteNote()
    {

        if (pos==-1)
            Toast.makeText(this, "Nothing to delete",Toast.LENGTH_SHORT).show();
        else
        {
            long id = myDbHelper.deleteNote(title.getText().toString());
            //myDbHelper.deleteNote(pos);
            //Toast.makeText(this, "Delete: " + id, Toast.LENGTH_SHORT).show();
        }//NoteActivity.getInstance()..notifyDataSetChanged();
    }

    public void confirmDelete()
    {

        new android.support.v7.app.AlertDialog.Builder(this)
                .setMessage("Đồng ý xóa ghi chú này ?")
                .setCancelable(false)
                .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        deleteNote();
                        finish();

                        //updateData(listContact);
                    }
                })
                .setNegativeButton("Không", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        //flag++;
                        Toast.makeText(getApplicationContext(), "Hủy", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();

    }
}
