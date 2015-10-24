package com.example.billy.nativedatabase.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.billy.nativedatabase.R;

/**
 * Created by Billy on 24/10/2015.
 */
public class RegisterUserActivity extends AppCompatActivity implements View.OnClickListener{

    public EditText etName;
    public EditText etLastName;
    public EditText etDni;
    public Button btnCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        etName = (EditText) findViewById(R.id.et_name);
        etLastName = (EditText) findViewById(R.id.et_last_name);
        etDni = (EditText) findViewById(R.id.et_dni);
        btnCreate = (Button) findViewById(R.id.btn_create);

        btnCreate.setOnClickListener(this);
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

    }
}
