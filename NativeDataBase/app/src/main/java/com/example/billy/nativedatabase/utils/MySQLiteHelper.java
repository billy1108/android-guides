package com.example.billy.nativedatabase.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.billy.nativedatabase.contract.FeedUserReaderContract.FeedEntry;


/**
 * Created by Billy on 24/10/2015.
 */
public class MySQLiteHelper extends SQLiteOpenHelper{

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" +
                    FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedEntry.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.COLUMN_NAME_LAST_NAME + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.COLUMN_NAME_DNI + TEXT_TYPE  +
            " )";

    public MySQLiteHelper(Context contexto) {
        super(contexto, Constants.DATA_BASE_NAME, null, Constants.DATA_BASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + FeedEntry.TABLE_NAME);
        onCreate(db);
    }


}
