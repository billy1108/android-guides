package com.example.billy.nativedatabase.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.billy.nativedatabase.contract.FeedUserReaderContract.FeedEntry;

import com.example.billy.nativedatabase.models.User;
import com.example.billy.nativedatabase.utils.Constants;
import com.example.billy.nativedatabase.utils.MySQLiteHelper;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Billy on 24/10/2015.
 */
public class UserDAO {

    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;

    public UserDAO(Context context) {
        this.dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException{
        this.database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public long insertar(String nombre, String apellido, String dni){
        long state = Constants.BAD_STATE;
        try{
            ContentValues values = new ContentValues();
            values.put(FeedEntry.COLUMN_NAME_NAME, nombre);
            values.put(FeedEntry.COLUMN_NAME_LAST_NAME, apellido);
            values.put(FeedEntry.COLUMN_NAME_DNI,dni);
            state = this.database.insert(FeedEntry.TABLE_NAME, FeedEntry.COLUMN_NAME_NULLABLE, values);
        }catch (Exception e){
            state = Constants.BAD_STATE;
        }
        return state;
    }

    public ArrayList<User> listadoGeneral(){
        Cursor c;
        ArrayList<User> listUser = new ArrayList<User>();
        c = this.database.rawQuery("SELECT * FROM " + FeedEntry.TABLE_NAME, null);
        while (c.moveToNext()){
            User user = new User();
            user.setName(c.getString(  c.getColumnIndexOrThrow(FeedEntry.COLUMN_NAME_NAME)  ));
            user.setLastName(c.getString(  c.getColumnIndexOrThrow(FeedEntry.COLUMN_NAME_LAST_NAME)  ));
            user.setDni(c.getString(  c.getColumnIndexOrThrow(FeedEntry.COLUMN_NAME_DNI)  ));
            listUser.add( user);
        }
        c.close();
        return listUser;
    }


}
