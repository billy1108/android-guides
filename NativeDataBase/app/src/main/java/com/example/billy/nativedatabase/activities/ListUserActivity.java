package com.example.billy.nativedatabase.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.billy.nativedatabase.DAO.UserDAO;
import com.example.billy.nativedatabase.R;
import com.example.billy.nativedatabase.adapters.UserListAdapter;
import com.example.billy.nativedatabase.models.User;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Billy on 24/10/2015.
 */
public class ListUserActivity extends AppCompatActivity {

    private UserDAO userDAO;
    private RecyclerView recycler;
    private LinearLayoutManager lManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);

        userDAO = new UserDAO(this);
        try {
            userDAO.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        showListUser();
    }

    private void showListUser(){
        String acum = "";
        ArrayList<User> users = null;
        users =  userDAO.listadoGeneral();
        UserListAdapter adaptador = new UserListAdapter(this, users);

        recycler = (RecyclerView) findViewById(R.id.reciclador);
        recycler.setHasFixedSize(true);

        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        recycler.setAdapter(adaptador);

    }


}
