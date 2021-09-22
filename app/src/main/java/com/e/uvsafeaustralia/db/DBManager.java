package com.e.uvsafeaustralia.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.e.uvsafeaustralia.models.QuestionModel;
import com.e.uvsafeaustralia.models.UserModel;


public class DBManager {
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "uvsafeaustralia.db";
        private final Context context;
        private static final String TEXT_TYPE = " TEXT";
        private static final String INTEGER = " INTEGER";
        private static final String COMMA_SEP = ", ";

        private static final String CREATE_LOCATION =
                "CREATE TABLE " + LocationDBStructure.tableEntry.TABLE_LOCATION + " (" +
                        LocationDBStructure.tableEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        LocationDBStructure.tableEntry.COLUMN_POSTCODE + TEXT_TYPE + COMMA_SEP +
                        LocationDBStructure.tableEntry.COLUMN_SUBURB + TEXT_TYPE + COMMA_SEP +
                        LocationDBStructure.tableEntry.COLUMN_LATITUDE + TEXT_TYPE + COMMA_SEP +
                        LocationDBStructure.tableEntry.COLUMN_LONGITUDE + TEXT_TYPE +
                        ");";

        private static final String CREATE_QUESTION =
                "CREATE TABLE " + QuestionDBStructure.tableEntry.TABLE_QUESTION + " (" +
                        QuestionDBStructure.tableEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        QuestionDBStructure.tableEntry.COLUMN_QUESTION_CATEGORY + TEXT_TYPE + COMMA_SEP +
                        QuestionDBStructure.tableEntry.COLUMN_QUESTION_NUMBER + INTEGER + COMMA_SEP +
                        QuestionDBStructure.tableEntry.COLUMN_QUESTION + TEXT_TYPE + COMMA_SEP +
                        QuestionDBStructure.tableEntry.COLUMN_ANSWER_OPTION1 + TEXT_TYPE + COMMA_SEP +
                        QuestionDBStructure.tableEntry.COLUMN_ANSWER_OPTION2 + TEXT_TYPE + COMMA_SEP +
                        QuestionDBStructure.tableEntry.COLUMN_ANSWER_OPTION3 + TEXT_TYPE + COMMA_SEP +
                        QuestionDBStructure.tableEntry.COLUMN_ANSWER_OPTION4 + TEXT_TYPE + COMMA_SEP +
                        QuestionDBStructure.tableEntry.COLUMN_CORRECT + TEXT_TYPE + COMMA_SEP +
                        QuestionDBStructure.tableEntry.COLUMN_ANSWER_EXPLANATION + TEXT_TYPE +
                        ");";

        private static final String CREATE_USER =
                "CREATE TABLE " + UserDBStructure.tableEntry.TABLE_USER + " (" +
                        UserDBStructure.tableEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        UserDBStructure.tableEntry.COLUMN_NICKNAME + TEXT_TYPE +
                        ");";

        private static final String CREATE_ANSWER =
                "CREATE TABLE " + AnswerDBStructure.tableEntry.TABLE_ANSWER + " (" +
                        AnswerDBStructure.tableEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        AnswerDBStructure.tableEntry.COLUMN_USER_ID + " INTEGER NOT NULL REFERENCES user (id), " +
                        AnswerDBStructure.tableEntry.COLUMN_QUESTION_ID + " INTEGER NOT NULL REFERENCES question (id), " +
                        AnswerDBStructure.tableEntry.COLUMN_SELECTED_ANSWER + TEXT_TYPE + COMMA_SEP +
                        AnswerDBStructure.tableEntry.COLUMN_STATUS + INTEGER +
                        ");";


        private MySQLiteOpenHelper myDBHelper;
        private SQLiteDatabase db;

        public DBManager(Context ctx) {
            this.context = ctx;
            myDBHelper = new MySQLiteOpenHelper(context);
        }

        public DBManager open() throws SQLException {
            db = myDBHelper.getWritableDatabase();
            return this;
        }

        public void close() {
            myDBHelper.close();
        }

        // Operations for Location DB
        public boolean isLocationDbEmpty() {
            Cursor cur = db.rawQuery("SELECT COUNT(*) FROM location", null);
            if (cur != null) {
                cur.moveToFirst();
                if (cur.getInt(0) == 0) {
                    return true;
                }
            }
            return false;
        }

