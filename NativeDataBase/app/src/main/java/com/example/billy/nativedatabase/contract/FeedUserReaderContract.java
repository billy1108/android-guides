package com.example.billy.nativedatabase.contract;

import android.provider.BaseColumns;

/**
 * Created by Billy on 24/10/2015.
 */
public class FeedUserReaderContract {

    public FeedUserReaderContract() {}

    /* Inner class that defines the table contents */
    public static abstract class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "users";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_LAST_NAME = "last_name";
        public static final String COLUMN_NAME_DNI = "dni";
        public static final String COLUMN_NAME_NULLABLE = null;

    }

}
