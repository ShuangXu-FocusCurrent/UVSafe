package com.e.uvsafeaustralia.db;

import android.provider.BaseColumns;

public class AnswerDBStructure {
    public static abstract class tableEntry implements BaseColumns {
        public static final String TABLE_ANSWER = "answer";
        public static final String COLUMN_USER_ID = "user_id";
        public static final String COLUMN_QUESTION_ID = "question_id";
        public static final String COLUMN_SELECTED_ANSWER = "selected_answer";
        public static final String COLUMN_STATUS = "status";
    }
}
