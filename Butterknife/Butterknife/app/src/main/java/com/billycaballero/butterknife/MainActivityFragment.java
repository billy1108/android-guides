package com.billycaballero.butterknife;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.billycaballero.butterknife.adapter.MyAdapter;
import com.billycaballero.butterknife.model.User;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A placeholder fragment containing a simple view.
 * http://jakewharton.github.io/butterknife/
 */
public class MainActivityFragment extends Fragment {
    @Bind(R.id.btn_test) Button btnTest;
    @Bind(R.id.tv_test) TextView tvTest;
    @Bind(R.id.lv_test) ListView lvTest;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);

        init();
        return view;
    }

    @OnClick(R.id.btn_test)
    public void doSomething(){
        Toast.makeText(getActivity(), "Click", Toast.LENGTH_LONG).show();
    }

    public void init(){
        ArrayList<User> users = getDataUsers();
        MyAdapter myAdapter = new MyAdapter(getActivity(), users );
        lvTest.setAdapter(myAdapter);
    }

    public ArrayList<User> getDataUsers(){
        ArrayList<User> users = new ArrayList<User>();
        for (int i = 1 ; i < 20 ; i ++){
            User user = new User("Billy " + i);
            users.add(user);
        }
        return users;
    }
}
