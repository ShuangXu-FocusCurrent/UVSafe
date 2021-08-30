package com.e.uvsafeaustralia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.annotation.Nullable;

public class DBManager {
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "uvsafeaustralia.db";
        private final Context context;
        private static final String TEXT_TYPE = " TEXT";
        private static final String COMMA_SEP = ", ";
        private static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + DBStructure.tableEntry.TABLE_NAME + " (" +
                        DBStructure.tableEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        DBStructure.tableEntry.COLUMN_POSTCODE + TEXT_TYPE + COMMA_SEP +
                        DBStructure.tableEntry.COLUMN_SUBURB + TEXT_TYPE + COMMA_SEP +
                        DBStructure.tableEntry.COLUMN_STATE + TEXT_TYPE + COMMA_SEP +
                        DBStructure.tableEntry.COLUMN_LATITUDE + TEXT_TYPE + COMMA_SEP +
                        DBStructure.tableEntry.COLUMN_LONGITUDE + TEXT_TYPE +
                        ");";
        private static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + DBStructure.tableEntry.TABLE_NAME;

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

        public boolean isDbEmpty() {
            Cursor cur = db.rawQuery("SELECT COUNT(*) FROM location", null);
            if (cur != null) {
                cur.moveToFirst();
                if (cur.getInt(0) == 0) {
                    return true;
                }
            }
            return false;
        }
        public void insertLocation(String postcode, String suburb, String state, String latitude, String longitude) {
            ContentValues values = new ContentValues();
            values.put(DBStructure.tableEntry.COLUMN_POSTCODE, postcode);
            values.put(DBStructure.tableEntry.COLUMN_SUBURB, suburb);
            values.put(DBStructure.tableEntry.COLUMN_STATE, state);
            values.put(DBStructure.tableEntry.COLUMN_LATITUDE, latitude);
            values.put(DBStructure.tableEntry.COLUMN_LONGITUDE, longitude);
            db.insert(DBStructure.tableEntry.TABLE_NAME, null, values);
        }

        public Cursor getAllLocations() {
            return db.query(
                    DBStructure.tableEntry.TABLE_NAME,
                    columns, null, null,  null,  null, DBStructure.tableEntry.COLUMN_SUBURB);
        }

        public Cursor getSearchedLocations(String suburb) {
            return db.rawQuery("SELECT * FROM '"+DBStructure.tableEntry.TABLE_NAME+"' WHERE '"+DBStructure.tableEntry.COLUMN_SUBURB+"' = ?", new String[] {suburb});
//            return db.query(DBStructure.tableEntry.TABLE_NAME, columns,
//                    DBStructure.tableEntry.COLUMN_SUBURB + "= ?",
//                            new String[]{suburb},
//                            null, null, DBStructure.tableEntry.COLUMN_SUBURB);


        }

        private String[] columns = {
                DBStructure.tableEntry.COLUMN_POSTCODE,
                DBStructure.tableEntry.COLUMN_SUBURB,
                DBStructure.tableEntry.COLUMN_STATE,
                DBStructure.tableEntry.COLUMN_LATITUDE,
                DBStructure.tableEntry.COLUMN_LONGITUDE
        };

        private String[] getcolumns = {
                DBStructure.tableEntry.COLUMN_POSTCODE,
                DBStructure.tableEntry.COLUMN_SUBURB + "= ?",
                DBStructure.tableEntry.COLUMN_STATE,
                DBStructure.tableEntry.COLUMN_LATITUDE,
                DBStructure.tableEntry.COLUMN_LONGITUDE
        };

        public int deleteLocation(String rowId) {
            String[] selectionArgs = {String.valueOf(rowId)};
            String selection = DBStructure.tableEntry._ID + " LIKE ?";
            return db.delete(DBStructure.tableEntry.TABLE_NAME, selection, selectionArgs);
        }

        private static class MySQLiteOpenHelper extends SQLiteOpenHelper {

            public MySQLiteOpenHelper(Context context) {
                super(context, DATABASE_NAME, null, DATABASE_VERSION);
            }
            public void onCreate(SQLiteDatabase db) {

                try {
                    db.execSQL(SQL_CREATE_ENTRIES);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                try {
                    db.execSQL(SQL_DELETE_ENTRIES);
                    onCreate(db);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }