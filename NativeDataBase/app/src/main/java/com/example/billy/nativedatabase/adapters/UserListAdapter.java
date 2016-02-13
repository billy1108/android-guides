package com.example.billy.nativedatabase.adapters;

import android.app.Activity;
import android.view.View;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.billy.nativedatabase.R;
import com.example.billy.nativedatabase.activities.UserDetailActivity;
import com.example.billy.nativedatabase.models.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Billy on 24/10/2015.
 */
public class UserListAdapter  extends RecyclerView.Adapter<UserListAdapter.SimpleViewHolder>
        implements ItemClickListener {
    private final Context context;
    private List<User> items;

    public static class SimpleViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        // Campos respectivos de un item
        public TextView nombre;
        public ItemClickListener listener;

        public SimpleViewHolder(View v, ItemClickListener listener) {
            super(v);

            nombre = (TextView) v.findViewById(R.id.list_item_textview);
            this.listener = listener;
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(v, getAdapterPosition());
        }
    }

    public UserListAdapter(Context context, List<User> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item, viewGroup, false);
        return new SimpleViewHolder(v, this);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder viewHolder, int i) {
        User currentItem = items.get(i);
        viewHolder.nombre.setText(currentItem.getName());
    }

    /**
     * Sobrescritura del método de la interfaz {@link ItemClickListener}
     *
     * @param view     item actual
     * @param position posición del item actual
     */
    @Override
    public void onItemClick(View view, int position) {
        UserDetailActivity.createInstance(
                (Activity) context, items.get(position));
    }

    public void swap(ArrayList<User> datas){
        items.clear();
        items.addAll(datas);
        notifyDataSetChanged();
    }

}


interface ItemClickListener {
    void onItemClick(View view, int position);
}
