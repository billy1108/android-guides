package com.caballero.billy.gg.adapters.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.caballero.billy.gg.R;

/**
 * Created by Billy on 13/02/2016.
 */
public class ProductListViewHolder extends RecyclerView.ViewHolder {

    public TextView tvName;
    public TextView tvPrice;

    public ProductListViewHolder(View itemView) {
        super(itemView);

        tvName = (TextView) itemView.findViewById(R.id.tv_name);
        tvPrice = (TextView) itemView.findViewById(R.id.tv_price);

    }
}
