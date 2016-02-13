package com.caballero.billy.gg.adapters.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.caballero.billy.gg.R;

/**
 * Created by Billy on 13/02/2016.
 */
public class UserListViewHolder extends RecyclerView.ViewHolder {

    public TextView tvName;
    public TextView tvLastName;
    public TextView tvDni;

    public UserListViewHolder(View view){
        super(view);

        tvName = (TextView) view.findViewById(R.id.tv_name);
        tvLastName = (TextView) view.findViewById(R.id.tv_last_name);
        tvDni = (TextView) view.findViewById(R.id.tv_dni);
    }
}
