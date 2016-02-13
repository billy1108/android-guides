package com.caballero.billy.gg.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.caballero.billy.gg.R;
import com.caballero.billy.gg.adapters.viewholders.ProductListViewHolder;
import com.caballero.billy.gg.adapters.viewholders.UserListViewHolder;
import com.caballero.billy.gg.models.Producto;
import com.caballero.billy.gg.models.User;

import java.util.List;

/**
 * Created by Billy on 13/02/2016.
 */
public class MyAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final Context context;
    private List<Object> items;

    private final int USER = 0, PRODUCTO = 1;

    public MyAdapter(Context context, List<Object> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case USER:
                View viewUser = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
                return new UserListViewHolder(viewUser);
            case PRODUCTO:
                View viewProduct = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
                return new ProductListViewHolder(viewProduct);
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof User){
            return USER;
        }else if(items.get(position) instanceof Producto){
            return PRODUCTO;
        }
        return 0;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()){
            case USER:
                User user = (User)items.get(position);
                UserListViewHolder userListViewHolder = (UserListViewHolder)holder;
                userListViewHolder.tvName.setText(user.getName());
                userListViewHolder.tvLastName.setText(user.getLastName());
                userListViewHolder.tvDni.setText(String.valueOf(user.getDni()));
                break;
            case PRODUCTO:
                Producto producto = (Producto)items.get(position);
                ProductListViewHolder productListViewHolder = (ProductListViewHolder)holder;
                productListViewHolder.tvName.setText(producto.getName());
                productListViewHolder.tvPrice.setText(producto.getPrecio());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}


