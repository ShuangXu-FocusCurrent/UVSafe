package com.e.uvsafeaustralia.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBManager {
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "uvsafeaustralia.db";
        private final Context context;
        private static final String TEXT_TYPE = " TEXT";
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
                        QuestionDBStructure.tableEntry.COLUMN_QUESTION + TEXT_TYPE + COMMA_SEP +
                        QuestionDBStructure.tableEntry.COLUMN_ANSWER_OPTION1 + TEXT_TYPE + COMMA_SEP +
                        QuestionDBStructure.tableEntry.COLUMN_ANSWER_OPTION2 + TEXT_TYPE + COMMA_SEP +
                        QuestionDBStructure.tableEntry.COLUMN_ANSWER_OPTION3 + TEXT_TYPE + COMMA_SEP +
                        QuestionDBStructure.tableEntry.COLUMN_ANSWER_OPTION4 + TEXT_TYPE + COMMA_SEP +
                        QuestionDBStructure.tableEntry.COLUMN_CORRECT + TEXT_TYPE + COMMA_SEP +
                        QuestionDBStructure.tableEntry.COLUMN_ANSWER_EXPLANATION + TEXT_TYPE +
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

        public void insertLocation(String postcode, String suburb, String latitude, String longitude) {
            ContentValues values = new ContentValues();
            values.put(LocationDBStructure.tableEntry.COLUMN_POSTCODE, postcode);
            values.put(LocationDBStructure.tableEntry.COLUMN_SUBURB, suburb);
            values.put(LocationDBStructure.tableEntry.COLUMN_LATITUDE, latitude);
            values.put(LocationDBStructure.tableEntry.COLUMN_LONGITUDE, longitude);
            db.insert(LocationDBStructure.tableEntry.TABLE_LOCATION, null, values);
        }

        public void insertQuestion(
                Enum question_category,
                String question,
                String answer_option1,
                String answer_option2,
                String answer_option3,
                String answer_option4,
                String correct,
                String answer_explanation) {
            ContentValues values = new ContentValues();
            values.put(QuestionDBStructure.tableEntry.COLUMN_QUESTION_CATEGORY, String.valueOf(question_category));
            values.put(QuestionDBStructure.tableEntry.COLUMN_QUESTION, question);
            values.put(QuestionDBStructure.tableEntry.COLUMN_ANSWER_OPTION1, answer_option1);
            values.put(QuestionDBStructure.tableEntry.COLUMN_ANSWER_OPTION2, answer_option2);
            values.put(QuestionDBStructure.tableEntry.COLUMN_ANSWER_OPTION3, answer_option3);
            values.put(QuestionDBStructure.tableEntry.COLUMN_ANSWER_OPTION4, answer_option4);
            values.put(QuestionDBStructure.tableEntry.COLUMN_CORRECT, correct);
            values.put(QuestionDBStructure.tableEntry.COLUMN_ANSWER_EXPLANATION, answer_explanation);
            db.insert(QuestionDBStructure.tableEntry.TABLE_QUESTION, null, values);
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

        private String[] questionColumns = {
                QuestionDBStructure.tableEntry.COLUMN_QUESTION_CATEGORY,
                QuestionDBStructure.tableEntry.COLUMN_QUESTION,
                QuestionDBStructure.tableEntry.COLUMN_ANSWER_OPTION1,
                QuestionDBStructure.tableEntry.COLUMN_ANSWER_OPTION2,
                QuestionDBStructure.tableEntry.COLUMN_ANSWER_OPTION3,
                QuestionDBStructure.tableEntry.COLUMN_ANSWER_OPTION4,
                QuestionDBStructure.tableEntry.COLUMN_CORRECT,
                QuestionDBStructure.tableEntry.COLUMN_ANSWER_EXPLANATION
        };

        public int deleteLocation(String rowId) {
            String[] selectionArgs = {String.valueOf(rowId)};
            String selection = LocationDBStructure.tableEntry._ID + " LIKE ?";
            return db.delete(LocationDBStructure.tableEntry.TABLE_LOCATION, selection, selectionArgs);
        }

        private static class MySQLiteOpenHelper extends SQLiteOpenHelper {

            public MySQLiteOpenHelper(Context context) {
                super(context, DATABASE_NAME, null, DATABASE_VERSION);
            }
            public void onCreate(SQLiteDatabase db) {

                try {
                    db.execSQL(CREATE_LOCATION);
                    db.execSQL(CREATE_QUESTION);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            }
        }

    }