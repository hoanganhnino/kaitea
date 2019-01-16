package com.example.hoang.kaitea;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.IdRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hoang.kaitea.data.DbHelper;
import com.example.hoang.kaitea.data.Question;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

public class TestActivity extends AppCompatActivity
{
    String prefname = "my_score";
    private static final String TAG = "Scoring";
    long timer = 180000;
    ArrayList<Question> listQuestion;
    ArrayList<String> justGetQuestion;
    ArrayList<Integer> justGetAnswer;
    Cursor c;
    TextView questionTV;
    RadioGroup questionGroupButton;
    RadioButton option1;
    RadioButton option2;
    RadioButton option3;
    RadioButton option4;
    //int selected;
    Button next;
    public static int i = 0;
    public static int score = 0;
    static boolean stopCounter = false;
    int index;
    HashSet<String> set;
    RadioButton selected;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        score = 0;
        i = 0;
        stopCounter = false;
        //index = -1;
        listQuestion = new ArrayList<>();
        justGetQuestion = new ArrayList<>();
        justGetAnswer = new ArrayList<>();
        questionGroupButton = (RadioGroup) findViewById(R.id.radio_group);
        questionTV = (TextView) findViewById(R.id.questionTextView);
        option1 = (RadioButton) findViewById(R.id.radio_option1);
        option2 = (RadioButton) findViewById(R.id.radio_option2);
        option3 = (RadioButton) findViewById(R.id.radio_option3);
        option4 = (RadioButton) findViewById(R.id.radio_option4);
        next = (Button) findViewById(R.id.nextButton);

