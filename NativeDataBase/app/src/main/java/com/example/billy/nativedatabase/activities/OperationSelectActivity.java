package com.example.billy.nativedatabase.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.billy.nativedatabase.R;

/**
 * Created by Billy on 24/10/2015.
 */
public class OperationSelectActivity extends AppCompatActivity implements View.OnClickListener {

    public Button btnRegister;
    public Button btnList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operation_select);

        btnRegister = (Button) findViewById(R.id.btn_register);
        btnList = (Button) findViewById(R.id.btn_list);

        btnRegister.setOnClickListener(this);
        btnList.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_register:
                goToCreateUserActivity();
                break;
            case R.id.btn_list:
                goToListUserActivity();
                break;
        }
    }

    private void goToCreateUserActivity(){
        Intent intent = new Intent(this, RegisterUserActivity.class);
        startActivity(intent);
    }

    private void goToListUserActivity(){
        Intent intent = new Intent(this, ListUserActivity.class);
        startActivity(intent);
    }
}
