package com.example.hoang.kaitea;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.example.hoang.kaitea.data.DbHelper;
import com.example.hoang.kaitea.data.ViewAnswerAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ViewAnswerActivity extends AppCompatActivity
{
    Cursor c;
    ArrayList<String> justGetQuestion;
    ArrayList<String> justCorectGetAns;
    ArrayList<String> justGetAns;
    SharedPreferences sharedPreferences;
    //Set<String> iWillGetMySet;
    String getIndexAns;
    ArrayList<Integer> getIndexInt;
    ListView listView;
    public static int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_answer);
        i = 0;
        justGetQuestion = new ArrayList<String>();
        justCorectGetAns = new ArrayList<String>();
        getIndexInt = new ArrayList<Integer>();
        justGetAns = new ArrayList<>();
        //oiWillGetMySet = new HashSet<>();
        sharedPreferences = getSharedPreferences("my_score",MODE_PRIVATE);
        //iWillGetMySet = sharedPreferences.getStringSet("answer", new HashSet<String>());
        //iWillGetMySet = sharedPreferences.getStringSet("answer", new HashSet<String>());
        //justGetAns = new ArrayList<String>(iWillGetMySet);
        getIndexAns = sharedPreferences.getString("indexAns","");
        Integer total = sharedPreferences.getInt("total",0);
        StringTokenizer str = new StringTokenizer(getIndexAns,",");
        for(int i = 0; i< total;i++)
        {
            getIndexInt.add(i, Integer.parseInt(str.nextToken()));
        }
        Log.d("TestList", "This is what I'm fucking get: "+getIndexInt);
        getQuestionOnDB();
        //SharedPreferences sharedPreferences = getSharedPreferences("my_score",MODE_PRIVATE);
        //justGetAns =  sharedPreferences.getStringSet("answer", new HashSet<String>());
        ViewAnswerAdapter myAdapter = new ViewAnswerAdapter(this, justGetQuestion, justGetAns, justCorectGetAns);
        listView =(ListView)findViewById(R.id.child_view_ans_listview);
        listView.setAdapter(myAdapter);
        //Toast.makeText(getApplicationContext(),"Answer list: "+iWillGetMySet,Toast.LENGTH_LONG).show();

    }

    public void getQuestionOnDB()
    {
        DbHelper myDbHelper = new DbHelper(this);
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
        c = myDbHelper.query("quiz_questions", null, null, null, null, null, null);

        c.moveToFirst();
        while (!c.isAfterLast())
        {
            //listQuestion.add(new Question(c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5), c.getInt(6)));
            Log.d("TestList","Get String i= "+i);
            justGetQuestion.add(c.getString(0));
            int getCorrectPosition = c.getInt(5);
            justCorectGetAns.add(c.getString(getCorrectPosition+1));
            int getAnswerPosition = getIndexInt.get(i);
            justGetAns.add(c.getString(getAnswerPosition+1));
            ++i;
            //Toast.makeText(this, "justGetQuestion: ",Toast.LENGTH_SHORT).show();
            c.moveToNext();

        }


    }


}
