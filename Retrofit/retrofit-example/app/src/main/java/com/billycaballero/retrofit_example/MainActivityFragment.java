package com.billycaballero.retrofit_example;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.billycaballero.retrofit_example.api.GitApi;
import com.billycaballero.retrofit_example.model.GitModel;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A placeholder fragment containing a simple view.
 * https://github.com/codepath/android_guides/wiki/Consuming-APIs-with-Retrofit
 * http://themakeinfo.com/2015/04/retrofit-android-tutorial/
 */
public class MainActivityFragment extends Fragment {

    EditText etName;
    TextView tvResult;
    Button   btnRequest;
    String API = "https://api.github.com";

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        tvResult = (TextView) view.findViewById(R.id.tv_result);
        btnRequest = (Button) view.findViewById(R.id.btn_request);
        etName = (EditText) view.findViewById(R.id.et_name);

        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeRequest();
            }
        });

        return view;
    }

    public void makeRequest(){
        String user = etName.getText().toString();

        //Retrofit section start from here...
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(API).build();  //create an adapter for retrofit with base url

        GitApi git = restAdapter.create(GitApi.class);                            //creating a service for adapter with our GET class

        //Now ,we need to call for response
        //Retrofit using gson for JSON-POJO conversion

        git.getFeed(user,new Callback<GitModel>() {
            @Override
            public void success(GitModel gitmodel, Response response) {
                //we get json object from github server to our POJO or model class
                tvResult.setText("Github Name :" + gitmodel.getName() + "\nWebsite :" + gitmodel.getBlog()+"\nCompany Name :"+gitmodel.getCompany());
            }

            @Override
            public void failure(RetrofitError error) {
                tvResult.setText(error.getMessage());
            }
        });
    }


}
