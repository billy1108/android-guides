package com.example.billy.nativedatabase.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.billy.nativedatabase.R;
import com.example.billy.nativedatabase.models.User;

/**
 * Created by Billy on 24/10/2015.
 */
public class UserDetailActivity extends AppCompatActivity {


    private static final String EXTRA_NAME = "user.detail.activity.name";
    private static final String EXTRA_LAST_NAME = "user.detail.activity.last.name";
    private static final String EXTRA_DNI = "user.detail.activity.dni";

    public TextView etName;
    public TextView etLastName;
    public TextView etDni;

    public String name;
    public String lastName;
    public String dni;

    /**
     * Inicia una nueva instancia de la actividad
     *
     * @param activity Contexto desde donde se lanzará
     * @param user    Item a procesar
     */
    public static void createInstance(Activity activity, User user) {
        Intent intent = getLaunchIntent(activity, user);
        activity.startActivity(intent);
    }

    /**
     * Construye un Intent a partir del contexto y la actividad
     * de detalle.
     *
     * @param context Contexto donde se inicia
     * @param user    Identificador de la chica
     * @return Intent listo para usar
     */
    public static Intent getLaunchIntent(Context context, User user) {
        Intent intent = new Intent(context, UserDetailActivity.class);
        intent.putExtra(EXTRA_NAME, user.getName());
        intent.putExtra(EXTRA_LAST_NAME, user.getLastName());
        intent.putExtra(EXTRA_DNI, user.getDni());
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        Intent i = getIntent();
        name = i.getStringExtra(EXTRA_NAME);
        lastName = i.getStringExtra(EXTRA_LAST_NAME);
        dni = i.getStringExtra(EXTRA_DNI);

        initView();
    }

    private void initView(){
        etName = (TextView) findViewById(R.id.et_name);
        etLastName = (TextView) findViewById(R.id.et_last_name);
        etDni = (TextView) findViewById(R.id.et_dni);

        etName.setText(name);
        etLastName.setText(lastName);
        etDni.setText(dni);
    }




}
