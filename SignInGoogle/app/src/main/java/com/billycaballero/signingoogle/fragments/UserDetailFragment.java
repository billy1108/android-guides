package com.billycaballero.signingoogle.fragments;

/**
 * Created by billycaballero on 10/22/15.
 */

import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.billycaballero.signingoogle.R;
import com.billycaballero.signingoogle.activities.BaseActivity;
import com.billycaballero.signingoogle.util.Constans;
import com.squareup.picasso.Picasso;


public class UserDetailFragment extends Fragment implements View.OnClickListener{

    public static final String TAG = UserDetailFragment.class.getSimpleName();

    public TextView tvUserName;
    public TextView tvUserEmail;
    public ImageView ivUserImage;

    private String userName;
    private String userEmail;
    private String userImage;

    public UserDetailFragment() {}

    public static UserDetailFragment newInstance(String username, String email, String image) {
        UserDetailFragment fragment = new UserDetailFragment();
        Bundle args = new Bundle();
        args.putString(Constans.USER_FULL_NAME, username);
        args.putString(Constans.USER_EMAIL, email);
        args.putString(Constans.USER_IMAGE, image);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userName = getArguments().getString(Constans.USER_FULL_NAME);
            userEmail = getArguments().getString(Constans.USER_EMAIL);
            userImage = getArguments().getString(Constans.USER_IMAGE);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user_detail, container, false);
        initView(view);

//        view.findViewById(R.id.logout).setOnClickListener(this);
        return view;
    }

    private void initView(View view){
        tvUserName = (TextView)view.findViewById(R.id.username);
        tvUserEmail = (TextView)view.findViewById(R.id.email);
        ivUserImage = (ImageView)view.findViewById(R.id.image);

        tvUserName.setText(userName);
        tvUserEmail.setText(userEmail);
        Picasso.with(getActivity()).load(userImage).into(ivUserImage);
    }

    @Override
    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.logout:
//                ((BaseActivity) getActivity()).onDisconnectClicked();
//                break;
//        }
    }
}
