package com.example.hoang.kaitea.data;

/**
 * Created by HOANG on 10/11/2018.
 */

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.hoang.kaitea.R;

import java.util.ArrayList;

/**
 * Created by HOANG on 8/15/2018.
 */

public class ViewAnswerAdapter extends ArrayAdapter<String>
{
    private Activity context;
    private ArrayList<String> question;
    private ArrayList<String> yourAns;
    private ArrayList<String> correctAns;
    //public static int i=0;


    public ViewAnswerAdapter(@NonNull Activity context, ArrayList<String> question,ArrayList<String> yourAns, ArrayList<String> correctAns)
    {
        super(context, R.layout.view_answer_child, question);
        this.context = context;
        this.question = question;
        this.yourAns = yourAns;
        this.correctAns = correctAns;
    }

    public View getView(int position, View view, ViewGroup parent)
    {

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.view_answer_child,null, true);
        TextView questionTextView = (TextView)rowView.findViewById(R.id.question_textview);
        TextView yourAnsTextView = (TextView)rowView.findViewById(R.id.your_answer_textview);
        TextView correctAnsTextView = (TextView)rowView.findViewById(R.id.correct_answer_textview);


        questionTextView.setText(question.get(position));
        yourAnsTextView.setText("Your Ans: "+yourAns.get(position));
        correctAnsTextView.setText("Correct Ans: "+correctAns.get(position));

        return rowView;
    }
}
