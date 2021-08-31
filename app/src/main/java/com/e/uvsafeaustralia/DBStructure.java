package com.e.uvsafeaustralia;

import android.provider.BaseColumns;

public class DBStructure {
    public static abstract class tableEntry implements BaseColumns {
        public static final String TABLE_NAME = "location";
        public static final String COLUMN_POSTCODE = "postcode";
        public static final String COLUMN_SUBURB = "suburb";
        public static final String COLUMN_LATITUDE = "latitude";
        public static final String COLUMN_LONGITUDE = "longitude";

    }
}
