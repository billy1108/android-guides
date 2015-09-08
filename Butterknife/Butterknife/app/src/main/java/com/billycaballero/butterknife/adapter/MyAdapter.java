package com.billycaballero.butterknife.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.billycaballero.butterknife.R;
import com.billycaballero.butterknife.model.User;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by billycaballero on 9/8/15.
 * https://github.com/codepath/android_guides/wiki/Using-an-ArrayAdapter-with-ListView
 */
public class MyAdapter extends ArrayAdapter<User> {

    private List<User> users;
    private LayoutInflater inflator;

    public MyAdapter(Activity context, ArrayList<User> users) {
        super(context, 0, users);
        this.users = users;
        inflator = context.getLayoutInflater();
    }

    @Override public View getView(int position, View view, ViewGroup parent) {
        User user = users.get(position);
        ViewHolder holder;
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            view = inflator.inflate(R.layout.cell_list, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        // Populate the data into the template view using the data object
        holder.name.setText(user.getName());
        // Return the completed view to render on screen
        return view;
    }

    static class ViewHolder {
        @Bind(R.id.tv_name_cell) TextView name;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}