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

        public long insertLocation(String postcode, String suburb, String state, String latitude, String longitude) {
            ContentValues values = new ContentValues();
            values.put(DBStructure.tableEntry.COLUMN_POSTCODE, postcode);
            values.put(DBStructure.tableEntry.COLUMN_SUBURB, suburb);
            values.put(DBStructure.tableEntry.COLUMN_STATE, state);
            values.put(DBStructure.tableEntry.COLUMN_LATITUDE, latitude);
            values.put(DBStructure.tableEntry.COLUMN_LONGITUDE, longitude);
            return db.insert(DBStructure.tableEntry.TABLE_NAME, null, values);
        }

        public Cursor getAllLocations() {
            return db.query(
                    DBStructure.tableEntry.TABLE_NAME,
                    columns, null, null,  null,  null,  null);
        }

        public ArrayList<HashMap<String, String>> getLocationsBySuburb(String suburb) {
            ArrayList<HashMap<String, String>> locationList = new ArrayList<>();
            String query = "SELECT postcode, suburb, state FROM " + DBStructure.tableEntry.TABLE_NAME;
            Cursor cursor = db.query(DBStructure.tableEntry.TABLE_NAME,
                    new String[]{DBStructure.tableEntry.COLUMN_POSTCODE,
                            DBStructure.tableEntry.COLUMN_SUBURB, DBStructure.tableEntry.COLUMN_STATE}, DBStructure.tableEntry.COLUMN_SUBURB + "?",
                    new String[]{suburb},null, null, null, null);
            if (cursor.moveToNext()) {
                HashMap<String, String> location = new HashMap<>();
                location.put("postcode", cursor.getString(cursor.getColumnIndex(DBStructure.tableEntry.COLUMN_POSTCODE)));
                location.put("suburb", cursor.getString(cursor.getColumnIndex(DBStructure.tableEntry.COLUMN_SUBURB)));
                location.put("state", cursor.getString(cursor.getColumnIndex(DBStructure.tableEntry.COLUMN_STATE)));
                locationList.add(location);
            }
            return locationList;
        }
//        public String getLocation(String location) {
////            SQLiteDatabase db = this.getWritableDatabase();
//            String whereclause = null;
//            String[] whereargs = null;
//            if (location.length() > 0 ) {
//                whereclause = DBStructure.tableEntry.COLUMN_SUBURB + " LIKE ?";
//                whereargs = new String[]{"%" + location + "%"};
//            }
//            return db.query(DBStructure.tableEntry.TABLE_NAME,null, whereclause, whereargs,null,null, "ascending","1");
//
////            Cursor c = db.query(DBStructure.tableEntry.TABLE_NAME, columns, null, null,  null,  null,  null);
////            StringBuffer buffer= new StringBuffer();
////            while (c.moveToNext())
////            {
////                int cid =c.getInt(c.getColumnIndex(DBStructure.tableEntry._ID));
////                String suburb =c.getString(c.getColumnIndex(DBStructure.tableEntry.COLUMN_SUBURB));
////                String postcode =c.getString(c.getColumnIndex(DBStructure.tableEntry.COLUMN_POSTCODE));
////                String state =c.getString(c.getColumnIndex(DBStructure.tableEntry.COLUMN_STATE));
////                buffer.append(cid+ " " + suburb + " " + postcode + " " + state +" \n");
////            }
////            return buffer.toString();
//        };

        private String[] columns = {
                DBStructure.tableEntry.COLUMN_POSTCODE,
                DBStructure.tableEntry.COLUMN_SUBURB,
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

//public class DBManager extends SQLiteOpenHelper {
//
//    public static final String LOCATION_TABLE = "LOCATION_TABLE";
//    public static final String POSTCODE = "POSTCODE";
//    public static final String SUBURB = "SUBURB";
//    public static final String STATE = "STATE";
//    public static final String LATITUDE = "LATITUDE";
//    public static final String LONGITUDE = "LONGITUDE";
//    public static final String ID = "ID";
//
//    public DBManager(@Nullable Context context) {
//        super(context, "location.db", null, 1);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        String createTableStatement = "CREATE TABLE " + LOCATION_TABLE + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + POSTCODE + " TEXT, " + SUBURB + " TEXT, " + STATE + " TEXT, " + LATITUDE + " TEXT, " + LONGITUDE + " TEXT" + ")";
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//
//    }
//}