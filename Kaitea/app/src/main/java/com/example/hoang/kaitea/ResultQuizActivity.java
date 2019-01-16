package com.example.hoang.kaitea;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultQuizActivity extends AppCompatActivity
{
    TextView scoreTV,resultTV;
    Button exit,tryAgainButton, viewAnsButton;
    ImageView img;
    //SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_quiz);
        scoreTV = (TextView)findViewById(R.id.scoreTV);
        resultTV = (TextView)findViewById(R.id.result_textview);
        Intent intent = getIntent();
        //pref = getApplication().getSharedPreferences("myscore",MODE_PRIVATE);

        String myScore = Integer.toString(intent.getIntExtra("myscore",0));
        String total = Integer.toString(intent.getIntExtra("total",0));

        scoreTV.setText(myScore+"/"+total);
        resultTV.setText(intent.getStringExtra("passOrFall"));

        exit = (Button)findViewById(R.id.exit_button);
        tryAgainButton = (Button)findViewById(R.id.try_again_button);
        viewAnsButton = (Button)findViewById(R.id.view_ans_button);
        img = (ImageView)findViewById(R.id.pass_or_fall_image_view);
        if (resultTV.getText().toString().equalsIgnoreCase("Bạn đã không vượt qua bài test. Ôn tập lại nhé."))
        {
            img.setImageResource(R.drawable.sad);
        }
        else
        {
            img.setImageResource(R.drawable.medal);
        }

        exit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

        tryAgainButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(), TestActivity.class);
                startActivity(i);

            }
        });

        viewAnsButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(), ViewAnswerActivity.class);
                startActivity(i);
            }
        });

    }


}
