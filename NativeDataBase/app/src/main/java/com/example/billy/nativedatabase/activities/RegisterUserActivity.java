package com.example.billy.nativedatabase.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.billy.nativedatabase.DAO.UserDAO;
import com.example.billy.nativedatabase.R;
import com.example.billy.nativedatabase.utils.Constants;

import java.sql.SQLException;

/**
 * Created by Billy on 24/10/2015.
 */
public class RegisterUserActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String TAG = RegisterUserActivity.class.getSimpleName();

    public EditText etName;
    public EditText etLastName;
    public EditText etDni;
    public Button btnCreate;
    private UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        etName = (EditText) findViewById(R.id.et_name);
        etLastName = (EditText) findViewById(R.id.et_last_name);
        etDni = (EditText) findViewById(R.id.et_dni);
        btnCreate = (Button) findViewById(R.id.btn_create);

        btnCreate.setOnClickListener(this);

        userDAO = new UserDAO(this);
        try {
            userDAO.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_create:
                createUser();
                break;
        }
    }

    private void createUser(){
        String name = etName.getText().toString();
        String lastName = etLastName.getText().toString();
        String dni = etDni.getText().toString();

        long state = Constants.BAD_STATE;
        state = userDAO.insertar(name, lastName, dni);
        if (state == Constants.BAD_STATE){
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.user_register_message_fail), Toast.LENGTH_LONG).show();
        }else{
            finish();
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.user_register_message_succesfull), Toast.LENGTH_LONG).show();
        }
    }
}
