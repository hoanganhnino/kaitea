package com.example.hoang.kaitea.data;

/**
 * Created by HOANG on 10/11/2018.
 */

import android.provider.BaseColumns;

/**
 * Created by HOANG on 8/3/2018.
 */

public class QuizContract
{
    private QuizContract()
    {
    }

    public static class QuestionsTable implements BaseColumns
    {
        public static final String TABLE_NAME = "quiz_questions";
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_OPTION1 = "option1";
        public static final String COLUMN_OPTION2 = "option2";
        public static final String COLUMN_OPTION3 = "option3";
        public static final String COLUMN_OPTION4 = "option4";
        public static final String COLUMN_ANSWER_NR = "answer_nr";
    }
}
