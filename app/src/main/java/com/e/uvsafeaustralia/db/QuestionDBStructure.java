package com.e.uvsafeaustralia.db;

import android.provider.BaseColumns;

public class QuestionDBStructure {
    public static abstract class tableEntry implements BaseColumns {
        public static final String TABLE_QUESTION = "question";
        public static final String COLUMN_QUESTION_CATEGORY = "question_category";
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_ANSWER_OPTION1 = "answer_option1";
        public static final String COLUMN_ANSWER_OPTION2 = "answer_option2";
        public static final String COLUMN_ANSWER_OPTION3 = "answer_option3";
        public static final String COLUMN_ANSWER_OPTION4 = "answer_option4";
        public static final String COLUMN_CORRECT = "correct";
        public static final String COLUMN_ANSWER_EXPLANATION = "answer_explanation";
    }
}