        public void insertLocation(String postcode, String suburb, String latitude, String longitude) {
            ContentValues values = new ContentValues();
            values.put(LocationDBStructure.tableEntry.COLUMN_POSTCODE, postcode);
            values.put(LocationDBStructure.tableEntry.COLUMN_SUBURB, suburb);
            values.put(LocationDBStructure.tableEntry.COLUMN_LATITUDE, latitude);
            values.put(LocationDBStructure.tableEntry.COLUMN_LONGITUDE, longitude);
            db.insert(LocationDBStructure.tableEntry.TABLE_LOCATION, null, values);
        }

        public Cursor getAllLocations() {
            return db.query(
                    LocationDBStructure.tableEntry.TABLE_LOCATION,
                    locationColumns, null, null,  null,  null, LocationDBStructure.tableEntry.COLUMN_SUBURB);
        }

        private String[] locationColumns = {
                LocationDBStructure.tableEntry.COLUMN_POSTCODE,
                LocationDBStructure.tableEntry.COLUMN_SUBURB,
                LocationDBStructure.tableEntry.COLUMN_LATITUDE,
                LocationDBStructure.tableEntry.COLUMN_LONGITUDE
        };

        public int deleteLocation(String rowId) {
            String[] selectionArgs = {String.valueOf(rowId)};
            String selection = LocationDBStructure.tableEntry._ID + " LIKE ?";
            return db.delete(LocationDBStructure.tableEntry.TABLE_LOCATION, selection, selectionArgs);
        }

        // Operations for Question DB
        public boolean isQuestionDbEmpty() {
            Cursor cur = db.rawQuery("SELECT COUNT(*) FROM question", null);
            if (cur != null) {
                cur.moveToFirst();
                if (cur.getInt(0) == 0) {
                    return true;
                }
            }
            return false;
        }

        public void insertQuestion(
                QuestionModel.EnumQCategory question_category,
                int question_number,
                String question,
                String answer_option1,
                String answer_option2,
                String answer_option3,
                String answer_option4,
                String correct,
                String answer_explanation) {
            ContentValues values = new ContentValues();
            values.put(QuestionDBStructure.tableEntry.COLUMN_QUESTION_CATEGORY, String.valueOf(question_category));
            values.put(QuestionDBStructure.tableEntry.COLUMN_QUESTION_NUMBER, question_number);
            values.put(QuestionDBStructure.tableEntry.COLUMN_QUESTION, question);
            values.put(QuestionDBStructure.tableEntry.COLUMN_ANSWER_OPTION1, answer_option1);
            values.put(QuestionDBStructure.tableEntry.COLUMN_ANSWER_OPTION2, answer_option2);
            values.put(QuestionDBStructure.tableEntry.COLUMN_ANSWER_OPTION3, answer_option3);
            values.put(QuestionDBStructure.tableEntry.COLUMN_ANSWER_OPTION4, answer_option4);
            values.put(QuestionDBStructure.tableEntry.COLUMN_CORRECT, correct);
            values.put(QuestionDBStructure.tableEntry.COLUMN_ANSWER_EXPLANATION, answer_explanation);
            db.insert(QuestionDBStructure.tableEntry.TABLE_QUESTION, null, values);
        }

        public Cursor getAllQuestions() {
            return db.query(
                    QuestionDBStructure.tableEntry.TABLE_QUESTION,
                    questionColumns, null, null,  null,  null, null);
        }

        public Cursor getQuestionsByCategory(QuestionModel.EnumQCategory enumQCategory) {
            String selection = "question_category=?";
            String[] args = {String.valueOf(enumQCategory)};
            return db.query(QuestionDBStructure.tableEntry.TABLE_QUESTION,
                    questionColumns,selection, args, null, null, QuestionDBStructure.tableEntry.COLUMN_QUESTION_NUMBER);
        }

        private String[] questionColumns = {
                QuestionDBStructure.tableEntry._ID,
                QuestionDBStructure.tableEntry.COLUMN_QUESTION_CATEGORY,
                QuestionDBStructure.tableEntry.COLUMN_QUESTION_NUMBER,
                QuestionDBStructure.tableEntry.COLUMN_QUESTION,
                QuestionDBStructure.tableEntry.COLUMN_ANSWER_OPTION1,
                QuestionDBStructure.tableEntry.COLUMN_ANSWER_OPTION2,
                QuestionDBStructure.tableEntry.COLUMN_ANSWER_OPTION3,
                QuestionDBStructure.tableEntry.COLUMN_ANSWER_OPTION4,
                QuestionDBStructure.tableEntry.COLUMN_CORRECT,
                QuestionDBStructure.tableEntry.COLUMN_ANSWER_EXPLANATION
        };

