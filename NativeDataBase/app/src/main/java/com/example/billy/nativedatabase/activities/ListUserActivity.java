package com.example.billy.nativedatabase.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.internal.util.Predicate;
import com.example.billy.nativedatabase.DAO.UserDAO;
import com.example.billy.nativedatabase.R;
import com.example.billy.nativedatabase.adapters.UserListAdapter;
import com.example.billy.nativedatabase.models.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Billy on 24/10/2015.
 */
public class ListUserActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etSearch;
    private Button btnSearch;
    private UserDAO userDAO;
    private RecyclerView recycler;
    private LinearLayoutManager lManager;
    public UserListAdapter adaptador;
    public ArrayList<User> persons;

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
        initElements();
    }

    private void initElements(){
        etSearch = (EditText) findViewById(R.id.et_search);
        btnSearch = (Button) findViewById(R.id.bt_search);
        btnSearch.setOnClickListener(this);
    }

    private void showListUser(){
        String acum = "";
        ArrayList<User> users = null;
        users =  userDAO.listadoGeneral();
        persons = users;
        adaptador = new UserListAdapter(this, users);

        recycler = (RecyclerView) findViewById(R.id.reciclador);
        recycler.setHasFixedSize(true);

        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        recycler.setAdapter(adaptador);

    }

    private void searchUsers(){
        ArrayList<User> users = new ArrayList<User>();
        final String query = etSearch.getText().toString();
        for (User user: persons){
            if (user.getLastName().equals(query)){
                users.add(user);
            }
        }
        adaptador.swap(users);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_search:
                searchUsers();
                break;
        }
    }
}
