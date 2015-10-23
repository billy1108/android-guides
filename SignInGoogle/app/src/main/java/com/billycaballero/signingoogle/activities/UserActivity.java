package com.billycaballero.signingoogle.activities;


/**
 * Created by billycaballero on 10/22/15.
 */

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.billycaballero.signingoogle.R;
import com.billycaballero.signingoogle.fragments.UserDetailFragment;
import com.billycaballero.signingoogle.util.Constans;

public class UserActivity extends BaseActivity {

    private String name;
    private String email;
    private String urlImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            name = extras.getString(Constans.USER_FULL_NAME);
            email = extras.getString(Constans.USER_EMAIL);
            urlImage = extras.getString(Constans.USER_IMAGE);
        }

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        initFragment();
    }

    private void initFragment(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        UserDetailFragment fragment = UserDetailFragment.newInstance(name, email, urlImage);
        fragmentTransaction.add(R.id.fragment_user_detail_container, fragment);
        fragmentTransaction.commit();
    }
}