        public int deleteQuestion(String rowId) {
            String[] selectionArgs = {String.valueOf(rowId)};
            String selection = QuestionDBStructure.tableEntry._ID + " LIKE ?";
            return db.delete(QuestionDBStructure.tableEntry.TABLE_QUESTION, selection, selectionArgs);
        }

        // Operations for User DB
        public boolean isUserDbEmpty() {
            Cursor cur = db.rawQuery("SELECT COUNT(*) FROM user", null);
            if (cur != null) {
                cur.moveToFirst();
                if (cur.getInt(0) == 0) {
                    return true;
                }
            }
            return false;
        }

        public Cursor getAllUsers() {
            return db.query(
                    UserDBStructure.tableEntry.TABLE_USER,
                    userColumns, null, null,  null,  null, UserDBStructure.tableEntry.COLUMN_NICKNAME);
        }

        public Cursor getUserByNickname(String nickname) {
            String selection = "nickname=?";
            String[] args = {(nickname)};
            return db.query(
                    UserDBStructure.tableEntry.TABLE_USER,
                    userColumns, selection, args,  null,  null, null);
        }
        public void insertUser(String nickname) {
            ContentValues values = new ContentValues();
            values.put(UserDBStructure.tableEntry.COLUMN_NICKNAME, nickname);
            db.insert(UserDBStructure.tableEntry.TABLE_USER, null, values);
        }

        private String[] userColumns = {
                UserDBStructure.tableEntry._ID,
                UserDBStructure.tableEntry.COLUMN_NICKNAME
        };

        // Operations for Answer DB
        public boolean isAnswerDbEmpty() {
            Cursor cur = db.rawQuery("SELECT COUNT(*) FROM answer", null);
            if (cur != null) {
                cur.moveToFirst();
                if (cur.getInt(0) == 0) {
                    return true;
                }
            }
            return false;
        }

        public Cursor getAllAnswers() {
            return db.query(
                    AnswerDBStructure.tableEntry.TABLE_ANSWER,
                    answerColumns, null, null,  AnswerDBStructure.tableEntry.COLUMN_USER_ID,  null, null);
        }

        public Cursor getUserAnswers(int userId) {
            String selection = "user_id=?";
            String[] args = {String.valueOf(userId)};
            return db.query(
                    AnswerDBStructure.tableEntry.TABLE_ANSWER,
                    answerColumns, selection, args,  null,  null, null);
        }

        private String[] answerColumns = {
                AnswerDBStructure.tableEntry._ID,
                AnswerDBStructure.tableEntry.COLUMN_USER_ID,
                AnswerDBStructure.tableEntry.COLUMN_QUESTION_ID,
                AnswerDBStructure.tableEntry.COLUMN_SELECTED_ANSWER,
                AnswerDBStructure.tableEntry.COLUMN_STATUS
        };

        public void insertAnswer(UserModel user, QuestionModel question, String selected, int status) {
            ContentValues values = new ContentValues();
            values.put(AnswerDBStructure.tableEntry.COLUMN_USER_ID, user.getUserId());
            values.put(AnswerDBStructure.tableEntry.COLUMN_QUESTION_ID, question.getqId());
            values.put(AnswerDBStructure.tableEntry.COLUMN_SELECTED_ANSWER, selected);
            values.put(AnswerDBStructure.tableEntry.COLUMN_STATUS, status);
            db.insert(AnswerDBStructure.tableEntry.TABLE_ANSWER, null, values);
        }

        public void deleteUserAnswers(int userId) {
            String selection = "user_id=?";
            String[] args = {String.valueOf(userId)};
            db.delete(AnswerDBStructure.tableEntry.TABLE_ANSWER, selection, args);
        }

        // SQLite helper
        private static class MySQLiteOpenHelper extends SQLiteOpenHelper {

            public MySQLiteOpenHelper(Context context) {
                super(context, DATABASE_NAME, null, DATABASE_VERSION);
            }

            public void onCreate(SQLiteDatabase db) {
                try {
                    db.execSQL(CREATE_LOCATION);
                    db.execSQL(CREATE_QUESTION);
                    db.execSQL(CREATE_USER);
                    db.execSQL(CREATE_ANSWER);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                String sqlLocation = "DROP TABLE IF EXISTS location";
                String sqlUser = "DROP TABLE IF EXISTS user";
                String sqlQuestion = "DROP TABLE IF EXISTS question";
                String sqlAnswer = "DROP TABLE IF EXISTS answer";

                db.execSQL(sqlLocation);
                db.execSQL(sqlUser);
                db.execSQL(sqlQuestion);
                db.execSQL(sqlAnswer);

                onCreate(db);
            }
        }
    }