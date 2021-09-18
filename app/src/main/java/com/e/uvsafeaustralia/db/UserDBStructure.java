package com.e.uvsafeaustralia.db;

import android.provider.BaseColumns;

public class UserDBStructure {
    public static abstract class tableEntry implements BaseColumns {
        public static final String TABLE_USER = "user";
        public static final String COLUMN_NICKNAME = "nickname";
    }
}
