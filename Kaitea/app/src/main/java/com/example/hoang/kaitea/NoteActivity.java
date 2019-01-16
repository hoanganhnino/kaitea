package com.example.hoang.kaitea;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.hoang.kaitea.data.EditNoteActivity;
import com.example.hoang.kaitea.data.Note;
import com.example.hoang.kaitea.data.NoteAdapter;
import com.example.hoang.kaitea.data.NoteDbHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NoteActivity extends AppCompatActivity
{
    ListView listView;
    List<Note> listNote;
    NoteAdapter adapter;
    //static NoteActivity noteActivity;
    FloatingActionButton fab;
    Cursor c;
    NoteDbHelper myDbHelper;
    int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        //noteActivity = this;
        listNote = new ArrayList<>();
        getNoteDB();
        listView = (ListView)findViewById(R.id.note_list_view);
        adapter = new NoteAdapter(this, listNote);

        myDbHelper = new NoteDbHelper(this);

        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent intent = new Intent(NoteActivity.this, EditNoteActivity.class);
                pos = position;
                intent.putExtra("position",position);
                //Toast.makeText(getApplicationContext(), "pos: "+position,Toast.LENGTH_SHORT).show();
                startActivityForResult(intent,1000);
            }
        });

        fab = (FloatingActionButton)findViewById(R.id.fab_note);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(NoteActivity.this, EditNoteActivity.class);
                startActivity(intent);
                //finish();
            }
        });
    }

   /* public static NoteActivity getInstance()
    {
        return noteActivity;
    }*/

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
//        adapter.updateData(listNote);

    }

    /*public static ArrayAdapter getAdapter()
    {

        return adapter;
    }
*/
    public void onResume()
    {
        super.onResume();
        adapter.clear();
        getNoteDB();
        adapter.updateData(listNote);
        //adapter.updateData(listNote);
        //adapter.notifyDataSetChanged();
    }

    public void onActivityResult(int reqCode, int resultCode, Intent data)
    {
        super.onActivityResult(reqCode, resultCode, data);
        //getNoteDB();
        //adapter.updateData(listNote);
        if(reqCode == 1000)
        {
            //adapter.remove(listNote.get(pos));
            adapter.notifyDataSetChanged();
        }
    }
}