        questionGroupButton.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId)
            {


                index = questionGroupButton.indexOfChild(findViewById(questionGroupButton.getCheckedRadioButtonId()));


                //Toast.makeText(QuizActivity.this, ""+index, Toast.LENGTH_SHORT).show();
                //Toast.makeText(QuizActivity.this, "Dap an o vi tri: "+listQuestion.get(i).getAnswerNr(), Toast.LENGTH_SHORT).show();

                selected = (RadioButton)findViewById( questionGroupButton.getCheckedRadioButtonId());
                //Toast.makeText(getApplicationContext(),"index: "+index,Toast.LENGTH_SHORT).show();
                //value = selected.getText().toString();
                //Toast.makeText(getApplicationContext(), "index: "+index,Toast.LENGTH_SHORT).show();
                //value = String.valueOf(selected.getText());
                //Toast.makeText(getApplicationContext(), "ID itself: "+questionGroupButton.getCheckedRadioButtonId()+" ID group"+index, Toast.LENGTH_LONG).show();
            }
        });
        getQuestionOnDB();
        //Toast.makeText(getApplicationContext(),"index: "+index,Toast.LENGTH_SHORT).show();
        index = -1;
        showQuestion(0);


        onClickButton();
        //savingPreferences();


    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_quiz_timer, menu);

        final MenuItem counter = menu.findItem(R.id.counter);
        new CountDownTimer(timer, 1000)
        {

            public void onTick(long millisUntilFinished)
            {
                long millis = millisUntilFinished;
                //String hms = (TimeUnit.MILLISECONDS.toHours(millis)) + ":" + (TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis))) + ":" + (TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
                String output = String.format("%02d:%02d:%02d",
                        TimeUnit.MILLISECONDS.toHours(millis),
                        TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                        TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
                //counter.setTitle(hms);
                counter.setTitle(output);
                timer = millis;

            }

            public void onFinish()
            {
                counter.setTitle("0:00:00");

                finish();
                if (stopCounter==false)
                startMyActivity();

            }
        }.start();

        return true;

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
            listQuestion.add(new Question(c.getString(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getInt(5)));
            //justGetQuestion.add(c.getString(1));
            //Toast.makeText(this, "justGetQuestion: "+i,Toast.LENGTH_SHORT).show();
            c.moveToNext();

        }


    }

    public void showQuestion(int i)
    {
        if (i == listQuestion.size())
        {
            startMyActivity();
        } else
        {


            questionTV.setText(i+1+". "+listQuestion.get(i).getQuestion());

            option1.setText(listQuestion.get(i).getOption1());
            option2.setText(listQuestion.get(i).getOption2());
            option3.setText(listQuestion.get(i).getOption3());
            option4.setText(listQuestion.get(i).getOption4());
        }

    }

    public void onClickButton()
    {



        next.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (index == -1)
                {
                    Toast.makeText(getApplicationContext(), "Choice any option ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    trueOrFalse();
                    showQuestion(++i);

                    justGetAnswer.add(index);
                    //Toast.makeText(getApplicationContext(),"Saving: "+value,Toast.LENGTH_SHORT).show();
                    Log.d("TestList", "My fucking answer list: " + justGetAnswer);
                    Log.d("TesList", "Static i= " + i);

                    questionGroupButton.clearCheck();
                    option1.clearFocus();
                    option2.clearFocus();
                    option3.clearFocus();
                    option4.clearFocus();
                    option1.setChecked(false);
                    option2.setChecked(false);
                    option3.setChecked(false);
                    option4.setChecked(false);
                }
            }
        });



        //String scoreString = Integer.toString(score);
        //Toast.makeText(getApplicationContext(), scoreString,Toast.LENGTH_LONG).show();
        //Log.d(TAG, "score: "+score);


    }

    public void startMyActivity()
    {
        //i = 0;
        //savingPreferences();
        Intent intent = new Intent(this, ResultQuizActivity.class);
        intent.putExtra("myscore", score);
        intent.putExtra("total", listQuestion.size());
        intent.putExtra("passOrFall", passOrFall(score, listQuestion.size()));
        //intent.putStringArrayListExtra("justGetQuestion", (ArrayList<String>) set);
        //Intent intentViewAns = new Intent(this, ViewAnswerActivity.class);
        startActivity(intent);

        finish();
    }


    public void trueOrFalse()
    {
        //int selectedID = questionGroupButton.getCheckedRadioButtonId();
        //int index = questionGroupButton.indexOfChild(findViewById(questionGroupButton.getCheckedRadioButtonId()));
        //Toast.makeText(this, "dap an dung: "+listQuestion.get(i).getAnswerNr(), Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, "index: "+index, Toast.LENGTH_SHORT).show();
        if (index == listQuestion.get(i).getAnswerNr())
        {
            score += 1;
            //Toast.makeText(this, "Dung roi. Score: " + score, Toast.LENGTH_SHORT).show();

        } else
        {
            //Toast.makeText(this, "Sai roi. Score: " + score, Toast.LENGTH_SHORT).show();
        }
        //Toast.makeText(this, "Ban vua chon : "+value, Toast.LENGTH_SHORT).show();
        //justGetAnswer.add((String) selected.getText());

    }

    public String passOrFall(int correctAns, int total)
    {
        if (correctAns >= total / 2)
            return "Chúc mừng! Bạn đã vượt qua bài test";
        return "Bạn đã không vượt qua bài test. Ôn tập lại nhé.";
    }

    public void savingPreferences()
    {
        //tạo đối tượng getSharedPreferences
        SharedPreferences pre = getSharedPreferences(prefname, MODE_PRIVATE);
        //tạo đối tượng Editor để lưu thay đổi
        SharedPreferences.Editor editor = pre.edit();
        editor.clear();
        //lưu vào editor
        //String scoreString = Integer.toString(score);
        editor.putInt("myscore", score);
        editor.putInt("total", listQuestion.size());
        editor.putString("passOrFall", passOrFall(score, listQuestion.size()));

        //set = new HashSet<String>();
        StringBuilder str = new StringBuilder();
        for(int i = 0;i<justGetAnswer.size();i++)
        {
            str.append(justGetAnswer.get(i)).append(",");
        }
        Log.d("TestList","My string: "+str.toString());
        editor.putString("indexAns",str.toString());
        //set.addAll(Array.(justGetAnswer));
        //Toast.makeText(getApplicationContext(),"This is set get: "+set,)
        //Log.d("ListTest","This is set: "+set);
        //editor.putStringSet("answer", set);
        //Toast.makeText(getApplicationContext(),"Saving: "+justGetAnswer.get(i),Toast.LENGTH_SHORT).show();
        //set = new HashSet<String>();
        //set.addAll(justGetQuestion);
        //editor.putStringSet("justGetQuestion", set);

        //chấp nhận lưu xuống file
        editor.commit();
        //Toast.makeText(this, "saving SharePre", Toast.LENGTH_SHORT).show();
    }

    public void restoringPreferences()
    {
        SharedPreferences pre = getSharedPreferences(prefname, MODE_PRIVATE);
        int myScore = pre.getInt("myscore", 0);
        int total = pre.getInt("total", 0);
        //Toast.makeText(this, "retoring SharePre score: " + score, Toast.LENGTH_SHORT).show();
    }

    protected void onPause()
    {
        // TODO Auto-generated method stub
        super.onPause();
        //gọi hàm lưu trạng thái ở đây
        savingPreferences();
    }

    @Override
    protected void onResume()
    {
        // TODO Auto-generated method stub
        super.onResume();
        //gọi hàm đọc trạng thái ở đây
        restoringPreferences();
    }

    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        stopCounter = true;
                        TestActivity.this.finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}
